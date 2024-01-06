package interfaces;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransientGameTaskTest {

    @Test
    public void testTransientTaskExecution() {
        // Créer une mock implementation de TransientGameTask
        TransientGameTask task = mock(TransientGameTask.class);
        when(task.isFinished()).thenReturn(true);

        // Exécuter la tâche et obtenir le résultat
        boolean result = task.isFinished();

        // Vérifier si la méthode isFinished() a été appelée et retourne le bon résultat
        verify(task, times(1)).isFinished();
        assertTrue(result, "La tâche doit être terminée.");
    }

    // Vous pouvez ajouter d'autres tests pour des implémentations spécifiques de TransientGameTask
}
