/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author plaut2u
 */
public class Fenetre extends Main {

    public Scene construitScene() {
        GridPane grid = new GridPane();
        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("Menu1");
        Menu menu2 = new Menu("Menu2");
        menuBar.getMenus().addAll(menu1, menu2);
        MenuItem menuItem1 = new MenuItem("MenuItem1");
        menu1.getItems().addAll(menuItem1, new MenuItem("MenuItem2"));
        Label label = new Label("Label: ");
        TextField textField = new TextField();
        textField.setPromptText("champ texte");
        Button button1 = new Button("Bouton1");
        button1.setText("Bouton");
        //ImageView img = new ImageView(new Image("tile2048.jpg"));
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(label, textField, button1);
        Text text = new Text("Mon premier texte");
        Separator separator = new Separator();
        GridPane.setConstraints(menuBar, 0, 0); // force le positionnement du menu dans GridPane
        grid.getChildren().add(menuBar); // ajout du menu
        GridPane.setConstraints(hBox, 0, 1);
        grid.getChildren().add(hBox);
        GridPane.setConstraints(separator, 0, 2);
        grid.getChildren().add(separator);
        GridPane.setConstraints(text, 0, 3);
        grid.getChildren().add(text);
        StackPane root = new StackPane();
        root.getChildren().addAll(grid);
        Scene scene = new Scene(root, 500, 300);
// ou : Scene scene= new Scene(root);
        return scene;
    }

    @Override
    public void start(Stage primaryStage) { 
        primaryStage.setTitle("Ma première fenêtre");
        primaryStage.setScene(construitScene());
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) { // point d’entrée du programme
        launch(args); // appel à une méthode de la classe Application
    }
}
