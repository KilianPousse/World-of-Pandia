package fr.rabbyt.behaviors;

import fr.rabbyt.SimObject;

/**
 * Classe de comportement qui permet de gerer le focus des {@link fr.rabbyt.Entity}. <br>
 * Cette classe hérite de la classe {@link SimBehavior}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-04
 */
public class FocusBehavior extends SimBehavior {

    /** Classe des objets à focus */
    protected Class<? extends SimObject> focusClass = SimObject.class;

    /**
     * Constructeur de la classe FocusBevavior
     * @param focusClass Classe des objets à focus
     */
    FocusBehavior(Class<? extends SimObject> focusClass) {
        this.focusClass = focusClass;
    }

    /**
     * Action a réaliser
     */
    @Override
    protected boolean action() {
        // Recuperation de l'objet focus
        SimObject focus = target.getFocus();

        // Si aucun objet focus, en rechercher un
        if(focus == null) {
            focus = target.getMap().closestItem(target, target.getFocusingRadius(), focusClass);

            // Aucun focus trouvé
            if(focus == null) {
                return false;
            }

            target.setFocus(focus);
        }

        int dx = 0;
        int dy = 0;

        // Calcul le mouvement a faire pour si rendre
        if(focus.getX() < target.getX()) {
            dx = -1;
        }
        else if(focus.getX() > target.getX()) {
            dx = 1;
        }

        if(focus.getY() < target.getY()) {
            dy = -1;
        }
        else if(focus.getY() > target.getY()) {
            dy = 1;
        }

        target.move(dx, dy);
        return true;
    }


    
}
