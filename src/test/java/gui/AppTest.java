/*
 *  La classe App qui sert de point d'entrée pour une application JavaFX. Tester une application JavaFX,
 * en particulier son interface utilisateur (UI), peut être plus complexe que des tests unitaires standards
 * en raison de l'interaction avec les éléments graphiques et l'environnement de l'application.

Pour tester la classe App, vous devrez utiliser des outils et des techniques spécifiques à JavaFX,
tels que TestFX, qui est une bibliothèque conçue pour tester les applications JavaFX.
 */

package gui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest extends ApplicationTest {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        new App().start(stage); // Démarrer l'application
    }

    @Test
    public void testInitialScene() {
        // Vérifier que la scène initiale est bien celle attendue (par exemple, le menu principal)
        assertNotNull(stage.getScene(), "La scène ne doit pas être nulle après le démarrage de l'application.");
        assertEquals("JUNGLE PACMAN", stage.getTitle(), "Le titre de la fenêtre doit être 'JUNGLE PACMAN'.");
        // Plus de tests pour vérifier les éléments de la scène initiale
    }

    @Test
    public void testMenuInteraction() {
        // Interagir avec le menu et vérifier les changements de scène ou d'état
        // Exemple : cliquer sur le bouton 'Jouer' et vérifier le changement de scène
        // Utilisez les méthodes fournies par TestFX pour simuler les interactions utilisateur
    }

    // Ajoutez plus de tests pour tester d'autres interactions et comportements
}
