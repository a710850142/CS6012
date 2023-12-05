package assignment07; // 包声明 - Declaring the package

import org.junit.jupiter.api.BeforeEach; // 导入JUnit 5的BeforeEach注解 - Importing BeforeEach annotation from JUnit 5
import org.junit.jupiter.api.Test; // 导入JUnit 5的Test注解 - Importing Test annotation from JUnit 5
import static org.junit.jupiter.api.Assertions.*; // 导入JUnit 5的断言类 - Importing Assertions from JUnit 5

import java.util.List; // 导入Java的List接口 - Importing List interface from Java

public class GraphTest {
    private Graph graph; // 私有Graph对象 - Private Graph object
    private char[][] maze; // 私有迷宫字符数组 - Private maze character array
    private final int height = 5; // 迷宫的高度为5 - Maze height set to 5
    private final int width = 5; // 迷宫的宽度为5 - Maze width set to 5

    @BeforeEach
    void setUp() {
        // 初始化测试迷宫 - Initialize a test maze
        maze = new char[][]{
                {' ', ' ', ' ', ' ', 'X'},
                {'X', 'X', ' ', 'X', ' '},
                {' ', ' ', ' ', 'X', ' '},
                {' ', 'X', 'X', 'X', ' '},
                {' ', ' ', ' ', ' ', ' '}
        };
        graph = new Graph(maze, height, width); // 创建Graph对象 - Creating a Graph object
    }

    @Test
    void testConstructor() {
        // 测试构造函数是否正确初始化Graph - Test if the graph is initialized correctly by the constructor
        assertNotNull(graph, "Graph should not be null"); // 断言Graph不为空 - Asserting graph is not null
        // 在此处添加更多检查 - Additional checks can be added here
    }

    @Test
    void testFindPath() {
        // 从图的内部结构使用节点 - Use nodes from the graph's internal structure
        Node start = graph.nodes[0][0]; // 设置起点 - Setting start node
        Node goal = graph.nodes[4][4]; // 设置终点 - Setting goal node
        List<Node> path = graph.findPath(start, goal); // 查找路径 - Finding the path

        assertNotNull(path, "Path should not be null"); // 断言路径不为空 - Asserting path is not null
        assertFalse(path.isEmpty(), "Path should not be empty"); // 断言路径不为空 - Asserting path is not empty

        // 进行额外检查 - Additional checks
        assertTrue(path.contains(start), "Path should contain start node"); // 断言路径包含起点 - Asserting path contains start node
        assertTrue(path.contains(goal), "Path should contain goal node"); // 断言路径包含终点 - Asserting path contains goal node
        // 在此处添加更多断言以检查路径是否正确 - Additional assertions to check if the path is correct
    }

    @Test
    void testBuildPath() {
        // 从图的内部结构使用节点 - Use nodes from the graph's internal structure
        Node start = graph.nodes[0][0]; // 设置起点 - Setting start node
        Node goal = graph.nodes[4][4]; // 设置终点 - Setting goal node
        List<Node> path = graph.findPath(start, goal); // 查找路径 - Finding the path

        assertNotNull(path, "Path should not be null"); // 断言路径不为空 - Asserting path is not null
        assertFalse(path.isEmpty(), "Path should not be empty"); // 断言路径不为空 - Asserting path is not empty

        // 检查构建的路径的正确性 - Check the correctness of the built path
        assertEquals(start, path.get(0), "First node in the path should be the start node"); // 断言路径的第一个节点是起点 - Asserting the first node in the path is the start node
        assertEquals(goal, path.get(path.size() - 1), "Last node in the path should be the goal node"); // 断言路径的最后一个节点是终点 - Asserting the last node in the path is the goal node
    }

    @Test
    void testGetNeighbors() {
        // 测试getNeighbors方法 - Test the getNeighbors method
        Node node = new Node(2, 2, false); // 创建一个节点用于测试 - Creating a node for testing
        List<Node> neighbors = graph.getNeighbors(node); // 获取邻居节点 - Getting the neighbor nodes
        assertNotNull(neighbors, "Neighbors list should not be null"); // 断言邻居列表不为空 - Asserting neighbors list is not null
        assertEquals(4, neighbors.size(), "Should have 4 neighbors"); // 断言应该有4个邻居 - Asserting there should be 4 neighbors
        // 在此处添加更多断言以检查邻居的正确性 - Additional assertions to check the correctness of neighbors
    }
}
