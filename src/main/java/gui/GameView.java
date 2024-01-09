package gui;

import enums.Cell;
import enums.Direction;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.MazeState;
import config.MazeConfig;
import java.util.Objects;

/**
 * Description : gérer la vue graphique du jeu
 */
public class GameView extends Group {
    // extends Group car on va ajouter des éléments graphiques à la vue dans un fichier fxml (GamePage.fxml)
    // class parameters
    public final static double CELL_WIDTH = 30.0; // l'ideale pour ne pas dépasser le frame de l'écran
    private int rows;
    private int cols;
    private MazeConfig maze; // pour récupérer la structure du labyrinthe ; la position de pacman et des ...
    private ImageView[][] cellImages; // Une grille de 2D pour stocker les images de chaque cellule du labyrinthe
    // c'est ainsi que le labyrinthe est affiché

    // Images pour les différents éléments du jeu
    private Image pacmanEastImage;
    private Image pacmanNorthImage;
    private Image pacmanSouthImage;
    private Image pacmanWestImage;
    private Image lionImage;
    private Image gorillaImage;
    private Image snakeImage;
    private Image tigerImage;
    private Image bluelionImage;
    private Image bluegorillaImage;
    private Image bluesnakeImage;
    private Image bluetigerImage;
    private Image treeImage;
    private Image loadBanana;
    private Image banana;

    public GameView() {
        // Initialisation des images à partir des ressources (chargement des images depuis des fichiers)

        this.pacmanEastImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacmanRight.gif")));
        this.pacmanNorthImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacmanUp.gif")));
        this.pacmanSouthImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacmanDown.gif")));
        this.pacmanWestImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pacmanLeft.gif")));

        this.lionImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/lionRight.gif")));
        this.bluelionImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bluelion.gif")));

        this.gorillaImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/GorillaDown.gif")));
        this.bluegorillaImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bluegorilla.gif")));

        this.snakeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/snake.gif")));
        this.bluesnakeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bluesnake.gif")));

        this.tigerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tiger.gif")));
        this.bluetigerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bluetiger.gif")));

        this.treeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/arbre.png")));
        this.loadBanana = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/energ.png")));
        this.banana = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bananaa.png")));

    }

    private void initializeGrid() {
        // Initialisation de la grille de 2D pour stocker les images de chaque cellule du labyrinthe
        // CELL_WIDTH est la taille d'une cellule en pixels , on peut changer la taille de l'écran du labyrinthe avec
        if (this.rows > 0 && this.cols > 0) {
            this.cellImages = new ImageView[this.rows][this.cols];
            for (int row = 0; row < this.rows; row++) {
                for (int column = 0; column < this.cols; column++) {
                    ImageView imageView = new ImageView();
                    imageView.setX((double) column * CELL_WIDTH);
                    imageView.setY((double) row * CELL_WIDTH);
                    imageView.setFitWidth(CELL_WIDTH);
                    imageView.setFitHeight(CELL_WIDTH);
                    this.cellImages[row][column] = imageView;
                    this.getChildren().add(imageView);
                }
            }
        }
    }

    public void animate( MazeState state) {
         // Met à jour la vue du jeu en fonction de l'état du jeu
        // Récupération de la configuration du labyrinthe depuis l'état du jeu
        maze = state.getConfig();

        // Vérification de la taille du labyrinthe pour la correspondance avec la grille d'images
        assert maze.getRows() == this.rows && maze.getCols() == this.cols;

        // Parcours de chaque cellule du labyrinthe pour afficher les éléments correspondants dans la vue
        for (int row = 0; row < this.rows; row++){
            for (int column = 0; column < this.cols; column++){
                Cell value = maze.getCell(row, column);
                if (value == Cell.TREE) {
                    this.cellImages[row][column].setImage(this.treeImage);
                }
                else if (value == Cell.ENERGIZER) {
                    this.cellImages[row][column].setImage(this.loadBanana);
                }
                else if (value == Cell.DOT) {
                    this.cellImages[row][column].setImage(this.banana);
                }
                else {
                    this.cellImages[row][column].setImage(null);
                }

                // Adapter l'image de PACMAN selon sa direction
                if (row == maze.pacManPos.getX() && column == maze.pacManPos.getY() && (state.getLastDirection() == Direction.EAST || state.getLastDirection() == Direction.NONE)) {
                    this.cellImages[row][column].setImage(this.pacmanEastImage);
                }
                else if (row == maze.pacManPos.getX() && column == maze.pacManPos.getY() && state.getLastDirection() == Direction.WEST) {
                    this.cellImages[row][column].setImage(this.pacmanWestImage);
                }
                else if (row == maze.pacManPos.getX() && column == maze.pacManPos.getY() && state.getLastDirection() == Direction.NORTH) {
                    this.cellImages[row][column].setImage(this.pacmanNorthImage);
                }
                else if (row == maze.pacManPos.getX() && column == maze.pacManPos.getY() && state.getLastDirection() == Direction.SOUTH) {
                    this.cellImages[row][column].setImage(this.pacmanSouthImage);
                }

                // Clignoter les animaux grace au counter du ghostEatingMode (6,4,2) ; image normale else image en bleu
                if (MazeState.isGhostEatingMode() && (Controller.getGhostEatingModeCounter() == 6 ||Controller.getGhostEatingModeCounter() == 4 || Controller.getGhostEatingModeCounter() == 2)) {
                    if (row == maze.lionPos.getX() && column == maze.lionPos.getY()) {
                        this.cellImages[row][column].setImage(this.lionImage);
                    }
                    if (row == maze.gorillaPos.getX() && column == maze.gorillaPos.getY()) {
                        this.cellImages[row][column].setImage(this.gorillaImage);
                    }
                    if (row == maze.snakePos.getX() && column == maze.snakePos.getY()) {
                        this.cellImages[row][column].setImage(this.snakeImage);
                    }
                    if (row == maze.tigerPos.getX() && column == maze.tigerPos.getY()) {
                        this.cellImages[row][column].setImage(this.tigerImage);
                    }
                }
                // Animaux en bleu si ghostEatingMode est activé
                else if (MazeState.isGhostEatingMode()) {
                    if (row == maze.lionPos.getX() && column == maze.lionPos.getY()) {
                        this.cellImages[row][column].setImage(this.bluelionImage);
                    }
                    if (row == maze.gorillaPos.getX() && column == maze.gorillaPos.getY()) {
                        this.cellImages[row][column].setImage(this.bluegorillaImage);
                    }
                    if (row == maze.snakePos.getX() && column == maze.snakePos.getY()) {
                        this.cellImages[row][column].setImage(this.bluesnakeImage);
                    }
                    if (row == maze.tigerPos.getX() && column == maze.tigerPos.getY()) {
                        this.cellImages[row][column].setImage(this.bluetigerImage);
                    }
                }
                // sinon images normales
                else {
                    if (row == maze.lionPos.getX() && column == maze.lionPos.getY()) {
                        this.cellImages[row][column].setImage(this.lionImage);
                    }
                    if (row == maze.gorillaPos.getX() && column == maze.gorillaPos.getY()) {
                        this.cellImages[row][column].setImage(this.gorillaImage);
                    }
                    if (row == maze.snakePos.getX() && column == maze.snakePos.getY()) {
                        this.cellImages[row][column].setImage(this.snakeImage);
                    }
                    if (row == maze.tigerPos.getX() && column == maze.tigerPos.getY()) {
                        this.cellImages[row][column].setImage(this.tigerImage);
                    }
                }
            }
        }
    }

    //Getters et setters

    public int getRowCount() {
        return this.rows;
    }

    public void setRowCount(int rowCount) {
        this.rows = rowCount;
        this.initializeGrid();
    }

    public int getColumnCount() {
        return this.cols;
    }

    public void setColumnCount(int columnCount) {
        this.cols = columnCount;
        this.initializeGrid();
    }

}
