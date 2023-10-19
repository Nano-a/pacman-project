

package gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;


public class Menu extends StackPane {
    /*
     * On a apporté une modification à la classe "menu"
     *  en faisant en sorte qu'elle étende la classe
     *  StackPane plutôt que VBox. Cette décision découle 
     * de notre besoin de superposer des boutons sur une 
     * image de fond. Pour clarifier, voici la distinction
     *  entre StackPane et VBox :
     * 
     * // petite explication ( commentaire à supprimer ) :
      StackPane : C'est un conteneur de mise en page dans JavaFX 
    qui empile ses noeuds enfants les uns par-dessus les autres.
     Dans un StackPane, les noeuds enfants se superposent, 
     et le dernier noeud ajouté est affiché au-dessus des autres.
 
      VBox : En revanche, VBox est un autre conteneur
     de mise en page JavaFX, mais il adopte une mise en 
     page verticale. Dans un VBox, les noeuds enfants sont 
     disposés verticalement les uns sous les autres, avec
      un arrangement ordonné de haut en bas.
     
     */

    private Button jouer;
    private Button parametres;
    private Button quitter;

    public Menu() {
        //chargement de l'image de fond
        Image backgroundImage = new Image(getClass().getResourceAsStream("/menuPrincipal.png"));
        ImageView backgroundImageView = new ImageView(backgroundImage);


        // Création d'un VBox pour organiser les boutons
        VBox buttonContainer = new VBox();
        jouer = new Button("Jouer");
        parametres = new Button("Paramètres");
        quitter = new Button("Quitter");

          //images des bouttons :

        Image parametresImage = new Image(getClass().getResourceAsStream("/parametres.png"));
        ImageView parametresImageView = new ImageView(parametresImage);
        parametresImageView.setFitWidth(50); 
        parametresImageView.setFitHeight(50);

        parametres.setGraphic(parametresImageView);


         Image jouerImage = new Image(getClass().getResourceAsStream("/jouer.png"));
        ImageView jouerImageView = new ImageView(jouerImage);
        jouerImageView.setFitWidth(50); 
        jouerImageView.setFitHeight(50);

        jouer.setGraphic(jouerImageView);


         Image quitterImage = new Image(getClass().getResourceAsStream("/quitter.png"));
        ImageView quitterImageView = new ImageView(quitterImage);
        quitterImageView.setFitWidth(50); 
        quitterImageView.setFitHeight(50);

        quitter.setGraphic(quitterImageView);

      

        // Ajout des boutons au VBox
        buttonContainer.getChildren().addAll(jouer, parametres, quitter);

        // Configuration de l'espacement vertical entre les boutons
        buttonContainer.setSpacing(10);

        // Aligner le VBox au centre du StackPane, à la fois horizontalement et verticalement
        StackPane.setAlignment(buttonContainer, Pos.CENTER);

        // Ajouter des marges pour ajuster l'espacement par rapport à l'image de fond
        StackPane.setMargin(buttonContainer, new Insets(300));

        // Ajout de l'image de fond, du VBox contenant les boutons dans le StackPane
        getChildren().addAll(backgroundImageView, buttonContainer);
    }

    public Button getJouerButton() {
        return jouer;
    }

    public Button getParametresButton(){
        return parametres;
    }

    public Button getQuitterButton() {
        return quitter;
    }
} 





