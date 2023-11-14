
import org.junit.jupiter.api.Test;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//Student: Ray Ding, Partner: Xiyao Xu


class ArrayMedianUtilsTest {

    @Test
    public void testMedianComparable_NormalCase_OddLength() {
        Integer[] array = {3, 1, 2};
        assertEquals(2, ArrayMedianUtils.medianComparable(array));
    }

    @Test
    public void testMedianComparable_NormalCase_EvenLength() {
        Integer[] array = {4, 1, 3, 2};
        assertEquals(3, ArrayMedianUtils.medianComparable(array));
    }

    @Test
    public void testMedianComparable_EmptyArray() {
        Integer[] array = {};

        assertThrows(IllegalArgumentException.class, () -> {
            ArrayMedianUtils.medianComparable(array);
        });
    }

    @Test
    public void testMedianComparable_NullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayMedianUtils.medianComparable(null);
        });
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

    @Test
    public void testMedianComparator_NormalCase_NaturalOrder() {
        String[] array = {"apple", "banana", "cat", "dog"};
//        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        assertEquals("cat", ArrayMedianUtils.medianComparator(array, Comparator.naturalOrder())); // Expecting "cat" now
    }

    @Test
    public void testMedianComparator_EmptyArray() {
        String[] array = {};
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayMedianUtils.medianComparator(array, lengthComparator);
        });
    }

    @Test
    public void testMedianComparator_NullArray() {
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        assertThrows(IllegalArgumentException.class, () ->{
            ArrayMedianUtils.medianComparator(null, lengthComparator);
        });
    }

    @Test
    public void testMedianComparator_NullComparator() {
        String[] array = {"apple", "banana", "cat"};
        assertThrows(IllegalArgumentException.class, () ->{
            ArrayMedianUtils.medianComparator(array, null);
        });
    }

//    @Test
//    public void testMedianWithComparator2() {//test string array with self defined order in StringComparator
//        String[] array = {"bird", "cat", "dog", "ant"};
//        class StringComparator implements Comparator<String>{
//            @Override
//            public int compare(String str1, String str2) {
//                int lengthComparison = Integer.compare(str1.length(), str2.length());
//                if(lengthComparison != 0){
//                    return lengthComparison;
//                }
//                else{
//                    return str1.compareTo(str2);
//                }
//            }
//        }
//        assertEquals("dog", ArrayMedianUtils.medianComparator(array,new StringComparator()));
//    }



}