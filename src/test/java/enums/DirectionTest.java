package enums;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    void testExistenceOfEnumValues() {
        assertNotNull(Direction.NONE);
        assertNotNull(Direction.NORTH);
        assertNotNull(Direction.EAST);
        assertNotNull(Direction.SOUTH);
        assertNotNull(Direction.WEST);
    }
}
