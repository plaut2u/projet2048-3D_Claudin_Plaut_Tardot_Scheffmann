package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChargerSauvegarde {
    
    public static void main(String[] args){
        Case caseYO;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
        try{
            final FileInputStream fichierIn = new FileInputStream("personne.ser");
            ois = new ObjectInputStream(fichierIn);
            caseYO = (Case) ois.readObject();
            System.out.println("Case : ");
            System.out.println("valeur : " + caseYO.getValeur());
        } catch(final java.io.IOException e){
            e.printStackTrace();
        } catch(final ClassNotFoundException e){
            e.printStackTrace();
        } finally{
            try{
                if(ois != null){
                    ois.close();
                }
            } catch (final IOException ex){
                ex.printStackTrace();
            }
        }
    }
  
//FIN
}
