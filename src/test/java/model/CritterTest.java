package model;

import geometry.RealCoordinates;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CritterTest {

    @Test
    public void testNextPos() {
        // Créer un mock de Critter
        Critter critter = mock(Critter.class);

        // Configurer le comportement du mock
        when(critter.getPos()).thenReturn(new RealCoordinates(0, 0));
        when(critter.getDirection()).thenReturn(Direction.EAST);
        when(critter.getSpeed()).thenReturn(1.0);

        // Calculer la position suivante
        RealCoordinates nextPos = critter.nextPos(1000000000); // 1 seconde en nanosecondes

        // Vérifier si la position suivante est correcte
        assertEquals(new RealCoordinates(1, 0), nextPos, "La position suivante doit être correctement calculée.");

        // Répétez les tests pour d'autres directions et vitesses
    }

    // Ajoutez d'autres tests pour les méthodes getPos, getDirection, setPos, setDirection, etc.
}
