package audio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URL;
import javax.sound.sampled.Clip;
import static org.mockito.Mockito.mock;

public class ClipUtilTest {

    @Test
    public void testCreateClip() throws Exception {
        // Remplacez ceci par une URL valide de fichier audio pour votre test
        URL validAudioUrl = this.getClass().getResource("/medias/click1.mp3"); // Exemple de chemin vers un fichier audio
        Clip clip = ClipUtil.createClip(validAudioUrl);
        assertNotNull(clip, "Le clip ne devrait pas être null pour une URL valide.");
    }

    @Test
    public void testCreateSimultaneousClip() throws Exception {
        // Assurez-vous que le chemin est correct
        URL validAudioUrl = this.getClass().getResource("/medias/click1.mp3"); // Exemple de chemin vers un fichier audio
        SimultaneousClip simultaneousClip = ClipUtil.createSimultaneousClip(validAudioUrl);
        assertNotNull(simultaneousClip, "SimultaneousClip ne devrait pas être null pour une URL valide.");
    }

    @Test
    public void testSetVolume() {
        Clip clip = mock(Clip.class); // Utilisez Mockito pour mocker un Clip
        assertDoesNotThrow(() -> ClipUtil.setVolume(0.5f, clip), "Le réglage du volume ne devrait pas lancer d'exception.");
    }

    // Ajoutez plus de tests pour les cas limites et les cas d'erreur
}
