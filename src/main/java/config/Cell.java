package config;

public record Cell(boolean northWall, boolean eastWall, boolean southWall, boolean westWall, Cell.Content initialContent) {
    public enum Content { NOTHING, DOT, ENERGIZER, WALL}
    public static class Builder {
        private boolean northWall = false;
        private boolean eastWall = false;
        private boolean southWall = false;
        private boolean westWall = false;
        private Content content = Content.NOTHING;

        public Builder withNorthWall(boolean northWall) {
            this.northWall = northWall;
            return this;
        }

        public Builder withEastWall(boolean eastWall) {
            this.eastWall = eastWall;
            return this;
        }

        public Builder withSouthWall(boolean southWall) {
            this.southWall = southWall;
            return this;
        }

        public Builder withWestWall(boolean westWall) {
            this.westWall = westWall;
            return this;
        }

        public Builder withContent(Content content) {
            this.content = content;
            return this;
        }

        public Cell build() {
            return new Cell(northWall, eastWall, southWall, westWall, content);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
    public static Cell wU(Content c) { return new Cell(true, true, true, false, c); }
    // U-shaped cells
    public static Cell nTee(Content c) { return new Cell(true, false, false, false, c); }
    public static Cell eTee(Content c) { return new Cell(false, true, false, false, c); }
    public static Cell sTee(Content c) { return new Cell(false, false, true, false, c); }
    public static Cell wTee(Content c) { return new Cell(false, false, false, true, c); }
    public static Cell vPipe(Content c) { return new Cell(false, true, false, true, c); }
    public static Cell neVee(Content c) { return new Cell(false, false, true, true, c); }
    public static Cell hPipe(Content c) { return new Cell(true, false, true, false, c); }
    public static Cell open(Content c) { return new Cell(true, false, false, false, c); }
    public static Cell seVee(Content c) { return new Cell(true, false, false, false, c); }
    public static Cell swVee(Content c) { return new Cell(true, false, false, false, c); }
    public static Cell nwVee(Content c) { return new Cell(true, false, false, false, c); }


    // Factory methods for different types of cells
    public static Cell wall() {
        return new Cell(true, true, true, true, Content.WALL);
    }

    public static Cell dot() {
        return new Cell(false, false, false, false, Content.DOT);
    }

    public static Cell energizer() {
        return new Cell(false, false, false, false, Content.ENERGIZER);
    }

    public static Cell empty() {
        return new Cell(false, false, false, false, Content.NOTHING);
    }

}
