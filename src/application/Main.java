/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Gregoire
 */
public class Main extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("menuConnexionScene.fxml"));
        
        Scene scene = new Scene(root);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (null != e.getCode()) switch (e.getCode()) {
                    case D:
                        System.out.println("Mouvement à droite");
                        break;
                    case Q:
                        System.out.println("Mouvement à gauche");
                        break;
                    case Z:
                        System.out.println("Mouvement en haut");
                        break;
                    case S:
                        System.out.println("Mouvement en bas");
                        break;
                    case A:
                        System.out.println("Mouvement au dessus");
                        break;
                    case E:
                        System.out.println("Mouvement en dessous");
                        break;
                    default:
                        break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
