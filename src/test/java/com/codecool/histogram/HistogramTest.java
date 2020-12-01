package com.codecool.histogram;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class HistogramTest {

    private final Histogram sampleHistogram = new Histogram();
    private final String sampleWords = "Roads? Where we're going we don't need roads.";

    @Nested
    public class GenerateRanges {
        @Test
        public void positiveIntegersIsGiven_normalRanges() {
            List<Range> result = sampleHistogram.generateRanges(2, 3);

            List<Range> expected = new ArrayList<>(
                    List.of(new Range(1, 2),
                            new Range(3, 4),
                            new Range(5, 6)
                    )
            );

            assertIterableEquals(expected, result);
        }

        @ParameterizedTest(name = "generateRanges({0}, {1})")
        @CsvSource({"-2, 3", "2, -3"})
        public void negativeIntegerAsParameters_illegalArgumentExceptionMessage(int x, int y) {
            assertThrows(IllegalArgumentException.class, () -> sampleHistogram.generateRanges(x, y));
        }
    }

    @Nested
    public class Generate {
        @Test
        public void wordsInRange_allWordsInOneOfRanges() {
            List<Range> ranges = sampleHistogram.generateRanges(2, 3);
            Map<Range, Integer> result = sampleHistogram.generate(sampleWords, ranges);

            Map<Range, Integer> expected = new HashMap<>(
                    Map.of(new Range(1, 2), 1,
                            new Range(3, 4), 3,
                            new Range(5, 6), 4
                    )
            );

            assertEquals(expected, result);
        }

        @Test
        public void wordsOutOfRange_emptyMap() {
            List<Range> ranges = sampleHistogram.generateRanges(1, 1);
            Map<Range, Integer> result = sampleHistogram.generate(sampleWords, ranges);

            HashMap<Range, Integer> expected = new HashMap<>();

            assertEquals(expected, result);
        }

        @Test
        public void emptyText_emptyMap() {
            List<Range> ranges = sampleHistogram.generateRanges(2, 3);
            Map<Range, Integer> result = sampleHistogram.generate("", ranges);

            HashMap<Range, Integer> expected = new HashMap<>();

            assertEquals(expected, result);
        }

        @Test
        public void nullText_throwIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> sampleHistogram.generate(null, sampleHistogram.generateRanges(2, 3)));
        }

        @Test
        public void nullRange_throwIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> sampleHistogram.generate("1955? You're my", null));
        }
    }

    @Nested
    public class GetHistogram {
        List<Range> sampleRanges = sampleHistogram.generateRanges(2, 3);

        @Test
        public void callBeforeGenerateHistogram_emptyMap() {
            Histogram histogram = new Histogram();

            assertEquals(new HashMap<>(), histogram.getHistogram());
        }

        @Test
        public void callAfterGenerateHistogram_RightMap() {
            sampleHistogram.generate(sampleWords, sampleRanges);

            Map<Range, Integer> expected = new HashMap<>(
                    Map.of(new Range(1, 2), 1,
                            new Range(3, 4), 3,
                            new Range(5, 6), 4
                    )
            );

            assertEquals(expected, sampleHistogram.getHistogram());
        }

        @Test
        public void callAfterMultipleCalling_RightMap() {
            sampleHistogram.generate(sampleWords, sampleRanges);
            sampleHistogram.getHistogram();
            sampleHistogram.getHistogram();

            Map<Range, Integer> expected = new HashMap<>(
                    Map.of(new Range(1, 2), 1,
                            new Range(3, 4), 3,
                            new Range(5, 6), 4
                    )
            );

            assertEquals(expected, sampleHistogram.getHistogram());
        }

        @Test
        public void stringRepresentationBeforeGenerate_EmptyString() {
            Histogram histogram = new Histogram();
            String result = histogram.toString();

            assertEquals("", result);
        }

        @Test
        public void getHistogram_stringRepresentationAfterGenerate_RightString() {
            sampleHistogram.generate(sampleWords, sampleRanges);

            String expectedSampleString = "5  - 6 | ****\n" +
                    "3  - 4 | ***\n" +
                    "1  - 2 | *\n";

            assertEquals(expectedSampleString, sampleHistogram.toString());
        }
    }

    @Nested
    public class normalizeValues {
        private final List<Range> sampleRanges = sampleHistogram.generateRanges(2, 3);

        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = {"getMinValue", "getMaxValue"})
        public void getValue_returnValue(String value) {
            sampleHistogram.generate(sampleWords, sampleRanges);

            switch (value) {
                case "getMinValue":
                    assertEquals(1, sampleHistogram.getMinValue());
                case "getMaxValue" :
                    assertEquals(4, sampleHistogram.getMaxValue());
            }
        }

        @Test
        public void callNormalizeValues_StringRangesFrom0To100() {
            // Arrange:
            sampleHistogram.generate(sampleWords, sampleRanges);

            // Act:
            sampleHistogram.normalizeValues();
            String result = sampleHistogram.toString();

            // Assert:
            String expected = "5  - 6 | " + "*".repeat(100) + "\n" +
                    "3  - 4 | " + "*".repeat(66) + "\n" +
                    "1  - 2 | \n";

            assertEquals(expected, result);
        }
    }
}
