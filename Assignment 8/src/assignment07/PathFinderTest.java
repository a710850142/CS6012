package assignment07; // 包声明 - Declaring the package

import org.junit.jupiter.api.*; // 导入JUnit 5相关类 - Importing JUnit 5 related classes
import org.junit.jupiter.api.io.TempDir; // 导入JUnit 5临时目录相关类 - Importing JUnit 5 TempDir related class

import java.io.File; // 导入File类 - Importing File class
import java.io.PrintWriter; // 导入PrintWriter类 - Importing PrintWriter class
import java.nio.file.Path; // 导入Path类 - Importing Path class
import java.util.Scanner; // 导入Scanner类 - Importing Scanner class

import static org.junit.jupiter.api.Assertions.*; // 导入JUnit 5断言 - Importing JUnit 5 assertions

public class PathFinderTest {

    @TempDir
    Path tempDir; // 创建一个临时目录 - Creating a temporary directory

    File inputFile; // 输入文件 - Input file
    File outputFile; // 输出文件 - Output file

    @BeforeEach
    void setUp() throws Exception {
        inputFile = tempDir.resolve("input.txt").toFile(); // 创建输入文件 - Creating the input file
        outputFile = tempDir.resolve("output.txt").toFile(); // 创建输出文件 - Creating the output file

        // 创建一个示例迷宫输入文件 - Creating a sample maze input file
        try (PrintWriter writer = new PrintWriter(inputFile)) {
            writer.println("3 3"); // 写入迷宫尺寸 - Writing maze dimensions
            writer.println("S X G"); // 写入迷宫第一行 - Writing the first row of the maze
            writer.println("  X  "); // 写入迷宫第二行 - Writing the second row of the maze
            writer.println("     "); // 写入迷宫第三行 - Writing the third row of the maze
        }
    }

    @Test
    void testSolveMaze() throws Exception {
        PathFinder.solveMaze(inputFile.getAbsolutePath(), outputFile.getAbsolutePath()); // 调用solveMaze方法 - Calling solveMaze method

        assertTrue(outputFile.exists(), "Output file should be created"); // 检查输出文件是否被创建 - Checking if the output file is created

        // 读取输出文件并执行断言 - Reading the output file and performing assertions
        try (Scanner scanner = new Scanner(outputFile)) {
            assertTrue(scanner.hasNext(), "Output file should not be empty"); // 检查输出文件是否非空 - Checking if the output file is not empty
            // 在此进行更多内容的检查 - Perform additional content checks here
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        // 如有必要，进行文件清理 - Cleaning up files if necessary
    }
}
