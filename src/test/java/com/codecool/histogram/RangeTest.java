package com.codecool.histogram;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    private final Range range = new Range(3, 7);

    @Nested
    public class RangeConstructor {
        @Test
        public void ValueIsSmallerThat0_returnIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Range(-1, 1));
        }

        @Test
        public void ValuesAreInNormalRange_DoNotReturnAnyExceptions() {
            assertDoesNotThrow(() -> range);
        }
    }

    @Nested
    public class IsInRange {
        @Test
        public void wordLengthIsInRange_returnTrue() {
            assertTrue(range.isInRange("foobar"));
        }

        @Test
        public void wordLengthEqualsToRangeFrom_returnTrue() {
            assertTrue(range.isInRange("foo"));
        }

        @Test
        public void wordLengthEqualsToRangeTo_returnTrue() {
            assertTrue(range.isInRange("foobar1"));
        }

        @Test
        public void wordLengthInOutOfRange_returnFalse() {
            assertFalse(range.isInRange("foobarfoobar"));
        }
    }

    @Test
    public void isString_normalRange_returnExpectedString() {
        assertEquals("3  - 7 ", range.toString());
    }
}
