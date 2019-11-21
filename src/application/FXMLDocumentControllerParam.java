/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Gregoire
 */
public class FXMLDocumentControllerParam implements Initializable, ParametresApplication, ControlledScreen{
    
    ScreensController myController;
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    private boolean tempMusic;
    private String tempStyle;
    private ObservableList<String> availableChoices;

    @FXML
    private ChoiceBox<String> styleChoicer;

    @FXML
    private CheckBox musicCheckBox;
    
    @FXML
    private Button okButton;
    
    @FXML
    private Button cancelButton;

    @FXML
    private void musicChange(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
    }

    @FXML
    private void ok(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        Main.music = musicCheckBox.isSelected();
        if(styleChoicer.getSelectionModel().getSelectedItem().equals("Par défaut")){
            Main.skinMode = "default";
        }else if(styleChoicer.getSelectionModel().getSelectedItem().equals("Temple")){
            Main.skinMode = "temple";
        }else if(styleChoicer.getSelectionModel().getSelectedItem().equals("Minecraft")){
            Main.skinMode = "minecraft";
        }else if(styleChoicer.getSelectionModel().getSelectedItem().equals("Noël")){
            Main.skinMode = "noel";
        }
        Main.cheminImg = "img/" + Main.skinMode + "/";
        Main.cheminSound = "sound\\" + Main.skinMode + "\\";
        if(Main.music == true){
            Main.manageMusic("changed");
        } else{
            Main.manageMusic("stop");
        }
        
        Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
        Main.mainContainer.setScreen(Main.screenMenuID);
        
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        Main.music = tempMusic;
        Main.skinMode = tempStyle;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(Main.music) musicCheckBox.setSelected(true);
        tempMusic = Main.music;
        tempStyle = Main.skinMode;
        availableChoices = FXCollections.observableArrayList("Par défaut", "Temple", "Minecraft", "Noël"); 
        styleChoicer.setItems(availableChoices);
        if(Main.skinMode.equals("default")){
            styleChoicer.getSelectionModel().select("Par défaut");
        } else if(Main.skinMode.equals("temple")){
            styleChoicer.getSelectionModel().select("Temple");
        } else if(Main.skinMode.equals("minecraft")){
            styleChoicer.getSelectionModel().select("Minecraft");
        } else if(Main.skinMode.equals("noel")){
            styleChoicer.getSelectionModel().select("Par défaut");
        }
    }

}
