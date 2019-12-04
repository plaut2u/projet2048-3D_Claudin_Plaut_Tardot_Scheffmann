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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Class FXMLDocumentControllerGameOver to manage GameOverScene.fxml
 * @author Gregoire
 */
public class FXMLDocumentControllerGameOver   implements Initializable, ParametresApplication, ControlledScreen {
    
    ScreensController myController;
    
    /**
    * Method to set a new screen 
    * @param screenParent the parent screen
    * 
    */
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private VBox VBoxBackground;
    
    /**
    * Method triggered by a button to go back to menu
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void backToMenu(MouseEvent event) throws IOException{
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.joueur.setJeuEnCours(false);
        
        Main.mainContainer.unloadScreen(Main.screenGameID);
        Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
        myController.setScreen(Main.screenMenuID);
    }
    
    /**
    * Method triggered by a button to begin a new game
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void rebeginGame(MouseEvent event) throws IOException{
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.joueur.setJeuEnCours(false);
        
        Main.mainContainer.unloadScreen(Main.screenGameID);
        Main.mainContainer.loadScreen(Main.screenGameID, Main.screenGameFile);
        myController.setScreen(Main.screenGameID);
    }
    
    /**
    * Method triggered by a button to quit game
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void quitGame(MouseEvent event) throws IOException{
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        System.exit(0);
    }
    
    /**
    * Method to initialize the component
    * Add style sheet to the background
    * 
    * @param url the current url
    * @param rb the ResourceBundle
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VBoxBackground.getStylesheets().add("css/" + skinMode + ".css");
    }
    
}