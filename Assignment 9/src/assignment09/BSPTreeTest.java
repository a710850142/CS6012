package assignment09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BSPTreeTest {
    private BSPTree tree;
    private ArrayList<Segment> segments;

    @BeforeEach
    void setUp() {
        segments = new ArrayList<>();
        // Add segments to the list
        segments.add(new Segment(0, 0, 1, 1));
        segments.add(new Segment(1, 1, 2, 2));
        // Add more segments as needed
        tree = new BSPTree(segments);
    }

    @Test
    void testInsert() {
        Segment newSegment = new Segment(2, 2, 3, 3);
        tree.insert(newSegment);
        // Assertions to check if the segment is inserted correctly
    }

    @Test
    void testTraverseFarToNear() {
        SegmentCallback callback = segment -> {
            // Define your callback logic here
        };
        tree.traverseFarToNear(1.0, 1.0, callback);
        // Assertions to verify the traversal order
    }

    @Test
    void testCollision() {
        Segment query = new Segment(1, 1, 3, 3);
        Segment result = tree.collision(query);
        // Assertions to check the collision result
    }

    @Test
    void testConstructor() {
        assertNotNull(tree);
        // Other assertions to test the state of the tree after construction
    }
    @Test
    void testEmptyTreeConstruction() {
        BSPTree emptyTree = new BSPTree();
        assertNotNull(emptyTree);
        // Additional assertions to verify the state of the tree
    }

    @Test
    void testInsertIntoEmptyTree() {
        BSPTree emptyTree = new BSPTree();
        Segment newSegment = new Segment(3, 3, 4, 4);
        emptyTree.insert(newSegment);
        // Assertions to verify the new segment is inserted
    }

    @Test
    void testTraverseEmptyTree() {
        BSPTree emptyTree = new BSPTree();
        emptyTree.traverseFarToNear(0, 0, segment -> fail("Should not traverse any segments"));
    }

    @Test
    void testCollisionWithNoIntersection() {
        Segment nonIntersectingSegment = new Segment(10, 10, 11, 11);
        assertNull(tree.collision(nonIntersectingSegment));
    }
}
