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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Gregoire
 */
public class FXMLDocumentControllerMenu implements Initializable, ParametresApplication, ControlledScreen {
    
    ScreensController myController;
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void beginGame(MouseEvent event) throws IOException {
        
        Main.mainContainer.loadScreen(Main.screenGameID, Main.screenGameFile);
        myController.setScreen(Main.screenGameID);
        
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
