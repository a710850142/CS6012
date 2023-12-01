package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class ContainsTimingExperiment {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        // Initial spin to allow for JVM warm-up
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        try (FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) {
            Random random = new Random();
            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);

                long totalTime = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // Setup
                    SortedSet<Integer> set = new TreeSet<>();
                    for (int i = 0; i < size; i++) {
                        set.add(i);
                    }
                    int findElement = random.nextInt(size); // Random element for contains check

                    // Timing
                    long start = System.nanoTime();
                    set.contains(findElement); // Changed from remove to contains
                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double averageTime = totalTime / (double) ITER_COUNT;
                System.out.println(size + "\t" + averageTime); // Output to console
                fw.write(size + "\t" + averageTime + "\n"); // Write to file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
