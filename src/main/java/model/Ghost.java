package model;

import geometry.RealCoordinates;

// Classe énumération pour les fantômes, implémentant l'interface Critter.
public enum Ghost implements Critter {
    // Définition des différents types de fantômes.
    BLINKY, INKY, PINKY, CLYDE;

    // Attributs pour la position et la direction du fantôme.
    private RealCoordinates pos;
    private Direction direction = Direction.NONE;

    // Implémentation des méthodes de l'interface Critter.
    @Override
    public RealCoordinates getPos() {
        return pos;
    }

    @Override
    public void setPos(RealCoordinates newPos) {
        pos = newPos;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public double getSpeed() {
        return 0;
    }

}