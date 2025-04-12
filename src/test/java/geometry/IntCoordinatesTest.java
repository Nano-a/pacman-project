package geometry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class IntCoordinatesTest {

    @Test
    void testCoordinateInitialization() {
        IntCoordinates coords = new IntCoordinates(5, 10);
        assertEquals(5, coords.getX());
        assertEquals(10, coords.getY());
    }

    @Test
    void testSetCoordinates() {
        IntCoordinates coords = new IntCoordinates(0,0);
        coords.setX(7);
        coords.setY(12);
        assertEquals(7, coords.getX());
        assertEquals(12, coords.getY());
    }
}
