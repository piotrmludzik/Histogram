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
    public void range_fromSmallerThat0_illegalArgumentException () {
        assertThrows(IllegalArgumentException.class, () -> new Range(-1, 1));
    }

    @Test
    @Order(1)
    public void range_normalRange_doesNotAnyExeptions() {
        assertDoesNotThrow(() -> new Range(3, 7));
    }

    @Test
    @Order(2)
    public void isInRange_wordLengthIsInRange_true() {
        Range range = new Range(3, 7);
        assertTrue(range.isInRange("foobar"));
    }

    @Test
    @Order(3)
    public void isInRange_wordLengthEqualsToRangeFrom_true() {
        Range range = new Range(3, 7);
        assertTrue(range.isInRange("foo"));
    }

    @Test
    @Order(4)
    public void isInRange_wordLengthEqualsToRangeTo_true() {
        Range range = new Range(3, 7);
        assertTrue(range.isInRange("foobar1"));
    }

    @Test
    @Order(5)
    public void isInRange_wordLengthInOutOfRange_false() {
        Range range = new Range(3, 7);
        assertFalse(range.isInRange("foobarfoobar"));
    }

    @Test
    @Order(6)
    public void isString_wordLengthInOutOfRange_false() {
        Range range = new Range(3, 7);
        assertEquals("3 - 7", range.toString());
    }
}
