package fr.rabbyt;

import java.awt.Color;
import java.io.Serializable;

/**
 * Classe qui represente de un bambou qui peut
 * etre manger par certaine {@link Entity}. <br>
 * Cette classe hérite de la classe {@link Food}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-03
 */
public class Bamboo extends Food implements Serializable {
    
    /* ========= Constructeurs =========== */
    /**
     * Constructeur de la classe Bamboo.
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     */
    Bamboo(int x, int y) {
        super(x, y, Color.GREEN, 100);
    }
    
}
