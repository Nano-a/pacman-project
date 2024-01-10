package config;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;


class MazeConfigTest {

    @Test
    void testMazeDimensions() {
        MazeConfig config = new MazeConfig("src/main/resources/levels/level1.txt");
        // Supposons que le constructeur ou une méthode définit les dimensions
        assertEquals(21, config.getRows());
        assertEquals(19, config.getCols());
    }

    @Test
    void testGridInitialization() {
        MazeConfig config = new MazeConfig("src/main/resources/levels/level1.txt");
        assertNotNull(config.getCell(15,15));
    }
}
