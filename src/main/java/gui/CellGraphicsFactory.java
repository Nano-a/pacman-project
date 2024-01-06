/*
 * Ce fichier joue un rôle crucial dans la représentation graphique des cellules
 * du labyrinthe dans le jeu
 */

package gui;

import geometry.IntCoordinates;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.MazeState;

import static config.Cell.Content.DOT;

// Cette classe est responsable de la création des éléments graphiques pour les cellules du labyrinthe.
public class CellGraphicsFactory {
    private final double scale;

    // Constructeur de la classe. Prend un paramètre d'échelle pour le rendu graphique.
    public CellGraphicsFactory(double scale) {
        this.scale = scale;
    }

    // Méthode pour créer les éléments graphiques d'une cellule du labyrinthe basée sur son état.
    public GraphicsUpdater makeGraphics(MazeState state, IntCoordinates pos) {
        var group = new Group();
        group.setTranslateX(pos.x()*scale);
        group.setTranslateY(pos.y()*scale);
        var cell = state.getConfig().getCell(pos);
        var dot = new Circle();
        double radius;
        group.getChildren().add(dot);
        // Utilise l'état du labyrinthe et la position pour déterminer le contenu de la cellule.
        switch (cell.initialContent()) {
            case DOT:
                radius = scale / 15;
                break;
            case ENERGIZER:
                radius = scale / 5;
                break;
            case NOTHING:
            default:
                radius = 0;
                break;
        }
        dot.setRadius(radius);
        dot.setCenterX(scale/2);
        dot.setCenterY(scale/2);
        dot.setFill(Color.YELLOW);
        if (cell.northWall()) {
            var nWall = new Rectangle();
            nWall.setHeight(scale/10);
            nWall.setWidth(scale);
            nWall.setY(0);
            nWall.setX(0);
            nWall.setFill(Color.BLUEVIOLET);
            group.getChildren().add(nWall);
        }
        if (cell.eastWall()) {
            var nWall = new Rectangle();
            nWall.setHeight(scale);
            nWall.setWidth(scale/10);
            nWall.setY(0);
            nWall.setX(9*scale/10);
            nWall.setFill(Color.BLUEVIOLET);
            group.getChildren().add(nWall);
        }
        if (cell.southWall()) {
            var nWall = new Rectangle();
            nWall.setHeight(scale/10);
            nWall.setWidth(scale);
            nWall.setY(9*scale/10);
            nWall.setX(0);
            nWall.setFill(Color.BLUEVIOLET);
            group.getChildren().add(nWall);
        }
        if (cell.westWall()) {
            var nWall = new Rectangle();
            nWall.setHeight(scale);
            nWall.setWidth(scale/10);
            nWall.setY(0);
            nWall.setX(0);
            nWall.setFill(Color.BLUEVIOLET);
            group.getChildren().add(nWall);
        }
        return new GraphicsUpdater() {
            @Override
            public void update() {
                dot.setVisible(!state.getGridState(pos));
            }

            @Override
            public Node getNode() {
                return group;
            }
        };
    }
}
