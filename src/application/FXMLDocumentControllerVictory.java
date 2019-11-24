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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Class FXMLDocumentControllerVictory to manage VictoryScene.fxml
 * @author Gregoire
 */
public class FXMLDocumentControllerVictory implements Initializable, ParametresApplication, ControlledScreen {

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
    private Label objectifLabel;
    
    @FXML
    private VBox VBoxBackground;
    
    /**
    * Method triggered by a button to go back to menu
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void backToMenu(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.joueur.setJeuEnCours(true);

        Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
        myController.setScreen(Main.screenMenuID);
    }
    
    /**
    * Method triggered by a button to continue the current game
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void continueGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.joueur.setJeuEnCours(true);
        
        myController.setScreen(Main.screenGameID);

    }
    
    /**
    * Method triggered by a button to quit the game
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void quitGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        System.out.println("quit game");
        System.exit(0);
    }
    
    /**
    * Method triggered by a button to begin a new game
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void rebeginGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.joueur.setJeuEnCours(false);

        Main.mainContainer.unloadScreen(Main.screenGameID);
        Main.mainContainer.loadScreen(Main.screenGameID, Main.screenGameFile);
        myController.setScreen(Main.screenGameID);

    }
    
    /**
    * Method which initialize the component.
    * Set the objectifLabel to his value,
    * Add the style sheet to the background,
    * Play music if wanted.
    * 
    * @param url the current url
    * @param rb the resourceBundle
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        objectifLabel.setText(Integer.toString(OBJECTIF));
        VBoxBackground.getStylesheets().add("css/" + skinMode + ".css");
        if(Main.music){
            Sound victory = new Sound("sound\\" + "victory.wav");
            victory.start();
        }
    }

}
