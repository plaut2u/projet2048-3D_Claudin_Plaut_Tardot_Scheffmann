/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Gregoire
 */
public class FXMLDocumentController implements Initializable, ParametresApplication {

    @FXML
    private GridPane grid1, grid2, grid3;
    @FXML
    private Pane fond;
    @FXML
    private Label moves;

    // variables globales non définies dans la vue (fichier .fxml)
    private final ArrayList<Pane> listPane = new ArrayList<>();
    private final ArrayList<Label> listLabel = new ArrayList<>();
    private final Pane p = new Pane(); // panneau utilisé pour dessiner une tuile "2"

    private final Label c = new Label("2");
    private int direction = 0;
    private int x = 68, y = 201;
    private int objectifx = 68, objectify = 201;

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

//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent e) {
//
//                if (null != e.getCode()) {
//                    switch (e.getCode()) {
//                        case D:
//                            System.out.println("Mouvement à droite");
//                            direction = DROITE;
//                            break;
//                        case Q:
//                            System.out.println("Mouvement à gauche");
//                            direction = GAUCHE;
//                            break;
//                        case Z:
//                            System.out.println("Mouvement en haut");
//                            direction = HAUT;
//                            break;
//                        case S:
//                            System.out.println("Mouvement en bas");
//                            direction = BAS;
//                            break;
//                        case A:
//                            System.out.println("Mouvement au dessus");
//                            direction = UP;
//                            break;
//                        case E:
//                            System.out.println("Mouvement en dessous");
//                            direction = DOWN;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//        });

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        for (int i = 0; i < TAILLE * TAILLE * NBGRILLES; i++) {
            listPane.add(new Pane());
            listLabel.add(new Label());
        }

        move();

    }

    private void move() {
        switch (direction) {
            case DROITE:
                break;
            case GAUCHE:
                break;
            case HAUT:
                break;
            case BAS:
                break;
            case UP:
                break;
            case DOWN:
                break;
            default:
                break;
        }
        draw();
    }

    private void draw() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("le contrôleur initialise la vue");
        // utilisation de styles pour la grille et la tuile (voir styles.css)
        p.getStyleClass().add("pane");
        c.getStyleClass().add("tuile");
        grid1.getStyleClass().add("gridpane");
        GridPane.setHalignment(c, HPos.CENTER);
        fond.getChildren().add(p);
        p.getChildren().add(c);

        // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche
        p.setLayoutX(x);
        p.setLayoutY(y);
        p.setVisible(true);
        c.setVisible(true);
    }

    /*
     * Méthodes listeners pour gérer les événements (portent les mêmes noms que
     * dans Scene Builder
     */
    @FXML
    public void keyPressed(KeyEvent ke) {
        System.out.println("touche appuyée");
        String touche = ke.getText();
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            if (objectifx > 68) { // possible uniquement si on est pas dans la colonne la plus à gauche
                objectifx -= (int) (107); // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1)); // mise à jour du compteur de mouvement
            }
        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            if (objectifx < (int) 214) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectifx += (int) 107;
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
            }
        } else if (touche.compareTo("z") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            if (objectify > (int) 201) { // possible uniquement si on est pas dans la ligne la plus haute (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectify -= (int) 97;
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
            }
        } else if (touche.compareTo("s") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            if (objectify < (int) 415-97) { // possible uniquement si on est pas dans la ligne la plus basse (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectify += (int) 97;
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
            }
        }
        System.out.println("objectifx=" + objectifx);
        System.out.println("objectify=" + objectify);
        Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
                while (x != objectifx) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                    if (x < objectifx) {
                        x += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                    } else {
                        x -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                    }
                    // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                    Platform.runLater(new Runnable() { // classe anonyme
                        @Override
                        public void run() {
                            //javaFX operations should go here
                            p.relocate(x, y); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                            p.setVisible(true);
                        }
                    }
                    );
                    Thread.sleep(1);
                }
                while (y != objectify) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                    if (y < objectify) {
                        y += 1; // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                    } else {
                        y -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                    }
                    // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                    Platform.runLater(new Runnable() { // classe anonyme
                        @Override
                        public void run() {
                            //javaFX operations should go here
                            p.relocate(x, y); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                            p.setVisible(true);
                        }
                    }
                    );
                    Thread.sleep(1);
                }
                // end while
                return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
            } // end call

        };
        Thread th = new Thread(task); // on crée un contrôleur de Thread
        th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
        th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
    }
}
