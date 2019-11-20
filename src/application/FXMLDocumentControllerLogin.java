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
import javafx.stage.Stage;
import model.Joueur;

/**
 *
 * @author Gregoire
 */
public class FXMLDocumentControllerLogin implements Initializable, ParametresApplication, ControlledScreen {

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private ImageView titleImg;
    
    @FXML
    private ImageView loginImg;

    @FXML
    private TextField pseudoLogIn;

    @FXML
    private PasswordField mdpLogIn;
    
    @FXML
    private TextField pseudoSignIn;
    
    @FXML
    private PasswordField mdpSignIn;
    
    @FXML
    private PasswordField mdpSignInVerif;
    

    @FXML
    private void login(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();

        //Ouverture BDD
        //variables : pseudoLogIn, mdpLogIn, pseudoSignIn, mdpSignIn, mdpSignInVerif
        //cas de connexion :
//        if () { //si le psuedo appartient deja a la bdd
//            if () { //alors si le mdp correspond au pseudo
//                Main.joueur = new Joueur(pseudoLogIn.getText(), mdpLogIn.getText());
//                
//                //Main.joueur.setMeilleurScore(/*meilleur score recup de la BDD*/);
//                //Main.joueur.setNbvictoires(/*nb victoire de la bdd*/);
//                
//                Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
//                myController.setScreen(Main.screenMenuID);
//            } else{ //sinon si le mdp est mauvais
//                //message d'erreur et c'est tout
//            }
//        } else if(){ //sinon si le pseudo n'appartient pas et n'est pas null
//            //message d'erruer et c'est tout
//        }
        // cas d'inscription
//        if () { //si le pseudo voulu est déjà utilisé
//            //message d'erreur 
//        } else if(){ //sinon si not null
//            if(){ //si mot de passe et confirm mot de passe égaux et mot de passe 1 not null
//                //ça marche, on créé une nouvelle ligne dans la bdd
////                Main.joueur = new Joueur(pseudoLogIn.getText(), mdpLogIn.getText());
////                
////                Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
////                myController.setScreen(Main.screenMenuID);
//            } else if(){ //si mdp différent et not null
//                //message d'erreur 
//            }
//        }

        Main.joueur = new Joueur(pseudoLogIn.getText(), mdpLogIn.getText());

        Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
        myController.setScreen(Main.screenMenuID);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image(Main.cheminImg + "loginImg.png");
        loginImg.setImage(img);
        Image img2 = new Image(Main.cheminImg + "title.png");
        titleImg.setImage(img2);
    }

}
