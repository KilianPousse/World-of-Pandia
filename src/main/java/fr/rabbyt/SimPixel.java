package fr.rabbyt;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe abstraite qui modélise un objet qui sera traité par une carte de simulation
 * ({@link SimMap}). <br>
 * Cette classe implémente l'interface {@link SimObject}.
 * 
 * @author Kilian POUSSE
 * @version 1.1
 * @since 2025-01-31
 */
public abstract class SimPixel implements SimObject {

    /* ======= Constantes de classe ======= */
    /** Ensemble des identifiants des objets */
    private static Set<Integer> idSet = new HashSet<>();    



    /* ======= Variables d'instance ======= */

    /** Identifiant de l'objet (UNIQUE) */
    private int id;

    /** Coordonnée sur l'axe X */
    private int x;

    /** Coordonnée sur l'axe Y */
    private int y;

    /** Couleur du pixel de l'objet */
    private Color color;

    /** Couleur du contour du pixel */
    private Color outline = null;

    /** Carte de la simulation où se trouve l'objet */
    protected SimMap map = null;




    /* ========= Constructeurs =========== */
    /**
     * Constructeur de la classe SimPixel. 
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     * @param color Couleur du pixel de l'objet
     */
    SimPixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    /**
     * Constructeur de la classe SimPixel. 
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     * @param color Couleur du pixel de l'objet
     * @param outline Couleur du contour du pixel (Peut etre 'null')
     */
    SimPixel(int x, int y, Color color, Color outline) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.outline = outline;
    }



    /* ======= Methodes d'instance ======= */
    /**
     * Setter: Permet d'informer que quelle carte se trouve l'objet.
     * @param map Carte de la simulation où se trouve l'objet
     */
    @Override
    public void setMap(SimMap map) {
        this.map = map;
    }

    /**
     * Getter: Permet de récupérer sur quelle carte se trouve l'objet.
     * @return Carte de la simulation où se trouve l'objet
     */
    @Override
    public SimMap getMap() {
        return map;
    }

    /**
     * Setter: Initialise l'identifiant de l'objet. Si l'identifiant n'est
     * pas valide (id négatif ou égal à 0), alors il sera généré automatiquement.
     * @param id Identifiant de l'objet. 
     */
    @Override
    public void setId(Integer id) {
        // Si l'id existe déjà ou et i correcte => générer un nouveau id
        if(idSet.contains(id) || id <= 0) {
            for(Integer i = 1; i < Integer.MAX_VALUE; i++) {
                // Prendre un id libre
                if(!idSet.contains(i)) { 
                    idSet.add(i);
                    this.id = i;
                    return;
                }
            }
            throw new IllegalStateException("Aucune ID disponible !");
        }
        // Ajouter l'id dans l'ensemble pour éviter les doublons
        idSet.add(id);
        this.id = id;
    }

    /**
     * Getter: Permet de récupérer l'identifiant de l'objet.
     * @return Identifiant de l'objet. 
     */
    @Override
    public Integer getId() {
        if(id <= 0) return null;
        return id;
    }

    /**
     * Setter: Initialise la position sur l'abscisse
     * @param x Coordonnée sur l'axe X
     */
    @Override
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Setter: Initialise la position sur l'ordonnée
     * @param y Coordonnée sur l'axe Y
     */
    @Override
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * Getter: Récupere la position sur l'abscisse
     * @return Coordonnée sur l'axe X
     */
    @Override
    public Integer getX() {
        return x;
    }

    /**
     * Getter: Récupere la position sur l'ordonnée
     * @return Coordonnée sur l'axe Y
     */
    @Override
    public Integer getY() {
        return y;
    }

    /**
     * Setter: Initialise la couleur de l'objet pour l'affichage
     * @param color Couleur du pixel de l'objet
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Getter: Récupere la couleur de l'objet pour l'affichage
     * @return Couleur du pixel de l'objet
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Setter: Initialise la couleur du contour de l'objet
     * @param color Couleur du contour du pixel de l'objet
     */
    @Override
    public void setOutline(Color color) {
        this.outline = color;
    }

    /**
     * Getter: Récupere la couleur du contour de l'objet pour l'affichage
     * @return Couleur du conture du pixel de l'objet
     */
    @Override
    public Color getOutline() {
        return outline;
    }

    /**
     * Téléporte un objet à une position donnée (avec verification).
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     */
    @Override
    public void teleport(Integer x, Integer y) {
        // Vérifier si la carte est nulle
        if(map == null) {
            throw new NullPointerException("La carte n'a pas été définie.");
        }
    
        // Vérifier si les coordonnées sont valides
        if(!map.isValidCoordinate(x, y)) {
            throw new IllegalArgumentException("Les coordonnées sont hors des limites de la carte.");
        }
    
        // Si tout est bon, définir les nouvelles coordonnées
        setX(x);
        setY(y);
    }
    
    /**
     * Permet d'actualiser l'objet.
     */
    @Override
    public void update() {
        // Logique de mise à jour de l'objet (à définir dans les sous-classes)
    }

    /**
     * Calcule la distance entre deux objets de la carte.
     * @param object Second objet pour calculer la distance
     * @return La distance entre les deux
     */
    @Override
    public Float distance(SimObject object) {
        // Ouais bon, c'est des maths quoi!

        // Calculer la différence entre les coordonnées X et Y
        int dx = object.getX() - this.getX();
        int dy = object.getY() - this.getY();
        
        // Utilisation de Math.hypot pour calculer la distance
        return (float) Math.hypot(dx, dy);
    }

    /**
     * Redefinission: Converssion en chaine de caracteres
     * @return La chaine de caracteres equivalante
     */
    @Override
    public String toString() {
        return "<" + this.getClass() + ", id " + String.format("%08X", id) + ">";
    }
    
    
}
