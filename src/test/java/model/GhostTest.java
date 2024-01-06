package model;

import geometry.RealCoordinates;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GhostTest {

    @Test
    public void testGhostPositionsAndDirections() {
        // Tester pour un type de fantôme, par exemple BLINKY
        Ghost blinky = Ghost.BLINKY;

        // Définir et tester la position
        RealCoordinates pos = new RealCoordinates(5.0, 5.0);
        blinky.setPos(pos);
        assertEquals(pos, blinky.getPos(), "La position doit être correctement définie et récupérée pour BLINKY.");

        // Définir et tester la direction
        blinky.setDirection(Direction.EAST);
        assertEquals(Direction.EAST, blinky.getDirection(), "La direction doit être correctement définie et récupérée pour BLINKY.");

        // Test de la méthode getSpeed
        assertEquals(0, blinky.getSpeed(), "La vitesse de BLINKY doit être correctement récupérée.");

        // Répétez ces tests pour INKY, PINKY, et CLYDE si nécessaire
    }

    // Ajoutez d'autres tests si nécessaire pour couvrir des comportements spécifiques des fantômes
}
