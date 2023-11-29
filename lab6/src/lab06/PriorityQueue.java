package lab06;

public interface PriorityQueue<T extends Comparable<T>> {
    void add(T element);
    T removeMin();
    boolean isEmpty();
}
