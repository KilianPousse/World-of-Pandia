package fr.rabbyt;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe SimApp - Gère l'affichage et la simulation.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-01-31
 */
public class SimApp extends JFrame {

    private SimMap map;
    private JLabel label;
    private Timer timer;
    private long startTime; // Temps de départ de la simulation
    private long lastEventTime; // Dernier moment où un Pandian a été ajouté
    private List<Integer> nbPandians = new ArrayList<>();

    public SimApp(int width, int height, int pixelSize) {
        // Initialisation de la fenêtre
        this.setTitle("World of Pandia");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Initialisation de la carte
        this.map = new SimMap(width, height, pixelSize);
        initSimulation();

        // Création de l'image initiale
        label = new JLabel(map.draw());
        this.add(label);
        this.pack();
        this.setLocationRelativeTo(null); // Centre la fenêtre
    }

    /**
     * Initialise la simulation avec les objets de départ.
     */
    private void initSimulation() {
        try {
            for (int i = 0; i < 20; i++) {
                map.generate(Bamboo.class);
                map.generate(Pandian.class);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la génération des objets : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Génère un certain nombre de bambous.
     */
    public void generateBamboo(int n) {
        try {
            for (int i = 0; i < n; i++) {
                map.generate(Bamboo.class);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la génération des bambous : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Génère un certain nombre de Pandians.
     */
    public void generatePandian(int n) {
        try {
            for (int i = 0; i < n; i++) {
                map.generate(Pandian.class);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la génération des Pandians : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Démarre la simulation avec un rafraîchissement toutes les 100ms.
     */
    public void run() {
        startTime = System.currentTimeMillis(); // Capture du temps de départ
        lastEventTime = startTime; // Initialisation du dernier événement

        ActionListener task = e -> {
            map.update(); // Mise à jour de la carte
            label.setIcon(map.draw()); // Redessiner l'image

            long elapsedTime = System.currentTimeMillis() - startTime; // Temps écoulé total
            long sinceLastEvent = System.currentTimeMillis() - lastEventTime; // Temps depuis le dernier ajout de Bamboo

            System.out.println("Temps écoulé : " + elapsedTime / 1000 + "s | Nombre de Pandians : " + nbPandians);

            if (sinceLastEvent >= 10000) { // Ajoute du bambou toutes les 10 secondes
                generateBamboo(1);
                nbPandians.add(map.getNbPandians());
                lastEventTime = System.currentTimeMillis(); // Réinitialiser le dernier événement
            }
        };

        timer = new Timer(100, task);
        timer.start();
    }

    /**
     * Point d'entrée du programme.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimApp app = new SimApp(100, 100, 4);
            app.setVisible(true);
            app.run();
        });
    }
}
