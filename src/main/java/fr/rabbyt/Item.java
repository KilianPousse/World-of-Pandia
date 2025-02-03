package fr.rabbyt;

import java.awt.Color;


/**
 * Classe abstraite qui modélise un item qui sera traité par une carte de simulation
 * ({@link SimMap}). Cet item est un objet qui qui peut être possitionné qu'a un seul endroit.<br>
 * Cette classe hérite de la classe {@link SimPixel}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-03
 */
public abstract class Item extends SimPixel {
    
    /* ========= Constructeurs =========== */
    /**
     * Constructeur de la classe Item.
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     * @param color Couleur du pixel de l'objet
     */
    Item(int x, int y, Color color) {
        super(x, y, color);
    }

    


    /* ======= Methodes d'instance ======= */
    /**
     * Changement de position pour un Item (avec echangage de position)
     * @param x Coordonnées sur l'axe X
     * @param y Coordonnées sur l'axe Y
     */
    @Override
    public void teleport(Integer x, Integer y) {
        if(map.isEmpty(x, y)) {
            super.teleport(x, y);
            map.switchIteam(this, null);
        }
    }
    
}
