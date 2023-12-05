package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import assignment07.BadHashFunctor;
import assignment07.GoodHashFunctor;
import assignment07.MediocreHashFunctor;
import assignment07.ChainingHashTable;
import assignment07.HashFunctor;

public class ContainsTimingExperiment {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
//        testHashFunctor(new BadHashFunctor(), "BadHashFunctor_experiment.tsv");
//        testHashFunctor(new GoodHashFunctor(), "GoodHashFunctor_experiment.tsv");
        testHashFunctor(new MediocreHashFunctor(), "MediocreHashFunctor_experiment.tsv");
    }

    private static void testHashFunctor(HashFunctor functor, String fileName) {
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        try (FileWriter fw = new FileWriter(new File(fileName))) {
            Random random = new Random();
            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);
                long totalTime = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    ChainingHashTable set = new ChainingHashTable(size, functor);
                    for (int i = 0; i < size; i++) {
                        set.add(Integer.toString(i));
                    }
                    int findElement = random.nextInt(size);

                    long start = System.nanoTime();
                    set.contains(Integer.toString(findElement));
                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double averageTime = totalTime / (double) ITER_COUNT;
                System.out.println(functor.getClass().getSimpleName() + " - " + size + "\t" + averageTime);
                fw.write(size + "\t" + averageTime + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
