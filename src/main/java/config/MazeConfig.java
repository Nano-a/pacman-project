package config;

import geometry.IntCoordinates;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeConfig {

    private  Cell[][] grid;
    private  IntCoordinates pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos;

    // constructeur
    public MazeConfig(Cell[][] grid, IntCoordinates pacManPos, IntCoordinates blinkyPos, IntCoordinates pinkyPos,
                      IntCoordinates inkyPos, IntCoordinates clydePos) {
        this.grid = grid ;
        this.pacManPos = pacManPos;
        this.blinkyPos = blinkyPos;
        this.inkyPos = inkyPos;
        this.pinkyPos = pinkyPos;
        this.clydePos = clydePos;
    }


    // getters
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

    public  int getWidth() {
        return grid[0].length;
    }

    public  int getHeight() {
        return grid.length;
    }

    public Cell getCell(IntCoordinates pos) {
        return grid[Math.floorMod(pos.y(), getHeight())][Math.floorMod(pos.x(), getWidth())];
    }

    private MazeConfig loadMazeFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/config/pacman_maze.txt"))) {

            String line;
            Cell[][] grid = new Cell[31][28];  // to fix to generalise for each text file
            int row = 0;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split("");

                for (int col = 0; col < columns.length; col++) {
                    grid[row][col] = parseCell(columns[col]);
                }

                row++;
            }

            // Use the Cell[][] grid to create a MazeConfig
            IntCoordinates pacManPos = new IntCoordinates(15, 15);
            IntCoordinates blinkyPos = new IntCoordinates(0, 3);
            IntCoordinates pinkyPos = new IntCoordinates (3, 5);
            IntCoordinates inkyPos = new IntCoordinates(5,5);
            IntCoordinates clydePos = new IntCoordinates(5,1);

            this.grid = grid ;
            this.pacManPos = pacManPos;
            this.blinkyPos = blinkyPos;
            this.inkyPos = inkyPos;
            this.pinkyPos = pinkyPos;
            this.clydePos = clydePos;

            return new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Cell parseCell(String cellValue) {
        int value = Integer.parseInt(cellValue);
        switch (value) {
            case 0:
                return Cell.open(Cell.Content.NOTHING);  // Open space
            case 1:
                return Cell.closed(Cell.Content.NOTHING); // Closed space
            case 2:
                return Cell.open(Cell.Content.DOT);  // Dot
            case 3:
                return Cell.open(Cell.Content.ENERGIZER); // Energizer
            case 4:
                return Cell.open(Cell.Content.NOTHING); // FIXME

            case 5 :
                return Cell.open(Cell.Content.NOTHING); // FIXME

            case 6 :
                return Cell.open(Cell.Content.NOTHING); //FIXME

            case 7 :
                return Cell.open(Cell.Content.NOTHING); //FIXME

            case 8 :
                return Cell.open(Cell.Content.NOTHING); //FIXME

            default:
                return Cell.open(Cell.Content.NOTHING);  //FIXME
        }
    }

    public static MazeConfig makeExample1() {
        // FIXME : static call problem , but we should only call loadMazeFromFile , the first creation of m is useless
        MazeConfig m  = new MazeConfig(new Cell[8][9], new IntCoordinates(3, 0),
        new IntCoordinates(15, 15),
         new IntCoordinates (3, 5),
        new IntCoordinates(5,5),
        new IntCoordinates(5,1));

        //labyrinth loaded from the file
        m.loadMazeFromFile();
        return m ;

    }
}
