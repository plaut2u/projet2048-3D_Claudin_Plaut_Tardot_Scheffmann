/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Joueur;

/**
 *
 * @author Gregoire
 */
public class FXMLDocumentControllerLogin implements Initializable, ParametresApplication, ControlledScreen {
    
    ScreensController myController;
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private ImageView loginImg;
    
    @FXML
    private TextField pseudoLogIn;
    
    @FXML
    private PasswordField mdpLogIn;
    
    
    @FXML
    private void login(MouseEvent event) throws IOException{
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        //check bdd si psuedo utilisé
        //si pseudo déjà utilisé on fait rien
        //sinon on créé le nouveau joueur
        Main.joueur = new Joueur(pseudoLogIn.getText(),mdpLogIn.getText());
        
        Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
        myController.setScreen(Main.screenMenuID);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image(Main.cheminImg + "loginImg.png");
        loginImg.setImage(img);
    }
    
}
