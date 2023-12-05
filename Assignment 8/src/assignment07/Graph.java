package assignment07; // 包声明 - Declaring the package

import java.util.*; // 导入Java工具包 - Importing Java utility classes

public class Graph {
    final Node[][] nodes; // 节点数组 - Array of nodes
    private final int height, width; // 图的高度和宽度 - Height and width of the graph

    public Graph(char[][] maze, int height, int width) {
        this.height = height; // 初始化高度 - Initializing height
        this.width = width; // 初始化宽度 - Initializing width
        nodes = new Node[height][width]; // 初始化节点数组 - Initializing the node array

        for (int i = 0; i < height; i++) { // 遍历每一行 - Looping through each row
            for (int j = 0; j < width; j++) { // 遍历每一列 - Looping through each column
                nodes[i][j] = new Node(i, j, maze[i][j] == 'X'); // 根据迷宫创建节点 - Creating nodes based on the maze
            }
        }
    }

    public List<Node> findPath(Node start, Node goal) {
        Queue<Node> queue = new LinkedList<>(); // 创建队列 - Creating a queue
        boolean[][] visited = new boolean[height][width]; // 访问标记数组 - Visited flag array
        queue.add(start); // 将起点加入队列 - Adding the start node to the queue
        visited[start.x][start.y] = true; // 标记起点为已访问 - Marking the start node as visited

        while (!queue.isEmpty()) { // 当队列不为空时 - While the queue is not empty
            Node current = queue.poll(); // 弹出队列头部节点 - Polling the node at the front of the queue
            if (current.equals(goal)) { // 如果当前节点是目标节点 - If the current node is the goal node
                return buildPath(current); // 构建并返回路径 - Build and return the path
            }

            for (Node neighbor : getNeighbors(current)) { // 遍历当前节点的邻居 - Looping through the neighbors of the current node
                if (!visited[neighbor.x][neighbor.y] && !neighbor.isWall) { // 如果邻居未被访问且不是墙 - If the neighbor has not been visited and is not a wall
                    neighbor.parent = current; // 设置当前节点为邻居的父节点 - Setting the current node as the parent of the neighbor
                    queue.add(neighbor); // 将邻居加入队列 - Adding the neighbor to the queue
                    visited[neighbor.x][neighbor.y] = true; // 标记邻居为已访问 - Marking the neighbor as visited
                }
            }
        }
        return Collections.emptyList(); // 如果找不到路径返回空列表 - Returning an empty list if no path is found
    }

    private List<Node> buildPath(Node goal) {
        List<Node> path = new ArrayList<>(); // 创建路径列表 - Creating a path list
        Node current = goal; // 从目标节点开始 - Starting from the goal node
        while (current != null) { // 当当前节点不为空时 - While the current node is not null
            path.add(current); // 将当前节点加入路径 - Adding the current node to the path
            current = current.parent; // 移动到父节点 - Moving to the parent node
        }
        Collections.reverse(path); // 反转路径列表 - Reversing the path list
        return path; // 返回路径 - Returning the path
    }

    List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>(); // 创建邻居列表 - Creating a neighbors list
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义移动方向 - Defining movement directions

        for (int[] dir : directions) { // 遍历每个方向 - Looping through each direction
            int newX = node.x + dir[0]; // 计算新的X坐标 - Calculating the new X coordinate
            int newY = node.y + dir[1]; // 计算新的Y坐标 - Calculating the new Y coordinate
            if (newX >= 0 && newX < height && newY >= 0 && newY < width) { // 如果新坐标在范围内 - If the new coordinates are within bounds
                neighbors.add(nodes[newX][newY]); // 将相应节点加入邻居列表 - Adding the corresponding node to the neighbors list
            }
        }
        return neighbors; // 返回邻居列表 - Returning the neighbors list
    }
}
