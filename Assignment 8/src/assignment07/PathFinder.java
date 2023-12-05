package assignment07; // 包声明 - Declaring the package

import java.io.*; // 导入IO相关的类 - Importing IO-related classes
import java.util.List; // 导入List类 - Importing List class
import java.util.Scanner; // 导入Scanner类 - Importing Scanner class

public class PathFinder {

    public static void solveMaze(String inputFile, String outputFile) {
        char[][] maze; // 定义字符型二维数组表示迷宫 - Defining a 2D char array for the maze
        Node start = null, goal = null; // 定义起点和终点节点 - Defining start and goal nodes
        int height = 0, width = 0; // 定义迷宫的高度和宽度 - Defining the height and width of the maze

        try (Scanner scanner = new Scanner(new File(inputFile))) { // 使用Scanner读取文件 - Using Scanner to read the file
            if (scanner.hasNextLine()) { // 检查是否有下一行 - Checking if there is a next line
                String[] dimensions = scanner.nextLine().split(" "); // 读取迷宫的尺寸 - Reading the dimensions of the maze
                height = Integer.parseInt(dimensions[0]); // 解析高度 - Parsing height
                width = Integer.parseInt(dimensions[1]); // 解析宽度 - Parsing width
                maze = new char[height][width]; // 初始化迷宫数组 - Initializing the maze array
            } else {
                throw new IllegalArgumentException("Invalid maze file format"); // 抛出异常，文件格式不正确 - Throwing an exception for incorrect file format
            }

            for (int i = 0; i < height; i++) { // 遍历迷宫的每一行 - Looping through each row of the maze
                String line = scanner.nextLine(); // 读取一行 - Reading a line
                for (int j = 0; j < width; j++) { // 遍历每一列 - Looping through each column
                    maze[i][j] = line.charAt(j); // 将字符填入迷宫 - Filling the maze with characters
                    if (maze[i][j] == 'S') { // 如果是起点 - If it's the start point
                        start = new Node(i, j, false); // 初始化起点节点 - Initializing the start node
                    } else if (maze[i][j] == 'G') { // 如果是终点 - If it's the goal point
                        goal = new Node(i, j, false); // 初始化终点节点 - Initializing the goal node
                    }
                }
            }

            Graph graph = new Graph(maze, height, width); // 创建图 - Creating the graph
            List<Node> path = graph.findPath(start, goal); // 查找路径 - Finding the path

            for (Node node : path) { // 遍历路径上的每个节点 - Looping through each node in the path
                if (maze[node.x][node.y] == ' ') { // 如果节点是空白处 - If the node is a blank space
                    maze[node.x][node.y] = '.'; // 标记路径 - Marking the path
                }
            }

            writeMazeToFile(maze, outputFile, height, width); // 将结果写入文件 - Writing the result to a file

        } catch (FileNotFoundException e) { // 捕获文件未找到异常 - Catching file not found exception
            e.printStackTrace(); // 打印异常堆栈 - Printing the stack trace
        }
    }

    private static void writeMazeToFile(char[][] maze, String outputFile, int height, int width) {
        try (PrintWriter output = new PrintWriter(new FileWriter(outputFile))) { // 创建写入器 - Creating a writer
            output.println(height + " " + width); // 写入高度和宽度 - Writing height and width
            for (int i = 0; i < height; i++) { // 遍历迷宫的每一行 - Looping through each row of the maze
                for (int j = 0; j < width; j++) { // 遍历每一列 - Looping through each column
                    output.print(maze[i][j]); // 写入字符 - Writing the character
                }
                output.println(); // 写入换行 - Writing a new line
            }
        } catch (IOException e) { // 捕获IO异常 - Catching IO exception
            e.printStackTrace(); // 打印异常堆栈 - Printing the stack trace
        }
    }
}
