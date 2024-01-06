package gameobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OtherObjectsTest {

    /*
        @Test y a rien a tester pour le moment, mais si voulez tester la presence des valeur, voici un extrai
     */
    @Test
    public void testExistenceOfValues() {
        assertNotNull(ObjectTypes.STATIC, "STATIC doit exister dans ObjectTypes.");
        assertNotNull(ObjectTypes.ENTITY, "ENTITY doit exister dans ObjectTypes.");
        // Testez les autres valeurs
    }

}
