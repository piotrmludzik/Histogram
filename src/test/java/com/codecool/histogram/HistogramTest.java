package com.codecool.histogram;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {

    @Test
    @Order(0)
    public void generateRanges_positiveIntegers_threeRanges() {
        Histogram histogram = new Histogram();
        List<Range> expected = new ArrayList<>(
                List.of(new Range(1, 2),
                        new Range(3, 4),
                        new Range(5, 6)
                )
        );

        assertIterableEquals(expected, histogram.generateRanges(2, 3));
    }

    @Test
    @Order(1)
    public void generateRanges_negativeIntegerAsStepParameter_illegalArgumentExceptionMessage() {
        Histogram histogram = new Histogram();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> histogram.generateRanges(-2, 3));

        assertEquals("Step must be positive.", thrown.getMessage());
    }

    @Test
    @Order(2)
    public void generateRanges_negativeIntegerAsAmountParameter_illegalArgumentExceptionMessage() {
        Histogram histogram = new Histogram();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> histogram.generateRanges(2, -3));

        assertEquals("Amount must be positive.", thrown.getMessage());
    }

    @Test
    @Order(3)
    public void generate_wordsInRange_allWordsInOneOfRanges() {
        Histogram histogram = new Histogram();
        String words = "Now that's a risk you'll have to take you're life depends on it.";
        List<Range> ranges = histogram.generateRanges(2, 3);

        Map<Range, Integer> actual = histogram.generate(words, ranges);
        Map<Range, Integer> expected = new HashMap<>(
            Map.of(new Range(1, 2), 4,
                    new Range(3, 4), 5,
                    new Range(5, 6), 3
            )
        );

        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    public void generate_wordsOutOfRange_emptyMap() {
        Histogram histogram = new Histogram();
        String words = "Y'know this time it wasn't my fault. The Doc set.";
        List<Range> ranges = histogram.generateRanges(1, 1);

        assertEquals(new HashMap<>(), histogram.generate(words, ranges));
    }

    @Test
    @Order(5)
    public void generate_emptyText_emptyMap() {
        Histogram histogram = new Histogram();
        String words = "";
        List<Range> ranges = histogram.generateRanges(2, 3);

        assertEquals(new HashMap<>(), histogram.generate(words, ranges));
    }

    @Test
    @Order(6)
    public void generate_nullText_throwIllegalArgumentException() {
        Histogram histogram = new Histogram();

        assertThrows(IllegalArgumentException.class,
                () -> histogram.generate(null, histogram.generateRanges(2, 3)));
    }

    @Test
    @Order(7)
    public void generate_nullRange_throwIllegalArgumentException() {
        Histogram histogram = new Histogram();

        assertThrows(IllegalArgumentException.class,
                () -> histogram.generate("1955? You're my", null));
    }

    @Test
    @Order(8)
    public void getHistogram_callBeforeGenerateHistogram_emptyMap() {
        Histogram histogram = new Histogram();

        assertEquals(new HashMap<>(), histogram.getHistogram());
    }

    @Test
    @Order(9)
    public void getHistogram_callAfterGenerateHistogram_RightMap() {
        Histogram histogram = new Histogram();
        histogram.generate("Alright, McFly, you're asking for it, and now you're gonna get it.", histogram.generateRanges(2, 3));

        Map<Range, Integer> expected = new HashMap<>(
                Map.of(new Range(1, 2), 2,
                        new Range(3, 4), 4,
                        new Range(5, 6), 5
                )
        );

        assertEquals(expected, histogram.getHistogram());
    }

    @Test
    @Order(10)
    public void getHistogram_callAfterMultipleCalling_RightMap() {
        Histogram histogram = new Histogram();
        histogram.generate("Roads? Where we're going we don't need roads.", histogram.generateRanges(2, 3));

        Map<Range, Integer> expected = new HashMap<>(
                Map.of(new Range(1, 2), 1,
                        new Range(3, 4), 3,
                        new Range(5, 6), 4
                )
        );

        histogram.getHistogram();
        histogram.getHistogram();
        assertEquals(expected, histogram.getHistogram());
    }

    @Test
    @Order(11)
    public void getHistogram_stringRepresentationBeforeGenerate_EmptyString() {
        Histogram histogram = new Histogram();

        assertEquals("", histogram.toString());
    }

    @Test
    @Order(12)
    public void getHistogram_stringRepresentationAfterGenerate_RightString() {
        Histogram histogram = new Histogram();
        histogram.generate("Alright, McFly, you're asking for it, and now you're gonna get it.", histogram.generateRanges(2, 3));

        String expected = "5  - 6 | *****\n" +
                "3  - 4 | ****\n" +
                "1  - 2 | **\n";

        assertEquals(expected, histogram.toString());
    }
}
