package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe qui contient les fonctions qui permettent de sauvegarder et de charger
 * une partie.
 *
 * @author William
 */
public class Enregistreur {

    /**
     * Permet de sauvegarder le plateau de jeu dans le fichier "sauvegarde.ser".
     *
     * @param p Plateau sur lequel on joue et que l'on veut sauvegarder
     */
    public static void serialiser(Plateau p) {
        // Création d'un nouveau plateau (copie du plateau du jeu en cours)
        final Plateau save = new Plateau();
        // Copie du plateau entré en paramètre
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

    /**
     * Récupère le fichier "sauvegarde.ser" pour charger la partie là où on l'avait sauvegardé.
     * 
     * @return Plateau si "sauvegarde.ser" contient un plateau sauvegardé, null sinon
     */
    public static Plateau deserialiser() {
        ObjectInputStream ois = null;

        try {
            final FileInputStream fichierIn = new FileInputStream("sauvegarde.ser");
            ois = new ObjectInputStream(fichierIn);
            final Plateau save = (Plateau) ois.readObject();
            return save;
        } catch (final java.io.IOException e) {
            //e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            //e.printStackTrace();
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

    /**
     * Vide tout le contenu du fichier "sauvegarde.ser".
     */
    public static void reset() {
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
