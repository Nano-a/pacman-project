package gui;

import enums.FxmlFiles;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;
import enums.Difficulty;
import javafx.util.Duration;

/**
 * Contrôleur de la page de choix de difficulté
 * accessible depuis le menu principal/pause/win/lose
 * permet de choisir la difficulté de la partie
 * et de commencer une nouvelle partie
 */

public class NewGamePageController extends CommonController {
    @FXML  Button Start,easy,medium,hard,Return;
    ArrayList<Button> buttons; // Initialisation de la liste des boutons
    Difficulty difficulty;

    @FXML
    private void initialize() {
        difficulty = Difficulty.NORMAL; // difficulté par défaut est NORMAL (5.0)
        buttons = new ArrayList<>(); // Initialisation de la liste des boutons
        buttons.add(easy); buttons.add(medium); buttons.add(hard);

    }


    public void updateLanguage(boolean isFrench) {
        if (isFrench) {
            // Mettez à jour les textes en français
            easy.setText("FACILE");
            medium.setText("MOYEN");
            hard.setText("DIFFICILE");
            Start.setText("COMMENCER");
            Return.setText("RETOUR");
        } else {
            // Mettez à jour les textes en anglais
            easy.setText("EASY");
            medium.setText("MEDIUM");
            hard.setText("HARD");
            Start.setText("START");
            Return.setText("RETURN");
        }
    }

    @FXML
    private void setEasy() {
        setDifficulty(easy, Difficulty.SLOW); // on change la difficulté
        Controller.setSpeed(Difficulty.SLOW.getSpeedScale()); // on passe la valeur du controller du jeu
        SoundController.click();
    }

    @FXML
        private void setMedium() {
        setDifficulty(medium, Difficulty.NORMAL);
        Controller.setSpeed(Difficulty.NORMAL.getSpeedScale());
        SoundController.click();
    }

    @FXML
    private void setHard() {
        setDifficulty(hard, Difficulty.FAST);
        Controller.setSpeed(Difficulty.FAST.getSpeedScale());
        SoundController.click();
    }

    @FXML
    public void startGame() {
        SoundController.click();
        // show a snippest of the story
        manager.switchScene(FxmlFiles.STORY);
        PauseTransition delay = new PauseTransition(Duration.millis(3000)); // On affiche temporairement le gif JunglePacman
        delay.setOnFinished(event -> {
            manager.switchScene(FxmlFiles.GAME);
            SoundController.musique(SettingsAudioController.themePlaySong);
            Controller gameController = (Controller) manager.getController(FxmlFiles.GAME);
            if (gameController != null) {
                gameController.resetGame();
                gameController.setUpKeyEvents();
                 }
        });
        delay.play();
    }

    // bouton retour
    @FXML
    private void switchToMenu() {
        SoundController.click();
        manager.switchScene(FxmlFiles.MENU); }

    private void setDifficulty(Button button, Difficulty difficulty) {
        buttons.forEach(b -> b.setId("menuButton"));
        button.setId("difficulty-selected");
        this.difficulty = difficulty;
    }

}
