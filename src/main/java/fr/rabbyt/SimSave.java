package fr.rabbyt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SimSave {


    public static SimMap load(String savePath) {

        SimMap map = null;
        
        LogManager.add("Chargement de la sauvegarde ...");
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath))) {
            map = (SimMap) ois.readObject();
            LogManager.add("Chargement de la sauvegarde terminée");
        }
        catch(IOException | ClassNotFoundException e) {
            LogManager.addError("Probleme lors du chargement (" + e.getMessage() + ")");
            e.printStackTrace();  // Affiche la trace complète de l'erreur
        }

        return map;
    }


    public static void save(String savePath, SimMap map) {
        LogManager.add("Sauvegarde de la sauvegarde ...");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath))) {
            oos.writeObject(map);
            LogManager.add("Sauvegarde de la sauvegarde terminée");
        }
        catch(IOException e) {
            LogManager.addError("Probleme lors de la sauvegarde (" + e.getMessage() + ")");
        }
    }


}
