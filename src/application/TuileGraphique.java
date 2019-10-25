/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Case;

/**
 *
 * @author plaut2u
 */
public class TuileGraphique extends Case {

    private int objectifx, objectify, posx, posy;
    public Thread thread;
    private Pane p;
    private final Label c;

    public TuileGraphique(int abs, int ord, int v, Pane fond) {
        super(abs, ord, v);
        this.objectifx = abs;
        this.objectify = ord;
        this.posx = abs;
        this.posy = ord;
        this.c = new Label(String.valueOf(v));
        this.p = new Pane();

        this.p.getStyleClass().add("pane");
        this.c.getStyleClass().add("tuile");
        GridPane.setHalignment(this.c, HPos.CENTER);
        fond.getChildren().add(this.p);
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
