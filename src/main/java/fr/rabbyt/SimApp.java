package fr.rabbyt;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
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
    private JMenuBar menuBar;

    public SimApp(int width, int height, int pixelSize) {

        // Initialisation de la fenêtre
        this.setTitle("World of Pandia");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Initialisation de la carte
        this.map = new SimMap(width, height, pixelSize);
        //initSimulation();

        // Création de l'image initiale
        label = new JLabel(map.draw());
        this.add(label);
        this.pack();
        this.setLocationRelativeTo(null); // Centre la fenêtre

        // Initialisation de la barre de menu
        initMenuBar();

        
    }

    /**
    * Ouvre un explorateur de fichiers pour charger une sauvegarde (.wop)
    */
    private void loadSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Charger une sauvegarde");

        // Filtrer les fichiers pour n'afficher que les fichiers ".wop"
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Fichiers World of Pandia (.wop)", "wop"));

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String savePath = selectedFile.getAbsolutePath();
            System.out.println("Fichier sélectionné : " + savePath);

            // Appel à la méthode de chargement (à implémenter dans SimMap)
            try {
                map = SimSave.load(savePath); // Charge la sauvegarde
                JOptionPane.showMessageDialog(this, "Sauvegarde chargée avec succès !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors du chargement de la sauvegarde : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Sauvegarde la simulation actuelle dans un fichier .wop
     */
    private void saveSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sauvegarder la simulation");

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Fichiers World of Pandia (.wop)", "wop"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String savePath = selectedFile.getAbsolutePath();

            // Ajoute l'extension ".wop" si elle est absente
            if(!savePath.endsWith(".wop")) {
                savePath += ".wop";
            }

            try {
                SimSave.save(savePath, map);  // Appel à la méthode de sauvegarde
                JOptionPane.showMessageDialog(this, "Sauvegarde effectuée avec succès !");
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Initialisation de la barre de menu
     */
    public void initMenuBar() {
        // Création de la barre de menus
        this.menuBar = new JMenuBar();
        
        // Création des menus
        JMenu menuFichier = new JMenu("Fichier");
        JMenu menuAffichage = new JMenu("Affichage");

        // Création des éléments de menu
        JMenuItem itemNewSave = new JMenuItem("Nouvelle Save");
        JMenuItem itemLoadSave = new JMenuItem("Charger Save");
        JMenuItem itemSaveSave = new JMenuItem("Sauver Save");

        // Ajout des actions au éléments
        itemNewSave.addActionListener(e -> initSimulation());
        itemLoadSave.addActionListener(e -> loadSave());
        itemSaveSave.addActionListener(e -> saveSave());

        // Ajout des éléments aux menus
        menuFichier.add(itemNewSave);
        menuFichier.add(itemLoadSave);
        menuFichier.add(itemSaveSave);
        menuFichier.addSeparator(); // Séparateur visuel

        // Ajout des menus à la barre de menus
        this.menuBar.add(menuFichier);
        this.menuBar.add(menuAffichage);

        setJMenuBar(this.menuBar);
    }

    /**
     * Initialise la simulation avec des paramètres définis par l'utilisateur.
     */
    private void initSimulation() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Champs pour la taille de la carte
        JTextField widthField = new JTextField("100");
        JTextField heightField = new JTextField("100");

        // Champs pour le nombre de Pandian et Bamboo
        JTextField pandianCountField = new JTextField("20");
        JTextField bambooCountField = new JTextField("20");

        // Ajout des labels et champs au panneau
        panel.add(new JLabel("Largeur de la carte :"));
        panel.add(widthField);
        panel.add(new JLabel("Hauteur de la carte :"));
        panel.add(heightField);
        panel.add(new JLabel("Nombre de Pandiens :"));
        panel.add(pandianCountField);
        panel.add(new JLabel("Nombre de Bamboos :"));
        panel.add(bambooCountField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Paramètres de la Simulation", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                int pandianCount = Integer.parseInt(pandianCountField.getText());
                int bambooCount = Integer.parseInt(bambooCountField.getText());

                map = new SimMap(width, height);

                for (int i = 0; i < pandianCount; i++) {
                    map.generate(Pandian.class);
                }

                for (int i = 0; i < bambooCount; i++) {
                    map.generate(Bamboo.class);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs numériques valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la génération des objets : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            System.out.println("Initialisation annulée par l'utilisateur.");
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
            if(map != null){

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
