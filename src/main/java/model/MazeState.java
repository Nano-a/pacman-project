package model;

import config.MazeConfig;
import geometry.IntCoordinates;
import geometry.RealCoordinates;

import java.util.List;
import java.util.Map;

// Importations nécessaires pour l'interface utilisateur JavaFX
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static model.Ghost.*;

// Classe finale pour gérer l'état du labyrinthe dans le jeu.
public final class MazeState {
    private final MazeConfig config; // Configuration du labyrinthe.
    private final int height; // Hauteur du labyrinthe.
    private final int width; // Largeur du labyrinthe.
    private final Label scoreLabel = new Label(); // Étiquette pour afficher le score.

    private final boolean[][] gridState; // État des cellules du labyrinthe (occupé ou non).

    private final List<Critter> critters; // Liste des créatures (Pac-Man et fantômes).
    private int score; // Score actuel du joueur.

    private final Map<Critter, RealCoordinates> initialPos; // Positions initiales des créatures.
    private int lives = 3; // Nombre de vies du joueur.

    // Constructeur de MazeState.
    public MazeState(MazeConfig config) {
        this.config = config;
        height = config.getHeight();
        width = config.getWidth();
        scoreLabel.setText("Score: " + score); // Initialisation de l'étiquette du score.
        VBox vbox = new VBox(); // Boîte verticale pour l'interface utilisateur.
        vbox.getChildren().add(scoreLabel); // Ajout de l'étiquette du score à la boîte.
        // Initialisation des créatures et de l'état du labyrinthe.
        critters = List.of(PacMan.INSTANCE, Ghost.CLYDE, BLINKY, INKY, PINKY);
        gridState = new boolean[height][width];
        initialPos = Map.of(
                PacMan.INSTANCE, config.getPacManPos().toRealCoordinates(1.0),
                BLINKY, config.getBlinkyPos().toRealCoordinates(1.0),
                INKY, config.getInkyPos().toRealCoordinates(1.0),
                CLYDE, config.getClydePos().toRealCoordinates(1.0),
                PINKY, config.getPinkyPos().toRealCoordinates(1.0)
        );
        resetCritters(); // Réinitialisation des positions des créatures.
    }

    public List<Critter> getCritters() {
        return critters;
    }

    public double getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update(long deltaTns) {
        // FIXME: too many things in this method. Maybe some responsibilities can be delegated to other methods or classes?
        for  (var critter: critters) {
            var curPos = critter.getPos();
            var nextPos = critter.nextPos(deltaTns);
            var curNeighbours = curPos.intNeighbours();
            var nextNeighbours = nextPos.intNeighbours();
            boolean wallCollision = false; // booléan qui va permettre de savoir quand pacman touche un mur
            if (!curNeighbours.containsAll(nextNeighbours)) {
                switch (critter.getDirection()) {
                    case NORTH -> {
                        for (var n: curNeighbours) {
                            if (config.getCell(n).northWall()) {
                                wallCollision = true;
                                break;
                            }
                        }
                    }
                    case EAST -> {
                        for (var n: curNeighbours) {
                            if (config.getCell(n).eastWall()) {
                                wallCollision = true;
                                break;
                            }
                        }
                    }
                    case SOUTH -> {
                        for (var n: curNeighbours) {
                            if (config.getCell(n).southWall()) {
                                wallCollision = true;
                                break;
                            }
                        }
                    }
                    case WEST -> {
                        for (var n: curNeighbours) {
                            if (config.getCell(n).westWall()) {
                                wallCollision = true;
                                break;
                            }
                        }
                    }
                }
            }
            // Update position only if there is no wall collision
            if (!wallCollision) {
                critter.setPos(nextPos.warp(width, height));
            }
        }
        // FIXME Pac-Man rules should somehow be in Pacman class
        var pacPos = PacMan.INSTANCE.getPos().round();
        if (!gridState[pacPos.y()][pacPos.x()]) {
            addScore(1);
            gridState[pacPos.y()][pacPos.x()] = true;
        }
        for (var critter : critters) {
            if (critter instanceof Ghost && critter.getPos().round().equals(pacPos)) {
                if (PacMan.INSTANCE.isEnergized()) {
                    addScore(10);
                    resetCritter(critter);
                } else {
                    playerLost();
                    return;
                }
            }
        }
    }

    // Méthode pour ajouter des points au score.
    private void addScore(int increment) {
        score += increment;
        displayScore(); // Affichage du score mis à jour.
    }

    // Méthode pour afficher le score.
    private void displayScore() {
        // Affichage du score dans la console (à remplacer par un affichage dans l'interface utilisateur).
        System.out.println("Score: " + score);
    }

    // Méthode appelée lorsque le joueur perd une vie.
    private void playerLost() {
        lives--;
        if (lives == 0) {
            System.out.println("Game over!"); // Affichage du message de fin de jeu.
            System.exit(0); // Sortie du jeu.
        }
        System.out.println("Lives: " + lives); // Affichage des vies restantes.
        resetCritters(); // Réinitialisation des positions des créatures.
    }


    private void resetCritter(Critter critter) {
        critter.setDirection(Direction.NONE);
        critter.setPos(initialPos.get(critter));
    }

    private void resetCritters() {
        for (var critter: critters) resetCritter(critter);
    }

    public MazeConfig getConfig() {
        return config;
    }

    public boolean getGridState(IntCoordinates pos) {
        return gridState[pos.y()][pos.x()];
    }
}
