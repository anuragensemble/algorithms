package datastructures.symboltable;

import datastructures.queues.QueueCircularArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int count;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }

    public BinarySearchTree() {
        this.root = null;
    }
    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.value = val;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public Value get(Key key) {
        Node Root = this.root;
        while (Root != null) {
            int cmp = key.compareTo(Root.key);
            if (cmp < 0) {
                Root = Root.left;
            } else if (cmp > 0) {
                Root = Root.right;
            } else {
                return Root.value;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        // Base condition when node not found in BST
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // Search left
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            // Search right
            node.right = delete(node.right, key);
        } else {
            // key found
            // Case 1/2: 0/1 child. Return other child (null if no right child as well);
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Case 3: Node to be deleted has both left and right

            // Step 1: Store the node to be deleted.
            Node delNode = node;
            // Step 2: Find the successor of node to be deleted. This is the minimum in node's right subtree.
            node = min(delNode.right);
            //Step 3: Delete min in delNode's right subtree. (Note in Step 2 you have got the minimum node and this will be returned up)
            node.right = deleteMin(delNode.right);
            //Step 4: Copy the left subtree of delNode to node (min/successor of delNode)
            node.left = delNode.left;
        }
        // Update size
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private void deleteMin(Key key) {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    @Override
    public Iterable<Key> keys() {
        // Inorder traversal
        List<Key> queue = new ArrayList<>();
        inorder(root, queue);
        return queue;
    }

    private void inorder(Node x, List<Key> queue) {
        if (x == null) {
            return;
        }
        inorder(x.left, queue);
        queue.add(x.key);
        inorder(x.right, queue);
    }

    public Node min(Node node) {
        if (node == null) {
            node = root;
        }
        Node min = null;
        while (node != null) {
            min = node;
            node = node.left;
        }
        return min;
    }

    public Key max() {
        Node node = root;
        Key max = null;
        while (node != null) {
            max = node.key;
            node = node.right;
        }
        return max;
    }

    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            return floor(node.left, key);
        }
        // Since it's the floor, on the right side we can have no nodes which are smaller than the key. Then the node becomes the floor
        Node rightNode = floor(node.right, key);
        if (rightNode != null) {
            // Found a node in right subtree greater than root but smaller than key.
            return rightNode;
        } else {
            // Did not find a node in right subtree greater than root but smaller than key. root is the floor.
            return node;
        }
    }

    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        }
        if (cmp > 0) {
            return ceiling(node.right, key);
        }
        // Since it's the ceiling, on the left side we can have no nodes larger than key and smaller than root.
        Node leftNode = ceiling(node.left, key);
        if (leftNode != null) {
            return leftNode;
        } else {
            return node;
        }
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
//        else if (cmp == 0)
        return size(x.left);
    }

    public String toString() {
        return pretty(root, "", 1);
    }

    private String pretty(Node root, String prefix, int dir) {
        if (root == null) {
            return "";
        }

        String space = " ".repeat(("" + root.key).length());
        return pretty(root.right, prefix + "│  ".charAt(dir) + space, 2)
                + prefix + "└ ┌".charAt(dir) + root.key
                + " ┘┐┤".charAt((root.left  != null ? 2 : 0)
                + (root.right != null ? 1 : 0)) + "\n"
                + pretty(root.left, prefix + "  │".charAt(dir) + space, 0);
    }
    public static void main (String[] args) {
        BinarySearchTree<String, Integer> bst = new BinarySearchTree();
        bst.put("D", 4);
        bst.put("A", 1);
        bst.put("E", 2);
        bst.put("C", 3);
        System.out.println(bst);
        System.out.println(bst.min(null).key);
        System.out.println(bst.max());
        System.out.println(bst.floor("F"));
        System.out.println(bst.ceiling("B"));
        System.out.println(bst.root.count);
        System.out.println(bst.size());

        for (String key: bst.keys()) {
            System.out.print(key + "\t");
            System.out.println();
        }
        bst.delete("D");
        System.out.println(bst);
        System.out.println(bst.root.count);
        for (String key: bst.keys()) {
            System.out.print(key + "\t");
        }
    }
}
