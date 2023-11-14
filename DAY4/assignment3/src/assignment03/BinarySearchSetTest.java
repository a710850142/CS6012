package assignment03;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class BinarySearchSetTest {

    private BinarySearchSet<Integer> set;

    // Set up for tests - initializes a new BinarySearchSet before each test
    @Before
    public void setUp() {
        set = new BinarySearchSet<>();
    }

    // Tests whether the comparator is null for natural ordering
    @Test
    public void testComparator() {
        assertNull(set.comparator());
    }

    // Tests the retrieval of the first (smallest) element in the set
    @Test
    public void testFirst() {
        set.add(5);
        set.add(3);
        assertEquals(Integer.valueOf(3), set.first());
    }

    // Tests if NoSuchElementException is thrown when calling first() on an empty set
    @Test(expected = NoSuchElementException.class)
    public void testFirstEmpty() {
        set.first();
    }

    // Tests the retrieval of the last (largest) element in the set
    @Test
    public void testLast() {
        set.add(5);
        set.add(3);
        assertEquals(Integer.valueOf(5), set.last());
    }

    // Tests if NoSuchElementException is thrown when calling last() on an empty set
    @Test(expected = NoSuchElementException.class)
    public void testLastEmpty() {
        set.last();
    }

    // Tests adding elements to the set, including adding duplicate elements
    @Test
    public void testAdd() {
        assertTrue(set.add(4)); // Should add successfully
        assertFalse(set.add(4)); // Duplicate should not be added
        assertTrue(set.add(2)); // Should add successfully
    }

    // Tests adding a collection of elements to the set
    @Test
    public void testAddAll() {
        assertTrue(set.addAll(Arrays.asList(1, 2, 3))); // All elements should be added
        assertFalse(set.addAll(Arrays.asList(2, 3))); // No new element added
    }

    // Tests clearing all elements from the set
    @Test
    public void testClear() {
        set.add(1);
        set.clear();
        assertTrue(set.isEmpty()); // Set should be empty after clear
    }

    // Tests checking for the presence of an element in the set
    @Test
    public void testContains() {
        set.add(3);
        assertTrue(set.contains(3)); // Element is in the set
        assertFalse(set.contains(4)); // Element is not in the set
    }

    // Tests checking if the set contains all elements from a collection
    @Test
    public void testContainsAll() {
        set.addAll(Arrays.asList(1, 2, 3));
        assertTrue(set.containsAll(Arrays.asList(1, 3))); // Elements are in the set
        assertFalse(set.containsAll(Arrays.asList(1, 4))); // Not all elements are in the set
    }

    // Tests if the set is empty
    @Test
    public void testIsEmpty() {
        assertTrue(set.isEmpty()); // Initially the set is empty
        set.add(1);
        assertFalse(set.isEmpty()); // Set is not empty after adding an element
    }

    // Tests the iterator of the set for correct ordering and values
    @Test
    public void testIterator() {
        set.addAll(Arrays.asList(3, 1, 2));
        Integer[] expected = {1, 2, 3}; // Expected order
        int i = 0;
        for (Integer num : set) {
            assertEquals(expected[i++], num); // Check if elements are in sorted order
        }
    }

    // Tests removing elements from the set
    @Test
    public void testRemove() {
        set.add(1);
        assertTrue(set.remove(1)); // Element should be removed successfully
        assertFalse(set.remove(1)); // Element is no longer in the set
    }

    // Tests removing a collection of elements from the set
    @Test
    public void testRemoveAll() {
        set.addAll(Arrays.asList(1, 2, 3));
        assertTrue(set.removeAll(Arrays.asList(1, 3))); // Specified elements should be removed
        assertFalse(set.contains(1)); // Removed element should not be present
        assertTrue(set.contains(2)); // Element not specified for removal should remain
        assertFalse(set.contains(3)); // Removed element should not be present
    }

    // Tests the size of the set
    @Test
    public void testSize() {
        set.addAll(Arrays.asList(1, 2, 3));
        assertEquals(3, set.size()); // Size should match the number of elements added
    }

    // Tests converting the set to an array
    @Test
    public void testToArray() {
        set.addAll(Arrays.asList(3, 1, 2));
        assertArrayEquals(new Integer[]{1, 2, 3}, set.toArray()); // Array should match the sorted set
    }
}
