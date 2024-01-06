package gui;

import model.Direction;
import model.PacMan;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PacmanControllerTest {

    private PacMan pacManMock;
    private PacmanController pacmanController;

    @BeforeEach
    public void setUp() {
        pacManMock = mock(PacMan.class);
        pacmanController = new PacmanController(pacManMock);
    }


    @Test
    public void testKeyPressedHandler() {
        // Simuler un événement de touche pressée
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false, false, false, false);
        pacmanController.keyPressedHandler(keyEvent);

        // Vérifier si la direction de PacMan a été mise à jour
        verify(pacManMock, times(1)).setDirection(Direction.WEST);

        // Répétez pour d'autres touches et directions
    }

    // Ajoutez d'autres tests si nécessaire, par exemple pour keyReleasedHandler
}
