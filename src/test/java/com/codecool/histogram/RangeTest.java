package com.codecool.histogram;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    @Nested
    public class RangeConstructor {
        @Test
        public void fromValueIsSmallerThat0_returnIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Range(-1, 1));
        }

        @Test
        public void ToValuesIsSmallerThatFromValue_returnIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Range(7, 3));
        }
    }

    @Nested
    public class IsInRange {
        private final Range range = new Range(3, 7);

        @ParameterizedTest(name = "word: {0}")
        @ValueSource(strings = { "123", "1234", "1234567" })
        public void wordLengthIsInRange_returnTrue(String word) {
            assertTrue(range.isInRange(word));
        }

        @Test
        public void wordLengthInOutOfRange_returnFalse() {
            assertFalse(range.isInRange("12345678"));
        }
    }

    @Test
    public void isString_normalRange_returnExpectedString() {
        Range range = new Range(3, 7);

        assertEquals("3  - 7 ", range.toString());
    }
}
