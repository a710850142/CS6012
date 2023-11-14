package assignment03;

import java.util.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {
    private static final int INITIAL_CAPACITY = 10; // Initial size of the array
    private E[] data; // Array to store the elements of the set
    private int size; // Number of elements in the set
    private Comparator<? super E> comparator; // Comparator for sorting elements, can be null for natural ordering

    // Constructor without a comparator, uses natural ordering of elements
    public BinarySearchSet() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
        comparator = null;
    }

    // Constructor with a specific comparator for custom ordering
    public BinarySearchSet(Comparator<? super E> comparator) {
        this(); // Calls the default constructor to initialize data and size
        this.comparator = comparator; // Sets the custom comparator
    }

    // Returns the comparator used for sorting, or null if using natural ordering
    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    // Returns the smallest element in the set, throws exception if set is empty
    @Override
    public E first() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("Set is empty");
        }
        return data[0];
    }

    // Returns the largest element in the set, throws exception if set is empty
    @Override
    public E last() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("Set is empty");
        }
        return data[size - 1];
    }

    // Adds a new element to the set if it is not already present
    @Override
    public boolean add(E element) {
        if (element == null) {
            return false; // Null elements are not allowed
        }
        int index = binarySearch(element);
        if (index >= 0) {
            return false; // Element already exists
        }
        index = -(index + 1); // Calculate insertion index
        ensureCapacity(); // Ensure there's enough space for a new element
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
        return true;
    }

    // Ensures the array has enough capacity to add new elements
    private void ensureCapacity() {
        if (size == data.length) {
            E[] newData = (E[]) new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    // Adds all elements from a collection to the set
    @Override
    public boolean addAll(Collection<? extends E> elements) {
        boolean changed = false;
        for (E element : elements) {
            changed |= add(element); // Adds each element, tracking if any were added
        }
        return changed;
    }

    // Clears all elements from the set
    @Override
    public void clear() {
        Arrays.fill(data, 0, size, null); // Sets all elements in the range to null
        size = 0;
    }

    // Checks if a specific element is in the set
    @Override
    public boolean contains(E element) {
        return binarySearch(element) >= 0;
    }

    // Checks if the set contains all elements of a collection
    @Override
    public boolean containsAll(Collection<? extends E> elements) {
        for (E element : elements) {
            if (!contains(element)) {
                return false; // Returns false if any element is not found
            }
        }
        return true;
    }

    // Checks if the set is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Provides an iterator to iterate over the elements in sorted order
//    @Override
//    public Iterator<E> iterator() {
//        return new Iterator<E>() {
//            private int currentIndex = 0;
//
//            @Override
//            public boolean hasNext() {
//                return currentIndex < size;
//            }
//
//            @Override
//            public E next() {
//                if (!hasNext()) {
//                    throw new NoSuchElementException();
//                }
//                return data[currentIndex++];
//            }
//            @Override
//            public void remove(){
//
//            }
//        };
//    }
    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            int index = 0;
            boolean callNext = false;

            @Override
            public boolean hasNext() {
                return size > index ;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = (E) data[index];
                index++;
                callNext = true;
                return result;
            }
            @Override
            public void remove(){
                if(callNext) {
                    index--;
                    size--;
                    for(int i = index  ; i < size ; i++){
                        data[i] = data[i+1];
                    }
                    callNext = false;

                }
                else {
                    throw new IllegalStateException();
                }
            }

        };
        return iterator;
    }
    // Removes a specific element from the set if present
    @Override
    public boolean remove(E element) {
        int index = binarySearch(element);
        if (index < 0) {
            return false; // Element not found
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null; // Clear to let GC do its work
        return true;
    }

    // Removes all elements in the specified collection from the set
    @Override
    public boolean removeAll(Collection<? extends E> elements) {
        boolean changed = false;
        for (E element : elements) {
            changed |= remove(element);
        }
        return changed;
    }

    // Returns the number of elements in the set
    @Override
    public int size() {
        return size;
    }

    // Returns an array containing all elements in the set, in sorted order
    @Override
    public E[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public int binarySearch(E element) {
        int low = 0, high = size - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            int compareReturn;

            if (comparator == null) {
                Comparable<? super E> midElement = (Comparable<? super E>) data[mid];
                compareReturn = midElement.compareTo(element);
            } else {
                compareReturn = comparator.compare(data[mid], element);
            }

            if (compareReturn < 0) {
                low = mid + 1;
            } else if (compareReturn > 0) {
                high = mid - 1;
            } else {
                return mid; // element found
            }
        }
        return -(low + 1); // element not found
    }
}


