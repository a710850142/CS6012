package lab06;

import java.util.ArrayList;
import java.util.Collections;

public class HeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    private ArrayList<T> heap;

    public HeapPriorityQueue() {
        heap = new ArrayList<>();
    }

    public HeapPriorityQueue(ArrayList<T> elements) {
        heap = elements;
        heapify();
    }

    private void percolateUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
                Collections.swap(heap, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void percolateDown(int index) {
        int childIndex;
        while (index < heap.size() / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            if (rightChild < heap.size() &&
                    heap.get(rightChild).compareTo(heap.get(leftChild)) < 0) {
                childIndex = rightChild;
            } else {
                childIndex = leftChild;
            }

            if (heap.get(index).compareTo(heap.get(childIndex)) > 0) {
                Collections.swap(heap, index, childIndex);
            } else {
                break;
            }

            index = childIndex;
        }
    }

    private void heapify() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    @Override
    public void add(T element) {
        heap.add(element);
        percolateUp(heap.size() - 1);
    }

    @Override
    public T removeMin() {
        if (isEmpty()) {
            return null;
        }

        T minElement = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            percolateDown(0);
        }
        return minElement;
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
