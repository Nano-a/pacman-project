package gui;

import model.Direction;
import model.PacMan;

import javafx.scene.input.KeyEvent;

public class PacmanController {
    public void keyPressedHandler(KeyEvent event) {
        PacMan.INSTANCE.setDirection(
                switch (event.getCode()) {
                    case LEFT -> Direction.WEST;
                    case RIGHT -> Direction.EAST;
                    case UP -> Direction.NORTH;
                    case DOWN -> Direction.SOUTH;
                    case STOP -> Direction.STOP;
                    default -> PacMan.INSTANCE.getDirection(); // do nothing
                }
        );
    }
     public void keyReleasedHandler(KeyEvent event) {
        PacMan.INSTANCE.setDirection(Direction.STOP); // afin que la pacamn s'arrête quand je relache la touche
    } 
}
