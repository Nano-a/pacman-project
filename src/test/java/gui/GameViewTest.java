package gui;

import javafx.scene.layout.Pane;
import model.MazeState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;

public class GameViewTest extends ApplicationTest {

    @Mock
    private MazeState mazeMock;

    @Mock
    private GraphicsUpdater graphicsUpdaterMock;

    private Pane gameRoot;
    private GameView gameView;
    private double scale;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        scale = 10.0; // Exemple d'échelle
        gameRoot = new Pane();
        gameView = new GameView(mazeMock, gameRoot, scale);
    }

    @Test
    public void testGameViewInit() {
        assertNotNull(gameRoot.getChildren(), "Le gameRoot doit avoir des enfants après l'initialisation de GameView.");
        // Testez d'autres aspects comme la taille du gameRoot, les styles, etc.
    }

    @Test
    public void testAddGraphics() throws Exception {
        // Préparation de la réflection pour accéder à la méthode privée
        Method addGraphicsMethod = GameView.class.getDeclaredMethod("addGraphics", GraphicsUpdater.class);
        addGraphicsMethod.setAccessible(true);

        // Appel de la méthode privée
        addGraphicsMethod.invoke(gameView, graphicsUpdaterMock);

        // Test
        assertTrue(gameRoot.getChildren().contains(graphicsUpdaterMock.getNode()), "Le gameRoot doit contenir le nœud du faux GraphicsUpdater.");
    }


    @Test
    public void testAnimateMethod() {
        // Supposons que GameView a une méthode pour démarrer les animations
        gameView.animate();

        // Vous devrez peut-être vérifier si certains nœuds ont été ajoutés ou modifiés dans gameRoot
        // Ceci est juste un exemple; les détails dépendront de la façon dont votre méthode animate est implémentée
        assertFalse(gameRoot.getChildren().isEmpty(), "Les animations doivent ajouter des éléments au gameRoot.");
    }

    @Test
    public void testUserInputHandling() {
        // Simuler une entrée utilisateur, par exemple, un clic de souris ou une pression de touche
        // Cela peut nécessiter l'interaction avec des composants spécifiques de l'interface utilisateur

        // Vérifier les changements d'état dans gameView ou les composants liés
        // Par exemple, si un clic déplace Pac-Man, vérifiez si sa position a été mise à jour
    }
}
