package assignment07; // 定义包名为assignment07 // Define package name as assignment07

import java.util.Collection; // 导入Java的Collection接口 // Import Java's Collection interface
import java.util.LinkedList; // 导入Java的LinkedList类 // Import Java's LinkedList class

public class ChainingHashTable implements Set<String> { // 定义一个公共的ChainingHashTable类，实现Set接口 // Define a public class ChainingHashTable that implements the Set interface
    private LinkedList<String>[] storage; // 声明一个用于存储链表的数组 // Declare an array for storing linked lists
    private int size; // 声明一个整型变量用于跟踪大小 // Declare an integer to track size
    private final HashFunctor functor; // 声明一个最终的HashFunctor变量 // Declare a final HashFunctor variable

    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor) { // 构造函数，初始化哈希表和哈希函数 // Constructor to initialize hash table and hash function
        storage = (LinkedList<String>[]) new LinkedList[capacity]; // 初始化链表数组 // Initialize the linked list array
        this.functor = functor; // 设置哈希函数 // Set the hash function
        size = 0; // 初始化大小为0 // Initialize size to 0
    }

    private int getHashIndex(String item) { // 私有方法，获取哈希索引 // Private method to get hash index
        return Math.abs(functor.hash(item)) % storage.length; // 计算并返回哈希索引 // Calculate and return hash index
    }

    @Override
    public boolean add(String item) { // 实现添加元素的方法 // Implement method to add an element
        int index = getHashIndex(item); // 获取哈希索引 // Get hash index
        if (storage[index] == null) { // 如果该索引处没有链表，创建一个新链表 // If no linked list at this index, create a new one
            storage[index] = new LinkedList<>(); // 初始化链表 // Initialize linked list
        }

        if (!storage[index].contains(item)) { // 如果链表中没有该元素，添加它 // If the item is not in the list, add it
            storage[index].add(item); // 添加元素 // Add the item
            size++; // 增加大小 // Increase size
            return true; // 返回true表示添加成功 // Return true to indicate successful addition
        }
        return false; // 返回false表示元素已存在 // Return false if item already exists
    }

    @Override
    public boolean addAll(Collection<? extends String> items) { // 实现添加多个元素的方法 // Implement method to add multiple elements
        boolean changed = false; // 用于跟踪是否有任何更改 // Track if any changes were made
        for (String item : items) { // 遍历所有元素 // Iterate over all items
            if (add(item)) { // 添加元素 // Add item
                changed = true; // 标记更改 // Mark as changed
            }
        }
        return changed; // 返回是否更改 // Return if changed
    }

    @Override
    public void clear() { // 实现清除所有元素的方法 // Implement method to clear all elements
        storage = (LinkedList<String>[]) new LinkedList[storage.length]; // 重新初始化链表数组 // Reinitialize the linked list array
        size = 0; // 重置大小为0 // Reset size to 0
    }

    @Override
    public boolean contains(String item) { // 实现检查是否包含某个元素的方法 // Implement method to check if an element is contained
        int index = getHashIndex(item); // 获取哈希索引 // Get hash index
        return storage[index] != null && storage[index].contains(item); // 检查链表是否包含该元素 // Check if the list contains the item
    }

    @Override
    public boolean containsAll(Collection<? extends String> items) { // 实现检查是否包含多个元素的方法 // Implement method to check if multiple elements are contained
        for (String item : items) { // 遍历所有元素 // Iterate over all items
            if (!contains(item)) { // 如果某个元素不包含在内，返回false // Return false if an item is not contained
                return false;
            }
        }
        return true; // 所有元素都包含在内，返回true // Return true if all items are contained
    }

    @Override
    public boolean isEmpty() { // 实现检查集合是否为空的方法 // Implement method to check if the set is empty
        return size == 0; // 如果大小为0，则为空 // If size is 0, it is empty
    }

    @Override
    public boolean remove(String item) { // 实现移除某个元素的方法 // Implement method to remove an element
        int index = getHashIndex(item); // 获取哈希索引 // Get hash index
        if (storage[index] != null && storage[index].remove(item)) { // 如果链表存在且元素被移除 // If the list exists and the item is removed
            size--; // 减小大小 // Decrease size
            return true; // 返回true表示移除成功 // Return true to indicate successful removal
        }
        return false; // 返回false表示元素不存在 // Return false if item does not exist
    }

    @Override
    public boolean removeAll(Collection<? extends String> items) { // 实现移除多个元素的方法 // Implement method to remove multiple elements
        boolean changed = false; // 用于跟踪是否有任何更改 // Track if any changes were made
        for (String item : items) { // 遍历所有元素 // Iterate over all items
            if (remove(item)) { // 移除元素 // Remove item
                changed = true; // 标记更改 // Mark as changed
            }
        }
        return changed; // 返回是否更改 // Return if changed
    }

    @Override
    public int size() { // 实现获取集合大小的方法 // Implement method to get the size of the set
        return size; // 返回大小 // Return the size
    }
}

//    public void simulateQuadProbe(String item) { // 一个模拟二次探测的方法 // A method to simulate quadratic probing
//        int hash = Math.abs(functor.hash(item)) % storage.length; // 计算哈希值 // Calculate hash value
//        System.out.println("Initial hash index for '" + item + "': " + hash); // 打印初始哈希索引 // Print initial hash index
//
//        for (int i = 1; i < storage.length; i++) { // 遍历所有可能的探测位置 // Iterate over all possible probing positions
//            int newIndex = (hash + i * i) % storage.length; // 计算新的索引 // Calculate new index
//            System.out.println("Quad probe " + i + ": " + newIndex); // 打印二次探测的索引 // Print quadratic probing index
//        }
//    }
//}
