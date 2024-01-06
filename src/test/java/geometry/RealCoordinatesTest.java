package geometry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

public class RealCoordinatesTest {

    @Test
    public void testOperations() {
        RealCoordinates coords = new RealCoordinates(2.5, 3.5);

        RealCoordinates added = coords.plus(new RealCoordinates(1.0, 1.0));
        assertEquals(3.5, added.x(), "L'addition de x doit être correcte.");
        assertEquals(4.5, added.y(), "L'addition de y doit être correcte.");

        RealCoordinates multiplied = coords.times(2);
        assertEquals(5.0, multiplied.x(), "La multiplication de x doit être correcte.");
        assertEquals(7.0, multiplied.y(), "La multiplication de y doit être correcte.");
    }

    @Test
    public void testIntNeighbours() {
        RealCoordinates coords = new RealCoordinates(2.5, 3.5);
        Set<IntCoordinates> neighbours = coords.intNeighbours();

        assertTrue(neighbours.contains(new IntCoordinates(2, 3)), "Doit contenir (2,3).");
        assertTrue(neighbours.contains(new IntCoordinates(3, 4)), "Doit contenir (3,4).");
        // Vérifiez les autres voisins attendus
    }

    @Test
    public void testRound() {
        RealCoordinates coords = new RealCoordinates(2.5, 3.6);
        IntCoordinates rounded = coords.round();

        assertEquals(3, rounded.x(), "La valeur arrondie de x doit être correcte.");
        assertEquals(4, rounded.y(), "La valeur arrondie de y doit être correcte.");
    }

    // Ajoutez d'autres tests pour les autres méthodes comme floorX(), floorY(), etc.
}
