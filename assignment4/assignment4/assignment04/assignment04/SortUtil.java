package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;



public class SortUtil {
    private static final int threshold = 1;
    public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comp){
        if(list == null || list.size() < 2) return;

        ArrayList<T> auxList = new ArrayList<> (Collections.nCopies(list.size(), null)); //auxiliary list with the same capacity of list
        mergesortRecursive(list, auxList, 0, list.size() - 1, comp);
    }

    private static <T> void mergesortRecursive(ArrayList<T> list, ArrayList<T> auxList, int low, int high, Comparator<? super T> comp) {
        if(high - low <= threshold){
            insertionSort(list, low, high, comp);
            return; // Cause the method to exit, and goes back to the next line after the recursive call in the previous level of recursion
        }

        int mid = low + (high-low)/2; // To avoid the case that low + high is larger than the max value of bits
        mergesortRecursive(list, auxList, low, mid, comp);
        mergesortRecursive(list, auxList, mid + 1, high, comp);
        merge(list, auxList, low, mid, high, comp);
    }

    private static <T> void insertionSort(ArrayList<T> list, int left, int right, Comparator<? super T> comp) {
        for (int i = left + 1; i <= right; i++){ // i starts at the second most left position
            T key = list.get(i); // set key with the element at index i
            int j = i - 1; // j starts at the left position of i

            while(j >= left && comp.compare(list.get(j), key) > 0){ // list.get(j) is larger than key
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key); // j+1 is due to j-- before exit the inner loop
        }
    }

    private static <T> void merge(ArrayList<T> list, ArrayList<T> auxList, int low, int mid, int high, Comparator<? super T> comp){
        for (int k = low; k <= high; k++){
            auxList.set(k, list.get(k)); //put two sorted sublists back into the auxiliary list
        }

        int i = low, j = mid + 1; // i starts at the beginning of the first sublist, j starts at the beginning of the second sublist
        for(int k = low; k <= high; k++){ // k is the index in the list where next element will be placed
            /* It handles the case where the first sublist get exhausted.
            It takes the next element auxList.get(j) from the second sublist and places it into the list at index k.
            j++ increments j to point to the next element in the second sublist for the next iteration.
             */
            if(i > mid) list.set(k, auxList.get(j++));
            // It handles the case where the second sublist get exhausted.
            else if(j > high) list.set(k, auxList.get(i++));
            //This two conditions compare the current elements of the two sublists using the comparator
            else if(comp.compare(auxList.get(j), auxList.get(i)) < 0) list.set(k,auxList.get(j++));
            else list.set(k, auxList.get(i++));
        }
    }

    public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comp) {
        quicksortRecursive(list, 0, list.size() - 1, comp);
    }

    private static <T> void quicksortRecursive(ArrayList<T> list, int low, int high, Comparator<? super T> comp) {
        if (high - low <= threshold){
            insertionSort(list, low, high, comp);
            return;
        }

        int pivotIndex = partition(list, low, high, comp);
        quicksortRecursive(list, low, pivotIndex - 1, comp);
        quicksortRecursive(list, pivotIndex + 1, high, comp);
    }

    private static <T> int partition(ArrayList<T> list, int low, int high, Comparator<? super T> comp) {

        // Choose random element for pivot
        int i = low, j = high + 1; // j is initialized to high + 1, because j is decremented before its first use in the below while loop
        Random random = new Random();
        int randomPivotIndex = low + random.nextInt(high - low + 1); // Random index for pivot
        Collections.swap(list, randomPivotIndex, low);
        T pivot = list.get(low); // Use the random element as pivot
        while(true){
            while (comp.compare(list.get(++i), pivot) < 0) // Find item on left to swap, i is incremented before its first use
                if(i == high) break;
            while (comp.compare(pivot, list.get(--j)) < 0) // Find item on right to swap, j is decremented before its first use
                if(j == low) break;
            if (i >= j) break; // If pointers cross, break from the while(true) loop
            Collections.swap(list, i, j); // Swap
        }
        // Swap pivot into its correct place
        Collections.swap(list, low , j);
        return j; // j is pivotIndex

        // Choose last element for pivot
//        int i = low - 1; // i starts one position to the left of the low
//        T pivot = list.get(high); // Use the last element as pivot
//        for (int j = low; j < high; j++){
//            if(comp.compare(list.get(j), pivot) <=  0){ // If current element is smaller than or equal to pivot
//                i++; // Increment i
//                Collections.swap(list, i, j); // Swap list[i] and list[j]
//            }
//        }
//        Collections.swap(list, i + 1, high); // Swap pivot into its correct place
//        return i + 1;

        // Choose first element for pivot
//        int i = high + 1; // i starts one position to the right of the high
//        T pivot = list.get(low); // Use the first element as pivot
//        for (int j = high; j > low; j--){
//            if(comp.compare(list.get(j), pivot) >=  0){ // If current element is larger than or equal to pivot
//                i--; // Increment i
//                Collections.swap(list, i, j); // Swap list[i] and list[j]
//            }
//        }
//        Collections.swap(list, i - 1, low); // Swap pivot into its correct place
//        return i - 1;
    }

    public static ArrayList<Integer> generateBestCase(int size) {
        ArrayList<Integer> list = new ArrayList<>(size); // size is used for setting the capacity
        for (int i = 1; i <= size; i++) { // ascending order
            list.add(i);
        }
        return list;
    }

    public static ArrayList<Integer> generateAverageCase(int size) {
        ArrayList<Integer> list = generateBestCase(size); // Call the above method
        Collections.shuffle(list); // shuffle the ascending list
        return list;
    }

    public static ArrayList<Integer> generateWorstCase(int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = size; i > 0; i--) { // descending order
            list.add(i);
        }
        return list;
    }

}
