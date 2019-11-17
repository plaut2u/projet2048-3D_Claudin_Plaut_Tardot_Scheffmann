/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Case;


/**
 *
 * @author plaut2u
 */
public class TuileGraphique extends Case implements ParametresApplication{

    private int objectifx, objectify, posx, posy;
    private Pane p, fond;
    private final Label c;
    private final ImageView img;

    public TuileGraphique(int abs, int ord, int v, Pane fd) {
        super(abs, ord, v);
        this.objectifx = abs;
        this.objectify = ord;
        this.posx = abs;
        this.posy = ord;
        this.c = new Label(String.valueOf(v));
        this.p = new Pane();
        this.fond = fd;
        
        
        Image image = new Image("img/" + "tile" + String.valueOf(v) + ".jpg");
        this.img = new ImageView();
        this.img.setImage(image);
        
        
//        this.p.getStyleClass().add("pane");
//        this.c.getStyleClass().add("tuile");
//        GridPane.setHalignment(this.c, HPos.CENTER);
        GridPane.setHalignment(this.img, HPos.CENTER);
//      
//        this.p.getChildren().add(this.c);   
//        this.p.setLayoutX(super.getX());
//        this.p.setLayoutY(super.getY());
//        this.p.setVisible(true);
//        this.c.setVisible(true);
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
    
    public ImageView getImg(){
        return this.img;
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
