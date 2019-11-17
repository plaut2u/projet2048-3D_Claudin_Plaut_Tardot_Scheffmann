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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Gregoire
 */
public class Main extends Application {
    
    public static String screenLoginID = "login";
    public static String screenLoginFile = "menuConnexionScene.fxml";
    public static String screenMenuID = "menu";
    public static String screenMenuFile = "choixModeScene.fxml";
    public static String screenGameID = "game";
    public static String screenGameFile = "gameScene.fxml";
    public static String screenVictoryID = "victory";
    public static String screenVictoryFile = "VictoryScene.fxml";
    public static String screenGameOverID = "gameover";
    public static String screenGameOverFile = "GameOverScene.fxml";
    public static ScreensController mainContainer = new ScreensController();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        mainContainer.loadScreen(Main.screenLoginID, Main.screenLoginFile);

        
        mainContainer.setScreen(Main.screenLoginID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("css/styles.css");
        primaryStage.setScene(scene);
//        primaryStage.sizeToScene();
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
    
}
