package fr.rabbyt;

import java.awt.Color;

/**
 * Interface qui modélise un objet qui sera traité par une carte de simulation
 * ({@link SimMap}). 
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-01-31
 */
public interface SimObject {
    /**
     * Setter: Permet d'informer que quelle carte se trouve l'objet.
     * @param map Carte de la simulation où se trouve l'objet
     */
    public void setMap(SimMap map);
    
    /**
     * Getter: Permet de récupérer sur quelle carte se trouve l'objet.
     * @return Carte de la simulation où se trouve l'objet
     */
    public SimMap getMap();

    /**
     * Setter: Initialise l'identifiant de l'objet. Si l'identifiant n'est
     * pas valide (id négatif ou égal à 0), alors il sera généré automatiquement.
     * @param id Identifiant de l'objet. 
     */
    public void setId(Integer id);

    /**
     * Getter: Permet de récupérer l'identifiant de l'objet.
     * @return Identifiant de l'objet. 
     */
    public Integer getId();

    /**
     * Setter: Initialise la position sur l'abscisse
     * @param x Coordonnée sur l'axe X
     */
    public void setX(Integer x);

    /**
     * Setter: Initialise la position sur l'ordonnée
     * @param y Coordonnée sur l'axe Y
     */
    public void setY(Integer y);

    /**
     * Getter: Récupere la position sur l'abscisse
     * @return Coordonnée sur l'axe X
     */
    public Integer getX();

    /**
     * Getter: Récupere la position sur l'ordonnée
     * @return Coordonnée sur l'axe Y
     */
    public Integer getY();

    /**
     * Setter: Initialise la couleur de l'objet pour l'affichage
     * @param color Couleur du pixel de l'objet
     */
    public void setColor(Color color);

    /**
     * Getter: Récupere la couleur de l'objet pour l'affichage
     * @return Couleur du pixel de l'objet
     */
    public Color getColor();

    /**
     * Setter: Initialise la couleur du contour de l'objet
     * @param color Couleur du contour du pixel de l'objet
     */
    public void setOutline(Color color);

    /**
     * Getter: Récupere la couleur du contour de l'objet pour l'affichage
     * @return Couleur du conture du pixel de l'objet
     */
    public Color getOutline();

    /**
     * Téléporte un objet à une position donnée (avec verification).
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     */
    public void teleport(Integer x, Integer y);

    /**
     * Permet d'actualiser l'objet.
     */
    public void update();

    /**
     * Calcule la distance entre deux objets de la carte.
     * @param object Second objet pour calculer la distance
     * @return La distance entre les deux
     */
    public Float distance(SimObject object);
} 