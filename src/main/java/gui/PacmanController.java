/*
 *  Ce fichier semble être responsable de la gestion des interactions de l'utilisateur
 * avec Pacman, notamment en gérant les entrées du clavier pour diriger le mouvement
 * de Pacman.
 */
package gui;

import model.Direction;
import model.PacMan;

import javafx.scene.input.KeyEvent;

// Cette classe est chargée de gérer les entrées de l'utilisateur pour contrôler Pacman.
public class PacmanController {
    public void keyPressedHandler(KeyEvent event) {
                // Définition de la direction de PacMan en fonction de la touche pressée.
        PacMan.INSTANCE.setDirection(
                switch (event.getCode()) {
                    case LEFT -> Direction.WEST;
                    case RIGHT -> Direction.EAST;
                    case UP -> Direction.NORTH;
                    case DOWN -> Direction.SOUTH;
                    default -> PacMan.INSTANCE.getDirection(); // Autrement, la direction reste inchangée.
                }
        );
    }
    public void keyReleasedHandler(KeyEvent event) {
        // Nothing to do?
    }
}
