package fr.rabbyt.behaviors;

import fr.rabbyt.Entity;

/**
 * Interface qui modélise un comportement qui peut être effectué 
 * par une {@link fr.rabbyt.Entity}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-01
 */
public interface Behavior {
    /**
     * Méthode pour réaliser l'action lié à un comportement
     */
    public boolean make();

    /**
     * Setter: Entité cible sur qui l'action sera réalisée
     * @param target Cible de l'action
     */
    public void setTarget(Entity target);

    /**
     * Getter: Entité cible sur qui l'action sera réalisée
     * @return Cible de l'action
     */
    public Entity getTarget();
}