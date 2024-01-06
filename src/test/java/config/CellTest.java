package config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void testCellBuilder() {
        Cell cell = Cell.builder()
                        .withNorthWall(true)
                        .withContent(Cell.Content.DOT)
                        .build();

        assertTrue(cell.northWall(), "La cellule devrait avoir un mur au nord.");
        assertEquals(Cell.Content.DOT, cell.initialContent(), "Le contenu initial de la cellule doit être un point.");
    }

    @Test
    public void testFactoryMethods() {
        Cell wallCell = Cell.wall();
        assertEquals(Cell.Content.WALL, wallCell.initialContent(), "Une cellule mur doit avoir le contenu 'WALL'.");

        Cell dotCell = Cell.dot();
        assertEquals(Cell.Content.DOT, dotCell.initialContent(), "Une cellule point doit avoir le contenu 'DOT'.");
    }

    // Ajoutez d'autres tests pour les autres méthodes factory et les cas limites
}
