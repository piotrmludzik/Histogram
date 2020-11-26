package com.codecool.histogram;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RangeTest {

    @Test
    @Order(0)
    public void Range_FromSmallerThat0_IllegalArgumentException () {
        assertThrows(IllegalArgumentException.class, () -> new Range(-1, 1));
    }

    @Test
    @Order(1)
    public void Range_NormalRange_DoesNotAnyExeptions() {
        assertDoesNotThrow(() -> new Range(3, 7));
    }

    @Test
    @Order(2)
    public void isInRange_WordLengthIsInRange_True() {
        Range range = new Range(3, 7);
        assertTrue(range.isInRange("foobar"));
    }

    @Test
    @Order(3)
    public void isInRange_WordLengthEqualsToRangeFrom_True() {
        Range range = new Range(3, 7);
        assertTrue(range.isInRange("foo"));
    }

    @Test
    @Order(4)
    public void isInRange_WordLengthEqualsToRangeTo_True() {
        Range range = new Range(3, 7);
        assertTrue(range.isInRange("foobar1"));
    }

    @Test
    @Order(5)
    public void isInRange_WordLengthInOutOfRange_False() {
        Range range = new Range(3, 7);
        assertFalse(range.isInRange("foobarfoobar"));
    }

    @Test
    @Order(6)
    public void isString_WordLengthInOutOfRange_False() {
        Range range = new Range(3, 7);
        assertEquals("3 - 7", range.toString());
    }
}
