/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import static application.Main.skinMode;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Joueur;

/**
 * Class FXMLDocumentControllerLogin to manage LoginScene.fxml
 *
 * @author Gregoire
 */
public class FXMLDocumentControllerLogin implements Initializable, ParametresApplication, ControlledScreen {

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
    private ImageView titleImg;

    @FXML
    private ImageView loginImg;

    @FXML
    private TextField pseudoLogIn;

    @FXML
    private PasswordField mdpLogIn;

    @FXML
    private TextField pseudoSignIn;

    @FXML
    private PasswordField mdpSignIn;

    @FXML
    private PasswordField mdpSignInVerif;

    @FXML
    private Label LogInError;

    @FXML
    private Label SignInError;

    @FXML
    private VBox VBoxBackground;

    /**
     * Method triggered by a button to try to login. We try to connect with the
     * Data Base, and we login or sign in the player according to his pseudo and
     * password.
     *
     * @param MouseEvent event
     * @return void
     * @throws java.io.IOException if io-Exeption
     */
    @FXML
    private void login(MouseEvent event) throws IOException, SQLException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();

        boolean success = false;

        //Ouverture BDD
        /* Connexion à la base de données */
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            /* Gérer les éventuelles erreurs ici. */
        }

        
        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(url, USER, PASSWORD);
            Statement statement = connexion.createStatement();

            if (pseudoLogIn.getText().equals("") && !pseudoSignIn.getText().equals("") && !mdpSignIn.getText().equals("")) { //cas d'inscription
                String requete = "";
                requete = "SELECT Pseudo FROM Projet2048";
                ResultSet rs = statement.executeQuery(requete);
                ArrayList<String> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(rs.getString("Pseudo"));
                }
                if (list.contains(pseudoSignIn.getText())) {
                    //pseudo déjà utilisé
                    SignInError.setText("Ce pseudo est déjà utilisé");
                    SignInError.setVisible(true);
                } else {
                    //inscription réussite
                    if (mdpSignIn.getText().equals(mdpSignInVerif.getText())) {
                        requete = "INSERT INTO Projet2048 VALUES('" + pseudoSignIn.getText() + "','" + mdpSignIn.getText() + "',0,0)";
                        int result = statement.executeUpdate(requete);
                        Main.joueur = new Joueur(pseudoSignIn.getText(), mdpSignIn.getText());

                        success = true;

                    } else {
                        //mot de passe différents
                        SignInError.setText("Les deux mots de passes sont différents");
                        SignInError.setVisible(true);
                    }
                }
            } else if (!pseudoLogIn.getText().equals("") && pseudoSignIn.getText().equals("")) { //cas de connexion
                String requete = "";
                requete = "SELECT Pseudo, Password FROM Projet2048";
                ResultSet rs = statement.executeQuery(requete);
                ArrayList<String> listPseudo = new ArrayList<>();
                ArrayList<String> listPassword = new ArrayList<>();
                while (rs.next()) {
                    listPseudo.add(rs.getString("Pseudo"));
                    listPassword.add(rs.getString("Password"));
                }
                if (listPseudo.contains(pseudoLogIn.getText())) {
                    //deja un compte créé
                    int index = listPseudo.indexOf(pseudoLogIn.getText());
                    if (listPassword.get(index).equals(mdpLogIn.getText())) {
                        //connexion réussite
                        Statement statement2 = connexion.createStatement();
                        requete = "SELECT NbVictory, BestScore, Pseudo FROM Projet2048 ";
                        ResultSet rs2 = statement2.executeQuery(requete);
                        ArrayList<String> listNbVictory = new ArrayList<>();
                        ArrayList<String> listBestScore = new ArrayList<>();
                        while (rs2.next()) {
                            listNbVictory.add(rs2.getString("NbVictory"));
                            listBestScore.add(rs2.getString("BestScore"));
                            listPseudo.add(rs2.getString("Pseudo"));
                        }
                        
                        int index2 = listPseudo.indexOf(pseudoLogIn.getText());

                        String nbVic = listNbVictory.get(index2);
                        String bestScore = listBestScore.get(index2);

                        Main.joueur = new Joueur(pseudoLogIn.getText(), mdpLogIn.getText());
                        Main.joueur.setMeilleurScore(Integer.parseInt(bestScore));
                        Main.joueur.setNbVictoires(Integer.parseInt(nbVic));
                        
                        success = true;

                    } else {
                        //pas bon mot de passe
                        LogInError.setText("Mot de passe incorrect");
                        LogInError.setVisible(true);
                    }
                } else {
                    //Le compte n'est pas créé
                    LogInError.setText("Le compte n'est pas créé, inscrivez-vous");
                    LogInError.setVisible(true);
                }
            }

        } catch (SQLException e) {
            /* Gérer les éventuelles erreurs ici */

        } finally {
            if (connexion != null) {
                try {
                    /* Fermeture de la connexion */
                    connexion.close();
                } catch (SQLException ignore) {
                    /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
                }
            }
        }

        if (success) {
            Main.mainContainer.loadScreen(Main.screenMenuID, Main.screenMenuFile);
            myController.setScreen(Main.screenMenuID);
        }
    }

    /**
     * Method which initialize the component. Set the objectifLabel to his
     * value, Add the style sheet to the background, Play music if wanted.
     *
     * @param url the current url
     * @param rb the ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image(Main.cheminImg + "loginImg.png");
        loginImg.setImage(img);
        Image img2 = new Image(Main.cheminImg + "title.png");
        titleImg.setImage(img2);
        VBoxBackground.getStylesheets().add("css/" + skinMode + ".css");
    }

}
