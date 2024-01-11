package enums;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FxmlFilesTest {

    @Test
    void testExistenceOfEnumValues() {
        assertNotNull(FxmlFiles.MENU);
        assertNotNull(FxmlFiles.START_GAME);
        assertNotNull(FxmlFiles.GAME);
        assertNotNull(FxmlFiles.SETTINGS);
    }

    @Test
    void testFxmlFilePaths() {
        assertEquals("Menu.fxml", FxmlFiles.MENU.getFxmlLocation());
        assertEquals("NewGamePage.fxml", FxmlFiles.START_GAME.getFxmlLocation());
        assertEquals("GamePage.fxml", FxmlFiles.GAME.getFxmlLocation());
        assertEquals("SettingsPage.fxml", FxmlFiles.SETTINGS.getFxmlLocation());


    }
}
