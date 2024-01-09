package model;

import config.MazeConfig;
import enums.Cell;
import geometry.IntCoordinates;
import enums.Direction;
import gui.Controller;
import gui.SoundController;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import java.util.Random;

/**
 * Description : gérer l'état du labyrinthe dans le jeu et tous le model ; move Pacman et les ghosts
 * les ghosts qui retournent à leurs positions initiliale , les sorties du labyrinthe , passer au level suivant
 * Manger les dots et les energizer et biensur les collisions entre les ghosts et Pacman
 * et le ghost eating mode ou les ghosts peuvent être mangé par Pacman
 */
public final class MazeState {
    private final MazeConfig config; // Configuration du labyrinthe à [partir du fichier]
    private static boolean gameOver; // variable de jeu pour savoir si on a perdu
    private static boolean Pause; // variable de jeu pour savoir si on est en pause
    private static boolean youWon; // variable de jeu pour savoir si on a gagné
    private static boolean ghostEatingMode;  // vsi on est en ghost eating mode
    private int score;
    private int level;
    private int lives;
    private static Direction lastDirection;
    private static Direction currentDirection = Direction.NONE;

    public Direction getLastDirection() {
        return lastDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public MazeState(MazeConfig config) {
        this.config = config;
    }

    public void startNewGame() {
        // initialiser les variables du jeu pour commencer une nouvelle partie
        this.gameOver = false;
        this.youWon = false;
        this.Pause = false;
        this.ghostEatingMode = false;
        config.setNbDots(0);
        config.setRows(0);
        config.setCols(0);
        this.score = 0;
        this.level = 1;
        this.lives = 3; // 3 vies
        config.loadMazeFromFile(Controller.getLevelFile(0)); // niveau 1 avec indice dans le tableau 0
    }


    public void startNextLevel() {
        if (this.isLevelComplete()) {
            this.level++;
            config.setCols(0);
            config.setCols(0);
            youWon = false;
            ghostEatingMode = false;
            try {
                config.loadMazeFromFile(Controller.getLevelFile(level - 1));
            } catch (ArrayIndexOutOfBoundsException e) {
                //si y'a plus de niveaux on a fini le 3 c'est à dire on a gagné
                youWon = true;
                gameOver = true;
                level--;
            }
        }
    }

    public boolean isLevelComplete() {
        // c'est à dire que tous les dots ont été mangé par PacMan
        return config.getNbDots() == 0;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static boolean isPaused() {
        return Pause;
    }

    public static boolean isGhostEatingMode() {
        return ghostEatingMode;
    }

    public static void setGhostEatingMode(boolean ghostEatingModeBool) {
        ghostEatingMode = ghostEatingModeBool;
    }

    public static boolean isYouWon() {
        return youWon;
    }


    public void resetPositions() {
        // Réinitialiser la position de Pac-Man à sa position de départ
        // appelé une fois il a perdu la vie
        for (int row = 0; row < config.getRows(); row++) {
            for (int column = 0; column < config.getCols(); column++) {
                if (config.grid[row][column] == Cell.PACMAN) {
                    config.pacManPos = new IntCoordinates(row, column);
                    break;
                }
            }
        }
        // Réinitialiser la position des fantômes à leur position de départ
        sendLionHome();
        sendGorillaHome();
    }

    private void addScore(int increment) {
        score += increment;
    }

    public MazeConfig getConfig() {
        return config;
    }
}

