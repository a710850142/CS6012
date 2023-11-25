package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PeekTimingExperiment {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        // 初始化计时
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        try (FileWriter fw = new FileWriter(new File("peek_timing_results.tsv"))) {
            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);

                long totalTime = 0;
                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // 初始化栈
                    Stack<Integer> stack = new ArrayStack<>(); // 或 LinkedListStack, 取决于要测试的栈类型
                    for (int i = 0; i < size; i++) {
                        stack.push(i);
                    }

                    // 开始计时
                    long start = System.nanoTime();
                    if (!stack.isEmpty()) {
                        stack.peek(); // 执行 peek 操作
                    }
                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }
                double averageTime = totalTime / (double) ITER_COUNT;
                System.out.println(size + "\t" + averageTime); // 控制台输出
                fw.write(size + "\t" + averageTime + "\n"); // 写入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
