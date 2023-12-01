package config;

import geometry.IntCoordinates;

import static config.Cell.Content.DOT;
import static config.Cell.*;
import static config.Cell.Content.NOTHING;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MazeConfig {

    /*Code precedent qui a été retiré
     * public MazeConfig(Cell[][] grid, IntCoordinates pacManPos, IntCoordinates blinkyPos, IntCoordinates pinkyPos, IntCoordinates inkyPos, IntCoordinates clydePos) {
        this.grid = new Cell[grid.length][grid[0].length];
        for (int i = 0; i < getHeight(); i++) {
            if (getWidth() >= 0) System.arraycopy(grid[i], 0, this.grid[i], 0, getHeight());
        }
        this.pacManPos = pacManPos;
        this.blinkyPos = blinkyPos;
        this.inkyPos = inkyPos;
        this.pinkyPos = pinkyPos;
        this.clydePos = clydePos;
    }
     */

    // Déclaration des variables d'instance pour le labyrinthe et les positions des personnages.
    private final Cell[][] grid;
    private final IntCoordinates pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos;
    private List<IntCoordinates> ghostPositions; // Déclaration de ghostPositions


    // Méthodes pour obtenir les positions de Pac-Man et des fantômes.
    public IntCoordinates getPacManPos() { return pacManPos; }
    public IntCoordinates getBlinkyPos() { return blinkyPos; }
    public IntCoordinates getPinkyPos() { return pinkyPos; }
    public IntCoordinates getInkyPos() { return inkyPos; }
    public IntCoordinates getClydePos() { return clydePos; }

    // Méthodes pour obtenir la largeur et la hauteur du labyrinthe.
    public int getWidth() { return grid[0].length; }
    public int getHeight() { return grid.length; }


    // Méthode pour obtenir une cellule spécifique à partir de ses coordonnées.
    public Cell getCell(IntCoordinates pos) {
        return grid[Math.floorMod(pos.y(), getHeight())][Math.floorMod(pos.x(), getWidth())];
    }

    // simple example with a square shape
    // TODO: mazes should be loaded from a text file
    /*
     * public static MazeConfig makeExample1() {
        return new MazeConfig(new Cell[][]{
                {nTee(DOT),    hPipe(DOT),     hPipe(DOT),     hPipe(DOT),     hPipe(DOT),     nTee(DOT)},
                {vPipe(DOT),    seVee(NOTHING), nTee(NOTHING),  nTee(NOTHING),  swVee(NOTHING), vPipe(DOT)},
                {vPipe(DOT),     wTee(NOTHING),  open(NOTHING),  open(NOTHING),  eTee(NOTHING),  vPipe(DOT)},
                {vPipe(DOT),    wTee(NOTHING),  open(NOTHING),  open(NOTHING),  eTee(NOTHING),  vPipe(DOT)},
                {vPipe(DOT),    neVee(NOTHING), sTee(NOTHING),  sTee(NOTHING),   nwVee(NOTHING), vPipe(DOT)},
                {neVee(DOT),    hPipe(DOT),     hPipe(DOT),     hPipe(DOT),     hPipe(DOT),     nwVee(DOT)}
        },
                new IntCoordinates(3, 0),
                new IntCoordinates(0, 3),
                new IntCoordinates(3, 5),
                new IntCoordinates(5, 5),
                new IntCoordinates(5, 1)
        );
    }
     */
    public MazeConfig(String resourcePath) throws IOException {
        // Chargement d'une ressource (comme un fichier de configuration pour le labyrinthe) à partir du chemin donné.
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
    
        // Vérification si la ressource est trouvée. Si non, une exception est lancée.
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }
    
        // Utilisation d'un BufferedReader pour lire le contenu de la ressource.
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // Lecture de toutes les lignes de la ressource et stockage dans une liste.
            List<String> lines = reader.lines().collect(Collectors.toList());
    
            // Initialisation du tableau de cellules 'grid' selon la taille des lignes lues.
            grid = new Cell[lines.size()][lines.get(0).length()];
            ghostPositions = new ArrayList<>(); // Initialisation de la liste pour les positions des fantômes.
            IntCoordinates tempPacManPos = null; // Variable temporaire pour stocker la position de Pac-Man.
    
            // Parcours de chaque caractère dans chaque ligne pour construire le labyrinthe.
            for (int y = 0; y < lines.size(); y++) {
                for (int x = 0; x < lines.get(y).length(); x++) {
                    char ch = lines.get(y).charAt(x); // Lecture du caractère actuel.
    
                    // Utilisation d'un switch pour déterminer l'action en fonction du caractère lu.
                    switch (ch) {
                        case '1':
                            grid[y][x] = Cell.wall(); // Placer un mur.
                            break;
                        case '2':
                            grid[y][x] = Cell.dot(); // Placer un point.
                            break;
                        case '3':
                            grid[y][x] = Cell.energizer(); // Placer un énergisant.
                            break;
                        case '4':
                            tempPacManPos = new IntCoordinates(x, y); // Stockage de la position de Pac-Man.
                            grid[y][x] = Cell.empty(); // Marquer la cellule comme vide.
                            break;
                        case '5':
                            ghostPositions.add(new IntCoordinates(x, y)); // Ajouter la position du fantôme.
                            grid[y][x] = Cell.empty(); // Marquer la cellule comme vide.
                            break;
                        default:
                            grid[y][x] = Cell.empty(); // Par défaut, la cellule est vide.
                            break;
                    }
                }
            }
    
            // Vérification si la position de Pac-Man a été trouvée.
            if (tempPacManPos == null) {
                throw new IOException("Pac-Man position not found in the map.");
            }
            this.pacManPos = tempPacManPos; // Affectation de la position de Pac-Man à la variable d'instance.
        }
    
    
        // Initialisation des positions (exemples fictifs)
        this.blinkyPos = new IntCoordinates(1, 2); // Position fictive de Blinky
        this.pinkyPos = new IntCoordinates(1, 3); // Position fictive de Pinky
        this.inkyPos = new IntCoordinates(1, 4); // Position fictive de Inky
        this.clydePos = new IntCoordinates(1, 5); // Position fictive de Clyde
    }
}
