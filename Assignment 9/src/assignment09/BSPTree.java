package assignment09; // 包声明 - Declare the package

import java.util.ArrayList; // 导入ArrayList类 - Import the ArrayList class

public class BSPTree {
    private static class Node {
        Segment segment; // 片段 - Segment
        Node left; // 左子节点 - Left child node
        Node right; // 右子节点 - Right child node

        Node(Segment segment) {
            this.segment = segment; // 初始化片段 - Initialize segment
        }
    }

    private Node root; // 树的根节点 - Root of the tree

    public BSPTree() {
        this.root = null; // 构造函数，初始化根节点为null - Constructor, initialize root as null
    }

    public BSPTree(ArrayList<Segment> segments) {
        for (Segment segment : segments) {
            insert(segment); // 插入片段 - Insert segment
        }
    }

    public void insert(Segment segment) {
        root = insert(root, segment); // 插入片段到树 - Insert segment into the tree
    }

    private Node insert(Node node, Segment segment) {
        if (node == null) {
            return new Node(segment); // 如果节点为空，创建新节点 - If node is null, create a new node
        }

        if (segment.whichSide(node.segment) < 0) {
            node.left = insert(node.left, segment); // 插入到左子树 - Insert into left subtree
        } else {
            node.right = insert(node.right, segment); // 插入到右子树 - Insert into right subtree
        }
        return node; // 返回节点 - Return node
    }

    public void traverseFarToNear(double x, double y, SegmentCallback callback) {
        traverseFarToNear(root, x, y, callback); // 从远到近遍历 - Traverse from far to near
    }

    private void traverseFarToNear(Node node, double x, double y, SegmentCallback callback) {
        if (node == null) {
            return; // 如果节点为空，结束 - If node is null, end
        }

        if (node.segment.whichSidePoint(x, y) < 0) {
            traverseFarToNear(node.left, x, y, callback); // 先遍历左子树 - First traverse left subtree
            callback.callback(node.segment); // 回调函数处理段 - Callback function to process segment
            traverseFarToNear(node.right, x, y, callback); // 再遍历右子树 - Then traverse right subtree
        } else {
            traverseFarToNear(node.right, x, y, callback); // 先遍历右子树 - First traverse right subtree
            callback.callback(node.segment); // 回调函数处理段 - Callback function to process segment
            traverseFarToNear(node.left, x, y, callback); // 再遍历左子树 - Then traverse left subtree
        }
    }

    public Segment collision(Segment query) {
        return collision(root, query); // 检测碰撞 - Check for collision
    }

    private Segment collision(Node node, Segment query) {
        if (node == null) {
            return null; // 如果节点为空，返回null - If node is null, return null
        }

        if (node.segment.intersects(query)) {
            return node.segment; // 如果相交，返回段 - If intersects, return segment
        }

        if (query.whichSide(node.segment) < 0) {
            return collision(node.left, query); // 检测左子树碰撞 - Check for collision in left subtree
        } else {
            return collision(node.right, query); // 检测右子树碰撞 - Check for collision in right subtree
        }
    }

}
