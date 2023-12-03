package gui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest extends ApplicationTest {

    @Test
    public void testMenuButtons() {
        Menu menu = new Menu();

        // Vérifier que le Menu contient les boutons
        Button jouerButton = menu.getJouerButton();
        assertNotNull(jouerButton, "Le bouton 'jouer' ne doit pas être null.");
        Button parametresButton = menu.getParametresButton();
        assertNotNull(parametresButton, "Le bouton 'parametres' ne doit pas être null.");
        Button quitterButton = menu.getQuitterButton();
        assertNotNull(quitterButton, "Le bouton 'quitter' ne doit pas être null.");

        // Vérifiez que les boutons sont organisés dans un VBox à l'intérieur du StackPane
        assertTrue(menu.getChildren().get(1) instanceof VBox, "Les boutons doivent être organisés dans un VBox.");

        // Vérifier les propriétés graphiques des boutons, comme les images
        assertTrue(jouerButton.getGraphic() instanceof ImageView, "Le bouton 'jouer' doit avoir une image graphique.");
        // Répétez pour les autres boutons

        // Vérifiez d'autres aspects de l'organisation et du style si nécessaire
    }

    // Ajoutez d'autres tests pour couvrir les interactions, clics sur les boutons, etc.
}
