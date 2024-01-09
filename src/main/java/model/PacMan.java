package model;

import geometry.RealCoordinates;
import enums.Direction;


// Classe pour représenter Pac-Man, implémentant l'interface Critter.
public final class PacMan implements Critter {
    private Direction direction = Direction.NONE; // Direction actuelle de Pac-Man.
    private RealCoordinates pos; // Position actuelle de Pac-Man.
    private boolean energized; // Indique si Pac-Man est sous l'effet d'un énergisant.

    // Constructeur privé pour le motif Singleton.
    private PacMan() {
    }

    // Instance unique de Pac-Man (Singleton).
    public static final PacMan INSTANCE = new PacMan();

    // Implémentation des méthodes de l'interface Critter.
    @Override
    public RealCoordinates getPos() {
        return pos;
    }

    @Override
    public double getSpeed() {
        return isEnergized() ? 6 : 4;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setPos(RealCoordinates pos) {
        this.pos = pos;
    }

    /**
     *
     * @return whether Pac-Man just ate an energizer
     */
    public boolean isEnergized() {
        // TODO handle timeout!
        return energized;
    }

    public void setEnergized(boolean energized) {
        this.energized = energized;
    }
}
