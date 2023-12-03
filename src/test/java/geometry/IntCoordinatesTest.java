package geometry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntCoordinatesTest {

    @Test
    public void testToRealCoordinates() {
        IntCoordinates intCoords = new IntCoordinates(2, 3);
        double scale = 1.5;
        RealCoordinates realCoords = intCoords.toRealCoordinates(scale);

        assertEquals(3.0, realCoords.x(), "La conversion de x doit être correcte.");
        assertEquals(4.5, realCoords.y(), "La conversion de y doit être correcte.");
    }

    @Test
    public void testAccessors() {
        IntCoordinates intCoords = new IntCoordinates(2, 3);

        assertEquals(2, intCoords.getX(), "L'accès à x doit retourner la bonne valeur.");
        assertEquals(3, intCoords.getY(), "L'accès à y doit retourner la bonne valeur.");
    }

    // Ajoutez d'autres tests si nécessaire
}
