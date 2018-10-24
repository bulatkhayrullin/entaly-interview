package com.entaly;

import com.entaly.util.BinaryTree;
import com.entaly.util.Node;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeIntegerTest {
    private BinaryTree<Integer> binaryTree;

    private List<Node<Integer>> createIntegerBinaryTree() {
        ArrayList<Node<Integer>> nodes = new ArrayList<Node<Integer>>() {{
            add(new Node<Integer>(8));
            add(new Node<Integer>(2));
            add(new Node<Integer>(4));
            add(new Node<Integer>(7));
            add(new Node<Integer>(10));
            add(new Node<Integer>(11));
            add(new Node<Integer>(1));
        }};
        binaryTree = new BinaryTree<>(nodes);
        return nodes;
    }

    @After
    public void afterTest() {
        binaryTree = null;
    }

    @Test
    public void emptyRootOnCreate() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        assertNull(binaryTree.getRoot());
    }

    @Test
    public void firstAddedElementIsRoot() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Node<Integer> root = new Node<>(1);
        binaryTree.add(root);
        assertEquals(root, binaryTree.getRoot());
    }

    @Test
    public void secondAddedElementIsNotRoot() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Node<Integer> notRoot = new Node<>(2);
        binaryTree.add(new Node<>(1));
        binaryTree.add(notRoot);
        assertNotEquals(notRoot, binaryTree.getRoot());
    }

    @Test
    public void correctLeftBalanced() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Node<Integer> root = new Node<>(2);
        Node<Integer> left = new Node<>(1);
        binaryTree.add(root);
        binaryTree.add(left);
        assertEquals(left, binaryTree.getRoot().getLeft());
    }

    @Test
    public void correctRightBalanced() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Node<Integer> root = new Node<>(2);
        Node<Integer> right = new Node<>(3);
        binaryTree.add(root);
        binaryTree.add(right);
        assertEquals(right, binaryTree.getRoot().getRight());
    }

    @Test
    public void nullParentIfEmpty() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Node parent = binaryTree.findCommonParentRecursive(new Node<>(1), new Node<>(2));
        assertNull(parent);
    }

    @Test
    public void findCorrectParent() {
        List<Node<Integer>> integerBinaryTree = createIntegerBinaryTree();
        Node parent = binaryTree.findCommonParentRecursive(integerBinaryTree.get(3), integerBinaryTree.get(6));
        assertEquals(integerBinaryTree.get(1), parent);
    }

    @Test
    public void findNotCorrectParent() {
        List<Node<Integer>> integerBinaryTree = createIntegerBinaryTree();
        Node parent = binaryTree.findCommonParentRecursive(integerBinaryTree.get(3), integerBinaryTree.get(6));
        assertNotEquals(integerBinaryTree.get(2), parent);
    }

    @Test
    public void findParentBetweenTwoSameNodes() {
        List<Node<Integer>> integerBinaryTree = createIntegerBinaryTree();
        Node parent = binaryTree.findCommonParentRecursive(integerBinaryTree.get(3), integerBinaryTree.get(3));
        assertEquals(integerBinaryTree.get(3), parent);
    }

}
