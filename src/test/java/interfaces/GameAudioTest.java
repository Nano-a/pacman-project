package interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameAudioTest {

    private GameAudio gameAudio;

    @BeforeEach
    public void setUp() {
        // Créer un mock de GameAudio
        gameAudio = mock(GameAudio.class);
        // Configurer le comportement du mock
        when(gameAudio.getVolume()).thenReturn(0.5f);
        when(gameAudio.getInitialVolume()).thenReturn(0.7f);
    }

    @Test
    public void testGetVolume() {
        // Vérifier que getVolume retourne la valeur attendue
        assertEquals(0.5f, gameAudio.getVolume(), "Le volume doit être correctement retourné.");
    }

    @Test
    public void testSetVolume() {
        // Définir le volume et vérifier si la méthode setVolume a été appelée
        gameAudio.setVolume(0.6f);
        verify(gameAudio, times(1)).setVolume(0.6f);
    }

    @Test
    public void testStopAllInstances() {
        // Appeler stopAllInstances et vérifier si la méthode a été appelée
        gameAudio.stopAllInstances();
        verify(gameAudio, times(1)).stopAllInstances();
    }

    // Ajoutez d'autres tests pour les classes concrètes qui implémentent GameAudio
}
