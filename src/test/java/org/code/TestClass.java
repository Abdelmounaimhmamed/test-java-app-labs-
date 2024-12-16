package org.code;

import org.code.Exercice1.Main;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestClass {

    @Test
    public void testFactorielPositif() {
        assertEquals(120, Main.factoriel(5));
        assertEquals(1, Main.factoriel(0));
        assertEquals(1, Main.factoriel(1));
    }

    @Test
    public void testFactorielException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Main.factoriel(-1);
        });
        assertEquals("Enter a positive number", exception.getMessage());
    }
}
