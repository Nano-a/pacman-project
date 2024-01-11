package gui;

import java.io.File;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Classe qui permet de gérer les sons et musiques du jeu en utilisant MediaPlayer
 * ne extend pas CommonController car il ne s'agit pas de basculer entre les scènes , ce controller ne représente pas une scène
 */

public class SoundController {
    public static Son son = new Son(); // static pour pouvoir l'appeler dans les autres classes
    public static Music music = new Music();

    /**
     * Permet de jouer le son de clic.
     */
    public static void click(){
        son.click();
    }

    /**
     * Joue le son correspondant au nom du fichier fourni.
     *
     * @param s Nom du fichier audio à jouer.
     */
    public static void sound(String s){
        son.sound(s);
    }

    /**
     * Joue la musique correspondant au nom du fichier fourni sans extension
     *
     * @param s Nom du fichier audio de la musique à jouer.
     */

    public static void musique(String s){
        music.joue(s);
    }


    public static void muteMusic(){
        music.mute();
    } // mets en sourdine la musique
    public static void unmuteMusic(){
        music.unmute();
    } // active de nouveau la musique

    public static void muteSounds(){
        son.mute();
    } // mets en sourdine les clicks
    public static void unmuteSounds(){
        son.unmute();
    } // active le son des clicks


    /**
     * Classe interne pour la gestion des sons.
     */
    public static class Son {

        static String[] clicks = {"click.mp3"};
        private static AudioClip clickSound;
        private boolean muteClicks = false;
        static String[] sons = {"eatingFruit.mp3","fail.mp3","ghostSprut.mp3"};
        static MediaPlayer[] media_clicks = new MediaPlayer[clicks.length];
        static MediaPlayer[] media_sons = new MediaPlayer[sons.length];

        public Son(){
            // On initialise les clicks
            for(int i = 0; i < media_clicks.length; i++){

                Media media = new Media(new File("src/main/resources/media/"+clicks[i]).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                media_clicks[i] = mediaPlayer;
            }
            // On initialise les sons
            for(int i = 0; i < media_sons.length; i++){
                Media media = new Media(new File("src/main/resources/media/"+sons[i]).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                media_sons[i] = mediaPlayer;
            }

        }

        public void mute(){
            for (MediaPlayer m : media_sons) {
                m.setMute(true);
            }
            for (MediaPlayer m : media_clicks) {
                m.setMute(true);
            }
            muteClicks = true ;
        }
        public void unmute(){
            for (MediaPlayer m : media_sons) {
                m.setMute(false);
            }
            for (MediaPlayer m : media_clicks) {
                m.setMute(false);
            }
            muteClicks = false ;
        }

        // Retourne true dans le cas ou il trouve le fichier, sinon false
        public boolean sound(String s){
            // On cherche si c'est un click
            for(int i = 0;i<clicks.length;i++){
                if(clicks[i].equals(s+".mp3") ){
                    media_clicks[i].play();
                    media_clicks[i].seek(media_clicks[i].getStartTime()); // On remet le son au début
                    return true;
                }
            }
            // si c'est un son
            for(int i = 0;i<sons.length;i++){
                if(sons[i].equals(s+".mp3")){
                    media_sons[i].play();
                    media_sons[i].seek(media_sons[i].getStartTime());
                    return true;
                }
            }
            // On renvoie false si rien trouvé
            return false;
        }

        public void click(){
            if ( muteClicks == false) {
            clickSound = new AudioClip(new File("src/main/resources/media/click.mp3").toURI().toString());
            clickSound.play(); }

        }
    }


    /**
     * Classe interne pour la gestion des musiques.
     */

    public static class Music {

        static String[] music = {"JungleTheme.mp3","Pacman Theme.mp3", "Electronic Music.mp3","GeometryDash Theme.mp3","Eighties Music.mp3","BananaKong Theme.mp3","Lofi Music.mp3"};
        static MediaPlayer [] media_music = new MediaPlayer[music.length];

        public Music(){
            // On initialise les musiques
            for(int i = 0; i < media_music.length; i++){
                Media media = new Media(new File("src/main/resources/media/"+music[i]).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Set to loop indefinitely
                media_music[i] = mediaPlayer;
            }
        }

        public void mute(){
            for (MediaPlayer m : media_music) {
                m.setMute(true);
            }
        }
        public void unmute(){
            for (MediaPlayer m : media_music) {
                m.setMute(false);
            }
        }

        public void stop(){
            for (MediaPlayer m : media_music) {
                m.stop();
            }
        }

        // Retourne true dans le cas ou il trouve le fichier, sinon false
        public boolean joue(String s){
            stop();
            for(int i = 0;i<music.length;i++){
                if(music[i].equals(s+".mp3") ){
                    media_music[i].play();
                    media_music[i].seek(media_music[i].getStartTime());
                    return true;
                }
            }
            return false;
        }
    }


}