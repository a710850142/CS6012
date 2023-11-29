package lab06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Import your custom priority queue interface and implementations
import lab06.PriorityQueue;
import lab06.TreeSetPriorityQueue;
import lab06.HeapPriorityQueue;

public class ContainsTimingExperiment2 {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        // Initial warm-up
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        // Open file writer for output
        try (FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) {
            Random random = new Random();
            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);

                long totalTimeTreeSetInOrder = 0;
                long totalTimeHeapInOrder = 0;
                long totalTimeTreeSetPermuted = 0;
                long totalTimeHeapPermuted = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // Generate in-order and permuted data
                    ArrayList<Integer> inOrderList = new ArrayList<>();
                    ArrayList<Integer> permutedList = new ArrayList<>();
                    for (int i = 0; i < size; i++) {
                        inOrderList.add(i);
                        permutedList.add(i);
                    }
                    Collections.shuffle(permutedList);

                    // Measure time for TreeSet with in-order data
                    PriorityQueue<Integer> treeSetQueueInOrder = new TreeSetPriorityQueue<>();
                    long startTreeSetInOrder = System.nanoTime();
                    for (int num : inOrderList) {
                        treeSetQueueInOrder.add(num);
                    }
                    long endTreeSetInOrder = System.nanoTime();
                    totalTimeTreeSetInOrder += endTreeSetInOrder - startTreeSetInOrder;

                    // Measure time for Heap with in-order data
                    long startHeapInOrder = System.nanoTime();
                    PriorityQueue<Integer> heapQueueInOrder = new HeapPriorityQueue<>(new ArrayList<>(inOrderList));
                    long endHeapInOrder = System.nanoTime();
                    totalTimeHeapInOrder += endHeapInOrder - startHeapInOrder;

                    // Measure time for TreeSet with permuted data
                    PriorityQueue<Integer> treeSetQueuePermuted = new TreeSetPriorityQueue<>();
                    long startTreeSetPermuted = System.nanoTime();
                    for (int num : permutedList) {
                        treeSetQueuePermuted.add(num);
                    }
                    long endTreeSetPermuted = System.nanoTime();
                    totalTimeTreeSetPermuted += endTreeSetPermuted - startTreeSetPermuted;

                    // Measure time for Heap with permuted data
                    long startHeapPermuted = System.nanoTime();
                    PriorityQueue<Integer> heapQueuePermuted = new HeapPriorityQueue<>(permutedList);
                    long endHeapPermuted = System.nanoTime();
                    totalTimeHeapPermuted += endHeapPermuted - startHeapPermuted;
                }

                // Calculate averages
                double averageTimeTreeSetInOrder = totalTimeTreeSetInOrder / (double) ITER_COUNT;
                double averageTimeHeapInOrder = totalTimeHeapInOrder / (double) ITER_COUNT;
                double averageTimeTreeSetPermuted = totalTimeTreeSetPermuted / (double) ITER_COUNT;
                double averageTimeHeapPermuted = totalTimeHeapPermuted / (double) ITER_COUNT;

                // Print and write the results
                System.out.println( size + " TreeSet InOrder: " + averageTimeTreeSetInOrder + " Heap InOrder: " + averageTimeHeapInOrder); //TreeSet InOrder Heap InOrder
                System.out.println( size + " TreeSet Permuted: " + averageTimeTreeSetPermuted + " Heap Permuted: " + averageTimeHeapPermuted);
                fw.write(size + "\t" + averageTimeTreeSetInOrder + "\t" + averageTimeHeapInOrder + "\t" + averageTimeTreeSetPermuted + "\t" + averageTimeHeapPermuted + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
