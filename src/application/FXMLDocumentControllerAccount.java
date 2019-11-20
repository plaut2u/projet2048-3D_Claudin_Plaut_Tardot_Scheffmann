/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Gregoire
 */
public class FXMLDocumentControllerAccount implements Initializable, ParametresApplication {
    
    private String tempPseudo;
    private String tempPassword;

    @FXML
    private Button okButtonAccount;

    @FXML
    private Button cancelButtonAccount;

    @FXML
    private Label errorPseudo;

    @FXML
    private Label errorPassword;

    @FXML
    private Label pseudoLabel;

    @FXML
    private Label nombreVictoires;

    @FXML
    private Label meilleurScore;

    @FXML
    private TextField newPseudo;

    @FXML
    private TextField confirmNewPseudo;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmNewPassword;

    @FXML
    private void ok(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        boolean stop = false;
        boolean noError = true;

        if (newPseudo.getText().equals(confirmNewPseudo.getText()) && !newPseudo.getText().equals("")) {
            Main.joueur.setPseudo(newPseudo.getText());
            noError = true;
        } else if (!newPseudo.getText().equals(confirmNewPseudo.getText())) {
            errorPseudo.setText("Les pseudos ne sont pas les mêmes, réessayer");
            errorPseudo.setVisible(true);
            noError = false;
            stop = true;
        }

        if (!stop) {
            if (oldPassword.getText().equals(Main.joueur.getPassword())) {
                if (newPassword.getText().equals(confirmNewPassword.getText()) && !newPassword.getText().equals("")) {
                    Main.joueur.setPassword(newPassword.getText());
                    noError = true;
                } else if (!newPassword.getText().equals(confirmNewPassword.getText())) {
                    errorPassword.setText("Les mots de passes ne sont pas les mêmes, réessayer");
                    errorPassword.setVisible(true);
                    noError = false;
                }
            } else if (oldPassword.getText().equals("")) {
                noError = true;
            } else {
                errorPassword.setText("Mauvais mot de passe, réessayer");
                errorPassword.setVisible(true);
                noError = false;
            }
        }

        if (noError) {
            Stage stage = (Stage) okButtonAccount.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    private void cancel(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();

        Main.joueur.setPseudo(tempPseudo);
        Main.joueur.setPassword(tempPassword);

        Stage stage = (Stage) cancelButtonAccount.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void disc(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Stage stage = (Stage) cancelButtonAccount.getScene().getWindow();
        stage.close();
        
//        Main.mainContainer.loadScreen(Main.screenLoginID, Main.screenLoginFile);
//        myController.setScreen(Main.screenLoginID);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pseudoLabel.setText(Main.joueur.getPseudo());
        meilleurScore.setText(Integer.toString(Main.joueur.getMeilleurScore()));
        nombreVictoires.setText(Integer.toString(Main.joueur.getNombreVictoires()));
        tempPseudo = Main.joueur.getPseudo();
        tempPassword = Main.joueur.getPassword();
    }

}
