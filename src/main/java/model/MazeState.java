package model;

import config.MazeConfig;
import enums.Cell;
import geometry.IntCoordinates;
import enums.Direction;
import gui.Controller;
import gui.SoundController;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import java.util.Random;

/**
 * Description : gérer l'état du labyrinthe dans le jeu et tous le model ; move Pacman et les ghosts
 * les ghosts qui retournent à leurs positions initiliale , les sorties du labyrinthe , passer au level suivant
 * Manger les dots et les energizer et biensur les collisions entre les ghosts et Pacman
 * et le ghost eating mode ou les ghosts peuvent être mangé par Pacman
 */
public final class MazeState {
    private final MazeConfig config; // Configuration du labyrinthe à [partir du fichier]
    private static boolean gameOver; // variable de jeu pour savoir si on a perdu
    private static boolean Pause; // variable de jeu pour savoir si on est en pause
    private static boolean youWon; // variable de jeu pour savoir si on a gagné
    private static boolean ghostEatingMode;  // vsi on est en ghost eating mode
    private int score;
    private int level;
    private int lives;
    private static Direction lastDirection;
    private static Direction currentDirection = Direction.NONE;

    public Direction getLastDirection() {
        return lastDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public MazeState(MazeConfig config) {
        this.config = config;
    }

    public void startNewGame() {
        // initialiser les variables du jeu pour commencer une nouvelle partie
        this.gameOver = false;
        this.youWon = false;
        this.Pause = false;
        this.ghostEatingMode = false;
        config.setNbDots(0);
        config.setRows(0);
        config.setCols(0);
        this.score = 0;
        this.level = 1;
        this.lives = 3; // 3 vies
        config.loadMazeFromFile(Controller.getLevelFile(0)); // niveau 1 avec indice dans le tableau 0
    }


    public void startNextLevel() {
        if (this.isLevelComplete()) {
            this.level++;
            config.setCols(0);
            config.setCols(0);
            youWon = false;
            ghostEatingMode = false;
            try {
                config.loadMazeFromFile(Controller.getLevelFile(level - 1));
            } catch (ArrayIndexOutOfBoundsException e) {
                //si y'a plus de niveaux on a fini le 3 c'est à dire on a gagné
                youWon = true;
                gameOver = true;
                level--;
            }
        }
    }

    public boolean isLevelComplete() {
        // c'est à dire que tous les dots ont été mangé par PacMan
        return config.getNbDots() == 0;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static boolean isPaused() {
        return Pause;
    }

    public static boolean isGhostEatingMode() {
        return ghostEatingMode;
    }

    public static void setGhostEatingMode(boolean ghostEatingModeBool) {
        ghostEatingMode = ghostEatingModeBool;
    }

    public static boolean isYouWon() {
        return youWon;
    }
    
    public void movePacman(Direction direction) {
        // Calcul de la prochaine direction souhaitée pour Pac-Man
        IntCoordinates pacmanNextDirection = changePos(direction);
        // Calcul de la prochaine position de Pac-Man en fonction de la direction
        IntCoordinates pacmanNextPos = config.pacManPos.add(pacmanNextDirection);

        // Si Pac-Man sort de l'écran, il réapparaît de l'autre côté (effet de rebond)
        pacmanNextPos = outOfGrid(pacmanNextPos);


        if (direction.equals(lastDirection)) {
            // On arrete PacMan si il rencontre un mur
            if (config.grid[(int) pacmanNextPos.getX()][(int) pacmanNextPos.getY()] == Cell.TREE){
                config.pacmanDirection = changePos(Direction.NONE);
                lastDirection = Direction.NONE ;
            }
            else {
                config.pacmanDirection = pacmanNextDirection;
                config.pacManPos = pacmanNextPos;
            }
        }


        // Si la dernière direction est différente de la direction actuelle, vérifie les murs avant de changer de directionoing in a new direction
        else {
            if (config.grid[(int) pacmanNextPos.getX()][(int) pacmanNextPos.getY()] == Cell.TREE){
                pacmanNextDirection = changePos(lastDirection);
                pacmanNextPos = config.pacManPos.add(pacmanNextDirection);

                // si avec la direction on rencontre un mur on arrete PacMan
                if (config.grid[(int) pacmanNextPos.getX()][(int) pacmanNextPos.getY()] == Cell.TREE){
                    config.pacmanDirection = changePos(Direction.NONE);
                    lastDirection =  Direction.NONE;
                }
                else {
                    // on continue dans la direction précédente
                    config.pacmanDirection = changePos(lastDirection);
                    config.pacManPos = config.pacManPos.add(config.pacmanDirection);
                }
            }

            //Hors les deux cas, on change direction et il avance dans la nouvelle direction
            // position est changé par +(1,0) ou -(1,0) ou +(0,1) ou -(0,1)
            else {
                config.pacmanDirection = pacmanNextDirection;
                config.pacManPos = pacmanNextPos;
                lastDirection = direction;
            }
        }
    }
    
    /**  @param direction
@Description : change la position de pacman selon la direction qu'on récupère dans le controller avec le clavier
@return*/

    
    public IntCoordinates changePos(Direction direction){
        if(direction == Direction.WEST){
            // y est décrémenté
            return new IntCoordinates(0,-1);
        }
        else if(direction == Direction.EAST){
            return new IntCoordinates(0,1);
        }
        else if(direction == Direction.NORTH){
            return new IntCoordinates(-1,0);
        }
        else if(direction == Direction.SOUTH){
            return new IntCoordinates(1,0);
        }
        else{
            return new IntCoordinates(0,0);
        }
    }
    
        public IntCoordinates outOfGrid(IntCoordinates objectPos) {
        // retourner la position correspodante quand on quitte le labyrinthe par les issus

        //du coté droit
        if (objectPos.getY() >= config.getCols()) {
            objectPos = new IntCoordinates(objectPos.getX(), 0);
            // sorti du  coté gauche
        }
        // si c'est du coté gauche
        if (objectPos.getY() < 0) {
            objectPos = new IntCoordinates(objectPos.getX(), config.getCols()-1);
            // on se retrouve dans le coté droit 
        }
        return objectPos;
    }
    
    
    
    public void sendLionHome() { //lion
        for (int row = 0; row < config.getRows(); row++) {
            for (int column = 0; column < config.getCols(); column++) {
                if (config.grid[row][column] == Cell.LION) {
                    // on récupère la position du lion initiale
                    config.lionPos = new IntCoordinates(row, column);
                }
            }
        }

        // Mettez en pause le mouvement du lion
        config.lionDirection = new IntCoordinates(0, 0);

        // Créez un PauseTransition de 2 secondes
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            // Après 2 secondes, remettez le fantôme en mouvement
            Platform.runLater(() -> {
                config.lionDirection = new IntCoordinates(-1, 0); // Remplacez par la logique de mouvement initial du fantôme
            });
        });

        // Démarrez la transition
        pause.play();
    }
    
    public void sendGorillaHome() {
        for (int row = 0; row < config.getRows(); row++) {
            for (int column = 0; column < config.getCols(); column++) {
                if (config.grid[row][column] == Cell.GORILLA) {
                    config.gorillaPos = new IntCoordinates(row, column);
                }
            }
        }
        // Mettez en pause le mouvement
        config.gorillaDirection = new IntCoordinates(0, 0);

        // Créez un PauseTransition de 2 secondes
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            // Après 2 secondes, remettez le fantôme en mouvement
            Platform.runLater(() -> {
                config.gorillaDirection = new IntCoordinates(-1, 0); // Remplacez par la logique de mouvement initial du fantôme
            });
        });

        // Démarrez la transition
        pause.play();
    }
    
    //TODO : Ajouter le 3ème et le 4ème animal avec une IA différente de celle du lion et du gorille
    public void moveGhosts() {
        IntCoordinates[] ghost1Data = moveAGhost(config.lionDirection, config.lionPos);
        IntCoordinates[] ghost2Data = moveAGhost(config.gorillaDirection,config.gorillaPos);
        config.lionDirection = ghost1Data[0];
        config.lionPos = ghost1Data[1];
        config.gorillaDirection = ghost2Data[0];
        config.gorillaPos = ghost2Data[1];

    }

    public IntCoordinates[] moveAGhost(IntCoordinates dir, IntCoordinates pos){
        Random generator = new Random(); //pour générer des directions aléatoires
        // Si les animaux sont en mode normal , et sils sont dans la meme ligne ou colonne que Pacman
        // ils le poursuivent jusqu'a renocntrer un mur et ils changent de direction
        // vers une direction random

        if (!ghostEatingMode) {
            // si meme ligne que le Pacman
            if (pos.getY() == config.pacManPos.getY()) {
                if (pos.getX() > config.pacManPos.getX()) {
                    dir = changePos(Direction.NORTH);
                } else {
                    dir = changePos(Direction.SOUTH);
                }

                IntCoordinates potentialPos = pos.add(dir);
                // animaux qui traversent le labyrinthe
                potentialPos = outOfGrid(potentialPos);

                //la on genere une direction random pour changer de direction jusqu'a ce que l'animal ne rencontre pas un mur
                while (config.grid[(int) potentialPos.getX()][(int) potentialPos.getY()] == Cell.TREE) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    dir = changePos(direction);
                    potentialPos = pos.add(dir);
                }
                pos = potentialPos;
            }
            // meme colonne
            else if (pos.getX() == config.pacManPos.getX()) {
                if (pos.getY() > config.pacManPos.getY()) {


                    dir = changePos(Direction.WEST);
                } else {
                    dir = changePos(Direction.EAST);
                }
                IntCoordinates potentialPos = pos.add(dir);
                potentialPos = outOfGrid(potentialPos);

                while (config.grid[(int) potentialPos.getX()][(int) potentialPos.getY()] == Cell.TREE) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    dir = changePos(direction);
                    potentialPos = pos.add(dir);
                }
                pos = potentialPos;
            }
            // Random
            else{
                IntCoordinates potentialPos = pos.add(dir);
                potentialPos = outOfGrid(potentialPos);
                while(config.grid[(int) potentialPos.getX()][(int) potentialPos.getY()] == Cell.TREE){
                    int randomNum = generator.nextInt( 4);
                    Direction direction = intToDirection(randomNum);
                    dir = changePos(direction);
                    potentialPos = pos.add(dir);
                }
                pos = potentialPos;
            }
        }
        // dans l'autre mode , les animaux fuient Pacman
        // on part à la direction opposé de Pacman jusqu'a rencontrer un mur
        // sinon random

        if (ghostEatingMode) {
            if (pos.getY() == config.pacManPos.getY()) {
                if (pos.getX() > config.pacManPos.getX()) {
                    dir = changePos(Direction.SOUTH);
                } else {
                    dir = changePos(Direction.NORTH);
                }

                IntCoordinates potentialPos = pos.add(dir);
                // hors labyrinthe
                potentialPos = outOfGrid(potentialPos);


                while (config.grid[(int) potentialPos.getX()][(int) potentialPos.getY()] == Cell.TREE) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    dir = changePos(direction);
                    potentialPos = pos.add(dir);
                }
                pos = potentialPos;

            } else if (pos.getX() == config.pacManPos.getX()) {
                if (pos.getY() > config.pacManPos.getY()) {
                    dir = changePos(Direction.EAST);
                } else {
                    dir = changePos(Direction.WEST);
                }
                IntCoordinates potentialPos = pos.add(dir);
                potentialPos = outOfGrid(potentialPos);
                // random
                while (config.grid[(int) potentialPos.getX()][(int) potentialPos.getY()] == Cell.TREE) {
                    int randomNum = generator.nextInt(4);
                    Direction direction = intToDirection(randomNum);
                    dir = changePos(direction);
                    potentialPos = pos.add(dir);
                }
                pos = potentialPos;
            }
            else{
                // random
                IntCoordinates potentialPos = pos.add(dir);
                potentialPos = outOfGrid(potentialPos);
                while(config.grid[(int) potentialPos.getX()][(int) potentialPos.getY()] == Cell.TREE){
                    int randomNum = generator.nextInt( 4);
                    Direction direction = intToDirection(randomNum);
                    dir = changePos(direction);
                    potentialPos = pos.add(dir);
                }
                pos = potentialPos;
            }
        }
        IntCoordinates[] data = {dir, pos};
        return data;

    }
    


    public void resetPositions() {
        // Réinitialiser la position de Pac-Man à sa position de départ
        // appelé une fois il a perdu la vie
        for (int row = 0; row < config.getRows(); row++) {
            for (int column = 0; column < config.getCols(); column++) {
                if (config.grid[row][column] == Cell.PACMAN) {
                    config.pacManPos = new IntCoordinates(row, column);
                    break;
                }
            }
        }
        // Réinitialiser la position des fantômes à leur position de départ
        sendLionHome();
        sendGorillaHome();
    }

    private void addScore(int increment) {
        score += increment;
    }

    public MazeConfig getConfig() {
        return config;
    }
}

