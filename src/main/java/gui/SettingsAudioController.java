package gui;

import enums.FxmlFiles;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * Controller pour les paramètres audio du jeu
 */

public class SettingsAudioController extends CommonController {
    @FXML Button soundButton, musicButton; // son pour les clicks , et musique pour la musique du fond
    @FXML Button ReturnButton;
    @FXML private ChoiceBox<String> themeMusicChoiceBox; // ChoiceBox pour choisir le thème musical
    @FXML public static String themePlaySong = "Pacman Theme"; // on initialise le thème musical à Pacman Theme
    private boolean isMusicOn(){
        // on initialise la musique à ON
        return musicButton.getText().equals("On");
    }

    private boolean isSoundOn(){
        // on initialise le son à ON
        return soundButton.getText().equals("On");
    }

    private void updateMusicButton() {
        if (isMusicOn()) {
            musicButton.setText("Off");
            musicButton.setId("soundOff");
            SoundController.muteMusic();
        } else {
            musicButton.setText("On");
            musicButton.setId("soundOn");
            SoundController.unmuteMusic();
        }
    }
    public  void updateSoundButton() {
           if (isSoundOn()) {
               soundButton.setText("Off");
               soundButton.setId("soundOff");
               SoundController.muteSounds();
            } else {
               soundButton.setText("On");
               soundButton.setId("soundOn");
               SoundController.unmuteSounds();
               SoundController.click();
            }
    }


    @FXML
    private void toggleMusic() {
        // on met à jour le bouton de la musique une fois cliqué
        SoundController.click();
        updateMusicButton();
    }

    @FXML
    private void toggleSound() {
        // on met à jour le bouton du son une fois cliqué
        //SoundController.click(); Off car on mute les clicks ( comme signe )
        updateSoundButton();
    }

    @FXML
    private void initialize() {
        // Ajouter un écouteur de changement de valeur à la ChoiceBox
        themeMusicChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Appeler la méthode pour changer le thème musical
                themePlaySong = newValue ;
                // playSongSnippet(newValue);
            }
        });
    }

    // Pour jouer un extrait du son afin de sélectionner le bon
     /*private void playSongSnippet(String songName) {
        new Thread(() -> {
            try {
                Thread.sleep(5000); // 5000 millisecondes pour un extrait de 5 secondes
                SoundController.theme(songName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        } */


    @FXML
    private void goBackToOptions() {
        SoundController.click();
        manager.switchScene(FxmlFiles.SETTINGS);
    }

    public void updateLanguage(boolean isFrench) {
        if(isFrench){
            ReturnButton.setText("RETOUR");
        }
        else{
           ReturnButton.setText("RETURN");
        }
    }
}
