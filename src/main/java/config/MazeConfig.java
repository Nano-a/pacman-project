package config;

import geometry.IntCoordinates;
import enums.Cell;
import enums.Direction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeConfig {

    private int rows; // le nombre de lignes dans le fichier texte du labyrinthe
    private int cols; // le nombre de colonnes dans le fichier texte du labyrinthe
    private int nbDots = 0 ; // le nombre de bananes dans le labyrinthe
    public Cell[][] grid; // la grille de cellules du labyrinthe ; le maze
    public IntCoordinates pacManPos;
    public IntCoordinates lionPos;
    public IntCoordinates gorillaPos;
    public IntCoordinates snakePos;
    public IntCoordinates tigerPos;
    public IntCoordinates pacmanDirection;
    public IntCoordinates lionDirection;
    public IntCoordinates gorillaDirection;
    public IntCoordinates snakeDirection;
    public IntCoordinates tigerDirection;

    // getters et pas besoin de setters,on ne va pas écrire sur les fichiers
    public int getRows() {return rows;}
    public void setRows(int rows) {this.rows = rows;}
    public int getCols() {return cols;}
    public void setCols(int cols) {this.cols = cols;}
    public int getNbDots() {return nbDots;}
    public void setNbDots(int nbDots) {this.nbDots = nbDots;}
    public IntCoordinates getPacManPos() {
        return pacManPos;
    }
    public IntCoordinates getLionPos() {return lionPos;}
    public IntCoordinates getGorillaPos() {
        return gorillaPos;
    }
    public IntCoordinates getSnakePos() {
        return snakePos;
    }
    public IntCoordinates getTigerPos() {
        return tigerPos;
    }

   // no constructor no need
    public MazeConfig ( String file ) {
        loadMazeFromFile(file);
    }

    public void numberOfRowsCol ( File file ){
        // cette méthode permet de calculer le nombre de lignes et de colonnes du fichier texte du labyrinthe
        // le principe des niveaux est on initialise le fichier du niveau 1 puis 2 puis 3 , sous réserve de collectionner tous les dots du jeu pour passer à un autre level
        // c'est géré dans le model
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // on calcule le nombre de lignes et de colonnes du fichier texte du labyrinthe
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                lineScanner.next();
                cols++;
            }
            rows++;
        }
        cols = cols/rows; // on a le nombre de colonnes par ligne
    }

    public void loadMazeFromFile(String fileName) {
        File file = new File(fileName);
        numberOfRowsCol(file); // on calcule le nombre de lignes et de colonnes du fichier texte du labyrinthe
        Scanner scanner2 = null;
        try {
            scanner2 = new Scanner(file); // on réinitialise le scanner pour lire le fichier depuis le début
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        grid = new Cell[rows][cols]; // on crée la matrice de cellules ; on a 9 types de cellules ;
        // une grille de cellules de la classe Cell qui est un enum et chaque Cell correspond en vue à une image
        // et non pas à un type Rectangle de Javafx comme auparavant avec des walls
        int row = 0;
        int pacManRow = 0;int pacManColumn = 0;
        int lionRow = 0;int lionColumn = 0;
        int gorillaRow = 0;int gorillaColumn = 0;
        int snakeRow = 0;int snakeColumn = 0;
        int tigerRow = 0;int tigerColumn = 0;
        while(scanner2.hasNextLine()){
            int column = 0;
            String line= scanner2.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()){
                String charactere  = lineScanner.next();
                Cell content;
                if (charactere.equals("T")){
                    content = Cell.TREE;
                }
                else if (charactere.equals("B")){ // banana
                    content = Cell.DOT;
                    nbDots++; // on compte le nombre de bananes
                }
                else if (charactere.equals("L")){ // Load of banans as an energizer
                    content = Cell.ENERGIZER;
                    nbDots++;
                }
                else if (charactere.equals("1")){
                    content = Cell.LION; // la position initiale du lion dans le labyrinthe
                    lionRow = row;
                    lionColumn = column;
                }
                else if (charactere.equals("2")){
                    content = Cell.GORILLA;
                    gorillaRow = row;
                    gorillaColumn = column;
                } else if (charactere.equals("3")){
                    content = Cell.SNAKE;
                    snakeRow = row;
                    snakeColumn = column;
                } else if (charactere.equals("4")){
                    content = Cell.TIGER;
                    tigerRow = row;
                    tigerColumn = column;
                } else if (charactere.equals("P")){
                    content = Cell.PACMAN; // la position initiale de PacMan dans le labyrinthe
                    pacManRow = row;
                    pacManColumn = column;
                }
                else //(value.equals("N")) empty cell
                {
                    content = Cell.NOTHING;
                }
                grid[row][column] = content; //on remplie la grille
                column++; //prochaine colonne
            }
            row++; //prochaine ligne
        }
        // on initilise les positions des entités selon le row et column dans fichier txt
        pacManPos = new IntCoordinates(pacManRow, pacManColumn);
        pacmanDirection = new IntCoordinates(0,0);
        lionPos = new IntCoordinates(lionRow,lionColumn);
        lionDirection = new IntCoordinates(-1, 0);
        gorillaPos = new IntCoordinates(gorillaRow,gorillaColumn);
        gorillaDirection = new IntCoordinates(-1, 0);
        snakePos = new IntCoordinates(snakeRow,snakeColumn);
        snakeDirection = new IntCoordinates(-1, 0);
        tigerPos = new IntCoordinates(tigerRow,tigerColumn);
        tigerDirection = new IntCoordinates(-1, 0);
    }

    public Cell getCell(int row, int column) {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        return this.grid[row][column];
    }

}
