package fr.rabbyt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
// import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
// import java.util.List;
// import java.util.stream.Collectors;
import javax.swing.ImageIcon;

/**
 * Classe qui modélise une carte de simulation. <br>
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-01-31
 */
public class SimMap {

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

    /** Ensemble des objets present sur la carte ({@link MapObject}) */
    private HashMap<Integer, SimObject> objects;



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
    public void addobject(SimObject object) {
        if(object.getId() == null) object.setId(0);
        object.setMap(this);
        this.objects.put(object.getId(), object);
    }

    /**
     * Suppression d'un objet de la map de simulation
     * @param object Objet a suprimer
     */
    public void removeObject(SimObject object) {
        this.objects.remove(object.getId());
    }

    /**
     * Suppression d'un objet de la map de simulation
     * @param id Identifiant de l'objet a suprimer
     */
    public void removeObject(Integer id) {
        this.objects.remove(id);
    }

    
    /*public List<SimObjectDistance> getNeighbors(SimObject object, int radius) {
        return objects.values().stream() 
                              .filter(o -> o != object) 
                              .filter(o -> Math.abs(o.getX() - object.getX()) <= radius && Math.abs(o.getY() - object.getY()) <= radius) 
                              .map(o -> new SimObjectDistance(o, object.distance(o))) 
                              .sorted(Comparator.comparingDouble(SimObjectDistance::getDistance)) 
                              .collect(Collectors.toList()); 
    }
    
    public static class SimObjectDistance {
        private SimObject object;
        private double distance;

        public SimObjectDistance(SimObject object, double distance) {
            this.object = object;
            this.distance = distance;
        }

        public SimObject getobject() {
            return object;
        }

        public double getDistance() {
            return distance;
        }
    }*/

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
        // Utiliser un Iterator pour éviter ConcurrentModificationException
        Iterator<SimObject> iterator = objects.values().iterator();
        while(iterator.hasNext()) {
            SimObject object = iterator.next();
            object.update();  // Mise à jour de l'objet
    
            // Si l'objet doit être supprimé (ex: Pandian n'a plus d'énergie), on utilise l'iterator
            if(object instanceof Pandian && ((Pandian) object).getEnergies() <= 0) {
                iterator.remove();  // Suppression sécurisée
                LogManager.addInfo(object + " a été supprimé de la carte.");
            }
        }
    }
    

}
