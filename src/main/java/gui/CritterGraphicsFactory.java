/*
 * Ce fichier est responsable de la création des représentations graphiques des "critters"
 * (créatures) dans le jeu, comme Pacman et les fantômes.
 */
package gui;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Critter;
import model.Ghost;
import model.PacMan;

// Cette classe est dédiée à la création des éléments graphiques pour les "critters" du jeu (Pacman et fantômes).
public final class CritterGraphicsFactory {
    private final double scale;

     // Constructeur prenant un facteur d'échelle pour les éléments graphiques.
    public CritterGraphicsFactory(double scale) {
        this.scale = scale;
    }

    // Méthode principale pour créer les graphiques en fonction du type de "critter".
    public GraphicsUpdater makeGraphics(Critter critter) {
        var size = 0.7; // Taille standard pour les éléments graphiques.
        var url = (critter instanceof PacMan) ? "pacman.png" : // URL de l'image pour le Pacman.
                switch ((Ghost) critter) { // URL de l'image pour les fantômes.
                    case BLINKY -> "ghost_blinky.png";
                    case CLYDE -> "ghost_clyde.png";
                    case INKY -> "ghost_inky.png";
                    case PINKY -> "ghost_pinky.png";
                };
        // Création de l'élément graphique (ImageView) à partir de l'URL de l'image.
        var image = new ImageView(new Image(url, scale * size, scale * size, true, true));
        return new GraphicsUpdater() {
            // Méthode pour mettre à jour la position de l'élément graphique.
            @Override
            // Mise à jour de la position en fonction de la position du critter dans le modèle de jeu.
            public void update() {
                image.setTranslateX((critter.getPos().x() + (1 - size) / 2) * scale);
                image.setTranslateY((critter.getPos().y() + (1 - size) / 2) * scale);
                // Debug.out("sprite updated");
            }

            // Méthode pour obtenir l'élément graphique.
            @Override
            public Node getNode() {
                return image;
            }
        };
    }
}
