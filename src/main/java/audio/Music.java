/*
 * Cette classe Music implémente l'interface GameAudio et gère la musique de fond du jeu. Elle
 * comprend des méthodes pour démarrer, arrêter la musique, et régler son volume.
 */
package audio;

import java.net.URL;
import javax.sound.sampled.Clip;

import interfaces.GameAudio;

// La classe Music gère la musique de fond dans le jeu.
public class Music implements GameAudio {

    //Attribut
    private Clip clip; // Le clip audio pour la musique
    private float initialVolume; // Volume initial de la musique

    // Constructeur pour créer une musique avec un emplacement et un volume initial
    public Music(URL location, float initialVolume) {
        // Création du clip audio pour la musique
        clip = ClipUtil.createClip(location);
        this.initialVolume = initialVolume;
        // Réglage du volume de la musique
        setVolume(initialVolume);
    }

     // Commence la musique
    public void start() { 
        if(!clip.isActive()) { 
            clip.setFramePosition(0); // Réinitialiser la position du clip au début
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Jouer la musique en boucle
        } 
    }

    // Arrête la musique
    public void stop() { 
        clip.stop(); // Arrêter le clip
    }

    //renvoie true si il y a de la musoque
    public boolean isPlaying() {
        return clip.isActive();
    }

    @Override
    public float getVolume() { return ClipUtil.getVolume(clip); }

    @Override
    // Régle le volume de la musiqu
    public void setVolume(float volume) {
        // Réglage du volume du clip
        ClipUtil.setVolume(volume, clip);
    }

    @Override
    public void stopAllInstances() { stop(); }

    @Override
    public float getInitialVolume() {
        return initialVolume;
    }
}
