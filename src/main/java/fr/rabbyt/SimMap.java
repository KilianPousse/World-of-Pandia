package fr.rabbyt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
// import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

// import java.util.List;
// import java.util.stream.Collectors;
import javax.swing.ImageIcon;

/**
 * Classe qui modélise une carte de simulation. <br>
 * 
 * @author Kilian POUSSE
 * @version 1.2
 * @since 2025-01-31
 */
public class SimMap implements Serializable {

    /* ======= Constantes de classe ======= */

    /** Taille maximale que peut avoir une dimension de la carte */
    public static final Integer MAX_SIZE = 999;

    /** Taille maximale d'un pixel sur la carte */
    public static final Integer MAX_PIXEL_SIZE = 64;

    /** Taille par defaut d'un pixel */
    public static final Integer DEF_PIXEL_SIZE = 8;


    
    /* ======= Variables d'instance ======= */

    /** Largeur de la carte en pixel */
    private Integer width;

    /** Hauteur de la carte en pixel */
    private Integer height;

    /** Taille d'un pixel */
    private Integer pixelSize;

    /** Ensemble des pixels present sur la carte ({@link MapObject}) */
    private HashMap<Integer, SimObject> objects;

    /** Matrice des items */
    private Item[][] items;



    /* ======= Méthodes de classes ======== */
    /**
     * Verifie la validation d'une dimenssion de la carte
     * @param size Taille a verifier
     * @return  Si la taille correspond aux critère alors Vrai, sinon Faux
     */
    private static boolean isValidSize(Integer size) {
        return 0 < size && size <= MAX_SIZE;
    }

    /**
     * Verifie la validation de la taille des pixels
     * @param size Taille a verifier
     * @return  Si la taille correspond aux critère alors Vrai, sinon Faux
     */
    private static boolean isValidPixelSize(Integer size) {
        return 0 < size && size <= MAX_PIXEL_SIZE;
    }




    /* ========= Constructeurs =========== */
    /**
     * Constructeur de la classe 'SimMap'.
     * @param width Largeur de la carte en pixel 
     * @param height Hauteur de la carte en pixel
     */
    public SimMap(Integer width, Integer height) {
        this.setSize(width, height);
        this.setPixelSize(DEF_PIXEL_SIZE);
        this.objects = new HashMap<>();
        this.items = new Item[width][height];
    }

    /**
     * Constructeur de la classe 'SimMap'.
     * @param width Largeur de la carte en pixel 
     * @param height Hauteur de la carte en pixel
     * @param pixelSize Taille des pixels
     */
    public SimMap(Integer width, Integer height, Integer pixelSize) {
        this.setSize(width, height);
        this.setPixelSize(pixelSize);
        this.objects = new HashMap<>();
        this.items = new Item[width][height];
    }


    
    /* ======= Methodes d'instance ======= */
    /**
     * Getter: Recuperer les dimensions de la map
     * @return Talbeau d'entier qui represente les dimensions
     */
    public Integer[] getSize() {
        Integer[] size = new Integer[2];
        size[0] = getWidth();
        size[1] = getHeight();
        return size;
    }
    
    /**
     * Setter: Initialise les dimensions de la map (avec validation)
     * @param width Largeur de la map
     * @param height Hauteur de la map
     */
    public void setSize(Integer width, Integer height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Setter: Initialise la largeur de la map (avec validation)
     * @param width Largeur de la map
     */
    public void setWidth(Integer width) {
        if(!isValidSize(width)) {
            throw new IllegalArgumentException("La largeur doit être entre 1 et " + MAX_SIZE);
        }
        this.width = width;
    }

    /**
     * Setter: Initialise la hauteur de la map (avec validation)
     * @param height Hauteur de la map
     */
    public void setHeight(Integer height) {
        if(!isValidSize(height)) {
            throw new IllegalArgumentException("La hauteur doit être entre 1 et " + MAX_SIZE);
        }
        this.height = height;
    }

    /**
     * Getter: Recuperer la largeur de la map
     * @return Largeur de la map
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Getter: Recuperer la hauteur de la map
     * @return Hauteur de la map
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Setter: Initaliser la taille des pixels (avec validation)
     * @param pixelSize Taille des pixels
     */
    public void setPixelSize(Integer pixelSize) {
        if(!isValidPixelSize(pixelSize)) {
            throw new IllegalArgumentException("La taille d'un pixel doit être entre 1 et " + MAX_PIXEL_SIZE);
        }
        this.pixelSize = pixelSize;
    }

    /**
     * Getter: Recuperer la taille des pixels
     * @return Taille des pixels
     */
    public Integer getPixelSize() {
        return pixelSize;
    }

    /**
     * Ajouter un objets à la map afin de le simuler.
     * @param object Objet a ajouter
     */
    public void add(SimObject object) {
        if(object.getId() == null) object.setId(0);
        object.setMap(this);
        this.objects.put(object.getId(), object);
    }

    /**
     * Ajouter un objet à la map et aux items
     * @param object Objet de type {@link Item} à ajouter
     * @throws IllegalArgumentException Si la place est déjà occupée par un autre item
     */
    public void add(Item object) {
        // Recuperation des coordonnées
        int x = object.getX();
        int y = object.getY();

        // Verification si il n'y a pas deja un item à cette endroit
        if(!isEmpty(x, y))
            throw new IllegalArgumentException("La position (" + x + ", " + y + ") est déjà occupée par un autre item.");

        add((SimObject) object);
        this.items[x][y] = object;
    }

    /**
     * Suppression d'un objet de la map de simulation
     * @param object Objet a suprimer
     */
    public void remove(SimObject object) {
        this.objects.remove(object.getId());
    }

    /**
     * Suppression d'un item de la map de simulation et de la matrice des items
     * @param object Item a suprimer
     */
    public void remove(Item object) {
        items[object.getX()][object.getY()] = null;
        remove((SimObject) object);
    }

    /**
     * Dessin sur une image le rendu de la map.
     * @return Une image representant la map a un instant donné
     */
    public ImageIcon draw() {
        int imgWidth = width * pixelSize;
        int imgHeight = height * pixelSize;
        BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // Fond blanc
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, imgWidth, imgHeight);

        // Dessiner les objects sur la carte
        for(SimObject o: objects.values()) {
            int x = o.getX() * pixelSize;
            int y = o.getY() * pixelSize;

            g.setColor(o.getColor());
            g.fillRect(x, y, pixelSize, pixelSize);
            
            if(o.getOutline() != null) {
                g.setColor(o.getOutline());
                g.drawRect(x, y, pixelSize, pixelSize);
            }
        }

        g.dispose();
        return new ImageIcon(image);
    }

    /**
     * Valisation des coordonnees en entréee.
     * @param x Coordonnée sur l'axe X a verifier
     * @param y Coordonnée sur l'axe Y a verifier
     * @return Vrai si (x, y) sont dans la map, sinon Faux
     */
    public boolean isValidCoordinate(Integer x, Integer y) {
        return (0 <= x && x < this.width) && (0 <= y && y < this.height);
    }

    /**
     * Actualise tous les objets de la map.
     */
    public void update() {

        List<Integer> keys = new ArrayList<>(objects.keySet());

        for(Integer id: keys) {
            try {
                SimObject obj = objects.get(id);
                obj.update();
                
            } catch (Exception e) {
            }
            
        }
    }

    /**
     * Permet de savoir si il n'y a pas d'item à une localisation donnée 
     * @param x Coordonnée du l'axe X
     * @param y Coordonnée du l'axe Y
     * @return Vrai si il n'y a pas d'item, Faux sinon
     */
    public boolean isEmpty(Integer x, Integer y) {
        if(!isValidCoordinate(x, y)) return false;
        return this.items[x][y] == null;
    }
    
    /**
     * Retourne une localisation possible afin de creer/generer un nouveau objet
     * @return Un tableau de deux coordonnées (X, Y), ou un NULL si aucune localisation à été trouvée
     */
    public Integer[] generateLocation() {
        Integer[] location = new Integer[2];

        Random rdm = new Random();

        // Chercher un emplacement libre
        for(int i = 0; i < 100; i++) {
            location[0] = rdm.nextInt(width);
            location[1] = rdm.nextInt(height);

            if(isEmpty(location[0], location[1])) {
                return location;
            }
        }

        return null;
    }

    /**
     * Genere un objet à une position aleatoire
     * @param cls Classe d'objet de simulation ({@link Pandian}, {@link Bamboo}, ...)
     * @return L'objet généré, ou null si erreur
     */
    public SimObject generate(Class<? extends SimObject> cls) {
        Integer[] location = generateLocation();
        SimObject object = null;
    
        if(location == null) {
            return object;
        }
    
        try {
            // Instanciation dynamique de l'objet avec un constructeur prenant deux entiers
            object = cls.getDeclaredConstructor(int.class, int.class)
                      .newInstance(location[0], location[1]);
            this.add(object);
        } catch(Exception e) {
            e.printStackTrace();
            return object;
        }

        return object;
    }

    /**
     * Permet d'enchanger deux items de place
     * @param o1 Iteam à changer numero 1
     * @param o2 Iteam à changer numero 2
     */
    public void switchIteam(Item o1, Item o2) {
        items[o1.getX()][o1.getY()] = o2;
        items[o2.getX()][o2.getY()] = o1;
    }

    /**
     * Retorune l'item à une localisation donnée
     * @param x Coordonnée du l'axe X
     * @param y Coordonnée du l'axe Y
     * @return L'iteam à la localisation donnée
     */
    public Item getItem(int x, int y) {
        return items[x][y];
    }

    /**
     * Recherche l'objet le plus proche dans le rayons donné autour de l'objet cible.
     * Avec validation de la classe de l'objet.
     * @param target Objet cible
     * @param radius Rayon maximal d'analyse autour de la cible 
     * @param cls Classe des objects à analyser
     * @return L'objet le plus proche de la cible
     */
    public SimObject closestItem(SimObject target, int radius, Class<? extends SimObject> cls) {
        SimObject closest = null;
        double minDistance = radius;
    
        for(SimObject obj : objects.values()) {
            // Vérifier si l'objet est de la classe demandée
            if(cls.isInstance(obj) && !obj.equals(target)) {
                double distance = target.distance(obj);
    
                // Trouver l'objet le plus proche
                if(distance < minDistance) {
                    minDistance = distance;
                    closest = obj;
                }
            }
        }
    
        return closest;
    }

    /**
     * Recherche l'objet le plus proche dans le rayons donné autour de l'objet cible
     * @param target Objet cible
     * @param radius Rayon maximal d'analyse autour de la cible 
     * @return L'objet le plus proche de la cible
     */
    public SimObject closestItem(SimObject target, int radius) {
        return closestItem(target, radius, SimPixel.class);
    }
    
    public int getNbPandians() {
        int count = 0;
        for(SimObject o: objects.values()) {
            if(o instanceof Pandian) {
                count++;
            }
        }
        return count;
    }
}