package com.codecool.histogram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TextReaderTest {

    String RES_PATH = "src/test/resources/";

    @Test
    public void read_fileNotExist_fileNotFoundException() {
        TextReader reader = new TextReader(RES_PATH + "ghost.txt");

        assertThrows(FileNotFoundException.class, reader::read);
    }

    @Test
    public void read_emptyFile_emptyString() throws IOException {
        TextReader reader = new TextReader(RES_PATH + "empty.txt");

        assertEquals("", reader.read());
    }

    @Test
    public void read_fileWithOneLine_oneLineString() throws IOException {
        TextReader reader = new TextReader(RES_PATH + "test.txt");

        assertEquals("zxy123\n", reader.read());
    }

    @Test
    public void read_fileWithMultipleLine_multipleLineString() throws IOException {
        // Arrange:
        TextReader reader = new TextReader(RES_PATH + "text.txt");

        // Act:
        String result = reader.read();

        // Assert:
        String expected = "qwerty\n" +
                "123456\n" +
                "~!@#$%\n";

        assertEquals(expected, result);
    }
}