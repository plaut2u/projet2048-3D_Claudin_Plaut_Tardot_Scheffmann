package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Enregistreur {
    // Sauvegarde le Plateade Jeu dans le fichier "plateau.ser"
    public static void serialiser(Plateau p) {
        //final Case caseSave = new Case(0, 2, 2048);
        //final Plateau plateauSave = plateau;
        ObjectOutputStream oos = null;
        
        try {
            final FileOutputStream fichier = new FileOutputStream("plateau.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(p);
            //oos.writeObject(plateauSave);
            //oos.writeObject(save);
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
        //Case caseLoad;
        Plateau plateauLoad;
        //Grille[] plateauLoad;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            final FileInputStream fichierIn = new FileInputStream("plateau.ser");
            ois = new ObjectInputStream(fichierIn);
            //caseLoad = (Case) ois.readObject();
            //plateauLoad = (Grille[]) ois.readObject();
            plateauLoad = (Plateau) ois.readObject();
            //System.out.println("Case : ");
            System.out.println("Plateau : ");
            //System.out.println("Case["+caseLoad.getX()+","+caseLoad.getY()+"], Valeur : " + caseLoad.getValeur());
            System.out.println(plateauLoad);
            return plateauLoad;
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
    

// FIN    
}
