/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Gregoire
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private void rebeginGame(MouseEvent event) {
        System.out.println("new partie");
    }

    @FXML
    private void quitGame(MouseEvent event) {
        System.out.println("quit game");
        System.exit(0);
    }

    @FXML
    private void login(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("choixModeScene.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void beginGame(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("gameScene.fxml"));

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (null != e.getCode()) {
                    switch (e.getCode()) {
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
            }
        });

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
