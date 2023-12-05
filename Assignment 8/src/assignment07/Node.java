package assignment07; // 包声明 - Declaring the package

public class Node {
    int x, y; // 节点的x和y坐标 - Node's x and y coordinates
    Node parent; // 父节点引用 - Reference to the parent node
    boolean isWall; // 是否是墙的标志 - Flag to indicate if it's a wall

    public Node(int x, int y, boolean isWall) {
        this.x = x; // 设置x坐标 - Setting the x coordinate
        this.y = y; // 设置y坐标 - Setting the y coordinate
        this.isWall = isWall; // 设置是否是墙的标志 - Setting the wall flag
        this.parent = null; // 初始化父节点为null - Initializing the parent node as null
    }
}
