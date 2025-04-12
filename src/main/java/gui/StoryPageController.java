package gui;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Contrôleur de la page d'histoire qui affiche une image de context pour dire de saiver pacman dans la jungle des animaux sauvages
 */
public class StoryPageController extends CommonController{

    @FXML private AnchorPane backgroundStory;

    @FXML
    public void initialize() {
        updateLanguage(SettingsLanguageController.isFrench);
    }

    public void updateLanguage(boolean isFrench) {
        // On change le texte de l'image selon la langue choisie
        if (isFrench) {
            // Définir l'image de fond pour le français
            backgroundStory.setId("BackgroundStoryFR");
        } else {
            // Définir l'image de fond pour l'anglais (ou autre langue)
            backgroundStory.setId("BackgroundStory");
        } }

}
