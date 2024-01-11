package gui;

import enums.FxmlFiles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class SettingsLanguageController extends CommonController {

    @FXML Button ReturnButton;

    @FXML
    private RadioButton englishRadio; // Reference to the English radio button

    @FXML
    private RadioButton frenchRadio; // Reference to the French radio button

    public static boolean isFrench = false ; // false = english, true = french
    private ToggleGroup languageToggleGroup;

    /*
    A ToggleGroup in JavaFX is a class used to group together a set of radio buttons
     or toggle buttons, allowing them to function as a mutually exclusive group.
      When you assign radio buttons or toggle buttons to a ToggleGroup,
     only one button within that group can be selected at a time.
     */

    public void initialize() {
        // Create a ToggleGroup and add the radio buttons to it
        languageToggleGroup = new ToggleGroup();
        englishRadio.setToggleGroup(languageToggleGroup);
        frenchRadio.setToggleGroup(languageToggleGroup);
        // Set initial selection (if needed)
        englishRadio.setSelected(true);
    }

    public void FrenchSelected() {
        // When the French radio button is selected, set the isFrench variable to true
        // and update  language dans SceneManager en passant la variable isFrench en paramètres
        SoundController.click();
        RadioButton selectedRadioButton = (RadioButton) languageToggleGroup.getSelectedToggle();
        isFrench = true ;
        manager.switchLanguage(true);
    }

    public void EnglishSelected() {
        SoundController.click();
        RadioButton selectedRadioButton = (RadioButton) languageToggleGroup.getSelectedToggle();
        isFrench = false ;
        manager.switchLanguage(false);
    }

    @FXML
    private void goBackToOptions() {
       SoundController.click();
        manager.switchScene(FxmlFiles.SETTINGS);
    }


    public void updateLanguage(boolean isFrench) {
        // update the return button
        if(isFrench) {
            ReturnButton.setText("RETOUR");
        } else {
            ReturnButton.setText("RETURN");
        }

    }
}

