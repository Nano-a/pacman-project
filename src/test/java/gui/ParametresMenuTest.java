package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class ParametresMenuTest extends ApplicationTest {

    @Test
    public void testParametresMenuButtons() {
        Menu mainMenu = new Menu();
        ParametresMenu parametresMenu = new ParametresMenu(mainMenu);

        // Vérifier la présence des boutons dans le menu de paramètres
        // Trouver les boutons par leur texte ou leur id
        Button retourButton = (Button) parametresMenu.lookup("#retourButton");
        assertNotNull(retourButton, "Le bouton 'retour' ne doit pas être null.");

        Button sonButton = (Button) parametresMenu.lookup("#sonButton");
        assertNotNull(sonButton, "Le bouton 'son' ne doit pas être null.");

        Button langueButton = (Button) parametresMenu.lookup("#langueButton");
        assertNotNull(langueButton, "Le bouton 'langue' ne doit pas être null.");

        // Vérifier que les boutons sont organisés dans un VBox à l'intérieur du StackPane
        assertTrue(parametresMenu.getChildren().get(1) instanceof VBox, "Les boutons doivent être organisés dans un VBox.");

        // Tester le comportement du bouton 'retour'
        // Vous pouvez simuler un clic sur le bouton 'retour' et vérifier si cela conduit au menu principal
    }

    // Ajoutez d'autres tests pour couvrir les interactions, clics sur les boutons, etc.
}
