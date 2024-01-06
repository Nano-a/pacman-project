package gui;

import model.Critter;
import model.Ghost;
import model.PacMan;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class CritterGraphicsFactoryTest extends ApplicationTest {

    @Test
    public void testMakeGraphicsForPacMan() {
        double scale = 10.0; // Exemple d'échelle
        CritterGraphicsFactory factory = new CritterGraphicsFactory(scale);
        Critter pacMan = PacMan.INSTANCE; // Remplacez par votre logique de création de PacMan

        GraphicsUpdater updater = factory.makeGraphics(pacMan);
        assertTrue(updater.getNode() instanceof ImageView, "Le nœud pour PacMan doit être un ImageView.");

        // Vérifiez les propriétés de l'ImageView, comme l'image source, la taille, etc.
        ImageView imageView = (ImageView) updater.getNode();
        // Testez les attributs de imageView ici, comme l'image affichée, la taille, etc.
    }

    @Test
    public void testMakeGraphicsForGhost() {
        double scale = 10.0; // Exemple d'échelle
        CritterGraphicsFactory factory = new CritterGraphicsFactory(scale);
        Critter ghost = Ghost.BLINKY; // Remplacez par votre logique de création de Ghost

        GraphicsUpdater updater = factory.makeGraphics(ghost);
        assertTrue(updater.getNode() instanceof ImageView, "Le nœud pour un fantôme doit être un ImageView.");

        // Vérifiez les propriétés de l'ImageView pour le fantôme.
        ImageView imageView = (ImageView) updater.getNode();
        // Testez les attributs de imageView ici, comme l'image affichée, la taille, etc.
    }

    // Ajoutez plus de tests pour couvrir d'autres types de fantômes et d'autres cas
}
