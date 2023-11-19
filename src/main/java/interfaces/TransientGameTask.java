package interfaces;

/**
 * Tâches temporaires qui expirent une fois qu'elles retournent vrai (true).
 */
@FunctionalInterface
public interface TransientGameTask {

    /**
     * Exécute une tâche et évalue si la tâche est terminée.
     * @return vrai (true) si la tâche est terminée, faux (false) si la tâche nécessite une exécution supplémentaire.
     */
    boolean isFinished();
}
