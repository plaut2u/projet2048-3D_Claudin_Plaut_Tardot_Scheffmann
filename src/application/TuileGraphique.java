/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Case;


/**
 *
 * @author plaut2u
 */
public class TuileGraphique extends Case implements ParametresApplication{

    private int objectifx, objectify, posx, posy;
    public Thread thread;
    private Pane p, fond;
    private final Label c;

    public TuileGraphique(int abs, int ord, int v, Pane fd) {
        super(abs, ord, v);
        this.objectifx = abs;
        this.objectify = ord;
        this.posx = abs;
        this.posy = ord;
        this.c = new Label(String.valueOf(v));
        this.p = new Pane();
        this.fond = fd;

        this.p.getStyleClass().add("pane");
        this.c.getStyleClass().add("tuile");
        GridPane.setHalignment(this.c, HPos.CENTER);
        
        //PROBLEME : quand on créer une case apres le debut du jeu
                //this.fond.getChildren().add(this.p); //impossible de passer cette ligne
                //System.out.println("Created fontchildren");
        //FIN PROBLEME
        
        this.p.getChildren().add(this.c);

        this.p.setLayoutX(super.getX());
        this.p.setLayoutY(super.getY());
        this.p.setVisible(true);
        this.c.setVisible(true);
    }

    //GETTERS
    public Pane getPane() {
        return this.p;
    }

    public Label getLabel() {
        return this.c;
    }

    public int getObjectifx() {
        return this.objectifx;
    }

    public int getObjectify() {
        return this.objectify;
    }

    public int getPosx() {
        return this.posx;
    }

    public int getPosy() {
        return this.posy;
    }
    
    public Pane getFond(){
        return this.fond;
    }

    //SETTERS
    public void setPane(Pane pa) {
        this.p = pa;
    }

    public void setObjectifx(int newx) {
        this.objectifx = newx;
    }

    public void setObjectify(int newy) {
        this.objectify = newy;
    }

    public void setPosx(int newx) {
        this.posx = newx;
    }

    public void setPosy(int newy) {
        this.posy = newy;
    }
}
