package assignment05; // 包声明：定义了类的命名空间 | Package declaration: Defines the namespace for the class

import java.net.URL;
import java.util.Iterator; // 导入Iterator接口 | Importing the Iterator interface
import java.util.NoSuchElementException; // 导入NoSuchElementException异常 | Importing NoSuchElementException exception

// 定义一个单链表类，实现List接口 | Define a singly linked list class that implements the List interface
public class SinglyLinkedList<E> implements List<E> {
    private Node<E> head; // 头结点 | Head node of the list
    private int size; // 链表大小 | Size of the list

    // 构造函数：初始化一个空链表 | Constructor: Initializes an empty linked list
    public SinglyLinkedList() {
        this.head = null; // 初始化头结点为null | Initializing head node as null
        this.size = 0; // 初始化大小为0 | Initializing size as 0
    }

    // 在链表开头插入一个元素 | Insert an element at the beginning of the list

    public void insertFirst(E element) {
        Node<E> newNode = new Node<>(element); // 创建一个新节点 | Creating a new node
        newNode.next = head; // 新节点的下一个指向原头结点 | The new node's next points to the original head
        head = newNode; // 更新头结点为新节点 | Updating the head to be the new node
        size++; // 链表大小增加 | Increasing the size of the list
    }

    // 在指定位置插入一个元素 | Insert an element at a specified position

    public void insert(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(); // 检查索引边界 | Checking index boundaries
        }

        if (index == 0) {
            insertFirst(element); // 如果索引为0，调用insertFirst方法 | If index is 0, call insertFirst method
            return;
        }

        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next; // 遍历到指定位置的前一个节点 | Traversing to the node before the specified position
        }

        Node<E> newNode = new Node<>(element); // 创建一个新节点 | Creating a new node
        newNode.next = current.next; // 新节点的下一个指向当前节点的下一个 | The new node's next points to the next of the current node
        current.next = newNode; // 当前节点的下一个更新为新节点 | Updating the next of the current node to the new node
        size++; // 链表大小增加 | Increasing the size of the list
    }

    /**
     * Gets the first element in the list.
     * O(1) for a singly-linked list.
     *
     * @param pop
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */

    public E getFirst(URL pop) throws NoSuchElementException {
        return null;
    }

    // 获取链表的第一个元素 | Get the first element of the list
    // 获取链表中特定位置的元素 | Get the element at a specific position in the list

    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    // 获取指定位置的元素 | Get the element at a specified position

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(); // 检查索引边界 | Checking index boundaries
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next; // 遍历到指定位置的节点 | Traversing to the node at the specified position
        }

        return current.data; // 返回当前节点的数据 | Returning the data of the current node
    }

    // 删除链表的第一个元素 | Delete the first element of the list

    public E deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException(); // 如果头结点为空，抛出异常 | Throw an exception if the head is null
        }

        E data = head.data; // 保存头结点的数据 | Saving the data of the head node
        head = head.next; // 更新头结点为下一个节点 | Updating the head to the next node
        size--; // 链表大小减少 | Decreasing the size of the list
        return data; // 返回被删除的数据 | Returning the deleted data
    }

    // 删除指定位置的元素 | Delete the element at a specified position

    public E delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(); // 检查索引边界 | Checking index boundaries
        }

        if (index == 0) {
            return deleteFirst(); // 如果索引为0，调用deleteFirst方法 | If index is 0, call deleteFirst method
        }

        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next; // 遍历到指定位置的前一个节点 | Traversing to the node before the specified position
        }

        E data = current.next.data; // 保存被删除节点的数据 | Saving the data of the node to be deleted
        current.next = current.next.next; // 更新当前节点的下一个为被删除节点的下一个 | Updating the next of the current node to the next of the node to be deleted
        size--; // 链表大小减少 | Decreasing the size of the list
        return data; // 返回被删除的数据 | Returning the deleted data
    }

    // 查找元素在链表中的位置 | Find the position of an element in the list

    public int indexOf(E element) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(element)) {
                return i; // 如果找到元素，返回索引 | If the element is found, return the index
            }
            current = current.next; // 遍历到下一个节点 | Traversing to the next node
        }
        return -1; // 如果没找到，返回-1 | Return -1 if the element is not found
    }

    // 获取链表大小 | Get the size of the list

    public int size() {
        return size; // 返回链表的大小 | Returning the size of the list
    }

    // 判断链表是否为空 | Check if the list is empty

    public boolean isEmpty() {
        return size == 0; // 如果大小为0，返回true | Return true if the size is 0
    }

    // 清空链表 | Clear the list

    public void clear() {
        head = null; // 将头结点设置为null | Setting the head node to null
        size = 0; // 将大小设置为0 | Setting the size to 0
    }

    // 将链表转换为数组 | Convert the list to an array

    public Object[] toArray() {
        Object[] array = new Object[size]; // 创建一个新数组 | Creating a new array
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data; // 将每个节点的数据放入数组 | Putting the data of each node into the array
            current = current.next; // 遍历到下一个节点 | Traversing to the next node
        }
        return array; // 返回数组 | Returning the array
    }

    // 提供一个迭代器 | Providing an iterator

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head; // 当前节点指针 | Pointer to the current node
            private Node<E> lastAccessed = null; // 最后访问的节点 | The last accessed node, for removal purposes

            public boolean hasNext() {
                return current != null; // 如果当前节点不是null，说明还有下一个元素 | If the current node is not null, there is a next element
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(); // 如果没有下一个元素，抛出异常 | Throws an exception if there is no next element
                }
                lastAccessed = current; // 在移动到下一个节点之前，更新lastAccessed | Update lastAccessed to the current node before moving to the next
                E data = current.data; // 获取当前节点的数据 | Retrieve the data from the current node
                current = current.next; // 移动到下一个节点 | Move to the next node
                return data; // 返回当前节点的数据 | Return the data from the current node
            }

            public void remove() {
                if (lastAccessed == null) {
                    throw new IllegalStateException("Call next() before calling remove()"); // 如果lastAccessed为null，表示没有调用next()，抛出异常 | Throws an exception if lastAccessed is null, indicating that next() was not called
                }

                if (lastAccessed == head) {
                    // 特殊情况：如果要删除的是头节点 | Special case: If the head node is being removed
                    head = head.next; // 直接将头节点更新为下一个节点 | Update the head to the next node
                } else {
                    // 查找lastAccessed的前一个节点 | Find the predecessor of lastAccessed
                    Node<E> predecessor = head;
                    while (predecessor != null && predecessor.next != lastAccessed) {
                        predecessor = predecessor.next; // 遍历直到找到前一个节点 | Traverse until the predecessor is found
                    }

                    if (predecessor == null) {
                        throw new IllegalStateException("The last accessed element no longer exists in the list."); // 如果前一个节点不存在，抛出异常 | Throw an exception if the predecessor does not exist
                    }

                    // 跳过lastAccessed，即将其从链表中删除 | Skip over lastAccessed, effectively removing it from the list
                    predecessor.next = lastAccessed.next;
                }

                lastAccessed = null; // 将lastAccessed重置为null | Reset lastAccessed to null
                size--; // 链表大小减少 | Decrement the size of the list
            }

        };
    }



    // 链表节点类 | Node class of the list
    private static class Node<E> {
        E data; // 数据 | Data
        Node<E> next; // 指向下一个节点 | Pointer to the next node

        Node(E data) {
            this.data = data; // 初始化数据 | Initializing
            this.next = null;
        }
    }
}
