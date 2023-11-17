package gui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class ParametresMenu extends StackPane {
    private Button retour;
    private Button son;
    private Button langue;
    private Menu menu;



    public ParametresMenu(Menu menu){
        this.menu = menu;
        Image backgroundImage = new Image (Objects.requireNonNull(getClass().getResourceAsStream("/menu/WallPaper.png")));
        ImageView backgroundImageView = new ImageView(backgroundImage);

        VBox buttonContainer = new VBox();
        retour = new Button("retour");
        son = new Button("son");
        langue = new Button("langue");

        // Ajout des boutons au VBox
        buttonContainer.getChildren().addAll(retour, son, langue);

        // Configuration de l'espacement vertical entre les boutons
        buttonContainer.setSpacing(15);

        // Aligner le VBox au centre du StackPane, à la fois horizontalement et verticalement
        StackPane.setAlignment(buttonContainer, Pos.CENTER);

        // Ajouter des marges pour ajuster l'espacement par rapport à l'image de fond
        StackPane.setMargin(buttonContainer, new Insets(250, 400, 0, 400));

        // Ajout de l'image de fond, du VBox contenant les boutons dans le StackPane
        getChildren().addAll(backgroundImageView, buttonContainer);

        retour.setOnAction(e -> {

            //Revenir à la scène du menu principal
            //getScene().setRoot(menu.getScene().getRoot());
            getScene().setRoot(menu);
            // Masquer le menu des paramètres
   /* setVisible(false);

    // Afficher le menu principal
    menu.setVisible(true); */
            //METTRE L'INITIALISATION DU MENU DANS UNE FONCTION À RAPPELER ICI
    // Supprimer le menu des paramètres de la scène
    getChildren().remove(buttonContainer);

    // Ajouter le menu principal à la scène
    getChildren().addAll(menu.getJouerButton(), 
    menu.getParametresButton(), menu.getQuitterButton()); 
    
           
          
        });
    }

    
}
