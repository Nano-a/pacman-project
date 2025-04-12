package enums;

public enum Cell{
    NOTHING, DOT, ENERGIZER, TREE , LION, GORILLA, SNAKE, TIGER, PACMAN ;
    
    // Nothing : empty cell
    // Dot : empty cell with a dot ; a banana 
    // Energizer : empty cell with an energizer ; load of bananas 
    // Tree : closed cell ; east,north,south,west wall == true
    // Lion : init position of ghost 1 lion 
    // Gorilla : init position of ghost 2 gorilla
    // Snake : init position of ghost 3 snake
    // Tiger : init position of ghost 4 tiger
    // Pacman : init position of pacman 
}
