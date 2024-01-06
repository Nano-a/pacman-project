package geometry;

import java.util.Objects;

/**
 * Pour les coordonnées entières, on utilise la classe IntCoordinates ; pour pacman et les animaux
 * au lieu de RealCoordinates
 */

public class IntCoordinates {

    private int x ;
    private int y ;

    // Obtient la coordonnée x
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Constructeur initialisant les coordonnées x et y
    public IntCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Ajoute les coordonnées d'un autre point à ce point
    public IntCoordinates add(IntCoordinates point) {

        return add(point.getX(), point.getY());
    }

    // Ajoute les coordonnées spécifiées à ce point
    public IntCoordinates add(int x, int y) {
        return new IntCoordinates(
                getX() + x,
                getY() + y);
    }

    // Vérifie l'égalité entre ce point et un autre objet
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof IntCoordinates) {
            IntCoordinates other = (IntCoordinates) obj;
            return getX() == other.getX() && getY() == other.getY();
        } else return false;
    }

    // Non utilisé dans notre application
    public RealCoordinates toRealCoordinates(double scale)
    {
        return new RealCoordinates(x * scale, y * scale);
    }



}
