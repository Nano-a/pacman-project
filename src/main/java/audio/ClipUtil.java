/*
 * Ce fichier définit une classe ClipUtil avec des méthodes pour créer un clip audio à partir
 * d'une URL et pour régler le volume d'un clip. Les commentaires ajoutés expliquent chaque étape
 * du processus de création et de manipulation des clips audio.
 */
package audio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


// La classe ClipUtil fournit des utilitaires pour créer et manipuler des clips audio.
public class ClipUtil {

    // Crée un clip audio à partir d'une URL en convertissant le format audio.
    static Clip createClip(URL location) {
        try (AudioInputStream in = AudioSystem.getAudioInputStream(location)) {
            // Obtenir le format du flux audio
            AudioFormat baseFormat = in.getFormat();
            // Créer un format audio décodé
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, in);
            Clip clip = AudioSystem.getClip();
            clip.open(din);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Cette fonction crée un objet SimultaneousClip à partir d'une URL de fichier audio en convertissant le format audio.
    static SimultaneousClip createSimultaneousClip(URL location) {
        try (AudioInputStream in = AudioSystem.getAudioInputStream(location)) {
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, in);
            byte[] data = getBytesFromInputStream(din);
            return new SimultaneousClip(decodedFormat, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //renvoie le volume
    static float getVolume(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    // Régler le volume d'un clip audio
    static void setVolume(float volume, Clip clip) {
        if (volume < 0f || volume > 1f) { 
            throw new IllegalArgumentException("Volume not valid: " + volume); 
        }
        // Obtenir le contrôle du volume
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        // Ajuster le volume
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    private static byte[] getBytesFromInputStream(AudioInputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        for (int len = is.read(buffer); len != -1; len = is.read(buffer)) { 
            os.write(buffer, 0, len); 
        }
        return os.toByteArray();
    }
}
