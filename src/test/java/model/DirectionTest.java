package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    @Test
    public void testDirectionValues() {
        // Vérifier que toutes les directions attendues sont présentes
        assertEquals(5, Direction.values().length, "Doit avoir 5 directions.");
        assertNotNull(Direction.NONE, "Direction.NONE doit être définie.");
        assertNotNull(Direction.NORTH, "Direction.NORTH doit être définie.");
        assertNotNull(Direction.EAST, "Direction.EAST doit être définie.");
        assertNotNull(Direction.SOUTH, "Direction.SOUTH doit être définie.");
        assertNotNull(Direction.WEST, "Direction.WEST doit être définie.");
    }

    // Ajoutez d'autres tests si nécessaire, par exemple pour tester le comportement de votre code en utilisant ces directions
}
