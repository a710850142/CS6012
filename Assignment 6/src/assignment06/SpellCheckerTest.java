package assignment06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class SpellCheckerTest {

    private SpellChecker spellChecker;

    // Set up for each test. This method is executed before each test method.
    @BeforeEach
    void setUp() {
        // Initialize a new SpellChecker instance before each test
        spellChecker = new SpellChecker();
    }

    // Test the constructor that initializes the dictionary with a list of words.
    @Test
    void testConstructorWithWords() {
        // Create a new SpellChecker instance with predefined words
        SpellChecker sc = new SpellChecker(Arrays.asList("apple", "banana"));
        // Assert that 'apple' is recognized as a correct word (spellCheck should return an empty list)
        assertTrue(sc.spellCheck(createTempFileWithContent("apple")).isEmpty());
    }

    // Test the constructor that initializes the dictionary from a file.
    @Test
    void testConstructorWithFile() throws IOException {
        // Create a temporary dictionary file
        File dictionaryFile = createTempFileWithContent("apple\nbanana");
        // Create a new SpellChecker instance using the dictionary file
        SpellChecker sc = new SpellChecker(dictionaryFile);
        // Assert that 'apple' is recognized as a correct word
        assertTrue(sc.spellCheck(createTempFileWithContent("apple")).isEmpty());
    }

    // Test adding a word to the dictionary.
    @Test
    void testAddToDictionary() {
        // Add 'orange' to the dictionary
        spellChecker.addToDictionary("orange");
        // Assert that 'orange' is now recognized as a correct word
        assertTrue(spellChecker.spellCheck(createTempFileWithContent("orange")).isEmpty());
    }

    // Test removing a word from the dictionary.
    @Test
    void testRemoveFromDictionary() {
        // Add and then remove 'grape' from the dictionary
        spellChecker.addToDictionary("grape");
        spellChecker.removeFromDictionary("grape");
        // Assert that 'grape' is now recognized as incorrect
        assertFalse(spellChecker.spellCheck(createTempFileWithContent("grape")).isEmpty());
    }

    // Test the spell checking functionality.
    @Test
    void testSpellCheck() {
        // Add 'kiwi' to the dictionary
        spellChecker.addToDictionary("kiwi");
        // Assert that 'kiwi' is recognized as correct, but 'kiwii' is not
        assertTrue(spellChecker.spellCheck(createTempFileWithContent("kiwi")).isEmpty());
        assertFalse(spellChecker.spellCheck(createTempFileWithContent("kiwii")).isEmpty());
    }

    // Helper method to create a temporary file with specified content.
    private File createTempFileWithContent(String content) {
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("test", ".txt");
            // Write the provided content to the file
            try (PrintWriter writer = new PrintWriter(tempFile)) {
                writer.println(content);
            }
            return tempFile;
        } catch (IOException e) {
            // Fail the test if there's an IO error during file creation
            fail("Failed to create temp file for testing");
            return null;
        }
    }
}
