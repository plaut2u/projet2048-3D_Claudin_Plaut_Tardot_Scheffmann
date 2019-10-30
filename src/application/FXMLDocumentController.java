/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Case;
import model.Plateau;

/**
 *
 * @author Gregoire
 */
public class FXMLDocumentController implements Initializable, ParametresApplication {

    @FXML
    private Pane fond;
    @FXML
    private Label moves;
    @FXML
    private Label score;

    // variables globales non définies dans la vue (fichier .fxml)
    private ArrayList<TuileGraphique> list = new ArrayList<>();
    Plateau p = new Plateau();
    boolean b = p.nouvelleCasePlateau();
    int direction;

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

    }

    private void draw() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            System.out.println(p);
            update(1);

        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Méthodes listeners pour gérer les événements (portent les mêmes noms que
     * dans Scene Builder
     */
    @FXML
    public void keyPressed(KeyEvent ke
    ) throws InterruptedException {
        String touche = ke.getText();
        boolean check = false;
        if (touche.compareTo("q") == 0) {
            direction = GAUCHE;
            for (int i = 0; i < list.size(); i++) {
                if ((list.get(i).getObjectifx() >= LARGEURTUILE + DEBUTGRILLEX && list.get(i).getObjectifx() < TAILLE * LARGEURTUILE + DEBUTGRILLEX) || (list.get(i).getObjectifx() > (LARGEURTUILE * TAILLE) + DEBUTGRILLEX + ESPACE && list.get(i).getObjectifx() <= (TAILLE * 2 * LARGEURTUILE) + ESPACE + DEBUTGRILLEX) || (list.get(i).getObjectifx() >= TAILLE * 2 * LARGEURTUILE + ESPACE * 2 + DEBUTGRILLEX + LARGEURTUILE && list.get(i).getObjectifx() <= TAILLE * 3 * LARGEURTUILE + ESPACE * 2 + DEBUTGRILLEX)) {
                    list.get(i).setObjectifx(list.get(i).getObjectifx() - LARGEURTUILE);
                    check = true;
                }
            }
            if (check) {
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
                boolean b2 = p.lanceurDeplacerCasePlateau(direction);
                if (b2 == true) {
                    //b = g.nouvelleCase();
                    b = p.nouvelleCasePlateau();
                    if (b == false) {
                        p.gameOver();
                    }
                }
                System.out.println(p);
                if (p.calculScore() >= OBJECTIF) {
                    p.victory();
                }
            }

        } else if (touche.compareTo("d") == 0) {
            direction = DROITE;
            for (int i = 0; i < list.size(); i++) {
                if ((list.get(i).getObjectifx() < DEBUTGRILLEX + LARGEURTUILE * 2 && list.get(i).getObjectifx() >= DEBUTGRILLEX) || (list.get(i).getObjectifx() < DEBUTGRILLEX + LARGEURTUILE * (2 + TAILLE) + ESPACE && list.get(i).getObjectifx() >= TAILLE * LARGEURTUILE + DEBUTGRILLEX + ESPACE) || (list.get(i).getObjectifx() < (TAILLE * 2 + 2) * LARGEURTUILE + 2 * ESPACE + DEBUTGRILLEX && list.get(i).getObjectifx() >= (TAILLE * 2) * LARGEURTUILE + 2 * ESPACE + DEBUTGRILLEX)) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                    list.get(i).setObjectifx(list.get(i).getObjectifx() + LARGEURTUILE);
                    check = true;
                }
            }
            if (check) {
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
                boolean b2 = p.lanceurDeplacerCasePlateau(direction);
                if (b2 == true) {
                    //b = g.nouvelleCase();
                    b = p.nouvelleCasePlateau();
                    if (b == false) {
                        p.gameOver();
                    }
                }
                System.out.println(p);
                if (p.calculScore() >= OBJECTIF) {
                    p.victory();
                }
            }
        } else if (touche.compareTo("z") == 0) {
            direction = HAUT;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getObjectify() > DEBUTGRILLEY) {
                    list.get(i).setObjectify(list.get(i).getObjectify() - HAUTEURTUILE);
                    check = true;
                }
            }
            if (check) {
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
                boolean b2 = p.lanceurDeplacerCasePlateau(direction);
                if (b2 == true) {
                    //b = g.nouvelleCase();
                    b = p.nouvelleCasePlateau();
                    if (b == false) {
                        p.gameOver();
                    }
                }
                System.out.println(p);
                if (p.calculScore() >= OBJECTIF) {
                    p.victory();
                }
            }
        } else if (touche.compareTo("s") == 0) {
            direction = BAS;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getObjectify() < DEBUTGRILLEY + 2 * HAUTEURTUILE) {
                    list.get(i).setObjectify(list.get(i).getObjectify() + HAUTEURTUILE);
                    check = true;
                }
            }
            if (check) {
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
                boolean b2 = p.lanceurDeplacerCasePlateau(direction);
                if (b2 == true) {
                    //b = g.nouvelleCase();
                    b = p.nouvelleCasePlateau();
                    if (b == false) {
                        p.gameOver();
                    }
                }
                System.out.println(p);
                if (p.calculScore() >= OBJECTIF) {
                    p.victory();
                }
            }
        } else if (touche.compareTo("e") == 0) {
            direction = DOWN;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getObjectifx() >= DEBUTGRILLEX && list.get(i).getObjectifx() <= 2 * TAILLE * LARGEURTUILE + DEBUTGRILLEX + ESPACE) {
                    list.get(i).setObjectifx(list.get(i).getObjectifx() + (TAILLE * LARGEURTUILE + ESPACE));
                    check = true;
                }
            }
            if (check) {
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
                boolean b2 = p.lanceurDeplacerCasePlateau(direction);
                if (b2 == true) {
                    //b = g.nouvelleCase();
                    b = p.nouvelleCasePlateau();
                    if (b == false) {
                        p.gameOver();
                    }
                }
                System.out.println(p);
                if (p.calculScore() >= OBJECTIF) {
                    p.victory();
                }
            }
        } else if (touche.compareTo("a") == 0) {
            direction = UP;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getObjectifx() >= DEBUTGRILLEX + ESPACE + TAILLE * LARGEURTUILE && list.get(i).getObjectifx() <= TAILLE * NBGRILLES * LARGEURTUILE + 2 * ESPACE + DEBUTGRILLEX) {
                    list.get(i).setObjectifx(list.get(i).getObjectifx() - (TAILLE * LARGEURTUILE + ESPACE));
                    check = true;
                }
            }
            if (check) {
                moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
                boolean b2 = p.lanceurDeplacerCasePlateau(direction);
                if (b2 == true) {
                    //b = g.nouvelleCase();
                    b = p.nouvelleCasePlateau();
                    if (b == false) {
                        p.gameOver();
                    }
                }
                System.out.println(p);
                if (p.calculScore() >= OBJECTIF) {
                    p.victory();
                }
            }
        }

        Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
                for (int k = 0; k < list.size(); k++) {
                    while (list.get(k).getPosx() != list.get(k).getObjectifx()) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                        if (list.get(k).getPosx() < list.get(k).getObjectifx()) {
                            list.get(k).setPosx(list.get(k).getPosx() + 1); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                        } else {
                            list.get(k).setPosx(list.get(k).getPosx() - 1); // si on va vers la gauche, idem en décrémentant la valeur de x
                        }
                        // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                for (int i = 0; i < list.size(); i++) {
                                    list.get(i).getPane().relocate(list.get(i).getPosx(), list.get(i).getPosy()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                    list.get(i).getPane().setVisible(true);
                                }
                            }
                        });
                        Thread.sleep(1);
                    }
                    while (list.get(k).getPosy() != list.get(k).getObjectify()) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                        if (list.get(k).getPosy() < list.get(k).getObjectify()) {
                            list.get(k).setPosy(list.get(k).getPosy() + 1); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                        } else {
                            list.get(k).setPosy(list.get(k).getPosy() - 1); // si on va vers la gauche, idem en décrémentant la valeur de x
                        }
                        // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                        Platform.runLater(new Runnable() { // classe anonyme
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                for (int i = 0; i < list.size(); i++) {
                                    list.get(i).getPane().relocate(list.get(i).getPosx(), list.get(i).getPosy()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                    list.get(i).getPane().setVisible(true);
                                }
                            }
                        }
                        );
                        Thread.sleep(1);
                    }
                }
                update(2);
                return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                // end call
            }
        };
        Thread th = new Thread(task); // on crée un contrôleur de Thread
        th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
        th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)

    }

    public void update(int n) throws InterruptedException {
       
        if (n == 1) { //LA PREMIERE FOIS QU'ON LANCE LE JEU
            for (int i = 0; i < NBGRILLES; i++) {
                for (Case elem : p.getPlateau()[i].getGrille()) {
                    int newx = (int) (LARGEURTUILE * elem.getX() + i * LARGEURTUILE * NBGRILLES) + DEBUTGRILLEX + (int) (ESPACE * i);
                    int newy = (int) (HAUTEURTUILE * elem.getY()) + DEBUTGRILLEY;
                    list.add(new TuileGraphique(newx, newy, elem.getValeur(), fond));
                    list.get(i).getPane().relocate(list.get(i).getPosx(), list.get(i).getPosy());
                    list.get(i).getPane().setVisible(true);
                    fond.getChildren().add(list.get(i).getPane());
                }
            }
        } else { //LES AUTRES FOIS
            //SUPRESSION DES ELEMENTS DE LA LISTE
//            for (int i = 0; i < NBGRILLES; i++) {
//                for (Case elem : p.getPlateau()[i].getGrille()) {
//                    for (int j = 0; j < list.size(); j++) {
//                        int newx = (int) (LARGEURTUILE * elem.getX() + i * LARGEURTUILE * NBGRILLES) + DEBUTGRILLEX + (int) (ESPACE * i);
//                        int newy = (int) (HAUTEURTUILE * elem.getY()) + DEBUTGRILLEY;
//                        if (list.get(j).getX() == newx && list.get(j).getY() == newy) {
//                            list.get(j).getPane().relocate(list.get(j).getPosx(), list.get(j).getPosy());
//                        }
//                        list.get(j).getPane().setVisible(false);
//                        list.remove(j);
//                    }
//                }
//            }
            //MISE A JOUR DU GRAPHIQUE EN FONCTION DE LA GRILLE
            for (int i = 0; i < NBGRILLES; i++) {
                for (Case elem : p.getPlateau()[i].getGrille()) {
                    int newx = (int) (LARGEURTUILE * elem.getX() + i * LARGEURTUILE * NBGRILLES) + DEBUTGRILLEX + (int) (ESPACE * i);
                    int newy = (int) (HAUTEURTUILE * elem.getY()) + DEBUTGRILLEY;
                    list.add(new TuileGraphique(newx, newy, elem.getValeur(), fond));
                }
            }
            System.out.println(list.size());
            for (int j = 0; j < list.size(); j++) {
                list.get(j).getPane().relocate(list.get(j).getPosx(), list.get(j).getPosy());
                list.get(j).getPane().setVisible(true);
                fond.getChildren().add(list.get(j).getPane());
                System.out.println("Airforce 44444444");
            }
            System.out.println(list);
        }
    }
}
