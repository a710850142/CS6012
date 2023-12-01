package assignment06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class SimpleSortedSetTest {

    private SimpleSortedSet<Integer> set;

    // Set up for each test. This method is executed before each test method.
    @BeforeEach
    void setUp() {
        // Initialize a new SimpleSortedSet instance before each test
        set = new SimpleSortedSet<>();
    }

    // Test adding a single element to the set.
    @Test
    void testAdd() {
        assertTrue(set.add(1)); // Should return true when adding a new element
        assertFalse(set.add(1)); // Should return false when adding a duplicate element
    }

    // Test adding multiple elements to the set.
    @Test
    void testAddAll() {
        assertTrue(set.addAll(Arrays.asList(1, 2, 3))); // Adding new elements
        assertFalse(set.addAll(Arrays.asList(1, 2))); // Trying to add duplicates
    }

    // Test clearing the set.
    @Test
    void testClear() {
        set.add(1);
        set.clear(); // Clearing the set should make it empty
        assertTrue(set.isEmpty());
    }

    // Test checking for the presence of an element in the set.
    @Test
    void testContains() {
        set.add(1);
        assertTrue(set.contains(1)); // Element is in the set
        assertFalse(set.contains(2)); // Element is not in the set
    }

    // Test checking for the presence of multiple elements in the set.
    @Test
    void testContainsAll() {
        set.addAll(Arrays.asList(1, 2, 3));
        assertTrue(set.containsAll(Arrays.asList(1, 2))); // All elements are in the set
        assertFalse(set.containsAll(Arrays.asList(1, 4))); // At least one element is not in the set
    }

    // Test retrieving the first (smallest) element in the set.
    @Test
    void testFirst() {
        set.addAll(Arrays.asList(2, 3, 1));
        assertEquals(1, set.first()); // The smallest element in the set
    }

    // Test if the set is empty.
    @Test
    void testIsEmpty() {
        assertTrue(set.isEmpty()); // Initially, the set is empty
        set.add(1);
        assertFalse(set.isEmpty()); // After adding an element, the set is not empty
    }

    // Test retrieving the last (largest) element in the set.
    @Test
    void testLast() {
        set.addAll(Arrays.asList(2, 3, 1));
        assertEquals(3, set.last()); // The largest element in the set
    }

    // Test removing an element from the set.
    @Test
    void testRemove() {
        set.add(1);
        assertTrue(set.remove(1)); // Removing an existing element
        assertFalse(set.remove(1)); // Trying to remove a non-existent element
    }

    // Test removing multiple elements from the set.
    @Test
    void testRemoveAll() {
        set.addAll(Arrays.asList(1, 2, 3));
        assertTrue(set.removeAll(Arrays.asList(1, 3))); // Removing existing elements
        assertFalse(set.removeAll(Arrays.asList(4))); // Trying to remove a non-existent element
    }

    // Test the size of the set.
    @Test
    void testSize() {
        set.addAll(Arrays.asList(1, 2, 3));
        assertEquals(3, set.size()); // The size should match the number of added elements
    }

    // Test converting the set to an ArrayList.
    @Test
    void testToArrayList() {
        set.addAll(Arrays.asList(2, 3, 1));
        assertEquals(Arrays.asList(1, 2, 3), set.toArrayList()); // The ArrayList should match the elements in the set
    }

    // Test adding a null element to the set.
    @Test
    void testAddNull() {
        assertThrows(NullPointerException.class, () -> set.add(null)); // Should throw NullPointerException
    }

    // Test checking for the presence of a null element in the set.
    @Test
    void testContainsNull() {
        assertThrows(NullPointerException.class, () -> set.contains(null)); // Should throw NullPointerException
    }

    // Test removing a null element from the set.
    @Test
    void testRemoveNull() {
        assertThrows(NullPointerException.class, () -> set.remove(null)); // Should throw NullPointerException
    }

    // Test getting the first element from an empty set.
    @Test
    void testFirstEmpty() {
        assertThrows(NoSuchElementException.class, () -> set.first()); // Should throw NoSuchElementException
    }

    // Test getting the last element from an empty set.
    @Test
    void testLastEmpty() {
        assertThrows(NoSuchElementException.class, () -> set.last()); // Should throw NoSuchElementException
    }
}
