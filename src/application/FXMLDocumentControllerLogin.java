/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import static application.Main.skinMode;
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
import javafx.scene.layout.VBox;
import model.Joueur;

/**
 * Class FXMLDocumentControllerLogin to manage LoginScene.fxml
 * @author Gregoire
 */
public class FXMLDocumentControllerLogin implements Initializable, ParametresApplication, ControlledScreen {

    ScreensController myController;
    
    /**
    * Method to set a new screen 
    * @param screenParent the parent screen
    * 
    */
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
    private VBox VBoxBackground;
    
    /**
    * Method triggered by a button to try to login.
    * We try to connect with the Data Base, 
    * and we login or sign in the player according to his pseudo and password.
    * @param MouseEvent event
    * @return void
    * @throws java.io.IOException if io-Exeption
    */
    @FXML
    private void login(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();

        //Ouverture BDD
        //variables : pseudoLogIn, mdpLogIn, pseudoSignIn, mdpSignIn, mdpSignInVerif
        String requete = "";
        requete = "SELECT * FROM Login WHERE pseudoLogIn.getText() = login";
        if ("".equals(requete)) {
            System.out.println("Ce pseudo n'est pas attribué, inscrivez-vous pour pouvoir jouer");
        } else {
            requete = "SELECT * FROM Password WHERE mdpLogIn.getText() = mdp";
            if (requete == "") {
                Main.joueur = new Joueur(pseudoLogIn.getText(), mdpLogIn.getText());
                Main.joueur.setMeilleurScore(SELECT * FROM HighScore WHERE Login = pseudoLogIn.getText() AND Password = mdpLogIn.getText() );
                Main.joueur.setNbvictoires(SELECT * FROM NbVictoires WHERE Login = pseudoLogIn.getText() AND Password = mdpLogIn.getText() );
                Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
                myController.setScreen(Main.screenMenuID);
            } else {
                System.out.println("azy t'es sérieux tu te souviens plus de ton mot de passe");
            }

        }
        requete = "SELECT * FROM Login WHERE pseudoSignIn.getText() = Login";

        if (requete != "") {
            System.out.println("Ce pseudo est déjà attribué");
        } else if (pseudoSignIn.getText() == null) {
            System.out.println("Veuillez entre un pseudo");
        } else if (mdpSignIn.getText() == null) {
            System.out.println("Veuillez mettre un mot de passe");
        } else if (mdpSignIn.getText() != mdpSignInVerif.getText()) {
            System.out.println("Veuillez deux mots de passe identitiques");
        } else {
            Main.joueur = new Joueur(pseudoSignIn.getText(), mdpSignIn.getText());
            Main.joueur.setMeilleurScore(0);
            Main.joueur.setNbvictoires(0);
            requete = "INSERT INTO (nomBase) VALUES(pseudoSignIn.getText(), mdpSignIn.getText(),0,0)"
            Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
            myController.setScreen(Main.screenMenuID);

        }

        //cas de connexion :
//        if (true) { //si le psuedo appartient deja a la bdd
//            if (true) { //alors si le mdp correspond au pseudo
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
    
    /**
    * Method which initialize the component.
    * Set the objectifLabel to his value,
    * Add the style sheet to the background,
    * Play music if wanted.
    * 
    * @param url the current url
    * @param rb the ResourceBundle
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image(Main.cheminImg + "loginImg.png");
        loginImg.setImage(img);
        Image img2 = new Image(Main.cheminImg + "title.png");
        titleImg.setImage(img2);
        VBoxBackground.getStylesheets().add("css/" + skinMode + ".css");
    }

}
