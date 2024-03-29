/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Joueur;

/**
 * Class Main of the project
 * @author Gregoire
 */
public class Main extends Application {
    
    public static String screenLoginID = "login";
    public static String screenLoginFile = "LoginScene.fxml";
    public static String screenMenuID = "menu";
    public static String screenMenuFile = "MenuScene.fxml";
    public static String screenGameID = "game";
    public static String screenGameFile = "GameScene.fxml";
    public static String screenVictoryID = "victory";
    public static String screenVictoryFile = "VictoryScene.fxml";
    public static String screenGameOverID = "gameover";
    public static String screenGameOverFile = "GameOverScene.fxml";
    public static String screenParamID = "param";
    public static String screenParamFile = "ParametersScene.fxml";
    public static String screenAccountID = "account";
    public static String screenAccountFile = "AccountScene.fxml";
    public static String screenClassementID = "classement";
    public static String screenClassementFile = "ClassementScene.fxml";
    public static ScreensController mainContainer = new ScreensController();
    public static String cheminImg;
    public static String cheminSound;
    public static Sound musicTheme;
    public static String skinMode;
    public static Joueur joueur;
    public static boolean music = false;
    public static boolean wantToLoad = false;
    
    /**
    * Method which start the view.
    * We instance some variables, open some screens, and create the primaryStage.
    * @param primaryStage the stage used during all the game to draw our scenes 
    * @throws java.lang.Exception if Exception
    * @see #manageMusic(String) 
    * 
    */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Skin
        skinMode = "default";
        cheminImg = "img/" + skinMode + "/";
        cheminSound = "sound/" + skinMode + "/";
        
        manageMusic("new");
        if(!music) manageMusic("stop");

        
        //ouverture des ecrans
        mainContainer.loadScreen(Main.screenLoginID, Main.screenLoginFile);
        mainContainer.setScreen(Main.screenLoginID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("css/" + skinMode + ".css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048-3D");
        primaryStage.getIcons().add(new Image("img/coupe.png"));
        primaryStage.setMinHeight(762);
        primaryStage.setMinWidth(1193);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
    * Method to manage the music.
    * Depending on s, we can stop the music, changed the music or create it.
    * @param s the string we used to know what the player wants to make with the music
    * 
    */
    public static void manageMusic(String s){
        if(s.equals("new")){
            musicTheme = new Sound(cheminSound + "theme.wav");
            musicTheme.start();
        } else if(s.equals("changed")){
            musicTheme.stop();
            musicTheme = new Sound(cheminSound + "theme.wav");
            musicTheme.start();
        } else if(s.equals("stop")){
            musicTheme.stop();
        }
    }
    
}
