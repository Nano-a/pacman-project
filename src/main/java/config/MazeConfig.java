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

    /*
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

    private final Cell[][] grid;
    private final IntCoordinates pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos;
    private List<IntCoordinates> ghostPositions; // Déclaration de ghostPositions


    public IntCoordinates getPacManPos() {
        return pacManPos;
    }

    public IntCoordinates getBlinkyPos() {
        return blinkyPos;
    }

    public IntCoordinates getPinkyPos() {
        return pinkyPos;
    }

    public IntCoordinates getInkyPos() {
        return inkyPos;
    }

    public IntCoordinates getClydePos() {
        return clydePos;
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

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
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            grid = new Cell[lines.size()][lines.get(0).length()];
            ghostPositions = new ArrayList<>();
            IntCoordinates tempPacManPos = null; // Variable temporaire pour pacManPos

            for (int y = 0; y < lines.size(); y++) {
                for (int x = 0; x < lines.get(y).length(); x++) {
                    char ch = lines.get(y).charAt(x);
                    switch (ch) {
                        case '1':
                            grid[y][x] = Cell.wall();
                            break;
                        case '2':
                            grid[y][x] = Cell.dot();
                            break;
                        case '3':
                            grid[y][x] = Cell.energizer();
                            break;
                        case '4':
                            tempPacManPos = new IntCoordinates(x, y); // Stockage de la position de Pac-Man
                            grid[y][x] = Cell.empty();
                            break;
                        case '5':
                            ghostPositions.add(new IntCoordinates(x, y));
                            grid[y][x] = Cell.empty();
                            break;
                        default:
                            grid[y][x] = Cell.empty();
                            break;
                    }
                }
            }

            if (tempPacManPos == null) {
                throw new IOException("Pac-Man position not found in the map.");
            }
            this.pacManPos = tempPacManPos; // Affectation de la position de Pac-Man
        }
        // Initialisation des positions (exemples fictifs)
        this.blinkyPos = new IntCoordinates(1, 2); // Position fictive de Blinky
        this.pinkyPos = new IntCoordinates(1, 3); // Position fictive de Pinky
        this.inkyPos = new IntCoordinates(1, 4); // Position fictive de Inky
        this.clydePos = new IntCoordinates(1, 5); // Position fictive de Clyde
    }
}
