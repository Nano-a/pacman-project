package model;

import geometry.IntCoordinates;
import config.MazeConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MazeStateTest {

    private MazeState mazeState;
    private MazeConfig configMock;

    @BeforeEach
    public void setUp() {
        configMock = mock(MazeConfig.class);
        mazeState = new MazeState(configMock);
    }

    @Test
    public void testInitialMazeState() {
        // Vérifier les conditions initiales du labyrinthe
        assertNotNull(mazeState.getConfig(), "Le MazeConfig ne doit pas être null.");
        // Vérifier les dimensions du labyrinthe
        assertEquals(configMock.getHeight(), mazeState.getHeight(), "La hauteur doit correspondre à celle du MazeConfig.");
        assertEquals(configMock.getWidth(), mazeState.getWidth(), "La largeur doit correspondre à celle du MazeConfig.");
        // Vérifiez d'autres aspects initiaux comme la liste des critters
    }

    @Test
    public void testUpdateMethod() {
        // Testez la méthode update pour différentes situations
        long deltaTns = 1000000000L; // 1 seconde en nanosecondes
        mazeState.update(deltaTns);

        // Vérifiez les mises à jour des positions des critters, des collisions, etc.
        // Vous pouvez simuler des scénarios spécifiques et vérifier le résultat attendu
    }

    @Test
    public void testScoreAndLivesManagement() {
        // Simulez des situations qui changeraient le score et le nombre de vies
        // Par exemple, simuler que PacMan mange des points ou est attrapé par un fantôme
        // Utilisez des méthodes publiques de MazeState pour réaliser ces simulations

        // Vérifiez les changements attendus dans le score et les vies
        // Il peut être nécessaire d'ajouter des méthodes d'accès (getters) dans MazeState pour cela
    }


    // Ajoutez d'autres tests pour couvrir tous les aspects de MazeState
}
