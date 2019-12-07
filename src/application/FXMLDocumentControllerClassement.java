/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import static application.ParametresApplication.DATABASE;
import static application.ParametresApplication.HOST;
import static application.ParametresApplication.PASSWORD;
import static application.ParametresApplication.PORT;
import static application.ParametresApplication.USER;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Joueur;

/**
 * Class FXMLDocumentControllerClassement to manage ClassementScene.fxml
 *
 * @author Gregoire
 */
public class FXMLDocumentControllerClassement implements Initializable, ParametresApplication {

    @FXML
    private Button okButton;

    @FXML
    private TableView tableClassement;

    /**
     * Method triggered by a button to activate or desactivate the music
     *
     * @param MouseEvent event
     * @return void
     *
     */
    @FXML
    private void ok(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();

        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method which initialize the component.
     *
     * @param url the current url
     * @param rb the ResourceBundle
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        TableColumn<String, Joueur> column1 = new TableColumn<>("Pseudo");
        column1.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        column1.setPrefWidth(160);
        
        TableColumn<String, Joueur> column2 = new TableColumn<>("Meilleur Score");
        column2.setCellValueFactory(new PropertyValueFactory<>("meilleurScore"));
        column2.setPrefWidth(160);
        
        TableColumn<String, Joueur> column3 = new TableColumn<>("Nombre de victoires");
        column3.setCellValueFactory(new PropertyValueFactory<>("nbVictoires"));
        column3.setPrefWidth(160);
        
        tableClassement.getColumns().add(column1);
        tableClassement.getColumns().add(column2);
        tableClassement.getColumns().add(column3);
        
        
        
        //Ouverture BDD
        /* Connexion à la base de données */
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            /* Gérer les éventuelles erreurs ici. */
        }

        String url2 = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(url2, USER, PASSWORD);
            Statement statement = connexion.createStatement();
            
            //RECUPERATION DONNEES
            String requete = "SELECT Pseudo, BestScore, NbVictory, Password FROM Projet2048";
            ResultSet rs = statement.executeQuery(requete);
            while (rs.next()) {
                Joueur j = new Joueur(rs.getString("Pseudo"),Integer.valueOf(rs.getString("BestScore")),Integer.valueOf(rs.getString("NbVictory")));
                tableClassement.getItems().add(j);
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
    }

}
