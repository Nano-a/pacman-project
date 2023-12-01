package model;

import geometry.RealCoordinates;

// Interface Critter, scellée pour permettre uniquement certaines classes (Ghost, PacMan) à l'étendre.
public sealed interface Critter permits Ghost, PacMan {
    // Méthodes abstraites à implémenter par les classes concrètes.
    RealCoordinates getPos();
    Direction getDirection();
    double getSpeed();
    // Méthode par défaut pour calculer la position suivante de la créature.
    /**
     * @param deltaTNanoSeconds temps écoulé depuis la dernière mise à jour en nanosecondes
     * @return la prochaine position si aucun mur n'est présent
     */
    default RealCoordinates nextPos(long deltaTNanoSeconds) {
        return getPos().plus((switch (getDirection()) {
            case NONE -> RealCoordinates.ZERO;
            // ... logique pour calculer la position suivante en fonction de la direction ...
            case NORTH -> RealCoordinates.NORTH_UNIT;
            case EAST -> RealCoordinates.EAST_UNIT;
            case SOUTH -> RealCoordinates.SOUTH_UNIT;
            case WEST -> RealCoordinates.WEST_UNIT;
        }).times(getSpeed()*deltaTNanoSeconds * 1E-9));
    }

    void setPos(RealCoordinates realCoordinates);
    void setDirection(Direction direction);
}
