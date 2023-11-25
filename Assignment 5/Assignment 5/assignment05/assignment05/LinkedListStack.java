package assignment05; // 声明包名 (Declare the package name)

import java.util.NoSuchElementException; // 导入 NoSuchElementException 异常类 (Import NoSuchElementException class)

/**
 * 使用链表实现的简单栈。
 * A simple stack implemented using a linked list.
 *
 * @param <E> - 栈中元素的类型 (The type of elements in the stack)
 */
public class LinkedListStack<E> implements Stack<E> { // 定义一个类 LinkedListStack，实现 Stack 接口 (Define LinkedListStack class implementing the Stack interface)

    private Node<E> top; // 栈顶元素 (Top element of the stack)
    private int size; // 栈的大小 (Size of the stack)

    private static class Node<E> { // 定义一个私有静态内部类 Node (Define a private static inner class Node)
        E element; // 节点存储的元素 (Element stored in the node)
        Node<E> next; // 指向下一个节点的引用 (Reference to the next node)

        Node(E element, Node<E> next) { // 节点的构造函数 (Node's constructor)
            this.element = element; // 初始化节点存储的元素 (Initialize the element stored in the node)
            this.next = next; // 初始化指向下一个节点的引用 (Initialize the reference to the next node)
        }
    }

    public LinkedListStack() { // LinkedListStack 的构造函数 (Constructor of LinkedListStack)
        top = null; // 初始化栈顶为 null (Initialize the top of the stack to null)
        size = 0; // 初始化栈的大小为 0 (Initialize the size of the stack to 0)
    }

    @Override
    public void clear() { // 清空栈的方法 (Method to clear the stack)
        top = null; // 将栈顶设置为 null (Set the top of the stack to null)
        size = 0; // 将栈的大小设置为 0 (Set the size of the stack to 0)
    }

    @Override
    public boolean isEmpty() { // 检查栈是否为空的方法 (Method to check if the stack is empty)
        return top == null; // 如果栈顶为 null，则栈为空 (The stack is empty if the top is null)
    }

    @Override
    public E peek() throws NoSuchElementException { // 查看栈顶元素的方法 (Method to view the top element of the stack)
        if (isEmpty()) { // 如果栈为空 (If the stack is empty)
            throw new NoSuchElementException("Stack is empty."); // 抛出 NoSuchElementException 异常 (Throw NoSuchElementException)
        }
        return top.element; // 返回栈顶元素 (Return the top element)
    }

    @Override
    public E pop() throws NoSuchElementException { // 移除并返回栈顶元素的方法 (Method to remove and return the top element of the stack)
        if (isEmpty()) { // 如果栈为空 (If the stack is empty)
            throw new NoSuchElementException("Stack is empty."); // 抛出 NoSuchElementException 异常 (Throw NoSuchElementException)
        }
        E element = top.element; // 保存栈顶元素 (Save the top element)
        top = top.next; // 将栈顶指向下一个节点 (Move the top to the next node)
        size--; // 栈的大小减一 (Decrease the size of the stack by one)
        return element; // 返回原栈顶元素 (Return the original top element)
    }

    @Override
    public void push(E element) { // 将元素压入栈顶的方法 (Method to push an element to the top of the stack)
        top = new Node<>(element, top); // 创建一个新节点，并将其设置为栈顶 (Create a new node and set it as the top)
        size++; // 栈的大小加一 (Increase the size of the stack by one)
    }

    @Override
    public int size() { // 返回栈的大小的方法 (Method to return the size of the stack)
        return size; // 返回栈的当前大小 (Return the current size of the stack)
    }
}
