package gui;

import enums.FxmlFiles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @Class Menu
 * @Description Ce fichier est le controller du fichier fxml de Menu , il contient les méthodes qui permettent de passer d'une scène à une autre
 * et de quitter le jeu
 * extends CommonController pour pouvoir utiliser l'attribut de CommonController : manager
 */

public class Menu extends CommonController{

    @FXML Button newGameButton, settingsButton, exitButton; // les boutons du Menu , je les ai mis en attributs pour pouvoir modifier leur text en changeant de langues

            private void switchFromMenu(FxmlFiles file) {
                manager.switchScene(file);
            }

            @FXML
            private void switchToNewGame() { // on passe à la scène de choix de difficulté et commencer le jeu
                SoundController.click(); // le son du click
                switchFromMenu(FxmlFiles.START_GAME);
            }

            @FXML
            private void switchToSettings() { // on passe à la scène de choix de langue et de son
                SoundController.click();
                switchFromMenu(FxmlFiles.SETTINGS);
            }

            @FXML
            private void exitGame() { // on quitte le jeu
                SoundController.click();
                System.exit(0);
            }

    public void updateLanguage(boolean language) {  // modifier le text des bouttons une fois la langue est changée
                // true : français ; false : anglais
        if (language) {
            newGameButton.setText("NOUVELLE PARTIE");
            settingsButton.setText("PARAMÈTRES");
            exitButton.setText("QUITTER");
        }else{
            newGameButton.setText("NEW GAME");
            settingsButton.setText("SETTINGS");
            exitButton.setText("EXIT");}
    }
}

