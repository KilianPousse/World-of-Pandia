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
        SwingUtilities.invokeLater(() -> {
            SimApp app = new SimApp(100, 100, 4);
            app.setVisible(true);
            app.run();
        });
    }
}
