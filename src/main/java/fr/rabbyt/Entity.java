package fr.rabbyt;

import java.awt.Color;
import fr.rabbyt.behaviors.*; 

/**
 * Classe abstraite qui modélise une entité qui sera traité par une carte de simulation
 * ({@link SimMap}). Cette entité est un objet qui possède des comportements et la 
 * possibilité de se déplacer. Un comportement ({@link fr.rabbyt.behaviors.Behavior}) est
 * une action que rélisera l'entité durant son actualisaiton. <br>
 * Cette classe hérite de la classe {@link SimPixel}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-01-31
 */
public abstract class Entity extends SimPixel {

    /* ======= Variables d'instance ======= */
    
    /** Comportement que peut réaliser l'entité */
    protected Behavior behavior = new NoBehavior();  




    /* ========= Constructeurs =========== */
    /**
     * Constructeur de la classe Entity.
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     * @param color Couleur du pixel de l'objet
     */
    Entity(int x, int y, Color color) {
        super(x, y, color);
    }
  

    /* /(•x•)\ */


    /* ======= Methodes d'instance ======= */
    /**
     * Permet de déplacer l'entité vers une direction
     * @param dx Coordonnée delta sur l'axe X (new_x = x + dx)
     * @param dy Coordonnée delta sur l'axe Y (new_y = y + dy)
     */
    public void move(Integer dx, Integer dy) {
        try {
            teleport(getX() + dx, getY() + dy);
        } catch(Exception e) {
            if (e instanceof NullPointerException) {
                LogManager.addError(this + ": " + e.getMessage());
            }
        }
    }

    /**
     * Redifinition: Permet d'actualiser l'entité avec son comportement
     */
    @Override
    public void update() {
        this.behavior.make();
    }

    /**
     * Setter: Permettre de renseigner un nouveau comportement à effectuer
     * durant l'actualisation de l'entité.
     * @param behavior Nouveau comportement
     */
    public void setBehavior(Behavior behavior) {
        behavior.setTarget(this);
        this.behavior = behavior;
    }

    /**
     * Getter: Permet de récuprerer le comprotement de l'entité effectué durant 
     * l'actualisation.
     * @return Le comportement de l'entité
     */
    public Behavior getBehavior() {
        return behavior;
    }

    /**
     * Suppression de l'entité par l'effet qu'on appelle la mort...
     * Pas de bol...
     */
    public void die() {
        if (this.map != null) {
            map.removeObject(getId());
        }
    }
}