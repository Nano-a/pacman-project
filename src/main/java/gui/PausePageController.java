package gui;

import enums.FxmlFiles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;


/**
 * Contrôleur de la page de pause ; accessible en appuyant sur la touche Echap durant le jeu
 * elle permet de reprendre le jeu, refaire la partie,
 * retourner au menu pour modifier le son ou la langue
 * ou bien de quitter entierement le jeu
 */
public class PausePageController extends CommonController {

    @FXML
    Button Resume, Restart, Menu, Quit;


    public void updateLanguage(boolean isFrench) {
        if(isFrench) {
            Resume.setText("REPRENDRE");
            Restart.setText("RECOMMENCER");
            Menu.setText("MENU");
            Quit.setText("QUITTER");
        } else {
            Resume.setText("RESUME");
            Restart.setText("RESTART");
            Menu.setText("MENU");
            Quit.setText("QUIT");
        }
    }
    @FXML
    public void resumeGame() {

            SoundController.click();
            // préférable que la music ne se reprend pas à 0 mais tant qu'on joue une nouvelle music c'est normal
            SoundController.musique(SettingsAudioController.themePlaySong);
            // Obtenir une référence au contrôleur principal du jeu
            Controller gameController = (Controller) manager.getController(FxmlFiles.GAME);
            if (gameController != null) {
                gameController.resumeGame(); // Appeler la méthode pour reprendre le jeu
            }
            manager.switchScene(FxmlFiles.GAME); // Revenir à la scène du jeu
        }

    @FXML
    public void backToMenu() {
        SoundController.click();
        manager.switchScene(FxmlFiles.MENU);
    }
    @FXML
    public void restartGame() {
        // on se dirige vers la page de difficulté pour commencer une nouvelle partie
        SoundController.click();
        manager.switchScene(FxmlFiles.START_GAME);
    }

    @FXML
    public void quitGame() {
        SoundController.click();
        System.exit(0);
    }
}
