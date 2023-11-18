package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ContainsTimingExperiment {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        // you spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
            ;

                                                                // changed
        try (FileWriter fw = new FileWriter(new File("mergesortThr100.tsv"))) { // open up a file writer so we can write
            // to file.
            Random random = new Random();
            for (int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
                int size = (int) Math.pow(2, exp); // or ..

                // Do the experiment multiple times, and average out the results
                long totalTime = 0;
                ArrayList<Integer> list = SortUtil.generateAverageCase(size);//changed

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    //SET UP!
//                    SortedSet<Integer> set = new TreeSet<>();
                    ArrayList<Integer> deepCopyList = new ArrayList<>(size);
                    for (Integer item: list) {
                        deepCopyList.add(item); // This is deep copy.
                    }

                    //shallow copy using constructor
//                    ArrayList<Integer> tempSet = new ArrayList<>(set);
                    int findElement = random.nextInt(size); // This gets me a random int between 0 and size;

                    // TIME IT!
                    long start = System.nanoTime();
                    SortUtil.quicksort(deepCopyList, Comparator.naturalOrder());//changed
                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }
                double averageTime = totalTime / (double) ITER_COUNT;
                System.out.println(size + "\t" + averageTime); // print to console
                fw.write(size + "\t" + averageTime + "\n"); // write to file.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}