package gui;

import enums.FxmlFiles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SettingsController extends CommonController {

    @FXML Button    audioButton, languageButton, returnButton;

    @FXML
    private void goToAudioSettings() {
        SoundController.click();
        manager.switchScene(FxmlFiles.SETTINGS_AUDIO);
    }

    @FXML
    private void goToLanguageSettings() {
        SoundController.click();
        manager.switchScene(FxmlFiles.SETTINGS_LANGUAGE);
    }

    @FXML
    private void goBackToMenu() {
        SoundController.click();
        manager.switchScene(FxmlFiles.MENU);
    }

    public void updateLanguage(boolean isFrench) {
        // true = french, false = english
        if(isFrench) {
            audioButton.setText("RÉGLAGES AUDIO");
            languageButton.setText("RÉGLAGES LANGUES");
            returnButton.setText("RETOUR");
        } else {
            audioButton.setText("AUDIO SETTINGS");
            languageButton.setText("LANGUAGE SETTINGS");
            returnButton.setText("RETURN");
        }
    }
}
