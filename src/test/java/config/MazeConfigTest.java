package config;

import geometry.IntCoordinates;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeConfigTest {

    @Test
    public void testMazeConfigInitialization() throws IOException {
        // Remplacez ceci par le chemin valide vers votre fichier de configuration de labyrinthe
        String validResourcePath = "path/to/maze/config/file";
        MazeConfig mazeConfig = new MazeConfig(validResourcePath);

        assertNotNull(mazeConfig, "MazeConfig ne devrait pas être null après initialisation.");
        // Plus de tests pour vérifier les dimensions du labyrinthe, les positions de Pac-Man et des fantômes, etc.
    }

    @Test
    public void testGetCell() throws IOException {
        String validResourcePath = "path/to/maze/config/file";
        MazeConfig mazeConfig = new MazeConfig(validResourcePath);

        IntCoordinates position = new IntCoordinates(1, 1);
        Cell cell = mazeConfig.getCell(position);

        assertNotNull(cell, "getCell ne devrait pas retourner null pour une position valide.");
        // Plus de tests pour vérifier le contenu de la cellule retournée.
    }

    // Ajoutez d'autres tests selon les fonctionnalités de MazeConfig
}
