package audio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URL;

public class MusicTest {

    @Test
    public void testStartAndStopMusic() throws Exception {
        URL validAudioUrl = this.getClass().getResource("/medias/click1.mp3"); 
        Music music = new Music(validAudioUrl, 0.5f);

        music.start();
        assertTrue(music.isPlaying(), "La musique devrait être en cours de lecture après le démarrage.");

        music.stop();
        assertFalse(music.isPlaying(), "La musique ne devrait pas être en cours de lecture après l'arrêt.");
    }

    @Test
    public void testSetVolume() throws Exception {
        // Assurez-vous que le chemin est correct
        URL validAudioUrl = this.getClass().getResource("/medias/click1.mp3");
        Music music = new Music(validAudioUrl, 0.5f);
        music.setVolume(0.7f);
        assertEquals(0.7f, music.getVolume(), "Le volume de la musique devrait être correctement réglé.");
    }

    // Autres tests pour isPlaying(), getInitialVolume(), etc.
}
