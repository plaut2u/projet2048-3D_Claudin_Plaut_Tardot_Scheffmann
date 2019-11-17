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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Gregoire
 */
public class FXMLDocumentControllerVictory  implements Initializable, ParametresApplication, ControlledScreen {
    
    ScreensController myController;
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private Label objectifLabel;
    
    @FXML
    private void backToMenu(MouseEvent event) throws IOException{
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.mainContainer.unloadScreen(Main.screenGameID);
        Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
        myController.setScreen(Main.screenMenuID);
    }
    
    @FXML
    private void continueGame(MouseEvent event) throws IOException{
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        myController.setScreen(Main.screenGameID);

    }
    
    @FXML
    private void quitGame(MouseEvent event) throws IOException{
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        System.out.println("quit game");
        System.exit(0);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        objectifLabel.setText(Integer.toString(OBJECTIF));

    }
    
}
