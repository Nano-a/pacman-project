package model;

import geometry.RealCoordinates;

import java.util.ArrayList;
import java.util.List;
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
    PINKY {
        @Override
        public Direction decideNextDirection(MazeState maze, PacMan pacMan) {
            RealCoordinates pinkyPos = this.getPos();
            RealCoordinates targetPos = pacMan.getPos().offset(pacMan.getDirection(), 4);

    
            // Implement the logic to choose the direction based on targetPos
            // and the current position of Pinky, ensuring it's not a wall.
            // Placeholder for logic
            return determineDirection(maze, pinkyPos, targetPos);
        }
    },
    INKY {
        @Override
        public Direction decideNextDirection(MazeState maze, PacMan pacMan) {
            RealCoordinates inkyPos = this.getPos();
            RealCoordinates blinkyPos = BLINKY.getPos();
            RealCoordinates pacManPos = pacMan.getPos().offset(pacMan.getDirection(), 2);
            
            // Calculate the reflection of Pac-Man's position relative to Blinky's position
            RealCoordinates targetPos = new RealCoordinates(
                2 * blinkyPos.x() - pacManPos.x(),
                2 * blinkyPos.y() - pacManPos.y()
            );
    
            // Implement the logic to choose the direction based on targetPos
            // and the current position of Inky, ensuring it's not a wall.
            // Placeholder for logic
            return determineDirection(maze, inkyPos, targetPos);
        }
    },
    CLYDE {
        @Override
        public Direction decideNextDirection(MazeState maze, PacMan pacMan) {
            RealCoordinates clydePos = this.getPos();
            RealCoordinates pacManPos = pacMan.getPos();
            
            double distance = clydePos.distance(pacManPos);
    
            if (distance < 5) {
                // Implement the logic for Clyde to take a random direction away from Pac-Man
                // Placeholder for logic
                return takeRandomDirection(maze, clydePos);
            } else {
                // If Clyde is not too close, he pursues Pac-Man like Blinky.
                // You can reuse Blinky's logic here or customize it for Clyde.
                return determineDirection(maze, clydePos, pacManPos);
            }
        }
    };
    
    // This is a helper method to determine direction based on the current position and target position
    private static Direction determineDirection(MazeState maze, RealCoordinates currentPos, RealCoordinates targetPos) {
        Direction bestDirection = Direction.NONE;
        double smallestDistance = Double.MAX_VALUE;
    
        for (Direction direction : Direction.values()) {
            if (maze.isDirectionAvailable(currentPos, direction)) {
                RealCoordinates newPos = currentPos.offset(direction, 1);
                double distance = newPos.distance(targetPos);
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    bestDirection = direction;
                }
            }
        }

        return bestDirection;
    }

    private static Direction takeRandomDirection(MazeState maze, RealCoordinates currentPos) {
        List<Direction> availableDirections = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            if (maze.isDirectionAvailable(currentPos, direction)) {
                availableDirections.add(direction);
            }
        }    

        if (!availableDirections.isEmpty()) {
            int randomIndex = new Random().nextInt(availableDirections.size());
            return availableDirections.get(randomIndex);
        } else {
            return Direction.NONE; // No available direction found
        }
    }

    

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
        return 10; // This should probably be changed to the actual speed of ghosts
    }

    // New method that each Ghost will implement for its AI
    public abstract Direction decideNextDirection(MazeState maze, PacMan pacMan);
    
}