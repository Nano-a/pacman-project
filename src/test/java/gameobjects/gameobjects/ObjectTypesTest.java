package gameobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObjectTypesTest {

    @Test
    public void testExistenceOfValues() {
        assertNotNull(ObjectTypes.STATIC, "STATIC doit exister dans ObjectTypes.");
        assertNotNull(ObjectTypes.ENTITY, "ENTITY doit exister dans ObjectTypes.");
        // Testez les autres valeurs
    }
}
