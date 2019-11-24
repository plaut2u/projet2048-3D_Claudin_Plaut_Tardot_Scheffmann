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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Enregistreur;

/**
 * Class FXMLDocumentControllerAccount to manage Accountcene.fxml
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
    private CheckBox resetCheckBox;
    
    /**
    * Method triggered by a button to apply changes and close the window
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void ok(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        boolean stop = false;
        boolean noError = true;

        if (newPseudo.getText().equals(confirmNewPseudo.getText()) && !newPseudo.getText().equals("")) { //si le nouveau pseudo voulu est égal a la confirmation du nouveau pseudo et non null
            Main.joueur.setPseudo(newPseudo.getText()); //alors on set le pseudo de ce joeur a ça
            noError = true;
        } else if (!newPseudo.getText().equals(confirmNewPseudo.getText())) { //sinon si les deux pseudos ne sont pas égaux
            errorPseudo.setText("Les pseudos ne sont pas les mêmes, réessayer"); //on affiche un message d'erreur
            errorPseudo.setVisible(true);
            noError = false;
            stop = true;
        }

        if (!stop) { //si pas d'erreur au dessus
            if (oldPassword.getText().equals(Main.joueur.getPassword())) { //si l'ancien password corresponds a ce qu'à rentré le joueur dans le champ de texte
                if (newPassword.getText().equals(confirmNewPassword.getText()) && !newPassword.getText().equals("")) { //alors si la confirmation et le nouveau mot de passe voulu sont égals et non nuls
                    Main.joueur.setPassword(newPassword.getText()); //on update le mot de passe de ce joueur
                    noError = true;
                } else if (!newPassword.getText().equals(confirmNewPassword.getText())) { //sinon si les deux mots de passes sont pas les memes
                    errorPassword.setText("Les mots de passes ne sont pas les mêmes, réessayer"); //message d'erreur
                    errorPassword.setVisible(true);
                    noError = false;
                }
            } else if (oldPassword.getText().equals("")) { //sinon si le champ de l'ancien mot de passe est null (donc que le joueur ne veut pas changer son mot de passe)
                noError = true; //il n'y a pas d'erreur
            } else { //sinon c'est qu'il s'est trompé
                errorPassword.setText("Mauvais mot de passe, réessayer"); //message d'erreur
                errorPassword.setVisible(true);
                noError = false;
            }
        }
        
        if (noError) { //si pas d'erreur, alors on peut fermer la fenetre
            Stage stage = (Stage) okButtonAccount.getScene().getWindow();
            stage.close();
        }
        
        if(resetCheckBox.isSelected()){ //si il veut supprmer la derniere sauvegarde
            Enregistreur.reset(); // on la supprime (on la reset)
        }

        

    }
    
    /**
    * Method triggered by a button to cancel changed
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void cancel(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();

        Main.joueur.setPseudo(tempPseudo);
        Main.joueur.setPassword(tempPassword);

        Stage stage = (Stage) cancelButtonAccount.getScene().getWindow();
        stage.close();
    }
    
    /**
    * Method triggered by a button to disconnect and go back to Login Menu
    * @param MouseEvent event
    * @return void
    * 
    */
    @FXML
    private void disc(MouseEvent event) throws IOException {
        Sound buttonClicked = new Sound("sound\\" + "button.wav");
        buttonClicked.start();
        
        Main.mainContainer.loadScreen(Main.screenLoginID, Main.screenLoginFile);
        Main.mainContainer.setScreen(Main.screenLoginID);
        
        Stage stage = (Stage) cancelButtonAccount.getScene().getWindow();
        stage.close();
    }
    
    /**
    * Method which initialize the component.
    * Set the player's pseudo,
    * Set his best score,
    * Set his number of victory,
    * Set the resetCheckBox selected to false.
    * 
    * @param url the current url
    * @param rb the resourceBundle
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pseudoLabel.setText(Main.joueur.getPseudo());
        meilleurScore.setText(Integer.toString(Main.joueur.getMeilleurScore()));
        nombreVictoires.setText(Integer.toString(Main.joueur.getNombreVictoires()));
        tempPseudo = Main.joueur.getPseudo();
        tempPassword = Main.joueur.getPassword();
        resetCheckBox.setSelected(false);
    }

}
