import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Comparator;

public class ArrayMedianUtilsTest {

    // Tests for medianComparable method
    @Test
    public void testMedianComparable_NormalCase_OddLength() {
        Integer[] array = {3, 1, 2};
        assertEquals(Integer.valueOf(2), ArrayMedianUtils.medianComparable(array));
    }

    @Test
    public void testMedianComparable_NormalCase_EvenLength() {
        Integer[] array = {4, 1, 3, 2};
        assertEquals(Integer.valueOf(3), ArrayMedianUtils.medianComparable(array));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMedianComparable_EmptyArray() {
        Integer[] array = {};
        ArrayMedianUtils.medianComparable(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMedianComparable_NullArray() {
        ArrayMedianUtils.medianComparable(null);
    }

    // Tests for medianComparator method
    @Test
    public void testMedianComparator_NormalCase_OddLength() {
        String[] array = {"cat", "apple", "banana"};
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        assertEquals("apple", ArrayMedianUtils.medianComparator(array, lengthComparator)); // Expecting "apple" now
    }

    @Test
    public void testMedianComparator_NormalCase_EvenLength() {
        String[] array = {"apple", "banana", "cat", "dog"};
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        assertEquals("apple", ArrayMedianUtils.medianComparator(array, lengthComparator)); // Expecting "apple" now
    }


    @Test(expected = IllegalArgumentException.class)
    public void testMedianComparator_EmptyArray() {
        String[] array = {};
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        ArrayMedianUtils.medianComparator(array, lengthComparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMedianComparator_NullArray() {
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        ArrayMedianUtils.medianComparator(null, lengthComparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMedianComparator_NullComparator() {
        String[] array = {"apple", "banana", "cat"};
        ArrayMedianUtils.medianComparator(array, null);
    }
}
