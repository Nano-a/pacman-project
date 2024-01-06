package model;

import geometry.RealCoordinates;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PacManTest {

    @Test
    public void testSingletonInstance() {
        // Vérifier que l'instance de PacMan est unique
        assertNotNull(PacMan.INSTANCE, "L'instance de PacMan ne doit pas être null.");
    }

    @Test
    public void testPositionAndDirection() {
        // Définir et tester la position
        RealCoordinates newPos = new RealCoordinates(5.0, 5.0);
        PacMan.INSTANCE.setPos(newPos);
        assertEquals(newPos, PacMan.INSTANCE.getPos(), "La position doit être correctement définie et récupérée.");

        // Définir et tester la direction
        PacMan.INSTANCE.setDirection(Direction.EAST);
        assertEquals(Direction.EAST, PacMan.INSTANCE.getDirection(), "La direction doit être correctement définie et récupérée.");
    }

    @Test
    public void testEnergizedState() {
        // Tester l'état énergisé
        PacMan.INSTANCE.setEnergized(true);
        assertTrue(PacMan.INSTANCE.isEnergized(), "Pac-Man doit être en état énergisé.");

        // Tester le changement de vitesse en état énergisé
        assertEquals(6, PacMan.INSTANCE.getSpeed(), "La vitesse doit être augmentée lorsqu'énergisé.");

        // Tester l'état non énergisé
        PacMan.INSTANCE.setEnergized(false);
        assertFalse(PacMan.INSTANCE.isEnergized(), "Pac-Man ne doit pas être en état énergisé.");
        assertEquals(4, PacMan.INSTANCE.getSpeed(), "La vitesse doit être normale lorsqu'il n'est pas énergisé.");
    }

    // Ajoutez d'autres tests si nécessaire pour couvrir d'autres comportements de PacMan
}
