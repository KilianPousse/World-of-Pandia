package fr.rabbyt.behaviors;

import fr.rabbyt.Entity;

/**
 * Classe abstraite qui modélise un comportement qui peut être effectué 
 * par une {@link fr.rabbyt.Entity}.
 * <br>Cette classe implémente l'interface {@link Behavior}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-01
 */
public abstract class SimBehavior implements Behavior {

    /* ======= Variables d'instance ======= */
    /** Entité qui effectura l'action lié au comportement */
    protected Entity target = null;     



    /* ========= Constructeurs =========== */
    /**
     * Constructeur de la classe SimBehavior.
     */
    SimBehavior() {/* ... */}


    

    /* ======= Methodes d'instance ======= */
    /**
     * Setter: Entité cible sur qui l'action sera réalisée
     * @param target Cible de l'action
     */
    @Override
    public void setTarget(Entity target) {
        this.target = target;
    }

    /**
     * Getter: Entité cible sur qui l'action sera réalisée
     * @return Cible de l'action
     */
    @Override
    public Entity getTarget() {
        return target;
    }

    /**
     * Méthode pour réaliser l'action lié à un comportement
     */
    @Override
    public final boolean make() {
        if(target == null) return false;
        return action();
    }

    /**
     * Méthode abstraite que doit effectuer l'entité cible
     */
    protected abstract boolean action();
}
