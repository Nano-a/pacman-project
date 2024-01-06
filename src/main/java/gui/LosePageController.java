package gui;

import enums.FxmlFiles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Contrôleur de la page de défaite ; accessible une fois perdu c'est a dire 0 vies restantes
 * elle permet de refaire une  partie,
 * retourner au menu pour modifier le son ou la langue
 * ou bien de quitter entierement le jeu
 */
public class LosePageController extends CommonController {

    @FXML
    Button Restart, Menu, Quit;

    @FXML
    public void backToMenu() {
        SoundController.click();
        manager.switchScene(FxmlFiles.MENU);
    }
    @FXML
    public void restartGame() {
        SoundController.click();
        manager.switchScene(FxmlFiles.START_GAME);
    }
    @FXML
    public void quitGame() {
        SoundController.click();
        System.exit(0);
    }

    @FXML
    public void updateLanguage( boolean isFrench) {
        if(isFrench) {
            Restart.setText("RECOMMENCER");
            Menu.setText("MENU");
            Quit.setText("QUITTER");
        } else {
            Restart.setText("RESTART");
            Menu.setText("MENU");
            Quit.setText("QUIT");
        }
    }
}
