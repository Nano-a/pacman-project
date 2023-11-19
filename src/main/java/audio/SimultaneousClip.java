package audio;

import javax.sound.sampled.AudioFormat;

public class SimultaneousClip {
    private AudioFormat format;
    private byte[] data;

    public SimultaneousClip(AudioFormat format, byte[] data) {
        this.format = format;
        this.data = data;
    }

    //renvoie le format
    public AudioFormat getFormat() {
        return format;
    }

    //renvoie les données de l'audio
    public byte[] getData() {
        return data;
    }
}
