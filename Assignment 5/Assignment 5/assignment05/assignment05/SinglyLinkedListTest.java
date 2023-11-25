package assignment05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.net.URL;

public class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test
    public void testInsertFirstAndSize() {
        list.insertFirst(1);
        Assertions.assertEquals(1, list.size());
        list.insertFirst(2);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testGetFirst() {
        list.insertFirst(1);
        Assertions.assertEquals(1, list.getFirst());
        list.insertFirst(2);
        Assertions.assertEquals(2, list.getFirst());
    }

    @Test
    public void testInsertAndGet() {
        list.insertFirst(1);
        list.insert(1, 2); // Insert 2 at index 1
        Assertions.assertEquals(2, list.get(1));
    }

    @Test
    public void testDeleteFirst() {
        list.insertFirst(1);
        list.insertFirst(2);
        Assertions.assertEquals(2, list.deleteFirst());
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testDelete() {
        list.insertFirst(1);
        list.insertFirst(2);
        Assertions.assertEquals(1, list.delete(1));
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testIndexOf() {
        list.insertFirst(1);
        list.insertFirst(2);
        Assertions.assertEquals(1, list.indexOf(1));
        Assertions.assertEquals(0, list.indexOf(2));
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(list.isEmpty());
        list.insertFirst(1);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        list.insertFirst(1);
        list.clear();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testToArray() {
        list.insertFirst(1);
        list.insertFirst(2);
        Object[] array = list.toArray();
        Assertions.assertArrayEquals(new Object[]{2, 1}, array);
    }

    @Test
    public void testIterator() {
        list.insertFirst(1);
        list.insertFirst(2);
        Iterator<Integer> iterator = list.iterator();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(2, iterator.next());
        Assertions.assertEquals(1, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorRemove() {
        list.insertFirst(1);
        list.insertFirst(2);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1, list.getFirst());
    }

    @Test
    public void testExceptionOnEmptyList() {
        Assertions.assertThrows(NoSuchElementException.class, () -> list.getFirst());
        Assertions.assertThrows(NoSuchElementException.class, () -> list.deleteFirst());
    }

    @Test
    public void testExceptionOnInvalidIndex() {
        list.insertFirst(1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.delete(1));
    }

    @Test
    public void testIteratorRemoveNonHeadElement() {
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3); // List is now [3, 2, 1]

        Iterator<Integer> iterator = list.iterator();
        iterator.next(); // Advance to 3
        iterator.next(); // Advance to 2

        iterator.remove(); // Should remove 2, list becomes [3, 1]

        Assertions.assertEquals(2, list.size()); // Confirm size is updated
        Assertions.assertEquals(3, list.getFirst()); // First element should be 3
        iterator = list.iterator(); // Create a new iterator to iterate through the list
        iterator.next(); // Skip first element (3)
        Assertions.assertEquals(1, iterator.next()); // Next element should be 1, confirming 2 was removed
        Assertions.assertFalse(iterator.hasNext()); // There should be no more elements after 1
    }

    @Test
    public void testGetFirstWithUrl() throws MalformedURLException {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        URL dummyUrl = new URL("http://example.com");
        list.insertFirst(1);
        list.insertFirst(2);

        Integer result = list.getFirst(dummyUrl);
        Assertions.assertNull(result); // Assert that the result is null as per the current implementation
    }

}
