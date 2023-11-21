package config;

import geometry.IntCoordinates;

import static config.Cell.Content.DOT;
import static config.Cell.*;
import static config.Cell.Content.NOTHING;

public class MazeConfig {
    public MazeConfig(Cell[][] grid, IntCoordinates pacManPos, IntCoordinates blinkyPos, IntCoordinates pinkyPos,
                      IntCoordinates inkyPos, IntCoordinates clydePos) {
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

    private final Cell[][] grid;
    private final IntCoordinates pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos;

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
    public static MazeConfig makeExample1() {
        return new MazeConfig(new Cell[][]{
                {corner('n', 'w', DOT),   pipe('h', DOT),    shapeT('n', DOT),    pipe('h', DOT),   pipe('h', DOT),     corner('n', 'e', DOT)},
                {corner('s', 'w', DOT),   shapeT('n', DOT),   shapeT('s', NOTHING),  pipe('h', DOT), shapeT('n', DOT), corner('s', 'e', DOT)},
                {shapeU('w', NOTHING),   shapeT('w', DOT),    pipe('h', DOT),  pipe('h', DOT),   shapeT('e', DOT),  shapeU('e', DOT)},
                {pipe('h', DOT), open(DOT), pipe('h', DOT),  pipe('h', DOT),  open(DOT), pipe('h', DOT)},
                {shapeU('w', NOTHING),   pipe('v', DOT),    shapeU('e', NOTHING),  shapeU('w', NOTHING),   pipe('v', DOT),  shapeU('e', NOTHING)},
                {pipe('h', DOT),    shapeT('s', DOT),     pipe('h', DOT),     pipe('h', DOT),     shapeT('s', DOT),     pipe('h', DOT)}


        },
                new IntCoordinates(3, 0),
                new IntCoordinates(0, 3),
                new IntCoordinates(3, 5),
                new IntCoordinates(5, 5),
                new IntCoordinates(5, 1)
        );
    }
}