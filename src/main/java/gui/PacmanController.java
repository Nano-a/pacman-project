package gui;

import model.Direction;
import model.PacMan;

import javafx.scene.input.KeyEvent;

public class PacmanController {
    public void keyPressedHandler(KeyEvent event) {

                switch (event.getCode()) {
                    case LEFT :
                        PacMan.INSTANCE.setDirection(Direction.WEST);

                        break;
                    case RIGHT :
                        PacMan.INSTANCE.setDirection(Direction.EAST);
                        break;
                    case UP :
                        PacMan.INSTANCE.setDirection(Direction.NORTH);
                        break;
                    case DOWN :
                        PacMan.INSTANCE.setDirection(Direction.SOUTH);
                        break;
                    default:
                        // do nothing
                        PacMan.INSTANCE.setDirection(PacMan.INSTANCE.getDirection());
                        break;

                }

    }
    public void keyReleasedHandler(KeyEvent event) {
        // Nothing to do?
    }
}
