package audio;

import java.net.URL;
import javax.sound.sampled.Clip;

import interfaces.GameAudio;

public class Music implements GameAudio {
    private Clip clip;
    private float initialVolume;

    public Music(URL location, float initialVolume) {
        clip = ClipUtil.createClip(location);
        this.initialVolume = initialVolume;
        setVolume(initialVolume);
    }

    public void start() { if(!clip.isActive()) { clip.setFramePosition(0); clip.loop(Clip.LOOP_CONTINUOUSLY); } }

    //arrete la musique
    public void stop() { if(clip.isActive()) { clip.stop(); } }

    public boolean isPlaying() { return clip.isActive(); }

    @Override
    public float getVolume() { return ClipUtil.getVolume(clip); }

    @Override
    public void setVolume(float volume) { ClipUtil.setVolume(volume, clip); }

    @Override
    public void stopAllInstances() { stop(); }

    @Override
    public float getInitialVolume() {
        return initialVolume;
    }
}