package gui;

import config.MazeConfig;
import enums.Difficulty;
import enums.FxmlFiles;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
import enums.Direction;
import model.MazeState;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Controller extends CommonController implements EventHandler<KeyEvent> {
    private static double speed = Difficulty.NORMAL.getSpeedScale();

    public static void setSpeed(double sp) {
        speed = sp;
    }

    @FXML
    public ImageView livesImage;
    @FXML
    public Label score;
    @FXML
    public Label level;
    @FXML
    private GameView gameView;
    private MazeState model;
    private static final String[] levelFiles = {"src/main/resources/levels/level1.txt", "src/main/resources/levels/level2.txt", "src/main/resources/levels/level3.txt"};
    private Timer timer;
    private static int ghostEatingModeCounter;
    private boolean paused;

    public Controller() {
        this.paused = false;
    }

    /**
     * Initialize and update the model and view from the first txt file and starts the timer.
     */
    public void initialize() {
        gameView.setRowCount(21);
        gameView.setColumnCount(19);
        String file = getLevelFile(0);
        MazeConfig config = new MazeConfig(file);
        this.model = new MazeState(config);
        this.model.startNewGame();
        this.update(Direction.NONE);
        ghostEatingModeCounter = 25;
    }

}