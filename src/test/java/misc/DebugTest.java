/*
 * Pour la classe Debug, les tests unitaires ne sont pas typiquement nécessaire ou utiles, car cette
 * classe sert principalement à afficher des messages de débogage. Les méthodes de débogage comme
 * celle-ci sont souvent exclues des tests unitaires car elles n'ont pas de logique métier
 * ou d'algorithme à tester.

Mais je le fais quand même, on ne sait rien -\_o_/-ça pourrait nous rendre util
 */

package misc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class DebugTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testOut() {
        String testMessage = "Test message";
        Debug.out(testMessage);
        String expectedOutput = ">> DEBUG >> " + testMessage + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString(), "Le message de débogage doit correspondre au format attendu.");
    }
}
