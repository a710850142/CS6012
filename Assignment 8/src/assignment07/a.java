package assignment07;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testNodeConstructorAndFields() {
        // Testing the constructor and field assignments
        Node node = new Node(1, 2, true);

        assertEquals(1, node.x, "X-coordinate should be 1");
        assertEquals(2, node.y, "Y-coordinate should be 2");
        assertTrue(node.isWall, "isWall should be true");
        assertNull(node.parent, "Parent should be null initially");
    }

    @Test
    void testParentAssignment() {
        // Testing parent assignment
        Node parent = new Node(0, 0, false);
        Node child = new Node(1, 1, false);

        child.parent = parent; // Assigning parent

        assertNotNull(child.parent, "Parent should not be null after assignment");
        assertEquals(parent, child.parent, "Parent should be the assigned node");
    }

    @Test
    void testIsWallAssignment() {
        // Testing isWall flag change
        Node node = new Node(1, 1, false);

        assertFalse(node.isWall, "isWall should be false initially");
        node.isWall = true; // Changing isWall flag
        assertTrue(node.isWall, "isWall should be true after change");
    }
}
