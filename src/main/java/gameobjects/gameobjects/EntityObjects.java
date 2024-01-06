package gameobjects;

/**
 Représente les différentes entités qui se déplacent sur la carte et stockent les informations
 de délai avant de quitter le point de spawn.
 */

public enum EntityObjects {
    PLAYER(0),
    BLINKY(0),
    INKY(1000),
    PINKY(2000),
    CLYDE(3000);

    private int delay;
    EntityObjects(int delay) {this.delay = delay; }
    public int getLeaveDelay() { return delay; }
    public static EntityObjects[] ghosts() { return new EntityObjects[] {BLINKY, INKY, PINKY, CLYDE}; }
}