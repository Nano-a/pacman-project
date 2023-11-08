package model;

import geometry.RealCoordinates;
import java.util.Random;

public enum Ghost implements Critter {

    BLINKY {
        @Override
        public Direction decideNextDirection(MazeState maze, PacMan pacMan) {
            // Get Blinky's current position
            RealCoordinates blinkyPos = this.getPos();
            // Get Pac-Man's current position
            RealCoordinates pacManPos = pacMan.getPos();
            
            // Determine the horizontal and vertical distances from Blinky to Pac-Man
            double diffX = pacManPos.x() - blinkyPos.x();
            double diffY = pacManPos.y() - blinkyPos.y();
            
            // Determine whether to prioritize horizontal or vertical movement
            // (Here, we simply choose the direction with the larger distance)
            if (Math.abs(diffX) > Math.abs(diffY)) {
                // Move horizontally
                return diffX > 0 ? Direction.EAST : Direction.WEST;
            } else {
                // Move vertically
                return diffY > 0 ? Direction.SOUTH : Direction.NORTH;
            }
        }
    },
    INKY {
        @Override
        public Direction decideNextDirection(MazeState maze, PacMan pacMan) {
            // Inky's AI logic goes here
            return Direction.NONE; // Placeholder
        }
    },
    PINKY {
        @Override
        public Direction decideNextDirection(MazeState maze, PacMan pacMan) {
            // Pinky's AI logic goes here
            return Direction.NONE; // Placeholder
        }
    },
    CLYDE {
        @Override
        public Direction decideNextDirection(MazeState maze, PacMan pacMan) {
            // Clyde's AI logic goes here
            return Direction.NONE; // Placeholder
        }
    };

    private RealCoordinates pos;
    private Direction direction = Direction.NONE;
    
    // Common methods for all ghosts
    @Override
    public RealCoordinates getPos() {
        return pos;
    }

    @Override
    public void setPos(RealCoordinates newPos) {
        pos = newPos;
    }

    @Override
    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public double getSpeed() {
        return 0; // This should probably be changed to the actual speed of ghosts
    }

    // New method that each Ghost will implement for its AI
    public abstract Direction decideNextDirection(MazeState maze, PacMan pacMan);
    
}