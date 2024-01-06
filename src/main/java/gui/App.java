package gui;

import enums.FxmlFiles;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Objects;

public class App extends Application {
    // Attributs et champs de la classe
    public static Rectangle2D screen = Screen.getPrimary().getBounds(); // Les dimensions de l'écran du joueur ( utile si on a envie de mettre le jeu en plein écran )
     static Stage primaryStage = null; // Le stage de l'application ; fenetre principale equivalent du JFrame en swing
     public static Scene gameScene ;
     public static ImageView imageView ; // pour le gif d'entrée comme une page de LOADING
    SceneManager controller ;

    // Changer l'implementation du menu au lieu de Vbox on utilisera des fichiers FXML

    @Override
    public void start(Stage primaryStage) {

        App.primaryStage = primaryStage; // On initialise le stage
        // Les paramètres de la fenêtre
        primaryStage.getIcons().clear(); // On enlève les icônes par défaut
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Logo.png")))); // On ajoute notre icône
        // A ne pas oublier Objects.requireNonNull() pour éviter les NullPointerException
        primaryStage.setTitle("JUNGLE PACMAN"); // On donne un titre à la fenêtre
        primaryStage.setResizable(true);
        primaryStage.setOnCloseRequest(event -> {
            // on clique sur le X de la fenetre pour quitter le jeu complètement pas juste fermer la fenetre
            System.exit(0); // Exiting the application
        });

        // La scène
        Canvas zoneDesign = new Canvas(875, 600); // zone de dessin rectangulaire sur laquelle on peut mettre des images, boutons ...
        // Dimension du pacMan Classique 875*600

        Scene gameScene = new Scene(new AnchorPane(zoneDesign));
        this.gameScene = gameScene ;
        /* AnchorPane est un type spécifique de Pane qui utilise un système de positionnement basé sur les ancres (anchors).
            Il permet de positionner les éléments enfants. */

        // Music of the menu (Jungle theme )
        SoundController.musique("JungleTheme");

        // Load and display an animated GIF qui sera temporairement affiché avant le début du jeu
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/JunglePacman.gif")))); //GIF animation d'entrée dans le jeu
       // Require non null pour éviter les NullPointerException à la place d'un try catch
        this.imageView = imageView ;
        AnchorPane.setTopAnchor(imageView, 0.0);
        AnchorPane.setLeftAnchor(imageView, 0.0);
        AnchorPane.setRightAnchor(imageView, 0.0);
        AnchorPane.setBottomAnchor(imageView, 0.0);
        AnchorPane anchorPane = (AnchorPane) gameScene.getRoot();
        anchorPane.getChildren().add(imageView);

        primaryStage.setScene(gameScene); // On ajoute la scène au stage
        primaryStage.sizeToScene(); // On adapte la taille de la fenêtre à la scène
        primaryStage.setAlwaysOnTop(false);
        primaryStage.requestFocus();// On met le focus sur la fenêtre
        primaryStage.show(); // On affiche la fenêtre

        // On attend 3 secondes avant de passer au Menu
        PauseTransition delay = new PauseTransition(Duration.millis(3000)); // On qffiche temporairement le gif JunglePacman
        delay.setOnFinished(event -> {
            //Appel vers le premier fichier fxml : Menu.fxml
            this.controller = new SceneManager(primaryStage); // Cette classe est pour la gestion des scènes et basculer entre elles
            this.controller.callInitialScene(FxmlFiles.MENU); // première scène affichée est le menu
        });
        delay.play();
    }
}
