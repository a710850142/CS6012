package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class BalancedVsUnbalancedExperiment {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        // Initial warm-up
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        try (FileWriter fw = new FileWriter(new File("balanced_unbalanced_experiment.tsv"))) {
            Random random = new Random();
            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);
                ArrayList<Integer> numbers = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    numbers.add(i);
                }
                Collections.shuffle(numbers, random);

                // Unbalanced BST (BinarySearchTree)
                long totalUnbalancedTime = 0;
                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
                    long start = System.nanoTime();
                    for (int num : numbers) {
                        bst.add(num);
                    }
                    long stop = System.nanoTime();
                    totalUnbalancedTime += stop - start;
                }

                // Balanced BST (TreeSet)
                long totalBalancedTime = 0;
                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    SortedSet<Integer> treeSet = new TreeSet<>();
                    long start = System.nanoTime();
                    for (int num : numbers) {
                        treeSet.add(num);
                    }
                    long stop = System.nanoTime();
                    totalBalancedTime += stop - start;
                }

                double averageUnbalancedTime = totalUnbalancedTime / (double) ITER_COUNT;
                double averageBalancedTime = totalBalancedTime / (double) ITER_COUNT;
                System.out.println(size + "\t" + averageUnbalancedTime + "\t" + averageBalancedTime);
                fw.write(size + "\t" + averageUnbalancedTime + "\t" + averageBalancedTime + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
