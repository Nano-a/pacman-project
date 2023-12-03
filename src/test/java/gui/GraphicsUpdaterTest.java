/*
La classe GraphicsUpdater est une interface définissant deux méthodes : update() et getNode().
Dans le contexte de JavaFX, cette interface est destinée à être implémentée par des classes qui
mettent à jour des éléments graphiques dans le cadre d'une animation ou d'une réaction à des
changements d'état.

Pour tester des implémentations de cette interface, On crée des classes mock qui implémentent
GraphicsUpdater et vérifier si elles se comportent comme prévu. Comme GraphicsUpdater est une
interface, on ne teste pas directement l'interface mais plutôt ses implémentations concrètes
dans le contexte de notre projet.
*/package gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GraphicsUpdaterTest {

    @Test
    public void testGraphicsUpdaterImplementation() {
        // Création d'une mock implementation de GraphicsUpdater
        GraphicsUpdater updater = mock(GraphicsUpdater.class);
        Node mockNode = new Pane(); // Utiliser un Pane comme Node mock pour l'exemple
        when(updater.getNode()).thenReturn(mockNode);

        // Simuler l'appel à update() et vérifier si la méthode a été appelée
        updater.update();
        verify(updater, times(1)).update();

        // Vérifier que getNode() retourne le bon Node
        assertEquals(mockNode, updater.getNode(), "getNode() doit retourner le Node mock.");
    }

    // Vous pouvez ajouter d'autres tests pour des implémentations spécifiques de GraphicsUpdater
}
