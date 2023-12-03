package gui;

import geometry.IntCoordinates;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.MazeState;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CellGraphicsFactoryTest extends ApplicationTest {

    @Test
    public void testMakeGraphics() {
        double scale = 10.0; // Exemple d'échelle
        CellGraphicsFactory factory = new CellGraphicsFactory(scale);

        // Créez un état de labyrinthe fictif et une position pour tester
        MazeState mazeState = mock(MazeState.class); // Utilisez Mockito pour créer un mock de MazeState afin d'initialiser le mazestates
        IntCoordinates position = new IntCoordinates(1, 1);

        GraphicsUpdater updater = factory.makeGraphics(mazeState, position);
        Node node = updater.getNode();

        assertTrue(node instanceof Group, "Le nœud retourné doit être un Group.");
        Group group = (Group) node;

        // Testez les propriétés des enfants du groupe, par exemple si des cercles ou des rectangles sont présents
        for (Node child : group.getChildren()) {
            if (child instanceof Circle) {
                Circle circle = (Circle) child;
                // Testez les propriétés du cercle (rayon, couleur, etc.)
            } else if (child instanceof Rectangle) {
                Rectangle rect = (Rectangle) child;
                // Testez les propriétés du rectangle (taille, couleur, etc.)
            }
        }

        // Vous pouvez ajouter plus de vérifications pour tester différents états et types de cellules
    }

    // Ajoutez d'autres tests pour couvrir tous les scénarios possibles
}
