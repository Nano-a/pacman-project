package interfaces;

/**
 * Opérations qui doivent être prises en charge par une implémentation audio.
 */
public interface GameAudio {

    /**
     * Obtenir le volume relatif du fichier audio.
     * @return Volume relatif compris entre 0 et 1.
     */
    public float getVolume();

    /**
     * Volume audio initial pour ajustement via les options.
     * @return Le volume initial de l'audio.
     */
    public float getInitialVolume();

    /**
     * Définir le volume relatif du fichier audio.
     * @param volume Volume relatif compris entre 0 et 1.
     */
    public void setVolume(float volume);

    /**
     * Arrêter toutes les instances de cet audio.
     */
    public void stopAllInstances();
}
