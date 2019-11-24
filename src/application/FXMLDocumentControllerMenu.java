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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Enregistreur;

/**
 * Class FXMLDocumentControllerMenu to manage MenuScene.fxml
 * @author Gregoire
 */
public class FXMLDocumentControllerMenu implements Initializable, ParametresApplication, ControlledScreen {
    
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
    private Label pseudoLabel;
    
    @FXML
    private Button newGameButton;
    
    @FXML
    private Button loadGameButton;
    
    @FXML
    private Button continueGameButton;
    
    @FXML
    private VBox VBoxBackground;
    
    @FXML
    private ImageView titleImg;
    
    @FXML
    private ImageView imgLeft;
    
    @FXML
    private ImageView imgRight;
    
    @FXML
    private Label noSaveLabel;
    
    /**
    * Method triggered by a button to set visible other buttons
    * @param MouseEvent event
    * @return void
    * @throws java.io.IOException if io-Exception
    */
    @FXML
    private void soloGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        newGameButton.setVisible(true);
        loadGameButton.setVisible(true);
        if(Main.joueur.getJeuEnCours()){
            continueGameButton.setVisible(true);
        }
    }
    
    /**
    * Method triggered by a button to continue last game
    * @param MouseEvent event
    * @return void
    * @throws java.io.IOException if io-Exception
    * 
    */
    @FXML
    private void continueGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        myController.setScreen(Main.screenGameID);
    }
    
    /**
    * Method triggered by a button to try to load a saved game
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void loadGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        //load partie
        if(Enregistreur.deserialiser() != null){
            Main.wantToLoad = true;
            System.out.println(Enregistreur.deserialiser());
            Main.joueur.setJeuEnCours(true);
        
            Main.mainContainer.loadScreen(Main.screenGameID, Main.screenGameFile);
            myController.setScreen(Main.screenGameID);
        }else{
            Main.wantToLoad = false;
            noSaveLabel.setVisible(true);
        }
    }
    
    /**
    * Method triggered by a button to begin a new game
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void beginGame(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.joueur.setJeuEnCours(true);
        
        Main.mainContainer.loadScreen(Main.screenGameID, Main.screenGameFile);
        myController.setScreen(Main.screenGameID);
    }
    
    /**
    * Method triggered by a button to disconnect and go back to login menu
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void disconnect(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.mainContainer.loadScreen(Main.screenLoginID, Main.screenLoginFile);
        myController.setScreen(Main.screenLoginID);
    }
    
    /**
    * Method triggered by a button to open the account window
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void goToAccount(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Parent root = FXMLLoader.load(getClass().getResource(Main.screenAccountFile));
        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("css/" + skinMode + ".css");

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Mon Compte");
        stage.getIcons().add(new Image("img/avatar.png"));
        stage.show();
    }
    
    /**
    * Method triggered by a button to open the param window
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void goToParam(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Parent root = FXMLLoader.load(getClass().getResource(Main.screenParamFile));
        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("css/" + skinMode + ".css");

        Stage stage = new Stage();
        stage.getIcons().add(new Image("img/param.png"));
        stage.setTitle("Paramètres");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
    * Method which initialize the component.
    * Set images on the pane,
    * Add style sheet to the background,
    * Set pseudo to the menu bar.
    * 
    * @param url the current url
    * @param rb the RescourceBundle
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pseudoLabel.setText(Main.joueur.getPseudo());
        Image img = new Image(Main.cheminImg + "title.png");
        titleImg.setImage(img);
        Image imgL = new Image(Main.cheminImg + "menuImgLeft.png");
        imgLeft.setImage(imgL);
        Image imgR = new Image(Main.cheminImg + "menuImgRight.png");
        imgRight.setImage(imgR);
        VBoxBackground.getStylesheets().add("css/" + skinMode + ".css");
    }
}
