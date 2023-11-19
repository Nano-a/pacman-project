import gui.App;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.stage.Stage;

public class AppTest {

    @Test
    public void testAppStart() {
        Stage stage = new Stage();
        App app = new App();
        app.start(stage);
        assertNotNull(stage); // Check if the stage is not null after the start method is called
        assertNotNull(stage.getScene());
        assertEquals("JUNGLE PACMAN", stage.getTitle());
        assertTrue(stage.isShowing()); // Check if the stage is showing

        // Simulate user actions and verify the application's behavior
        // For instance, if you have methods to handle button clicks or scene changes, you can call them here and verify their effects
    }
}
