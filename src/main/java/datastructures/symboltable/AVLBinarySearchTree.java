package datastructures.symboltable;

public class AVLBinarySearchTree<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int height;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    @Override
    public void put(Key key, Value val) {
        root = insert(root, key, val);
    }

    private Node insert(Node node, Key key, Value val) {
        if (node == null) return new Node(key, val);

        int comparator = key.compareTo(node.key);

        // Left subtree
        if (comparator < 0) node.left = insert(node.left, key, val);
        else if (comparator > 0) node.right = insert(node.right, key, val);
        else node.value = val;

        // Update height
        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);
        // Case-1: Left Left
        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);

        // Case-2 : Right Right
        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);

        // Case-3 : Left Right
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Case-4 : Right Left
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) return 0;

        return height(node.left) - height(node.right);
    }

    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        // Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        // Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
        return newRoot;
    }

    @Override
    public Value get(Key key) {
        Node node = this.root;
        while (node != null) {
            int comparator = key.compareTo(node.key);
            if (comparator < 0) {
                node = node.left;
            } else if (comparator > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        int comparator = key.compareTo(node.key);

        if (comparator < 0) node.left = delete(node.left, key);
        else if (comparator > 0) node.right = delete(node.right, key);
        else {
            // Key found
            // 0 or 1 children
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Step 1: Find the inorder successor of the "delNode" (minNode in right subtree)
            Node inorderSuccessor = min(node.right);

            // Step 2: Copy the inorder successor data to current node
            node.key = inorderSuccessor.key;
            node.value = inorderSuccessor.value;

            // Step 3: Delete the inorderSuccessor original node
            node.right = delete(node.right, inorderSuccessor.key);
        }

        // Ignore if the only element in tree was deleted
        if (root != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
            int balance = getBalance(node);

            if (balance > 1 && getBalance(node.left) >= 0) {
                // Left Left
                return rightRotate(node);
            }

            if (balance > 1 && getBalance(node.left) < 0) {
                // Left Right
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            if (balance < -1 && getBalance(node.right) <= 0) {
                // Right Right
                return leftRotate(node);
            }

            if (balance < -1 && getBalance(node.right) > 0) {
                // Right Left
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }
        return node;
    }

    private Node min(Node node) {
        Node min = null;
        while (node != null) {
            min = node;
            node = node.left;
        }
        return min;
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

    public static void main(String[] args) {
        AVLBinarySearchTree<String, Integer> avl = new AVLBinarySearchTree<>();
        avl.put("S", 1);
        avl.put("E", 2);
        avl.put("A", 3);
        avl.put("R", 4);
        avl.put("C", 5);
        avl.put("H", 6);
        avl.put("X", 7);
        avl.put("M", 8);
        avl.put("P", 9);
        avl.put("L", 10);
        System.out.println(avl);
        System.out.println(avl.get("P"));
        avl.delete("C");
        avl.delete("A");
        avl.delete("M");
        System.out.println(avl);
        System.out.println(avl.get("P"));
    }
}
