package gameobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityObjectsTest {

    @Test
    public void testGetLeaveDelay() {
        assertEquals(0, EntityObjects.PLAYER.getLeaveDelay(), "Le délai de départ du joueur doit être 0.");
        assertEquals(1000, EntityObjects.INKY.getLeaveDelay(), "Le délai de départ d'Inky doit être 1000.");
        // Testez les autres entités
    }

    @Test
    public void testGhosts() {
        EntityObjects[] ghosts = EntityObjects.ghosts();
        assertTrue(ghosts.length > 0, "Doit retourner au moins un fantôme.");
        assertArrayEquals(new EntityObjects[]{EntityObjects.BLINKY, EntityObjects.INKY, EntityObjects.PINKY, EntityObjects.CLYDE}, ghosts, "Doit retourner tous les fantômes.");
    }
}
