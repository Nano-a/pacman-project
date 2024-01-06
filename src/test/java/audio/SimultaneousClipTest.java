package audio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.sound.sampled.AudioFormat;

public class SimultaneousClipTest {

    @Test
    public void testSimultaneousClipProperties() {
        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, false); // Exemple de format audio
        byte[] data = new byte[10]; // Exemple de données audio

        SimultaneousClip simultaneousClip = new SimultaneousClip(format, data);

        assertEquals(format, simultaneousClip.getFormat(), "Le format doit correspondre à celui fourni au constructeur.");
        assertArrayEquals(data, simultaneousClip.getData(), "Les données doivent correspondre à celles fournies au constructeur.");
    }

}
