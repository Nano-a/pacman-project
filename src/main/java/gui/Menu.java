package gui;


import enums.FxmlFiles;
import javafx.fxml.FXML;

public class Menu extends CommonController{

            private void switchFromMenu(FxmlFiles file) {
                manager.switchScene(file);
            }

            @FXML
            private void initialize() {
            }

            @FXML
            private void switchToNewGame() {
               // String audioFilePath = "/media/click1.mp3";
              //  AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource(audioFilePath)).toExternalForm());
              //  audioClip.play();
                var pacmanController = new PacmanController(); //Pour controller le pacman avec les touches du clavier
                switchFromMenu(FxmlFiles.START_GAME);
            }

            @FXML
            private void switchToSettings() {
                //Media media = new Media(new File("/Users/naimcherchour/Desktop/pacman/src/main/resources/media/click7.mp3").toURI().toString());
                //MediaPlayer mediaPlayer = new MediaPlayer(media);
                //mediaPlayer.setAutoPlay(true);
               // String audioFilePath = "/media/click1.mp3";
               // AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource(audioFilePath)).toExternalForm());
               // audioClip.play();
                switchFromMenu(FxmlFiles.SETTINGS);
            }

            @FXML
            private void exitGame() {
                System.exit(0);
            }
}

