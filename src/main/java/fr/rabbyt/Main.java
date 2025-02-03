package fr.rabbyt;

import javax.swing.*;

/**
 * Classe Main du programme
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-01-31
 */
public class Main {
    /**
     * Methode principale
     * @param args Arguments
     */
    public static void main(String[] args) {
        // Création de la carte
        SimMap map = new SimMap(100, 100);
        for(int i=0; i<10; i++) map.generate(Pandian.class);
        for(int i=0; i<10; i++) map.generate(Bamboo.class);


        // Déclaration de l'image comme final
        final ImageIcon[] image = new ImageIcon[1];  // Utilisation d'un tableau pour modifier la référence dans le Timer
        image[0] = map.draw();

        // Affichage dans une fenêtre Swing
        JLabel label = new JLabel(image[0]);
        JFrame frame = new JFrame();
        frame.add(label);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Timer pour mettre à jour et redessiner la carte toutes les 100 ms
        Timer timer = new Timer(100, e -> {
            map.update();  // Mettre à jour la carte (tous les objets de la carte)
            image[0] = map.draw();  // Redessiner la carte après la mise à jour
            label.setIcon(image[0]);  // Mettre à jour l'image affichée
        });
        timer.start();  // Démarrer le Timer
    }
}
