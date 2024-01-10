package enums;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CellTest {

    @Test
    void testExistenceOfEnumValues() {
        assertNotNull(Cell.NOTHING);
        assertNotNull(Cell.DOT);
        assertNotNull(Cell.TREE);
        assertNotNull(Cell.ENERGIZER);
        assertNotNull(Cell.LION);
        assertNotNull(Cell.GORILLA);
        assertNotNull(Cell.SNAKE);
        assertNotNull(Cell.TIGER);
        assertNotNull(Cell.PACMAN);


    }
}
