package geometry;

// Définition d'un enregistrement (record) Java nommé IntCoordinates.
// Un enregistrement est une manière concise de créer une classe immuable.
public record IntCoordinates(int x, int y) {

    // Méthode pour convertir les coordonnées entières en coordonnées réelles.
    // Prend en paramètre un double 'scale' qui est utilisé pour la conversion.
    public RealCoordinates toRealCoordinates(double scale) {
        // Crée et retourne un nouvel objet RealCoordinates en multipliant les
        // coordonnées x et y par 'scale'.
        return new RealCoordinates(x * scale, y * scale);
    }

    // Constructeur explicite pour IntCoordinates.
    // Il est optionnel dans les enregistrements, car un constructeur par défaut est généré automatiquement.
    public IntCoordinates(int x, int y) {
        // Initialise les champs x et y avec les valeurs fournies.
        this.x = x;
        this.y = y;
    }

    // Méthode pour obtenir la valeur de x.
    // Les méthodes d'accès sont automatiquement générées dans un enregistrement, mais peuvent être redéfinies.
    public int getX() {
        return x;
    }

    // Méthode pour obtenir la valeur de y.
    // Similaire à getX(), permet d'accéder à la valeur de y.
    public int getY() {
        return y;
    }
}
