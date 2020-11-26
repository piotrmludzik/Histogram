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

    @Test
    @Order(0)
    public void testThrowFileNotFoundExceptionIfFileNotExist() {
        TextReader reader = new TextReader("src/test/resources/ghost.txt");
       assertThrows(FileNotFoundException.class, reader::read);
    }

    @Test
    @Order(1)
    public void testReturnEmptyStringIfFileIsEmpty() throws IOException {
        TextReader reader = new TextReader("src/test/resources/empty.txt");
        assertEquals("", reader.read());
    }

    @Test
    @Order(2)
    public void testReturnOneLineStringFromFile() throws IOException {
        TextReader reader = new TextReader("src/test/resources/test.txt");
        assertEquals("Harry Potter and the Sorcerer's Stone", reader.read());
    }

    @Test
    @Order(3)
    public void testReturnMultipleLineStringFromFile() throws IOException {
        TextReader reader = new TextReader("src/test/resources/text.txt");
        assertEquals("SORTING HAT SONG\n" +
                "\n" +
                "One thousand years ago this story starts\n" +
                "There were four sorcerers with strong and kind hearts\n" +
                "Bold Gryffindor from wild moor\n" +
                "Fair Ravenclaw from glen\n" +
                "Sweet Hufflepuff from valley broad\n" +
                "Shrewd Slytherin from fen\n" +
                "They had a dream to teach all that they knew\n" +
                "Witches and wizards came far and it grew\n" +
                "'Til a castle stood tall by the shores of a lake\n" +
                "And a thousand years later the magic remains\n" +
                "Old Hogwarts Sorting Hat sing me a song\n" +
                "Speak in my head tell me where I belong\n" +
                "And when things look bad and there's no where to run\n" +
                "Unite all the houses and we'll fight as one\n" +
                "Brave Godric Gryffindor favoured the strong\n" +
                "Those who had courage and knew right from wrong\n" +
                "And Rowena Ravenclaw taught only the best\n" +
                "So kind Helga Hufflepuff would teach all the rest\n" +
                "But Salazar Slytherin had is own plans\n" +
                "He thought the Muggle-borns didn't understand\n" +
                "The subtleties of magic and so he devised\n" +
                "The Chamber of Secrets with a monster inside\n" +
                "Old Hogwarts Sorting Hat sing me a song\n" +
                "Speak in my head tell me where I belong\n" +
                "And when things look bad and there's no where to run\n" +
                "Unite all the houses and we'll fight as one\n" +
                "Old Hogwarts Sorting Hat sing me a song\n" +
                "Speak in my head tell me where I belong\n" +
                "And when things look bad and there's no where to run\n" +
                "Unite all the houses and we'll fight as one\n" +
                "Unite all the houses and we'll fight as one.", reader.read());
    }
}
