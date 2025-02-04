package fr.rabbyt.behaviors;

import fr.rabbyt.Entity;
import fr.rabbyt.Food;
import fr.rabbyt.Pandian;
import fr.rabbyt.SimObject;

public class EatingBehavior extends SimBehavior {

    protected Behavior focusBehavior = new FocusBehavior(Food.class);
    protected Behavior rdmMoveBehavior = new RdmMoveBehavior();

    @Override
    public void setTarget(Entity target) {
        super.setTarget(target);
        focusBehavior.setTarget(target);
        rdmMoveBehavior.setTarget(target);
    }

    @Override
    protected boolean action() {
        
        if(focusBehavior.make()) {
            SimObject focus = target.getFocus();
            if(target.getX() == focus.getX() && target.getY() == focus.getY()) {
                ((Pandian) target).eat((Food) focus);
                target.setFocus(null);
            }
            return true;
        }

        return rdmMoveBehavior.make();
    }
    
}
