package interfaces;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PersistentGameTaskTest {

    @Test
    public void testPersistentTaskExecution() {
        // Créer une mock implementation de PersistentGameTask
        PersistentGameTask task = mock(PersistentGameTask.class);

        // Exécuter la tâche
        task.execute();

        // Vérifier si la méthode execute() a été appelée
        verify(task, times(1)).execute();
    }

    // Vous pouvez ajouter d'autres tests pour des implémentations spécifiques de PersistentGameTask
}
