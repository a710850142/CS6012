package assignment06; // 包声明 - Package declaration

// 导入所需的类 - Importing necessary classes
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

// 定义一个简单的有序集合类，泛型T需实现Comparable接口 - Defining a simple sorted set class with a generic type T that must implement Comparable
public class SimpleSortedSet<T extends Comparable<? super T>> implements SortedSet<T> {
    private ArrayList<T> elements; // 使用ArrayList存储元素 - Using ArrayList to store elements

    // 构造器，初始化ArrayList - Constructor to initialize ArrayList
    public SimpleSortedSet() {
        elements = new ArrayList<>();
    }

    @Override
    public boolean add(T item) { // 添加元素的方法 - Method to add an element
        if (item == null) throw new NullPointerException(); // 如果元素为空，抛出异常 - Throw an exception if the element is null
        int index = java.util.Collections.binarySearch(elements, item); // 二分查找元素位置 - Binary search for the position of the element
        if (index < 0) { // 如果元素不存在，添加到正确位置 - If the element does not exist, add it at the correct position
            elements.add(-index - 1, item);
            return true;
        }
        return false; // 如果元素已存在，返回false - Return false if the element already exists
    }

    @Override
    public boolean addAll(Collection<? extends T> items) { // 添加多个元素的方法 - Method to add multiple elements
        boolean modified = false;
        for (T item : items) {
            modified |= add(item); // 对每个元素调用add方法 - Call add method for each element
        }
        return modified; // 返回是否有元素被添加 - Return whether any elements were added
    }

    @Override
    public void clear() { // 清空集合的方法 - Method to clear the set
        elements.clear();
    }

    @Override
    public boolean contains(T item) { // 检查元素是否存在的方法 - Method to check if an element exists
        if (item == null) throw new NullPointerException(); // 如果元素为空，抛出异常 - Throw an exception if the element is null
        return java.util.Collections.binarySearch(elements, item) >= 0; // 二分查找确定元素是否存在 - Binary search to determine if the element exists
    }

    @Override
    public boolean containsAll(Collection<? extends T> items) { // 检查多个元素是否都存在的方法 - Method to check if all elements in a collection exist
        for (T item : items) {
            if (!contains(item)) return false; // 如果任何一个元素不存在，返回false - Return false if any element does not exist
        }
        return true; // 所有元素都存在，返回true - Return true if all elements exist
    }

    @Override
    public T first() { // 获取第一个元素的方法 - Method to get the first element
        if (isEmpty()) throw new NoSuchElementException(); // 如果集合为空，抛出异常 - Throw an exception if the set is empty
        return elements.get(0); // 返回第一个元素 - Return the first element
    }

    @Override
    public boolean isEmpty() { // 检查集合是否为空的方法 - Method to check if the set is empty
        return elements.isEmpty();
    }

    @Override
    public T last() { // 获取最后一个元素的方法 - Method to get the last element
        if (isEmpty()) throw new NoSuchElementException(); // 如果集合为空，抛出异常 - Throw an exception if the set is empty
        return elements.get(elements.size() - 1); // 返回最后一个元素 - Return the last element
    }

    @Override
    public boolean remove(T item) { // 移除元素的方法 - Method to remove an element
        if (item == null) throw new NullPointerException(); // 如果元素为空，抛出异常 - Throw an exception if the element is null
        return elements.remove(item); // 移除元素，返回是否成功 - Remove the element and return whether it was successful
    }

    @Override
    public boolean removeAll(Collection<? extends T> items) { // 批量移除元素的方法 - Method to remove multiple elements
        boolean modified = false;
        for (T item : items) {
            modified |= remove(item); // 对每个元素调用remove方法 - Call remove method for each element
        }
        return modified; // 返回是否有元素被移除 - Return whether any elements were removed
    }

    @Override
    public int size() { // 获取集合大小的方法 - Method to get the size of the set
        return elements.size();
    }

    @Override
    public ArrayList<T> toArrayList() { // 将集合转换为ArrayList的方法 - Method to convert the set to an ArrayList
        return new ArrayList<>(elements); // 返回一个新的ArrayList副本 - Return a new ArrayList copy
    }
}
