package lab06;

import java.util.TreeSet;

public class TreeSetPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    private TreeSet<T> treeSet;

    public TreeSetPriorityQueue() {
        treeSet = new TreeSet<>();
    }

    @Override
    public void add(T element) {
        treeSet.add(element);
    }

    @Override
    public T removeMin() {
        return treeSet.pollFirst();
    }

    @Override
    public boolean isEmpty() {
        return treeSet.isEmpty();
    }
}
