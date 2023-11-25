package assignment05;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.NoSuchElementException;

public class LinkedListStackTest {

    private LinkedListStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new LinkedListStack<>();
    }

    @Test
    public void testIsEmptyOnNewStack() {
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    public void testSizeOnNewStack() {
        Assertions.assertEquals(0, stack.size());
    }

    @Test
    public void testPushAndSize() {
        stack.push(1);
        Assertions.assertEquals(1, stack.size());
        stack.push(2);
        Assertions.assertEquals(2, stack.size());
    }

    @Test
    public void testPeekOnNonEmptyStack() {
        stack.push(1);
        Assertions.assertEquals(1, stack.peek());
        stack.push(2);
        Assertions.assertEquals(2, stack.peek());
    }

    @Test
    public void testPopAndSize() {
        stack.push(1);
        stack.push(2);
        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(1, stack.size());
        Assertions.assertEquals(1, stack.pop());
        Assertions.assertEquals(0, stack.size());
    }

    @Test
    public void testClearAndIsEmpty() {
        stack.push(1);
        stack.clear();
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    public void testPeekOnEmptyStack() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        });
    }

    @Test
    public void testPopOnEmptyStack() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });
    }
}

