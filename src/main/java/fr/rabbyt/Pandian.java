package fr.rabbyt;

import java.awt.Color;
import fr.rabbyt.behaviors.RdmMoveBehavior;

/*
 * (\(\
 * ( -.-)
 * o_(")(")
 */

/**
 * Classe qui modélise un Pandien et son comportement.
 * <br>Cette classe hérite de le classe {@link Entity}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-01
 */
public class Pandian extends Entity {

    /* ======= Constantes de classe ======= */

    /** Age maximal que peut avoir un Pandien */
    public static final int MAX_AGE = 99;

    /** Cout que coute une action en energies  */
    public static final int ACTION_ENERGIES = 1;

    /** Couleur des Pandiens */
    public static Color pandianColor = Color.blue;


    


    /* ======= Variables d'instance ======= */

    /** Nom d'un Pandien */
    private String name = "???";

    /** Age d'un Pandien */
    private int age = 0;

    /** Energies d'un Pandien (Si vide, il meurt) */
    private SimEnergy energies = new SimEnergy(200);

    



    /* ========= Constructeurs =========== */
    /** 
     * Constructeur de la classe Pandian.
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     */
    public Pandian(int x, int y) {
        super(x, y, pandianColor);
        setBehavior(new RdmMoveBehavior());
        this.name = PandianName.generate();
    }
    
    /** 
     * Constructeur de la classe Pandian.
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     * @param name Nom du Pandien
     */
    public Pandian(int x, int y, String name) {
        super(x, y, pandianColor);
        this.name = name;
        setBehavior(new RdmMoveBehavior());
    }




    /* ======= Methodes d'instance ======= */
    /**
     * Setter: Initialisation du nom
     * @param name Nom à donner au Pandien
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter: Recuperation du nom du Pandien
     * @return Nom du Pandien
     */
    public String getName() {
        return name;
    }

    /**
     * Setter: Initialisation de l'age du Pandien (avec verification)
     * @param age Age à initialiser
     */
    public void setAge(int age) {
        if(age < 0 || age > MAX_AGE) {
            LogManager.addWarning(this + ": Le nouvel age est incorrect.");
        }
        else {
            this.age = age;
        }
    }

    /**
     * Getter: Récupération de l'age d'un Pandien
     * @return Age d'un Pandien
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter: Initialisation de l'energie du Pandien
     * @param energies Energie à initialiser
     */
    public void setEnergies(int energies) {
        this.energies.setValue(energies);
    }
     /**
      * Getter: Recuperation de l'energie
      * @return Energie du Pandien
      */
    public int getEnergies() {
        return energies.getValue();
    }

    /**
     * Redefinission: Actualisation d'un Pandien avec la consommation d'energies
     * et la mort...
     */
    @Override
    public void update() {
        if (!energies.consume(ACTION_ENERGIES)) {
            die();
        } else {
            super.update();
        }
    }

    
}
