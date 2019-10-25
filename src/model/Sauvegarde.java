package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Sauvegarde {

    public static void main(String[] args) {
        final Case caseYO = new Case(0, 0, 2048);
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = new FileOutputStream("personne.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(caseYO);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

// FIN    
}
