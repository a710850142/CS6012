import java.util.Arrays;
import java.util.Comparator;

public class ArrayMedianUtils {

    // Method to find median with Comparable constraint
    public static <T extends Comparable<T>> T medianComparable(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        Arrays.sort(array);

        int middle = array.length / 2;
        return array[middle];
    }

    // Method to find median with a Comparator
    public static <T> T medianComparator(T[] array, Comparator<T> comparator) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        Arrays.sort(array, comparator);

        int middle = array.length / 2;
        return array[middle];
    }
}
