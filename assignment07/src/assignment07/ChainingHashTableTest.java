package assignment07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collection;

public class ChainingHashTableTest {

    private ChainingHashTable hashTable;
    private HashFunctor hashFunctor;

    @BeforeEach
    public void setUp() {
        hashFunctor = new YourHashFunctorImplementation(); // 替换为你的HashFunctor实现
        hashTable = new ChainingHashTable(10, hashFunctor); // 选择一个适当的容量
    }

    @Test
    @DisplayName("Test add method")
    public void testAdd() {
        // 测试 add 方法是否能正确添加元素
        Assertions.assertTrue(hashTable.add("test"));
        Assertions.assertFalse(hashTable.add("test")); // 尝试重复添加相同的元素
    }

    @Test
    @DisplayName("Test addAll method")
    public void testAddAll() {
        // 测试 addAll 方法是否能正确添加多个元素
        Collection<String> items = Arrays.asList("item1", "item2");
        Assertions.assertTrue(hashTable.addAll(items));
    }

    @Test
    @DisplayName("Test clear method")
    public void testClear() {
        // 测试 clear 方法是否能清除所有元素
        hashTable.add("test");
        hashTable.clear();
        Assertions.assertTrue(hashTable.isEmpty());
    }

    @Test
    @DisplayName("Test contains method")
    public void testContains() {
        // 测试 contains 方法是否能正确检测元素存在
        hashTable.add("test");
        Assertions.assertTrue(hashTable.contains("test"));
        Assertions.assertFalse(hashTable.contains("not_exist"));
    }

    @Test
    @DisplayName("Test containsAll method")
    public void testContainsAll() {
        // 测试 containsAll 方法是否能正确检测多个元素的存在
        Collection<String> items = Arrays.asList("item1", "item2");
        hashTable.addAll(items);
        Assertions.assertTrue(hashTable.containsAll(items));
    }

    @Test
    @DisplayName("Test isEmpty method")
    public void testIsEmpty() {
        // 测试 isEmpty 方法是否能正确判断哈希表是否为空
        Assertions.assertTrue(hashTable.isEmpty());
        hashTable.add("test");
        Assertions.assertFalse(hashTable.isEmpty());
    }

    @Test
    @DisplayName("Test remove method")
    public void testRemove() {
        // 测试 remove 方法是否能正确移除元素
        hashTable.add("test");
        Assertions.assertTrue(hashTable.remove("test"));
        Assertions.assertFalse(hashTable.remove("test")); // 尝试移除不存在的元素
    }

    @Test
    @DisplayName("Test removeAll method")
    public void testRemoveAll() {
        // 测试 removeAll 方法是否能正确移除多个元素
        Collection<String> items = Arrays.asList("item1", "item2");
        hashTable.addAll(items);
        Assertions.assertTrue(hashTable.removeAll(items));
    }

    @Test
    @DisplayName("Test size method")
    public void testSize() {
        // 测试 size 方法是否返回正确的大小
        hashTable.add("test");
        Assertions.assertEquals(1, hashTable.size());
    }

    // JUnit 5 不直接支持测试打印输出的方法，因此测试 simulateQuadProbe 方法可能需要一些额外的工作。
    // 可以考虑使用 System.setOut 更改输出流或者使用其他方式来捕获和检查输出。
}

