/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.geometry.HPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Case;


/**
 * Class TuileGrpahique, used to display tiles on the Plateau
 * @author plaut2u
 */
public class TuileGraphique extends Case implements ParametresApplication{

    private int objectifx, objectify, posx, posy;
    private Pane fond;
    private final ImageView img;

    public TuileGraphique(int abs, int ord, int v, Pane fd) {
        super(abs, ord, v);
        this.objectifx = abs;
        this.objectify = ord;
        this.posx = abs;
        this.posy = ord;
        this.fond = fd;
        
        Image image = new Image(Main.cheminImg + "tile" + String.valueOf(v) + ".jpg");
        this.img = new ImageView();
        this.img.setImage(image);
        GridPane.setHalignment(this.img, HPos.CENTER);
    }

    //GETTERS
    
    /**
    * Method to get the Obejctifx (the position x of where the tile should be) of the tile.
    * @return this.objectifx (int)
    */
    public int getObjectifx() {
        return this.objectifx;
    }
    
    /**
    * Method to get the Obejctify (the position y of where the tile should be) of the tile.
    * @return this.objectify (int)
    */
    public int getObjectify() {
        return this.objectify;
    }
    
    /**
    * Method to get the Posx (current position x) of the tile.
    * @return this.posx (int)
    */
    public int getPosx() {
        return this.posx;
    }
    
    /**
    * Method to get the Posy (current position y) of the tile.
    * @return this.posy (int)
    */
    public int getPosy() {
        return this.posy;
    }
    
    /**
    * Method to get the img of the tile.
    * @return this.img (ImageView)
    */
    public ImageView getImg(){
        return this.img;
    }

    //SETTERS

    /**
    * Method to set the Objectifx (the position x of where the tile should be) of the tile.
    * @param newx the new Objectifx
    */
    public void setObjectifx(int newx) {
        this.objectifx = newx;
    }
    
    /**
    * Method to set the Objectify (the position y of where the tile should be) of the tile.
    * @param newy the new Objectify
    */
    public void setObjectify(int newy) {
        this.objectify = newy;
    }
    
    /**
    * Method to set the Posx (the current position x) of the tile.
    * @param newx the new posx
    */
    public void setPosx(int newx) {
        this.posx = newx;
    }
    
    /**
    * Method to set the Posy (the current position y) of the tile.
    * @param newy the new posy
    */
    public void setPosy(int newy) {
        this.posy = newy;
    }
}
