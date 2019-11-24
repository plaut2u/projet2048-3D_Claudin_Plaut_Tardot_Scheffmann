package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Enregistreur {

    // Sauvegarde le Plateau de Jeu dans le fichier "plateau.ser"
    public static void serialiser(Plateau p) {
        // Création d'un nouveau plateau (copie du plateau du jeu en cours)
        final Plateau save = new Plateau();
        save.setPlateau(p.getPlateau());
        save.setBloque(p.getBloque());
        save.setScore(p.getScore());
        save.setMove(p.getMove());
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichierOut = new FileOutputStream("sauvegarde.ser");
            oos = new ObjectOutputStream(fichierOut);
            oos.writeObject(save);
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

    // Permet de charger le plateau de jeu et de reprendre la partie là où on l'avait sauvegardé
    public static Plateau deserialiser() {
        ObjectInputStream ois = null;

        try {
            final FileInputStream fichierIn = new FileInputStream("sauvegarde.ser");
            ois = new ObjectInputStream(fichierIn);
            final Plateau save = (Plateau) ois.readObject();
            return save;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    public static void reset(){
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichierOut = new FileOutputStream("sauvegarde.ser");
            oos = new ObjectOutputStream(fichierOut);
            oos.writeObject(null);
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
