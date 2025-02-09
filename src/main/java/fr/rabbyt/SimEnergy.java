package fr.rabbyt;

import java.io.Serializable;

/**
 * Classe qui modélise l'énergie utilisé par un Pandien ({@link Pandian}).
 * Cette energie est vitale. Si elle tombe à zero, le Pandien meurt. <br>
 * Cette classe implémente l'interface {@link SimObject}.
 * 
 * @author Kilian POUSSE
 * @version 1.0
 * @since 2025-02-01
 */
public class SimEnergy implements Serializable {

    private static final long serialVersionUID = 1L;

    /* ======= Constantes de classe ======= */
    /** Valeur maximale d'énergie */
    public static final int MAX_ENERGIES = 999;




    /* ======= Variables d'instance ======= */
    /** Valeur de l'energie actuelle */
    private int value = 0;



    /* ========= Constructeurs =========== */
    /** 
     * Constructeur qui initialise l'énergie
     * @param value Valeur actuelle d'énergie
     */
    public SimEnergy(int value) {
        setValue(value);
    }

    
    
    /* ======= Methodes d'instance ======= */
    /**
     * Méthode pour vérifier si l'énergie est vide
     * @return Vrai si l'énergie est a plat, Faux sinon
     */ 
    public boolean empty() {
        return value <= 0;
    }

    /**
     * Getter: Recupere la valeur de l'énergie
     * @return Valeur d'energie
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter: Initialise la valeur de l'énergie (avec vérification)
     * @param value Valeur d'energie
     */
    public void setValue(int value) {
        // Vérification si la valeur est valide
        if(value <= 0 || value > MAX_ENERGIES) {
            throw new IllegalArgumentException("La valeur de l'énergie doit être entre 1 et " + MAX_ENERGIES);
        }
        this.value = value;
    }

    /**
     * Consomme de l'énergie (réduire la valeur de l'énergie)
     * @param value Valeur de l'énergie à consommer
     * @return  Vrai si il reste de l'énergie, Faux sinon
     */
    public boolean consume(int value) {
        // Vérification si la quantité à consommer est valide
        if(value <= 0) {
            throw new IllegalArgumentException("La quantité à consommer doit être supérieure à 0");
        }

        // Si suffisamment d'énergie est disponible, on consomme la quantité
        if(this.value >= value) {
            this.value -= value;
            return true;  // Energie consommée avec succès
        }

        // Pas assez d'énergie pour consommer
        return false;  // Energie insuffisante
    }

    /**
     * Récupération d'énergie (augmenter la valeur de l'énergie)
     * @param value Valeur d'energie à récupérer
     */
    public void takeEnergy(int value) {
        // Vérification si la quantité à prendre est valide
        if(value <= 0) {
            throw new IllegalArgumentException("La quantité à prendre doit être supérieure à 0");
        }

        // Augmenter la valeur de l'énergie
        this.value += value;

        // Si la valeur dépasse la limite maximale, on la capte à la valeur maximale
        if(this.value > MAX_ENERGIES) {
            this.value = MAX_ENERGIES;  // Limiter l'énergie à la valeur maximale
        }
    }

    /**
     * Teste s'il y a au moins une certaine quantité d'énergie
     * @param value Quantité d'energie à tester
     * @return Vrai si il y a assez d'energie, Faux sinon
     */
    public boolean hasvalue(int value) {
        // Vérifie si la quantité d'énergie est suffisante par rapport au minimum demandé
        return this.value >= value;
    }

    /**
     * Redifinition: Representation en String
     * @return Le String equivalent
     */
    @Override
    public String toString() {
        return value + "/" + MAX_ENERGIES;
    }
}
