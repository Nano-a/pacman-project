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
    
        public void resetGame() {
        // Reset score, level, and any other game-related variables to initial values
        model.startNewGame();
        startTimer();
    }

    public void startTimer() {
        this.timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        update(model.getCurrentDirection());
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long) (1000.0 / speed);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }
    
      private void update(Direction direction) {
        this.model.step(direction); // le model du jeu est mis à jour
        this.gameView.animate(model); // animer la vue
        if (this.model.getLives() == 3) {
            try {
                this.livesImage.setVisible(true);
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ThreeHearts.gif")));
                this.livesImage.setImage(image);
            } catch (NullPointerException e) {
                System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
            }
        }
        if (this.model.getLives() == 2) {
            try {
                this.livesImage.setVisible(true);
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twoHearts.gif")));
                this.livesImage.setImage(image);

            } catch (NullPointerException e) {
                System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
            }
        }
        if (this.model.getLives() == 1) {
            try {
                this.livesImage.setVisible(true);
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/oneHeart.gif")));
                this.livesImage.setImage(image);

            } catch (NullPointerException e) {
                System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
            }
        }
        if (this.model.getLives() == 0) {
            try {
                this.livesImage.setVisible(false);

            } catch (NullPointerException e) {
                System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
            }
        }

        if (SettingsLanguageController.isFrench) {
            this.score.setText(String.format("Score: %d", this.model.getScore()));
            this.level.setText(String.format("Niveau: %d", this.model.getLevel()));
        } else {
            this.score.setText(String.format("Score: %d", this.model.getScore()));
            this.level.setText(String.format("Level: %d", this.model.getLevel()));
        }

        if (MazeState.isGameOver()) {
            this.timer.cancel();
            this.paused = true;
            SoundController.sound("fail");
            SoundController.musique("JungleTheme");
            manager.switchScene(FxmlFiles.LOSE);
        }
        if (MazeState.isYouWon()) {
            this.timer.cancel();
            this.paused = true;
            SoundController.musique("JungleTheme");
            manager.switchScene(FxmlFiles.WIN);
        }
        if (MazeState.isPaused()) {
            this.timer.cancel();
            this.paused = true;
            manager.switchScene(FxmlFiles.PAUSE);
        }
        
        if (MazeState.isGhostEatingMode()) {
            ghostEatingModeCounter--;
        }
        if (ghostEatingModeCounter == 0 && MazeState.isGhostEatingMode()) {
            MazeState.setGhostEatingMode(false);
        }
    }
    
    @Override
    public void handle(KeyEvent keyEvent) {
        boolean keyRecognized = true;
        KeyCode code = keyEvent.getCode();
        Direction direction = Direction.NONE;
        if (code == KeyCode.LEFT) {
            direction = Direction.WEST;
        } else if (code == KeyCode.RIGHT) {
            direction = Direction.EAST;
        } else if (code == KeyCode.UP) {
            direction = Direction.NORTH;
        } else if (code == KeyCode.DOWN) {
            direction = Direction.SOUTH;
        } else if (code == KeyCode.ESCAPE) {
            SoundController.musique("JungleTheme");
            manager.switchScene(FxmlFiles.PAUSE);
            pause();
        } else {
            keyRecognized = false;
        }
        if (keyRecognized) {
            keyEvent.consume();
            model.setCurrentDirection(direction);
        }
    }

    public void setUpKeyEvents() {
        Scene scene = this.score.getScene(); // Get the scene where the labels are present
        // Add event handler for key presses
        scene.setOnKeyPressed(this);
    }
    
    public void pause() {
        this.timer.cancel();
        this.paused = true;
    }

    public void resumeGame() {
        if (paused) {
            startTimer(); // Relancer le timer
            paused = false;
        }
    }


    public double getBoardWidth() {
        return GameView.CELL_WIDTH * this.gameView.getColumnCount();
    }

    public double getBoardHeight() {
        return GameView.CELL_WIDTH * this.gameView.getRowCount();
    }

    public static void setGhostEatingModeCounter() {

        SoundController.sound("ghostSprut");
        ghostEatingModeCounter = 25;
    }

    public static int getGhostEatingModeCounter() {
        return ghostEatingModeCounter;
    }

    public static String getLevelFile(int x) {
        return levelFiles[x];
    }

    public boolean getPaused() {
        return paused;
    }
    

}