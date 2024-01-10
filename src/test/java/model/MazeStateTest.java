package model;

import static org.junit.jupiter.api.Assertions.*;

import config.MazeConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.IntCoordinates;
import enums.Direction;

import java.io.File;

class MazeStateTest {

    private MazeState mazeState;

    @BeforeEach
    void setUp() {
        // Initialisez MazeState avec les paramètres nécessaires
        File f = new File("src/main/resources/levels/level1.txt");
        MazeConfig config = new MazeConfig(f.getAbsolutePath());
        mazeState = new MazeState(config);
    }

    @Test
    void testInitialState() {
        assertNotNull(mazeState);

    }

    @Test
    void testPacmanMovement() {
        // Supposons qu'il y ait une méthode pour déplacer Pacman
        mazeState.movePacman(Direction.NORTH);
        // Vérifiez la nouvelle position de Pacman
       // assertEquals(new IntCoordinates(15,15), mazeState.getConfig().pacManPos);
        // Assertions supplémentaires basées sur les règles du jeu
    }

    @Test
    void testGhostBehavior() {
        // Testez le comportement des fantômes dans le labyrinthe
        // Cela dépendra de la manière dont les fantômes sont implémentés et contrôlés dans MazeState
    }

    @Test
    void testGameStateChanged() {
        // Testez les changements d'état du jeu, comme la fin d'un niveau, la perte d'une vie, etc.
        // Cela dépendra de la façon dont ces états sont représentés et gérés dans MazeState
    }

    // Ajoutez plus de tests en fonction des autres fonctionnalités de MazeState
}

