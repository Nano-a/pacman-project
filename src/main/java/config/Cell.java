package config;

public record Cell(boolean northWall, boolean eastWall, boolean southWall, boolean westWall, Cell.Content initialContent) {
    public enum Content { NOTHING, DOT, ENERGIZER}
    public static Cell open(Content c) { return new Cell(false, false, false, false, c); }
    public static Cell closed(Content c) { return new Cell(true, true, true, true, c); }


}