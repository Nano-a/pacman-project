package gui;

import enums.FxmlFiles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller pour la page victoire ; Une fois 3 Niveaux finis avec succès, le joueur gagne la partie
 */

public class WinPageController extends CommonController {

    @FXML
    Button NewGame , Menu , Quit;

    // Changer la langue des boutons
    public void updateLanguage(boolean isFrench) {
        if(isFrench) {
            NewGame.setText("NOUVELLE PARTIE");
            Menu.setText("MENU");
            Quit.setText("QUITTER");
        } else {
            NewGame.setText("NEW GAME");
            Menu.setText("MENU");
            Quit.setText("QUIT");
        }
    }

    // Le joueur peut rejouer
    @FXML
    public void newGame() {
        SoundController.click();
        manager.switchScene(FxmlFiles.START_GAME);
    }

    // Le joueur peut retourner au menu
    @FXML
    public void backToMenu(){
        SoundController.click();
        manager.switchScene(FxmlFiles.MENU);
    }

    // le joueur peut quitter le jeu
    @FXML
    public void quitGame() {
        SoundController.click();
        System.exit(0);
    }
}
