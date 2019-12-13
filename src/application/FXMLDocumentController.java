/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import static application.Main.skinMode;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Case;
import model.Enregistreur;
import model.Plateau;

/**
 * Class FXMLDocumentController to manage GameScene.fxml
 *
 * @author Gregoire
 */
public class FXMLDocumentController implements Initializable, ParametresApplication, ControlledScreen {

    ScreensController myController;

    /**
     * Method to set a new screen
     *
     * @param screenParent the parent screen
     *
     */
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private Pane fond;

    @FXML
    private Label moves;

    @FXML
    private Label score;

    @FXML
    private Label pseudoLabel;

    @FXML
    private VBox VBoxBackground;

    // variables globales non définies dans la vue (fichier .fxml)
    private ArrayList<TuileGraphique> list = new ArrayList<>();
    Plateau p = new Plateau();
    boolean b = p.nouvelleCasePlateau();
    int direction;
    boolean hasWon = false;

    /**
     * Method triggered by a button to begin a new game
     *
     * @param MouseEvent event
     * @return void
     *
     */
    @FXML
    private void rebeginGame(MouseEvent event) {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();

        for (int i = 0; i < NBGRILLES; i++) {
            for (Case elem : p.getPlateau()[i].getGrille()) {
                for (int j = 0; j < list.size(); j++) {
                    int newx = (int) (LARGEURTUILE * elem.getX() + i * LARGEURTUILE * NBGRILLES) + DEBUTGRILLEX + (int) (ESPACE * i);
                    int newy = (int) (HAUTEURTUILE * elem.getY()) + DEBUTGRILLEY;
                    if (list.get(j).getX() == newx && list.get(j).getY() == newy) {
                        list.get(j).getImg().relocate(list.get(j).getPosx(), list.get(j).getPosy());
                    }
                    list.get(j).getImg().setVisible(false);
                    list.remove(j);
                }
            }
        }

        p = new Plateau();
        b = p.nouvelleCasePlateau();
        list = new ArrayList<>();
        moves.setText("0");
        score.setText("0");
        hasWon = false;
        Main.joueur.setJeuEnCours(false);

        for (int i = 0; i < NBGRILLES; i++) {
            for (Case elem : p.getPlateau()[i].getGrille()) {
                int newx = (int) (LARGEURTUILE * elem.getX() + i * LARGEURTUILE * NBGRILLES) + DEBUTGRILLEX + (int) (ESPACE * i);
                int newy = (int) (HAUTEURTUILE * elem.getY()) + DEBUTGRILLEY;
                list.add(new TuileGraphique(newx, newy, elem.getValeur(), fond));
                list.get(i).getImg().relocate(list.get(i).getPosx(), list.get(i).getPosy());
                list.get(i).getImg().setVisible(true);
                fond.getChildren().add(list.get(i).getImg());
            }
        }
    }

    /**
     * Method triggered by a button to save current game
     *
     * @param MouseEvent event
     * @return void
     *
     */
    @FXML
    private void saveGame(MouseEvent event) {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        Enregistreur.serialiser(p);
    }

    /**
     * Method triggered by a button to quit game
     *
     * @param MouseEvent event
     * @return void
     *
     */
    @FXML
    private void quitGame(MouseEvent event) {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        System.exit(0);
    }

    /**
     * Method triggered by a button to disconnect and go back to login menu
     *
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
     *
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
        stage.getIcons().add(new Image("img/avatar.png"));
        stage.setTitle("Mon Compte");
        stage.show();
    }

    /**
     * Method triggered by a button to open the param window
     *
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
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/param.png"));
        stage.setTitle("Paramètres");
        stage.show();
    }

    /**
     * Method triggered by a button to go back to menu
     *
     * @param MouseEvent event
     * @return void
     *
     */
    @FXML
    private void goToMenu(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();

        Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
        myController.setScreen(Main.screenMenuID);
    }

    /**
     * Method triggered by a key to try to move. Check what is the key, Changed
     * the direction depending on the key, Try to make soft move with Thread,
     * Update number of moves each times we moved. After all, call update(2).
     *
     * @param ke the key we pressed
     * @throws java.lang.InterruptedException If interrupted
     *
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
        } else if (touche.compareTo("d") == 0) {
            direction = DROITE;
            for (int i = 0; i < list.size(); i++) {
                if ((list.get(i).getObjectifx() < DEBUTGRILLEX + LARGEURTUILE * 2 && list.get(i).getObjectifx() >= DEBUTGRILLEX) || (list.get(i).getObjectifx() < DEBUTGRILLEX + LARGEURTUILE * (2 + TAILLE) + ESPACE && list.get(i).getObjectifx() >= TAILLE * LARGEURTUILE + DEBUTGRILLEX + ESPACE) || (list.get(i).getObjectifx() < (TAILLE * 2 + 2) * LARGEURTUILE + 2 * ESPACE + DEBUTGRILLEX && list.get(i).getObjectifx() >= (TAILLE * 2) * LARGEURTUILE + 2 * ESPACE + DEBUTGRILLEX)) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                    list.get(i).setObjectifx(list.get(i).getObjectifx() + LARGEURTUILE);
                    check = true;
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
        } else if (touche.compareTo("s") == 0) {
            direction = BAS;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getObjectify() < DEBUTGRILLEY + 2 * HAUTEURTUILE) {
                    list.get(i).setObjectify(list.get(i).getObjectify() + HAUTEURTUILE);
                    check = true;
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
        } else if (touche.compareTo("a") == 0) {
            direction = UP;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getObjectifx() >= DEBUTGRILLEX + ESPACE + TAILLE * LARGEURTUILE && list.get(i).getObjectifx() <= TAILLE * NBGRILLES * LARGEURTUILE + 2 * ESPACE + DEBUTGRILLEX) {
                    list.get(i).setObjectifx(list.get(i).getObjectifx() - (TAILLE * LARGEURTUILE + ESPACE));
                    check = true;
                }
            }
        }
        if (check) {
            moves.setText(Integer.toString(Integer.parseInt(moves.getText()) + 1));
            boolean b2 = p.lanceurDeplacerPlateau(direction);
            if (b2 == true) {
                b = p.nouvelleCasePlateau();
            }
        }
        if (!p.checkMovesPlateau()) {
            p.setBloque(true);
        }
        ArrayList<Thread> listThread = new ArrayList<>();

        for (int k = 0; k < list.size(); k++) {
            final int y = k;
            Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
                final int u = y;

                @Override
                public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
                    final int c = u;
                    while (list.get(c).getPosx() != list.get(c).getObjectifx()) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                        if (list.get(c).getPosx() < list.get(c).getObjectifx()) {
                            list.get(c).setPosx(list.get(c).getPosx() + 1); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                        } else {
                            list.get(c).setPosx(list.get(c).getPosx() - 1); // si on va vers la gauche, idem en décrémentant la valeur de x
                        }
                        // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                for (int i = 0; i < list.size(); i++) {
                                    list.get(i).getImg().relocate(list.get(i).getPosx(), list.get(i).getPosy()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                    list.get(i).getImg().setVisible(true);
                                }
                            }
                        });
                        Thread.sleep(100);
                    }
                    while (list.get(c).getPosy() != list.get(c).getObjectify()) { // si la tuile n'est pas à la place qu'on souhaite attendre en abscisse
                        if (list.get(c).getPosy() < list.get(c).getObjectify()) {
                            list.get(c).setPosy(list.get(c).getPosy() + 1); // si on va vers la droite, on modifie la position de la tuile pixel par pixel vers la droite
                        } else {
                            list.get(c).setPosy(list.get(c).getPosy() - 1); // si on va vers la gauche, idem en décrémentant la valeur de x
                        }
                        // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                        Platform.runLater(new Runnable() { // classe anonyme
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                for (int i = 0; i < list.size(); i++) {
                                    list.get(i).getImg().relocate(list.get(i).getPosx(), list.get(i).getPosy()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                    list.get(i).getImg().setVisible(true);
                                }
                            }
                        }
                        );
                        Thread.sleep(100);
                    }

                    return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                    // end call
                }
            };

            Thread tr = new Thread(task);// on crée un contrôleur de Thread
            tr.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
            tr.start();// et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
            listThread.add(tr);

        }

        Task taskUpdate = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task

                // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //javaFX operations should go here
                            update(2);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
                // end call
            }
        };
        Thread tr = new Thread(taskUpdate);// on crée un contrôleur de Thread
        tr.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
        tr.start();// et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)

        listThread.clear();
    }

    /**
     * Method to update the view and to put new case on the Plateau p. If first
     * time (n = 1), we check if the player wanted to load a saved game. If we
     * want to load, we just update the Plateau according to the saved Plateau..
     * If he didn't want to load a new game, we just start a new Plateau,
     * according to the model Plateau. If second time or mort (n different of
     * 1). We first delete all the element of the Plateau, we add on the Plateau
     * all the new elements (the moved tiles and the new tiles) according to the
     * model Plateau. Update of the score and the style sheet if changed. Test
     * victory or Game Over.
     *
     * @param n the param to control if we update the Plateau for the first
     * time, or during the game
     * @throws java.lang.InterruptedException if inerrupted
     * @throws java.io.IOException if Io
     *
     */
    public void update(int n) throws InterruptedException, IOException {
        if (n == 1) { //LA PREMIERE FOIS QU'ON LANCE LE JEU
            if (Main.wantToLoad) { //SI LANCEMENT D'UNE PARTIE SAUVEGARDÉE
                for (int i = 0; i < NBGRILLES; i++) {
                    for (Case elem : p.getPlateau()[i].getGrille()) {
                        int newx = (int) (LARGEURTUILE * elem.getX() + i * LARGEURTUILE * NBGRILLES) + DEBUTGRILLEX + (int) (ESPACE * i);
                        int newy = (int) (HAUTEURTUILE * elem.getY()) + DEBUTGRILLEY;
                        list.add(new TuileGraphique(newx, newy, elem.getValeur(), fond));
                    }
                }
                for (int j = 0; j < list.size(); j++) {
                    list.get(j).getImg().relocate(list.get(j).getPosx(), list.get(j).getPosy());
                    list.get(j).getImg().setVisible(true);
                    fond.getChildren().add(list.get(j).getImg());
                }
                score.setText(Integer.toString(p.calculScore()));
                Main.joueur.setJeuEnCours(true);
                VBoxBackground.getStylesheets().add("css/" + skinMode + ".css");
            } else { //SI LANCEMENT NORMAL
                for (int i = 0; i < NBGRILLES; i++) {
                    for (Case elem : p.getPlateau()[i].getGrille()) {
                        int newx = (int) (LARGEURTUILE * elem.getX() + i * LARGEURTUILE * NBGRILLES) + DEBUTGRILLEX + (int) (ESPACE * i);
                        int newy = (int) (HAUTEURTUILE * elem.getY()) + DEBUTGRILLEY;
                        list.add(new TuileGraphique(newx, newy, elem.getValeur(), fond));
                        list.get(i).getImg().relocate(list.get(i).getPosx(), list.get(i).getPosy());
                        list.get(i).getImg().setVisible(true);
                        fond.getChildren().add(list.get(i).getImg());

                    }
                }
                Main.joueur.setJeuEnCours(false);
            }
        } else { //LES AUTRES FOIS
            //SUPRESSION DES ELEMENTS DE LA LISTE
            for (int i = 0; i < NBGRILLES; i++) {
                for (Case elem : p.getPlateau()[i].getGrille()) {
                    for (int j = 0; j < list.size(); j++) {
                        int newx = (int) (LARGEURTUILE * elem.getX() + i * LARGEURTUILE * NBGRILLES) + DEBUTGRILLEX + (int) (ESPACE * i);
                        int newy = (int) (HAUTEURTUILE * elem.getY()) + DEBUTGRILLEY;
                        if (list.get(j).getX() == newx && list.get(j).getY() == newy) {
                            list.get(j).getImg().relocate(list.get(j).getPosx(), list.get(j).getPosy());
                        }
                        list.get(j).getImg().setVisible(false);
                        list.remove(j);
                    }
                }
            }
            //MISE A JOUR DU GRAPHIQUE EN FONCTION DE LA GRILLE
            for (int i = 0; i < NBGRILLES; i++) {
                for (Case elem : p.getPlateau()[i].getGrille()) {
                    int newx = (int) (LARGEURTUILE * elem.getX() + i * LARGEURTUILE * NBGRILLES) + DEBUTGRILLEX + (int) (ESPACE * i);
                    int newy = (int) (HAUTEURTUILE * elem.getY()) + DEBUTGRILLEY;
                    list.add(new TuileGraphique(newx, newy, elem.getValeur(), fond));
                }
            }
            for (int j = 0; j < list.size(); j++) {
                list.get(j).getImg().relocate(list.get(j).getPosx(), list.get(j).getPosy());
                list.get(j).getImg().setVisible(true);
                fond.getChildren().add(list.get(j).getImg());
            }

            //Mise a jour du score :
            int newBestScore = Math.max(Main.joueur.getMeilleurScore(), p.calculScore());
            Main.joueur.setMeilleurScore(newBestScore);

            score.setText(Integer.toString(p.calculScore()));
            Main.joueur.setJeuEnCours(true);
            VBoxBackground.getStylesheets().add("css/" + skinMode + ".css");

            //Test victoire ou défaite
            if (p.calculScore() >= OBJECTIF && !hasWon) {
                Main.mainContainer.loadScreen(Main.screenVictoryID, Main.screenVictoryFile);
                myController.setScreen(Main.screenVictoryID);
                hasWon = true;
            }
            if (p.getBloque()) {
                Main.mainContainer.loadScreen(Main.screenGameOverID, Main.screenGameOverFile);
                myController.setScreen(Main.screenGameOverID);
            }
        }
        Main.wantToLoad = false;
    }

    /**
     * Method which initialize the component. If we charge a saved game, we will
     * update the Plateau before anything else, After, call update(1), Set the
     * pseudo in the menu bar, Add style sheet in the background.
     *
     * @param url the current url
     * @param rb the ResourceBundle
     * @see #update(int)
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            if (Main.wantToLoad) {
                p = Enregistreur.deserialiser();
            }
            update(1);
            pseudoLabel.setText(Main.joueur.getPseudo());
            VBoxBackground.getStylesheets().add("css/" + skinMode + ".css");

        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
