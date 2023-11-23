package gui;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import config.MazeConfig;
import model.MazeState;
import java.util.Objects;


public class App extends Application {
    // Attributs et champs de la classe
    public static Rectangle2D screen = Screen.getPrimary().getBounds(); // Les dimensions de l'écran du joueur ( utile si on a envie de mettre le jeu en plein écran )\
    private static Stage primaryStage = null;
    Scene menuScene ; // La scène du menu

    //private Scenes current ; // Qui permet d'afficher les scènes dans le stage et de switcher entre les pages
    // Changer l'implementation du menu au lieu de Vbox on utilisera des fichiers FXML

    @Override
    public void start(Stage primaryStage) {
        App.primaryStage = primaryStage; // On initialise le stage

        // Les paramètres de la fenêtre
        primaryStage.getIcons().clear(); // On enlève les icônes par défaut
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Logo.png")))); // On ajoute notre icône
        // A ne pas oublier Objects.requireNonNull() pour éviter les NullPointerException
        primaryStage.setTitle("JUNGLE PACMAN"); // On donne un titre à la fenêtre
        primaryStage.setResizable(false);

        // La scène
        Canvas zoneDesign = new Canvas(875, 600); // zone de dessin rectangulaire sur laquelle on peut mettre des images, boutons ...
        // Dimension du pacMan Classique 875*600
        Scene gameScene = new Scene(new AnchorPane(zoneDesign));
        /* AnchorPane est un type spécifique de Pane qui utilise un système de positionnement basé sur les ancres (anchors).
            Il permet de positionner les éléments enfants. */


        // Load and display an animated GIF qui sera temporairement affiché avant le début du jeu
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/JunglePacman.gif")))); //GIF animation d'entrée dans le jeu
        AnchorPane.setTopAnchor(imageView, 0.0);
        AnchorPane.setLeftAnchor(imageView, 0.0);
        AnchorPane.setRightAnchor(imageView, 0.0);
        AnchorPane.setBottomAnchor(imageView, 0.0);
        AnchorPane anchorPane = (AnchorPane) gameScene.getRoot();
        anchorPane.getChildren().add(imageView);


        primaryStage.setScene(gameScene); // On ajoute la scène au stage
        primaryStage.sizeToScene(); // On adapte la taille de la fenêtre à la scène
        primaryStage.setAlwaysOnTop(false); // On met la fenêtre au premier plan
        primaryStage.requestFocus();// On met le focus sur la fenêtre
        primaryStage.show(); // On affiche la fenêtre

        PauseTransition delay = new PauseTransition(Duration.millis(3000)); // On qffiche temporairement le gif JunglePacman
        delay.setOnFinished(event -> {

            Menu menu = new Menu();
            menuScene = new Scene(menu, 875, 600);
            primaryStage.setScene(menuScene);
            primaryStage.show();
            ParametresMenu parametresMenu = new ParametresMenu(menu);
            menu.getJouerButton().setOnAction(e->{
                var pacmanController = new PacmanController(); //Pour controller le pacman avec les touches du clavier
                gameScene.setOnKeyPressed(pacmanController::keyPressedHandler);
//                gameScene.setOnKeyReleased(pacmanController::keyReleasedHandler);
                ((AnchorPane) gameScene.getRoot()).getChildren().removeAll(imageView);

                // new Maze
                var maze = new MazeState(MazeConfig.makeExample1());
                var gameView = new GameView(maze,(Pane) gameScene.getRoot(), 24.0); // Don't touch the scale


                primaryStage.setScene(gameScene);
                primaryStage.show();
                gameView.animate();
            } );
            menu.getParametresButton().setOnAction(e -> {
                primaryStage.setScene(new Scene(parametresMenu, 875, 600)); // Affiche la page des paramètres
                primaryStage.show();
            });
            menu.getQuitterButton().setOnAction(e-> System.exit(0));
        });
        delay.play();




        
    }
}
