package assignment06; // 包声明 - Package declaration

// 导入必要的库 - Importing necessary libraries
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Stack;

// 定义表达式树类 - Defining the Expression Tree class
public class ExpressionTree {

    BinaryNode root; // 根节点 - Root node of the expression tree

    // 用于表达式树的二进制节点类 - A binary node class specifically for expression trees
    private class BinaryNode {
        char operator; // 操作符，用于内部节点（如+，-，*，/） - Operator for interior nodes (e.g., +, -, *, /)
        double operand; // 操作数，用于叶子节点 - Operand for leaf nodes (a number)

        BinaryNode left; // 左子树 - Left subtree
        BinaryNode right; // 右子树 - Right subtree

        // 判断是否为叶子节点的方法 - Method to check if it's a leaf node
        boolean isLeaf() {
            return (left == null) && (right == null);
        }

        // 节点的字符串表示 - String representation of the node
        @Override
        public String toString() {
            if (isLeaf())
                return "" + operand;
            return "" + operator;
        }
    }

    // 使用后序遍历深度优先搜索算法计算表达式树的值 - Perform a post-order depth-first traversal to evaluate the expression tree
    private double PostOrderEvaluate(BinaryNode N) throws UnsupportedOperationException {
        if (N.isLeaf()) // 如果是叶子节点，返回操作数值 - If it's a leaf node, return the operand value
            return N.operand;

        double leftVal = PostOrderEvaluate(N.left); // 计算左子树 - Evaluate the left subtree
        double rightVal = PostOrderEvaluate(N.right); // 计算右子树 - Evaluate the right subtree

        // 根据操作符计算结果 - Perform the operation based on the operator
        switch (N.operator) {
            case '+':
                return leftVal + rightVal;
            case '-':
                return leftVal - rightVal;
            case '*':
                return leftVal * rightVal;
            case '/':
                return leftVal / rightVal;
        }

        // 如果操作符无效，抛出异常 - Throw exception if the operator is invalid
        throw new UnsupportedOperationException();
    }

    // 公共方法，从根开始调用递归方法计算表达式的值 - Public method to start recursive evaluation from the root
    public double evaluate() throws UnsupportedOperationException {
        return PostOrderEvaluate(root);
    }

    // 根据中缀表达式字符串构建表达式树 - Builds an expression tree from an infix expression string
    public void buildFromInfixExpression(String infix) {
        String postfix = infixToPostfix(infix); // 将中缀表达式转换为后缀表达式 - Convert infix expression to postfix
        String[] tokens = postfix.split(" "); // 分割表达式 - Split the expression into tokens

        Stack<BinaryNode> s = new Stack<BinaryNode>(); // 使用栈构建树 - Using a stack for tree construction

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                BinaryNode right = s.pop(); // 弹出右操作数 - Pop right operand
                BinaryNode left = s.pop(); // 弹出左操作数 - Pop left operand
                BinaryNode newNode = new BinaryNode(); // 创建新节点 - Create a new node
                newNode.left = left;
                newNode.right = right;
                newNode.operator = tokens[i].charAt(0);
                s.push(newNode); // 将新节点压入栈 - Push the new node onto the stack
            } else {
                BinaryNode newNode = new BinaryNode();
                newNode.operand = Double.parseDouble(tokens[i]);
                s.push(newNode); // 将操作数节点压入栈 - Push operand node onto the stack
            }
        }

        root = s.pop(); // 弹出根节点 - Pop the root node
    }

    // 将中缀表达式字符串转换为后缀形式 - Converts an infix expression string to postfix form
    private String infixToPostfix(String infix) {
        String postfix = "";
        Stack<String> s = new Stack<String>();
        String[] tokens = infix.split(" ");
        OperatorComparator cmp = new OperatorComparator(); // 比较器，用于比较操作符优先级 - Comparator for operator precedence

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                if (s.isEmpty())
                    s.push(tokens[i]);
                else {
                    while (true) {
                        if (s.isEmpty())
                            break;
                        if (cmp.compare(s.peek(), tokens[i]) >= 0)
                            postfix += s.pop() + " ";
                        else
                            break;
                    }
                    s.push(tokens[i]);
                }
            } else
                postfix += tokens[i] + " ";
        }

        while (!s.isEmpty()) { // 处理剩下的操作符 - Handle any remaining operators
            postfix += s.pop() + " ";
        }
        return postfix;
    }

    // 构造器，用中缀表达式字符串初始化表达式树 - Constructor to initialize an expression tree with an infix expression string
    public ExpressionTree(String infix) {
        buildFromInfixExpression(infix);
    }

    // 生成表示此树的.dot文件的方法 - Method to generate a .dot file representing the tree
    public void writeDot(String filename) {
        try {
            PrintWriter output = new PrintWriter(new FileWriter(filename));
            output.println("graph g {");
            if (root != null)
                output.println(root.hashCode() + "[label=\"" + root + "\"]");
            writeDotRecursive(root, output);
            output.println("}");
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 递归遍历树，将每个节点输出到.dot文件 - Recursively traverse the tree, outputting each node to the .dot file
    private void writeDotRecursive(BinaryNode n, PrintWriter output) throws Exception {
        if (n == null)
            return;
        if (n.left != null) {
            output.println(n.left.hashCode() + "[label=\"" + n.left + "\"]");
            output.println(n.hashCode() + " -- " + n.left.hashCode());
        }
        if (n.right != null) {
            output.println(n.right.hashCode() + "[label=\"" + n.right + "\"]");
            output.println(n.hashCode() + " -- " + n.right.hashCode());
        }

        writeDotRecursive(n.left, output);
        writeDotRecursive(n.right, output);
    }

    // 比较器类，用于比较运算符的优先级 - Comparator class for comparing the precedence of operators
    private class OperatorComparator implements Comparator<String> {
        public int compare(String arg0, String arg1) {
            return precedence(arg0.charAt(0)) - precedence(arg1.charAt(0));
        }

        // 确定运算符的优先级 - Method to determine the precedence of an operator
        private int precedence(char op) {
            switch (op) {
                case '+':
                case '-':
                    return 0;
                case '*':
                case '/':
                    return 1;
            }

            // 默认情况，不应该发生 - Default case, should not happen
            return -1;
        }

    }
}
