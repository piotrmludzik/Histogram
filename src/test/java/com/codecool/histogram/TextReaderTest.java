package com.codecool.histogram;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TextReaderTest {

    String RES_PATH = "src/test/resources/";

    @Test
    @Order(0)
    public void read_fileNotExist_fileNotFoundException() {
        TextReader reader = new TextReader(RES_PATH + "ghost.txt");
        assertThrows(FileNotFoundException.class, reader::read);
    }

    @Test
    @Order(1)
    public void read_emptyFile_emptyString() throws IOException {
        TextReader reader = new TextReader(RES_PATH + "empty.txt");
        String expected = "";
        assertEquals(expected, reader.read());
    }

    @Test
    @Order(2)
    public void read_fileWithOneLine_oneLineString() throws IOException {
        TextReader reader = new TextReader(RES_PATH + "test.txt");
        String expected = "Now, Biff, um, can I assume that your insurance is gonna pay for the damage?\n";
        assertEquals(expected, reader.read());
    }

    @Test
    @Order(3)
    public void read_fileWithMultipleLine_multipleLineString() throws IOException {
        TextReader reader = new TextReader(RES_PATH + "text.txt");
        String expected = "Uh Doc, uh no. No, don't be silly.\n" +
                "Yeah, well, how about my homework, McFly?\n" +
                "Of course, the Enchantment Under The Sea Dance they're supposed to go to this, that's where they kiss for the first time.\n" +
                "Okay.\n" +
                "This is more serious than I thought. Apparently your mother is amorously infatuated with you instead of your father.\n" +
                "Okay, alright, I'll prove it to you. Look at my driver's license, expires 1987. Look at my birthday, for crying out load I haven't even been born yet. And, look at this picture, my brother, my sister, and me. Look at the sweatshirt, Doc, class of 1984.\n" +
                "Oh, pleased to meet you, Calvin Marty Klein. Do you mind if I sit here?\n" +
                "Here you go, lady. There's a quarter.\n" +
                "You're gonna be in the car with her.\n" +
                "What, what, ma?\n" +
                "Hi, it's really a pleasure to meet you.\n" +
                "Ah.\n" +
                "Crazy drunk drivers.\n" +
                "You got a real attitude problem, McFly. You're a slacker. You remind me of you father when he went her, he was a slacker too.\n" +
                "Well, safe and sound, now, n good old 1955.\n" +
                "Yeah, it's 8:00.\n" +
                "I noticed you band is on the roster for dance auditions after school today. Why even bother Mcfly, you haven't got a chance, you're too much like your own man. No McFly ever amounted to anything in the history of Hill Valley.\n" +
                "Roads? Where we're going we don't need roads.\n" +
                "On the night I go back in time, you get- Doc.\n";
        assertEquals(expected, reader.read());
    }
}
