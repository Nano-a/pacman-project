package interfaces;

/**
 * Les tâches persistantes seront toujours exécutées.
 */
@FunctionalInterface
public interface PersistentGameTask {

    /**
     * Exécute une tâche qui a été définie via cette interface.
     */
    void execute();
}
