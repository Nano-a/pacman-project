package gui;

import config.MazeConfig;
import geometry.IntCoordinates;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import model.MazeState;

import java.util.ArrayList;
import java.util.List;

public class GameView {
    // class parameters
    private final MazeState maze;
    private final Pane gameRoot; // main node of the game
    // private final Label scoreLabel; // Label to display th

    private final List<GraphicsUpdater> graphicsUpdaters;

    private void addGraphics(GraphicsUpdater updater) {
        gameRoot.getChildren().add(updater.getNode());
        graphicsUpdaters.add(updater);
    }

    /**
     * @param maze  le "modèle" de cette vue (le labyrinthe et tout ce qui s'y trouve)
     * @param root  le nœud racine dans la scène JavaFX dans lequel le jeu sera affiché
     * @param scale le nombre de pixels représentant une unité du labyrinthe
     */
    public GameView(MazeState maze, Pane root, double scale) {
        this.maze = maze;
        this.gameRoot = root;
        // Create and configure the score label
       /* scoreLabel = new Label();
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        scoreLabel.setLayoutY(10); // Adjust the Y position for the label
        gameRoot.getChildren().add(scoreLabel); // Add the label to the game root */
        // pixels per cell
        root.setMinWidth(maze.getWidth() * scale);
        root.setMinHeight(maze.getHeight() * scale);
        root.setStyle("-fx-background-color: #000000");
        var critterFactory = new CritterGraphicsFactory(scale);
        var cellFactory = new CellGraphicsFactory(scale);
        graphicsUpdaters = new ArrayList<>();

        // Add critter and cell graphics ; the new maze
        for (var critter : maze.getCritters()) {
            addGraphics(critterFactory.makeGraphics(critter));
        }
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                IntCoordinates pos = new IntCoordinates(x, y);
                GraphicsUpdater updater = cellFactory.makeGraphics(maze, pos);
                addGraphics(updater);
            }
        }

        // Call drawMaze here or create a new method to handle it
        // Group mazeGroup = drawMaze();
       // gameRoot.getChildren().add(mazeGroup);

    }

    private Group drawMaze() { // to handle
        Group mazeGroup = new Group();
        CellGraphicsFactory graphicsFactory = new CellGraphicsFactory(20);

        MazeConfig mazeConfig = MazeConfig.makeExample1();
        MazeState mazeState = new MazeState(mazeConfig);

        for (int y = 0; y < mazeState.getHeight(); y++) {
            for (int x = 0; x < mazeState.getWidth(); x++) {
                IntCoordinates pos = new IntCoordinates(x, y);
                GraphicsUpdater graphicsUpdater = graphicsFactory.makeGraphics(mazeState, pos);
                mazeGroup.getChildren().add(graphicsUpdater.getNode());
            }
        }
        return mazeGroup;
    }


    public void animate() {
        new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                var deltaT = now - last;
                maze.update(deltaT);
                for (var updater : graphicsUpdaters) {
                    updater.update();
                }
               // updateScoreLabel(); // Update the score label
                last = now;
            }
        }.start();
    }

    /*private void updateScoreLabel() {
        scoreLabel.setText("Score: " + maze.getScore()); // Update the score label text
    }*/

}
