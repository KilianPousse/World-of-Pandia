package fr.rabbyt;

import java.awt.Color;

/**
 * Classe abstraite qui represente de la nourriture qui peut
 * etre manger par certaine {@link Entity}. <br>
 * Cette classe hérite de la classe {@link Item}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-03
 */
public abstract class Food extends Item {

    /* ======= Constantes de classe ======= */

    /** Energies maximales que peut rendre une nourriture */
    public static Integer MAX_ENERGIES = 500;


    
    /* ======= Variables d'instance ======= */

    /** Energies que rendra la nourriture si elle est mangée */
    private Integer energies;



    /* ======= Méthodes de classes ======== */
    /**
     * Constructeur de la classe Food.
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     * @param color Couleur du pixel de l'objet
     * @param energies Energies que rendra la nourriture
     */
    Food(int x, int y, Color color, int energies) {
        super(x, y, color);
        setEnergies(energies);
    }

    


    /* ======= Methodes d'instance ======= */

    /**
     * Setter: Initialiser l'energies rendu si la nourriture est mangée.
     * @param energies Energies que rendra la nourriture
     */
    public void setEnergies(Integer energies) {
        if(energies < MAX_ENERGIES)
            this.energies = energies;
        else if(0 < energies)
            this.energies = 1;
        else
            this.energies = MAX_ENERGIES;
    }

    /**
     * Getter: Retourne l'energies rendu si la nourriture est mangée.
     * @return Energies que rendra la nourriture
     */
    public Integer getEnergies() {
        return energies;
    }

    /**
     * Permet de faire manger cette nourriture
     * @return Energies que rendra la nourriture
     */
    public int getsEaten() {
        map.remove(this);
        return getEnergies();
    }
    
}
