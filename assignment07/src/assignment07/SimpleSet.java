package assignment07; // 定义包名为assignment07 // Define package name as assignment07

import java.util.Collection; // 导入Java的Collection接口 // Import Java's Collection interface
import java.util.HashSet; // 导入Java的HashSet类 // Import Java's HashSet class

public class SimpleSet<Type> implements Set<Type> { // 定义一个公开的SimpleSet类，这个类实现了Set接口 // Define a public class SimpleSet that implements the Set interface

    private HashSet<Type> set; // 声明一个私有的HashSet变量 // Declare a private HashSet variable

    public SimpleSet() { // SimpleSet的构造函数 // Constructor for SimpleSet
        this.set = new HashSet<>(); // 初始化HashSet实例 // Initialize the HashSet instance
    }

    @Override // 覆写接口的方法 // Override method from interface
    public boolean add(Type item) { // 定义添加元素的方法 // Define method to add an element
        return set.add(item); // 调用HashSet的add方法 // Call the add method of HashSet
    }

    @Override // 覆写接口的方法 // Override method from interface
    public boolean addAll(Collection<? extends Type> items) { // 定义添加多个元素的方法 // Define method to add multiple elements
        return set.addAll(items); // 调用HashSet的addAll方法 // Call the addAll method of HashSet
    }

    @Override // 覆写接口的方法 // Override method from interface
    public void clear() { // 定义清除所有元素的方法 // Define method to clear all elements
        set.clear(); // 调用HashSet的clear方法 // Call the clear method of HashSet
    }

    @Override // 覆写接口的方法 // Override method from interface
    public boolean contains(Type item) { // 定义检查是否包含某个元素的方法 // Define method to check if an element is contained
        return set.contains(item); // 调用HashSet的contains方法 // Call the contains method of HashSet
    }

    @Override // 覆写接口的方法 // Override method from interface
    public boolean containsAll(Collection<? extends Type> items) { // 定义检查是否包含多个元素的方法 // Define method to check if multiple elements are contained
        return set.containsAll(items); // 调用HashSet的containsAll方法 // Call the containsAll method of HashSet
    }

    @Override // 覆写接口的方法 // Override method from interface
    public boolean isEmpty() { // 定义检查集合是否为空的方法 // Define method to check if the set is empty
        return set.isEmpty(); // 调用HashSet的isEmpty方法 // Call the isEmpty method of HashSet
    }

    @Override // 覆写接口的方法 // Override method from interface
    public boolean remove(Type item) { // 定义移除某个元素的方法 // Define method to remove an element
        return set.remove(item); // 调用HashSet的remove方法 // Call the remove method of HashSet
    }

    @Override // 覆写接口的方法 // Override method from interface
    public boolean removeAll(Collection<? extends Type> items) { // 定义移除多个元素的方法 // Define method to remove multiple elements
        return set.removeAll(items); // 调用HashSet的removeAll方法 // Call the removeAll method of HashSet
    }

    @Override // 覆写接口的方法 // Override method from interface
    public int size() { // 定义获取集合大小的方法 // Define method to get the size of the set
        return set.size(); // 调用HashSet的size方法 // Call the size method of HashSet
    }
}
