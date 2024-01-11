package enums;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DifficultyTest {

    @Test
    void testSpeedValues() {
        assertEquals(4.0, Difficulty.SLOW.getSpeedScale());
        assertEquals(5.0, Difficulty.NORMAL.getSpeedScale());
        assertEquals(6.0, Difficulty.FAST.getSpeedScale());

    }
}