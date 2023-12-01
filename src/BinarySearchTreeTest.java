import org.junit.jupiter.api.DisplayName;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BinarySearchTreeTest {
    // https://stackoverflow.com/questions/21913408/reading-system-out-java-test
    java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();

    @org.junit.jupiter.api.Test
    @DisplayName("Add")
    void add() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(5); BST.add(4); BST.add(9); BST.add(6);
        assertEquals(true, BST.contains(4));

    }

    @org.junit.jupiter.api.Test
    @DisplayName("Contains")
    void contains() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(5); BST.add(4); BST.add(9); BST.add(6);
        assertAll(() -> assertEquals(true, BST.contains(4)),
                () -> assertEquals(false, BST.contains(7)));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Counting Nodes")
    void countNodes() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(5); BST.add(4); BST.add(9); BST.add(6);
        assertEquals(4, BST.countNodes());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Counting Leaf Nodes")
    void countLeafNodes() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(5); BST.add(4); BST.add(9); BST.add(6);
        assertEquals(2, BST.countLeafNodes());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Getting Height")
    void getHeight() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(5); BST.add(4); BST.add(9); BST.add(6);
        assertEquals(2, BST.getHeight());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Printing Pre-Order")
    void printPreorder() {
        BinarySearchTree BST = new BinarySearchTree();
        System.setOut(new java.io.PrintStream(out));
        BST.add(5); BST.add(4); BST.add(9); BST.add(6);
        BST.printPreorder();
        assertEquals("5 4 9 6 \r\n", out.toString());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Printing In-Order")
    void printInorder() {
        BinarySearchTree BST = new BinarySearchTree();
        System.setOut(new java.io.PrintStream(out));
        BST.add(5); BST.add(4); BST.add(9); BST.add(6);
        BST.printInorder();
        assertEquals("4 5 6 9 \r\n", out.toString());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Printing Post-Order")
    void printPostorder() {
        BinarySearchTree BST = new BinarySearchTree();
        System.setOut(new java.io.PrintStream(out));
        BST.add(5); BST.add(4); BST.add(9); BST.add(6);
        BST.printPostorder();
        assertEquals("4 6 9 5 \r\n", out.toString());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Deleting")
    void delete() {
        BinarySearchTree BST = new BinarySearchTree();
        BST.add(5); BST.add(2); BST.add(9); BST.add(1); BST.add(4); BST.add(6); BST.add(3);
        assertAll(() -> assertEquals(5, BST.delete(5)),
                () -> assertEquals(false, BST.contains(5)),
                () -> assertEquals(4, BST.delete(4)),
                () -> assertEquals(false, BST.contains(4)),
                () -> assertEquals(9, BST.delete(9)),
                () -> assertEquals(false, BST.contains(9)),
                () -> assertEquals(6, BST.delete(6)),
                () -> assertEquals(false, BST.contains(6)),
        () -> assertThrows(NoSuchElementException.class, () -> BST.delete(0)));
    }
}