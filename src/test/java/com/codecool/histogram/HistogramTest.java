package com.codecool.histogram;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class HistogramTest {

    private final Histogram sampleHistogram = new Histogram();
    private final String sampleWords = "Roads? Where we're going we don't need roads.";
    private final List<Range> sampleRanges = sampleHistogram.generateRanges(2, 3);
    private final Map<Range, Integer> actualSampleHistogramMap = sampleHistogram.generate(sampleWords, sampleRanges);

    private final Map<Range, Integer> expectedSampleHistogramMap = new HashMap<>(
            Map.of(new Range(1, 2), 1,
                    new Range(3, 4), 3,
                    new Range(5, 6), 4
            )
    );

    @Nested
    public class GenerateRanges {
        @Test
        public void positiveIntegersIsGiven_normalRanges() {
            List<Range> expectedSampleRanges = new ArrayList<>(
                    List.of(new Range(1, 2),
                            new Range(3, 4),
                            new Range(5, 6)
                    )
            );

            assertIterableEquals(expectedSampleRanges, sampleRanges);
        }

        @Test
        public void negativeIntegerAsStepParameter_illegalArgumentExceptionMessage() {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                    () -> sampleHistogram.generateRanges(-2, 3));
            assertEquals("Step must be positive.", thrown.getMessage());
        }

        @Test
        public void negativeIntegerAsAmountParameter_illegalArgumentExceptionMessage() {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                    () -> sampleHistogram.generateRanges(2, -3));
            assertEquals("Amount must be positive.", thrown.getMessage());
        }
    }

    @Nested
    public class Generate {
        @Test
        public void wordsInRange_allWordsInOneOfRanges() {
            assertEquals(expectedSampleHistogramMap, actualSampleHistogramMap);
        }

        @Test
        public void wordsOutOfRange_emptyMap() {
            assertEquals(new HashMap<>(), sampleHistogram.generate(sampleWords, sampleHistogram.generateRanges(1, 1)));
        }

        @Test
        public void emptyText_emptyMap() {
            assertEquals(new HashMap<>(), sampleHistogram.generate("", sampleRanges));
        }

        @Test
        public void nullText_throwIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> sampleHistogram.generate(null, sampleRanges));
        }

        @Test
        public void nullRange_throwIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> sampleHistogram.generate("1955? You're my", null));
        }
    }

    @Nested
    public class GetHistogram {
        @Test
        public void callBeforeGenerateHistogram_emptyMap() {
            Histogram histogram = new Histogram();

            assertEquals(new HashMap<>(), histogram.getHistogram());
        }

        @Test
        public void callAfterGenerateHistogram_RightMap() {
            assertEquals(expectedSampleHistogramMap, sampleHistogram.getHistogram());
        }

        @Test
        public void callAfterMultipleCalling_RightMap() {
            sampleHistogram.getHistogram();
            sampleHistogram.getHistogram();

            assertEquals(expectedSampleHistogramMap, sampleHistogram.getHistogram());
        }

        @Test
        public void stringRepresentationBeforeGenerate_EmptyString() {
            Histogram histogram = new Histogram();

            assertEquals("", histogram.toString());
        }

        @Test
        public void getHistogram_stringRepresentationAfterGenerate_RightString() {
            String expectedSampleString = "5  - 6 | ****\n" +
                    "3  - 4 | ***\n" +
                    "1  - 2 | *\n";

            assertEquals(expectedSampleString, sampleHistogram.toString());
        }
    }

    @Test
    public void getMinValue_callGetMinValue_returnMinValueOfRanges() {
        assertEquals(1, sampleHistogram.getMinValue());
    }
    @Test
    public void getMaxValue_callGetMaxValue_returnMaxValueOfRanges() {
        assertEquals(4, sampleHistogram.getMaxValue());
    }

    @Test
    public void normalizeValues_() {
        sampleHistogram.normalizeValues();

        String expected = "5  - 6 | ****************************************************************************************************\n" +
                "3  - 4 | ******************************************************************\n" +
                "1  - 2 | \n";

        assertEquals(expected, sampleHistogram.toString());
    }
}
