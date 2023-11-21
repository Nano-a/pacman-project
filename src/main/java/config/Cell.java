package config;

public record Cell(boolean northWall, boolean eastWall, boolean southWall, boolean westWall, Cell.Content initialContent) {
    public enum Content { NOTHING, DOT, ENERGIZER}

    // Default constructor
    public Cell(boolean northWall, boolean eastWall, boolean southWall, boolean westWall, Content initialContent) {
        this.northWall = northWall;
        this.eastWall = eastWall;
        this.southWall = southWall;
        this.westWall = westWall;
        this.initialContent = initialContent;
    }

    // FIXED: multimple factories reduced to few simple ones!
    public static Cell open(Content c) { return new Cell(false, false, false, false, c); }
    public static Cell closed(Content c) { return new Cell(true, true, false, false, c); }
    // straight pipes
    public static Cell pipe(char polarity, Content c) {
        boolean northWall = false;
        boolean eastWall = false;
        boolean southWall = false;
        boolean westWall = false;

        if (polarity == 'h') {
            northWall = true;
            southWall = true;
        } else if (polarity == 'v') {
            eastWall = true;
            westWall = true;
        }

        return new Cell(northWall, eastWall, southWall, westWall, c);
    }
    // corner cells
    public static Cell corner(char wall1, char wall2, Content c) {
        boolean northWall = false;
        boolean eastWall = false;
        boolean southWall = false;
        boolean westWall = false;

        if (wall1 == 'n' && wall2 == 'e') {
            northWall = true;
            eastWall = true;
        }
        if (wall1 == 'n' && wall2 == 'w') {
            northWall = true;
            westWall = true;
        }
        if (wall1 == 's' && wall2 == 'e') {
            southWall = true;
            eastWall = true;
        }
        if (wall1 == 's' && wall2 == 'w') {
            southWall = true;
            westWall = true;
        }

        return new Cell(northWall, eastWall, southWall, westWall, c);
    }
    // u-shaped cells
    public static Cell shapeU(char polarity, Content c) {
        boolean northWall = true;
        boolean eastWall = true;
        boolean southWall = true;
        boolean westWall = true;

        if (polarity == 'n') {
            northWall = false;
        } else if (polarity == 'e') {
            eastWall = false;
        } else if (polarity == 's') {
            southWall = false;
        } else if (polarity == 'w') {
            westWall = false;
        }

        return new Cell(northWall, eastWall, southWall, westWall, c);
    }
    // T-shaped Cells
    public static Cell shapeT(char polarity, Content c) {
        boolean northWall = false;
        boolean eastWall = false;
        boolean southWall = false;
        boolean westWall = false;

        if (polarity == 'n') {
            northWall = true;
        } else if (polarity == 'e') {
            eastWall = true;
        } else if (polarity == 's') {
            southWall = true;
        } else if (polarity == 'w') {
            westWall = true;
        }

        return new Cell(northWall, eastWall, southWall, westWall, c);
    }
}


