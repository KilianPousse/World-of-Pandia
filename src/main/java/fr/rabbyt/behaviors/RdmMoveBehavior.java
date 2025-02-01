package fr.rabbyt.behaviors;

import java.util.Random;

/**
 * Classe qui modélise un comportement d'une {@link fr.rabbyt.Entity}. 
 * L'entité va bouger aléatoirement sur la carte.
 * <br>Cette classe hérite de le classe {@link SimBehavior}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-01
 */
public class RdmMoveBehavior extends SimBehavior {

    /**
     * Action a réaliser
     */
    @Override
    protected void action() {
        Random rdm = new Random();
        int dx = rdm.nextInt(3) - 1;
        int dy = rdm.nextInt(3) - 1;
        target.move(dx, dy);
    }
    
}
