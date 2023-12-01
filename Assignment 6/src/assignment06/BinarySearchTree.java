package assignment06; // 包声明 - Package declaration

// 导入所需的类 - Importing necessary classes
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

// 定义二叉搜索树类，泛型T需实现Comparable接口 - Defining the Binary Search Tree class with a generic type T that must implement Comparable
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
    private Node root; // 根节点 - Root node of the tree

    // 内部节点类定义 - Internal class defining a node
    private class Node {
        T data; // 节点存储的数据 - Data stored in the node
        Node left, right; // 指向左右子节点的引用 - References to left and right child nodes

        Node(T data) { // 节点构造器 - Constructor for node
            this.data = data;
            left = null;
            right = null;
        }
    }

    // 二叉搜索树构造器 - Constructor for BinarySearchTree
    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean add(T item) { // 添加元素的方法 - Method to add an element
        // 如果根节点为空，设置新节点为根节点 - If root is null, set new node as root
        if (root == null) {
            root = new Node(item);
            return true;
        } else {
            // 否则递归添加 - Else, add recursively
            return addRecursive(root, item);
        }
    }

    // 递归添加节点的私有方法 - Private method for recursive addition of nodes
    private boolean addRecursive(Node current, T item) {
        // 如果新项小于当前节点，向左递归 - If new item is less than current node, recurse left
        if (item.compareTo(current.data) < 0) {
            if (current.left == null) {
                current.left = new Node(item);
                return true;
            } else {
                return addRecursive(current.left, item);
            }
        } else if (item.compareTo(current.data) > 0) {
            // 如果新项大于当前节点，向右递归 - If new item is greater than current node, recurse right
            if (current.right == null) {
                current.right = new Node(item);
                return true;
            } else {
                return addRecursive(current.right, item);
            }
        }
        // 如果项已存在，返回false - If item already exists, return false
        return false;
    }

    // 批量添加元素的方法 - Method to add a collection of elements
    @Override
    public boolean addAll(Collection<? extends T> items) {
        boolean result = false;
        for (T item : items) {
            // 对每个元素调用add方法 - Call add method for each item
            result |= add(item);
        }
        return result;
    }

    @Override
    public void clear() { // 清空树的方法 - Method to clear the tree
        root = null;
    }

    @Override
    public boolean contains(T item) { // 检查元素是否存在的方法 - Method to check if an element exists
        return containsRecursive(root, item);
    }

    // 递归检查元素是否存在的私有方法 - Private method for recursive check if an element exists
    private boolean containsRecursive(Node current, T item) {
        if (current == null) {
            return false;
        }
        if (item.equals(current.data)) {
            return true;
        }
        return item.compareTo(current.data) < 0 ?
                containsRecursive(current.left, item) :
                containsRecursive(current.right, item);
    }

    @Override
    public boolean containsAll(Collection<? extends T> items) {
        // 检查多个元素是否都存在的方法 - Method to check if all elements in a collection exist
        for (T item : items) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public T first() {
        // 获取树中最小元素的方法 - Method to get the smallest element in the tree
        if (root == null) {
            throw new NoSuchElementException();
        }
        return findSmallest(root);
    }

    // 找到最小元素的私有方法 - Private method to find the smallest element
    private T findSmallest(Node current) {
        return current.left == null ? current.data : findSmallest(current.left);
    }

    @Override
    public boolean isEmpty() {
        // 检查树是否为空的方法 - Method to check if the tree is empty
        return root == null;
    }

    @Override
    public T last() {
        // 获取树中最大元素的方法 - Method to get the largest element in the tree
        if (root == null) {
            throw new NoSuchElementException();
        }
        return findLargest(root);
    }

    // 找到最大元素的私有方法 - Private method to find the largest element
    private T findLargest(Node current) {
        return current.right == null ? current.data : findLargest(current.right);
    }

    @Override
    public boolean remove(T item) {
        // 移除元素的方法 - Method to remove an element
        // 首先检查树中是否包含要移除的元素
        if (contains(item)) {
            // 如果包含，调用递归方法removeRecursive来实际进行移除操作
            // 并将树的根节点设置为removeRecursive方法的返回值
            // 这是因为移除节点可能会导致树的根节点改变
            root = removeRecursive(root, item);
            // 移除成功，返回true
            return true;
        }
        // 如果树中不包含该元素，直接返回false
        return false;
    }

    // 递归移除节点的私有方法 - Private method for recursive removal of nodes
    private Node removeRecursive(Node current, T item) {
        if (current == null) {
            return null;
        }

        if (item.compareTo(current.data) < 0) {
            // 小于当前节点，向左递归 - If less than current node, recurse left
            current.left = removeRecursive(current.left, item);
        } else if (item.compareTo(current.data) > 0) {
            // 大于当前节点，向右递归 - If greater than current node, recurse right
            current.right = removeRecursive(current.right, item);
        } else {
            // 只有一个子节点或无子节点的情况 - Node with only one child or no child
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // 有两个子节点的情况：获取右子树中最小的元素 - Node with two children: Get the smallest in the right subtree
            current.data = findSmallest(current.right);

            // 删除中序后继节点 - Delete the inorder successor
            current.right = removeRecursive(current.right, current.data);
        }

        return current;
    }

    // 批量移除元素的方法 - Method to remove a collection of elements
    @Override
    public boolean removeAll(Collection<? extends T> items) {
        // 初始化一个布尔变量result，用于记录是否至少有一个元素被成功移除
        boolean result = false;

        // 遍历传入的集合items中的每一个元素
        for (T item : items) {
            // 对集合中的每个元素调用remove方法尝试移除
            // remove方法返回一个布尔值，表示该元素是否被成功移除
            // 使用位或运算符('|=')来更新result的值
            // 如果任何一次remove调用返回true，result将被设置为true
            result |= remove(item);
        }
        // 返回result，如果至少有一个元素被成功移除，result为true，否则为false
        return result;
    }


    @Override
    public int size() {
        // 获取树的大小（节点数）的方法 - Method to get the size (number of nodes) of the tree
        return sizeRecursive(root);
    }

    // 递归计算大小的私有方法 - Private method for recursive calculation of size
    private int sizeRecursive(Node current) {
        if (current == null) {
            return 0;
        } else {
            return 1 + sizeRecursive(current.left) + sizeRecursive(current.right);
        }
    }

    @Override
    public ArrayList<T> toArrayList() {
        // 将树转换为ArrayList的方法 - Method to convert the tree into an ArrayList
        // 创建一个空的ArrayList，用于存储树中的元素
        ArrayList<T> resultList = new ArrayList<>();
        // 调用私有的递归方法toArrayListRecursive，以中序遍历的方式添加元素到resultList中
        toArrayListRecursive(root, resultList);
        // 返回包含树中所有元素的ArrayList
        return resultList;
    }


    // 递归构建ArrayList的私有方法 - Private method for recursive construction of an ArrayList
// 递归构建ArrayList的私有方法 - Private method for recursive construction of an ArrayList
    private void toArrayListRecursive(Node current, ArrayList<T> resultList) {
        // 检查当前节点是否为空
        if (current != null) {
            // 递归地遍历左子树
            toArrayListRecursive(current.left, resultList);
            // 将当前节点的数据添加到resultList中
            resultList.add(current.data);
            // 递归地遍历右子树
            toArrayListRecursive(current.right, resultList);
        }
    }
}

