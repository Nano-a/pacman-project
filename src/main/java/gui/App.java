package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import config.MazeConfig;
import model.MazeState;


public class App extends Application {
    private Scene menuScene;
    @Override
    public void start(Stage primaryStage) {
        var root = new Pane();
        var gameScene = new Scene(root);
        var pacmanController = new PacmanController();
        gameScene.setOnKeyPressed(pacmanController::keyPressedHandler);
        gameScene.setOnKeyReleased(pacmanController::keyReleasedHandler);
        var maze = new MazeState(MazeConfig.makeExample1());
        var gameView = new GameView(maze, root, 100.0);

        Menu menu = new Menu();
        menuScene = new Scene(menu, 875, 600);

        ParametresMenu parametresMenu = new ParametresMenu(menu);

        menu.getJouerButton().setOnAction(e->{
            primaryStage.setScene(gameScene);
            primaryStage.show();
            gameView.animate();
        } );

        menu.getParametresButton().setOnAction(e -> {
            primaryStage.setScene(new Scene(parametresMenu, 875, 600)); // Affiche la page des paramètres
            primaryStage.show();
        });

        menu.getQuitterButton().setOnAction(e-> System.exit(0));

        Image icon = new Image("file:src/main/resources/inky.gif");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Pacman");
        primaryStage.show();
        
    }
}
