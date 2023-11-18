package assignment04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class SortUtilTest {
    private Comparator<Integer> comp;

    /**
     * Sets up common test objects and conditions before each test method is run.
     * Initializes a comparator for integers in natural order.
     */
    @BeforeEach
    public void setUp() {
        comp = Comparator.naturalOrder();
    }

    /**
     * Tests mergesort on an empty list of integers.
     * Ensures the list remains empty after sorting.
     */
    @Test
    public void testMergesortEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        SortUtil.mergesort(list, comp);
        assertTrue(list.isEmpty());
    }

    /**
     * Tests mergesort on a list containing a single integer element.
     * Ensures the list remains unchanged and contains the correct element.
     */
    @Test
    public void testMergesortSingleElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        SortUtil.mergesort(list, comp);
        assertEquals(1, list.size());
        assertEquals(11, list.get(0));
    }

    /**
     * Tests mergesort on a list with multiple integer elements.
     * Ensures the elements are sorted in non-descending order.
     */
    @Test
    public void testMergesortMultipleElements() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(20);
        SortUtil.mergesort(list, comp);
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) <= list.get(i + 1));
        }
    }

    /**
     * Tests mergesort on a list of strings.
     * Ensures the strings are sorted in their natural order.
     */
    @Test
    public void testMergesortListOfString() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "date"));
        Comparator<String> stringComparator = Comparator.naturalOrder();
        SortUtil.mergesort(list, stringComparator);
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).compareTo(list.get(i + 1)) <= 0);
        }
        assertEquals("banana", list.get(1));
    }

    /**
     * Tests mergesort on a list of PhoneNumber objects.
     * Ensures the PhoneNumbers are sorted based on the specified comparator.
     */
    @Test
    public void testMergesortListOfPhoneNumber() {
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>(Arrays.asList(
                new PhoneNumber("555-489-7110"),
                new PhoneNumber("787-654-3450"),
                new PhoneNumber("555-505-5522")
        ));
        SortUtil.mergesort(phoneNumbers, new PhoneNumber.OrderByNumber());
        assertEquals("(555) 505-5522", phoneNumbers.get(1).toString());
    }

    /**
     * Tests quicksort on an empty list of integers.
     * Ensures the list remains empty after sorting.
     */
    @Test
    public void testQuicksortEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        SortUtil.quicksort(list, comp);
        assertTrue(list.isEmpty());
    }

    /**
     * Tests quicksort on a list containing a single double element.
     * Ensures the list remains unchanged and contains the correct element.
     */
    @Test
    public void testQuicksortSingleElement() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(11.11);
        SortUtil.quicksort(list, Comparator.naturalOrder());
        assertEquals(1, list.size());
        assertEquals(11.11, list.get(0));
    }

    /**
     * Tests quicksort on a list with multiple integer elements.
     * Ensures the elements are sorted in non-descending order.
     */
    @Test
    public void testQuicksortMultipleElements() {
        ArrayList<Integer> list = SortUtil.generateAverageCase(20);
        SortUtil.quicksort(list, comp);
        for (int i = 0; i < list.size() -1; i++) {
            assertTrue(list.get(i) <= list.get(i + 1));
        }
    }

    /**
     * Tests the generation of the best case scenario for sorting algorithms.
     * Ensures that the list is already sorted in ascending order.
     */
    @Test
    public void testGenerateBestCase() {
        ArrayList<Integer> list = SortUtil.generateBestCase(20);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i + 1, list.get(i));
        }
    }

    /**
     * Tests the generation of the worst case scenario for sorting algorithms.
     * Ensures that the list is sorted in descending order.
     */
    @Test
    public void testGenerateWorstCase() {
        ArrayList<Integer> list = SortUtil.generateWorstCase(20);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(20 - i, list.get(i));
        }
    }
}
