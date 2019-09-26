/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author William
 */
public class Plateau implements Parametres{
    
    // Variables
    private Grid[] plateau;
    private boolean bloque;
    private int score;
    
    // Constructeur
    public Plateau() {
        this.plateau = new Grid[3];
        this.bloque = false;
        this.score = 0;
    }
    
    // Getter
    public Grid[] getPlateau(){
        return this.plateau;
    }
    
    public boolean getBloque(){
        return this.bloque;
    }
    
    public int getScore(){
        return this.score;
    }
    
    // Setter
    public void setPlateau(Grid[] p){
        this.plateau = p;
    }
    
    public void setBloque(boolean b){
        this.bloque = b;
    }
    
    public void setScore(int s){
        this.score = s;
    }
    
    // Méthodes
    
    
// FIN
}