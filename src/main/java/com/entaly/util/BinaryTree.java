package com.entaly.util;

import java.util.List;

/**
 * Class represents simple binary tree with root and with possibility to
 * find common parent Node
 *
 * @param <T> represents Node.data class
 *            extends Comparable<T> for possibility to use with any Comparable class
 */
public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinaryTree() {
    }

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    public BinaryTree(List<Node<T>> nodes) {
        for (Node<T> node : nodes) add(node);
    }

    public Node<T> getRoot() {
        return root;
    }

    public Node findCommonParentRecursive(Node<T> n1, Node<T> n2) {
        return findCommonParentRecursive(root, n1, n2);
    }

    private Node findCommonParentRecursive(Node<T> current, Node<T> n1, Node<T> n2) {
        if (current == null) {
            return null;
        }
        if (current == n1 || current == n2) {
            return current;
        }
        Node left = findCommonParentRecursive(current.getLeft(), n1, n2);
        Node right = findCommonParentRecursive(current.getRight(), n1, n2);
        if (left != null && right != null) {
            return current;
        }
        return left != null ? left : right;
    }

    public void add(Node<T> node) {
        if (root == null) {
            root = node;
        } else {
            add(root, node);
        }
    }

    private void add(Node<T> current, Node<T> node) {
        int compare = current.getData().compareTo(node.getData());
        if (compare < 0) {
            if (current.getRight() == null) {
                current.setRight(node);
                return;
            }
            add(current.getRight(), node);
        } else if (compare > 0) {
            if (current.getLeft() == null) {
                current.setLeft(node);
                return;
            }
            add(current.getLeft(), node);
        }
    }
}
