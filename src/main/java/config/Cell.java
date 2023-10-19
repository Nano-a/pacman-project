package config;

public record Cell(boolean northWall, boolean eastWall, boolean southWall, boolean westWall, Cell.Content initialContent) {
    public enum Content { NOTHING, DOT, ENERGIZER}
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

}
