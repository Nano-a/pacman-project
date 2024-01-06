package enums;

/**
 * Énumération représentant les différents fichiers FXML utilisés dans l'application.
 */

public enum FxmlFiles {
    MENU("Menu.fxml"), // Fichier FXML pour le menu principal
    START_GAME("NewGamePage.fxml"), // Fichier FXML pour la page de démarrage du jeu ou on sélectionne la difficulté
    GAME("GamePage.fxml"), // Fichier FXML pour la page du jeu de Pacman
    SETTINGS("SettingsPage.fxml"),  // Fichier FXML pour la page des paramètres
    SETTINGS_AUDIO("SettingsAudio.fxml"), // Fichier FXML pour les paramètres audio
    SETTINGS_LANGUAGE("SettingsLanguage.fxml"), // Fichier FXML pour les paramètres de langue
    WIN("WinPage.fxml"), // Fichier FXML pour la page de victoire après 3 niveaux
    LOSE("LosePage.fxml"), // Fichier FXML pour la page de défaite après 0 vies
    PAUSE("PausePage.fxml"), // Fichier FXML pour la page de pause
    STORY("StoryPage.fxml"); // Fichier FXML pour la page de l'histoire de jungle Pacman

    private String file;
    FxmlFiles(String file) {
        this.file = file;
    }
    public String getFxmlLocation() {
        return file;
    }
}
