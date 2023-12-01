package assignment06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    // Set up for each test. This method is executed before each test method.
    @BeforeEach
    void setUp() {
        // Initialize a new BinarySearchTree instance before each test
        bst = new BinarySearchTree<>();
    }

    // Test adding a single element to the BST.
    @Test
    void testAdd() {
        assertTrue(bst.add(5)); // Should be true when adding a new element
        assertFalse(bst.add(5)); // Should be false when adding a duplicate element
    }

    // Test adding multiple elements to the BST.
    @Test
    void testAddAll() {
        assertTrue(bst.addAll(Arrays.asList(3, 1, 4))); // Adding multiple new elements
        assertFalse(bst.addAll(Arrays.asList(3))); // Adding a duplicate element
    }

    // Test clearing the BST.
    @Test
    void testClear() {
        bst.add(1);
        bst.clear(); // Clearing the tree should make it empty
        assertTrue(bst.isEmpty());
    }

    // Test checking for the presence of an element in the BST.
    @Test
    void testContains() {
        bst.add(2);
        assertTrue(bst.contains(2)); // Element is in the tree
        assertFalse(bst.contains(3)); // Element is not in the tree
    }

    // Test checking for the presence of multiple elements in the BST.
    @Test
    void testContainsAll() {
        bst.addAll(Arrays.asList(2, 4, 6));
        assertTrue(bst.containsAll(Arrays.asList(2, 4))); // All elements are in the tree
        assertFalse(bst.containsAll(Arrays.asList(2, 5))); // At least one element is not in the tree
    }

    // Test retrieving the smallest element in the BST.
    @Test
    void testFirst() {
        bst.addAll(Arrays.asList(5, 3, 7));
        assertEquals(3, bst.first()); // The smallest element
    }

    // Test if the BST is empty.
    @Test
    void testIsEmpty() {
        assertTrue(bst.isEmpty()); // Initially, the tree is empty
        bst.add(1);
        assertFalse(bst.isEmpty()); // After adding an element, the tree is not empty
    }

    // Test retrieving the largest element in the BST.
    @Test
    void testLast() {
        bst.addAll(Arrays.asList(5, 3, 7));
        assertEquals(7, bst.last()); // The largest element
    }

    // Test removing an element from the BST.
    @Test
    void testRemove() {
        bst.addAll(Arrays.asList(5, 3, 7));
        assertTrue(bst.remove(5)); // Removing the root
        assertFalse(bst.contains(5)); // Root should be removed
        assertTrue(bst.remove(3)); // Removing a leaf
        assertFalse(bst.remove(8)); // Attempting to remove a non-existent element
    }

    // Test removing a node with two children from the BST.
    @Test
    void testRemoveNodeWithTwoChildren() {
        bst.addAll(Arrays.asList(20, 10, 30, 5, 15, 25, 35, 2, 7, 12, 17, 22, 27, 32, 37));
        assertTrue(bst.remove(10)); // Node with two children
        assertFalse(bst.contains(10)); // The node should be removed
        assertEquals(Arrays.asList(2, 5, 7, 12, 15, 17, 20, 22, 25, 27, 30, 32, 35, 37), bst.toArrayList()); // Check the remaining elements
    }

    // Test removing multiple elements from the BST.
    @Test
    void testRemoveAll() {
        bst.addAll(Arrays.asList(5, 3, 7));
        assertTrue(bst.removeAll(Arrays.asList(3, 7))); // Removing multiple elements
        assertFalse(bst.contains(7)); // Elements should be removed
    }

    // Test the size of the BST.
    @Test
    void testSize() {
        bst.addAll(Arrays.asList(5, 3, 7));
        assertEquals(3, bst.size()); // The size should match the number of added elements
    }

    // Test converting the BST to an ArrayList.
    @Test
    void testToArrayList() {
        bst.addAll(Arrays.asList(5, 3, 7));
        assertEquals(Arrays.asList(3, 5, 7), bst.toArrayList()); // The ArrayList should match the elements in the BST
    }

    // Test getting the first element from an empty BST.
    @Test
    void testFirstOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> bst.first()); // Should throw NoSuchElementException
    }

    // Test getting the last element from an empty BST.
    @Test
    void testLastOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> bst.last()); // Should throw NoSuchElementException
    }

    // Test removing an element from an empty BST.
    @Test
    void testRemoveOnEmpty() {
        assertFalse(bst.remove(1)); // Removing from an empty tree should be false
    }

    // Test removing multiple elements from an empty BST.
    @Test
    void testRemoveAllOnEmpty() {
        assertFalse(bst.removeAll(Arrays.asList(1, 2))); // Removing from an empty tree should be false
    }
}
