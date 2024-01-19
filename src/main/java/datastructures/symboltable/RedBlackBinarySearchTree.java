package datastructures.symboltable;

public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private Node root;
    private class Node {
        private Key key;
        private Value value;
        private byte color; // 0 for black, 1 for red
        private Node left;
        private Node right;
        private Node parent;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.parent = null;
            this.color = 0;
        }
    }

    @Override
    public void put(Key key, Value val) {
        root = insert(root, key, val);
        root.color = 0; // root is always black.
    }

    boolean leftLeft = false;
    boolean leftRight = false;
    boolean rightRight = false;
    boolean rightLeft = false;

    private Node insert(Node node, Key key, Value val) {
        if (node == null) {
            node = new Node(key, val);
            node.color = 1; // Insert new node as Red
            return node;
        }

        int cmp = key.compareTo(node.key);
        boolean redConflict = false;
        String redConflictChildDirection = null;
        if (cmp < 0) {
            node.left = insert(node.left, key, val);
            node.left.parent = node;
            redConflict = node.color == 1 && node.left.color == 1;
            redConflictChildDirection = "left";
        } else if (cmp > 0) {
            node.right = insert(node.right, key, val);
            node.right.parent = node;
            redConflict = node.color == 1 && node.right.color == 1;
            redConflictChildDirection = "right";
        }
        else node.value = val;

        if (this.leftLeft) {
            node.color = (byte) (node.color ^ 1);
            node.left.color = (byte) (node.left.color ^ 1);
            node = rightRotate(node);
            this.leftLeft = false;
        }
        if (this.rightRight) {
            node.color = (byte) (node.color ^ 1);
            node.right.color = (byte) (node.right.color ^ 1);
            node = leftRotate(node);
            this.rightRight = false;
        }
        if (this.leftRight) {
            node.left = leftRotate(node.left);
            node.color = (byte) (node.color ^ 1);
            node.left.color = (byte) (node.left.color ^ 1);
            node = rightRotate(node);
            this.leftRight = false;
        }
        if (this.rightLeft) {
            node.right = rightRotate(node.right);
            node.color = (byte) (node.color ^ 1);
            node.right.color = (byte) (node.right.color ^ 1);
            node = leftRotate(node);
            this.rightLeft = false;
        }

        // Balance tree
        if (redConflict) {
            Node uncle;
            String parentDirection;
            if (node.parent.left == node) {
                uncle = node.parent.right;
                parentDirection = "left";
            } else {
                uncle = node.parent.left;
                parentDirection = "right";
            }

            if (uncle == null || uncle.color == 0) {
                if (redConflictChildDirection.equals(parentDirection)) {
                    node.color = (byte) (node.color ^ 1);
                    node.parent.color = (byte) (node.parent.color ^ 1);
                    if (parentDirection.equals("left")) {
                        this.leftLeft = true;
                    } else {
                        this.rightRight = true;
                    }
                } else {
                    if (redConflictChildDirection.equals("left")) {
                        this.rightLeft = true; // For parent to be left rotated
                    } else {
                        this.leftRight = true;
                    }
                }
            } else {
                node.color = (byte) (node.color ^ 1);
                node.parent.color = (byte) (node.parent.color ^ 1);
                uncle.color = (byte) (uncle.color ^ 1);
            }
        }
        return node;
    }

    @Override
    public Value get(Key key) {
        Node node = this.root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
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

    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    public String toString() {
        return pretty(root, "", 1, false);
    }

    public String toStringColors() {
        return pretty(root, "", 1, true);
    }


    private String pretty(Node root, String prefix, int dir, boolean printColors) {
        if (root == null) {
            return "";
        }

        String color = "B";
        String nodeChar = root.key.toString();
        if (root.color == 1) color = "R";
        if (printColors) nodeChar = color;
        String space = " ".repeat(("" + root.key).length());
        return pretty(root.right, prefix + "│  ".charAt(dir) + space, 2, printColors)
                + prefix + "└ ┌".charAt(dir) + nodeChar
                + " ┘┐┤".charAt((root.left  != null ? 2 : 0)
                + (root.right != null ? 1 : 0)) + "\n"
                + pretty(root.left, prefix + "  │".charAt(dir) + space, 0, printColors);
    }

    public static void main (String[] args) {
        RedBlackBinarySearchTree<String, Integer> rbbst = new RedBlackBinarySearchTree<>();
        rbbst.put("S", 1);
        rbbst.put("E", 2);
        rbbst.put("A", 3);
//        rbbst.put("R", 4);
//        rbbst.put("C", 5);
//        rbbst.put("H", 6);
//        rbbst.put("X", 7);
//        rbbst.put("M", 8);
//        rbbst.put("P", 9);
//        rbbst.put("L", 10);
        System.out.println(rbbst);
        System.out.println(rbbst.toStringColors());
//        System.out.println(rbbst.get("P"));
//        rbbst.delete("C");
//        rbbst.delete("A");
//        rbbst.delete("M");
//        System.out.println(rbbst);
//        System.out.println(rbbst.get("P"));
    }


}
