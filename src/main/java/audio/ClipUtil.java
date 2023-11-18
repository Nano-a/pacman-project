package audio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class ClipUtil {

    static Clip createClip(URL location) {
        try (AudioInputStream in = AudioSystem.getAudioInputStream(location)) {
            AudioFormat baseFormat = in.getFormat();
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

    static float getVolume(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    static void setVolume(float volume, Clip clip) {
        if (volume < 0f || volume > 1f) { 
            throw new IllegalArgumentException("Volume not valid: " + volume); 
        }
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
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
