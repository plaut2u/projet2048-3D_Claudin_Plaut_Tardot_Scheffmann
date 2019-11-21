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
 *
 * @author Gregoire
 */
public class FXMLDocumentControllerVictory implements Initializable, ParametresApplication, ControlledScreen {

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private Label objectifLabel;
    
    @FXML
    private VBox VBoxBackground;
    
    
    /**
    * Method triggered by a button to go back to menu
    * @param MouseEvent : 
    */
    @FXML
    private void backToMenu(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.joueur.setJeuEnCours(true);

        Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
        myController.setScreen(Main.screenMenuID);
    }

    @FXML
    private void continueGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.joueur.setJeuEnCours(true);
        
        myController.setScreen(Main.screenGameID);

    }

    @FXML
    private void quitGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        System.out.println("quit game");
        System.exit(0);
    }

    @FXML
    private void rebeginGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.joueur.setJeuEnCours(false);

        Main.mainContainer.unloadScreen(Main.screenGameID);
        Main.mainContainer.loadScreen(Main.screenGameID, Main.screenGameFile);
        myController.setScreen(Main.screenGameID);

    }

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
