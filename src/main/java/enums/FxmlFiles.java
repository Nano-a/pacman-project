package enums;

public enum FxmlFiles {
    MENU("Menu.fxml"),
    START_GAME("NewGamePage.fxml"),
    SETTINGS("SettingsPage.fxml"),
    SETTINGS_LANGUAGE("SettingsLanguage.fxml"),
    SETTINGS_AUDIO("SettingsAudio.fxml"),
    PAUSE("PausePage.fxml"),
    GAME_WINDOW("GameWindow.fxml");

    private String file;
    FxmlFiles(String file) {
        this.file = file;
    }

    public String getFxmlLocation() {
        return file;
    }
}
