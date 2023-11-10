package model;

import geometry.RealCoordinates;

// Une interface 'sealed' limite les classes qui peuvent l'implémenter.
public sealed interface Critter permits Ghost, PacMan {
    // Méthode pour obtenir la position actuelle du 'Critter'.
    RealCoordinates getPos();

    // Méthode pour obtenir la direction actuelle vers laquelle le 'Critter' est orienté.
    Direction getDirection();

    // Méthode pour obtenir la vitesse du 'Critter'.
    double getSpeed();

    /**
     * Calcule la prochaine position du 'Critter' en fonction de sa vitesse et direction.
     * @param deltaTNanoSeconds le temps écoulé depuis la dernière mise à jour en nanosecondes
     * @return la nouvelle position s'il n'y a pas de mur (la détection des collisions n'est pas incluse ici)
     */
    default RealCoordinates nextPos(long deltaTNanoSeconds) {
        // Convertir le temps de nanosecondes en secondes pour le calcul de la vitesse
        final double deltaTimeSeconds = deltaTNanoSeconds * 1E-9;
        // Renvoie la nouvelle position en ajoutant le changement de position au position actuelle
        return getPos().plus((switch (getDirection()) {
            case NONE -> RealCoordinates.ZERO; // Aucun mouvement si la direction est NONE
            case NORTH -> RealCoordinates.NORTH_UNIT; // Vers le nord
            case EAST -> RealCoordinates.EAST_UNIT; // Vers l'est
            case SOUTH -> RealCoordinates.SOUTH_UNIT; // Vers le sud
            case WEST -> RealCoordinates.WEST_UNIT; // Vers l'ouest
            // Le cas par défaut lance une exception si la direction n'est pas reconnue
            //default -> throw new IllegalStateException("Valeur inattendue: " + getDirection());
        }).times(getSpeed() * deltaTimeSeconds));
    }

    // Définit la position du 'Critter'.
    void setPos(RealCoordinates realCoordinates);

    // Définit la direction vers laquelle le 'Critter' est orienté.
    void setDirection(Direction direction);
}
