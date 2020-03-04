package dev.previn.insurance.dtos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DollarTest {

    @Test
    void getValue() {
        final BigDecimal expectedValue = BigDecimal.TEN;
        final Dollar testDollar = new Dollar(BigDecimal.valueOf(10));
        assertTrue(expectedValue.compareTo(testDollar.getValue()) == 0);

        assertEquals("$10.00", testDollar.toString());
    }

    @Test
    void testToString() {
        assertEquals("$0.00", new Dollar(BigDecimal.valueOf(0)).toString());
        assertEquals("$1.18", new Dollar(BigDecimal.valueOf(1.17132)).toString());
        assertEquals("$5.09", new Dollar(BigDecimal.valueOf(5.09)).toString());
        assertEquals("$99.10", new Dollar(BigDecimal.valueOf(99.099)).toString());
        assertEquals("$99.10", new Dollar(BigDecimal.valueOf(99.1)).toString());
    }

    @Test
    void testNoNegatives() {
        assertThrows(IllegalArgumentException.class, () -> new Dollar(BigDecimal.valueOf(-1)));
    }
}