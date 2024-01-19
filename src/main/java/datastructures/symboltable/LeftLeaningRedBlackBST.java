package datastructures.symboltable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class LeftLeaningRedBlackBST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean color;
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }
    }

    private Node root;

    @Override
    public void put(Key key, Value val) {
        root = insert(root, key, val);
        root.color = BLACK;
    }

    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);

        if (cmp > 0) {
            node.right = insert(node.right, key, value);
        } else if (cmp < 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if(isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color;
    }

    @Override
    public Value get(Key key) {
        return null;
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    public String toString() {
        return pretty(root, "", 1);
    }

    private String pretty(Node node, String prefix, int dir) {
        if (node == null) {
            return "";
        }

        String space = " ".repeat(("" + node.key).length());
        return pretty(node.right, prefix + "│  ".charAt(dir) + space, 2)
                + prefix + "└ ┌".charAt(dir) + node.key
                + " ┘┐┤".charAt((node.left  != null ? 2 : 0)
                + (node.right != null ? 1 : 0)) + "\n"
                + pretty(node.left, prefix + "  │".charAt(dir) + space, 0);
    }

    public static void main(String[] args) {
        LeftLeaningRedBlackBST<String, Integer> llrbbst = new LeftLeaningRedBlackBST<>();
        llrbbst.put("S", 1);
        llrbbst.put("E", 2);
        llrbbst.put("A", 3);
        llrbbst.put("R", 4);
        llrbbst.put("C", 5);
        llrbbst.put("H", 6);
        llrbbst.put("X", 7);
        llrbbst.put("M", 8);
        llrbbst.put("P", 9);
        llrbbst.put("L", 10);
        System.out.println(llrbbst);
//        System.out.println(llrbbst.toStringColors());
    }
}
