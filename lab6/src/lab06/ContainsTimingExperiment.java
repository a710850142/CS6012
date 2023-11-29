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

public class ContainsTimingExperiment {

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

                long totalTimeTreeSet = 0;
                long totalTimeHeap = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // TreeSet-based Priority Queue
                    PriorityQueue<Integer> treeSetQueue = new TreeSetPriorityQueue<>();
                    long startTreeSet = System.nanoTime();
                    for (int i = 0; i < size; i++) {
                        treeSetQueue.add(i);
                    }
                    long endTreeSet = System.nanoTime();
                    totalTimeTreeSet += endTreeSet - startTreeSet;

                    // Heap-based Priority Queue
                    ArrayList<Integer> heapList = new ArrayList<>();
                    for (int i = 0; i < size; i++) {
                        heapList.add(i);
                    }
                    Collections.shuffle(heapList); // Shuffle for random element order
                    long startHeap = System.nanoTime();
                    PriorityQueue<Integer> heapQueue = new HeapPriorityQueue<>(heapList);
                    long endHeap = System.nanoTime();
                    totalTimeHeap += endHeap - startHeap;
                }

                double averageTimeTreeSet = totalTimeTreeSet / (double) ITER_COUNT;
                double averageTimeHeap = totalTimeHeap / (double) ITER_COUNT;

                // Print and write the results
                System.out.println("Size: " + size + " TreeSet: " + averageTimeTreeSet + " Heap: " + averageTimeHeap);
                fw.write(size + "\t" + averageTimeTreeSet + "\t" + averageTimeHeap + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
