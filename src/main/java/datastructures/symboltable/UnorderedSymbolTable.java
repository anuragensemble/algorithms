package datastructures.symboltable;

import java.util.ArrayList;
import java.util.List;

public class UnorderedSymbolTable<Key, Value> implements SymbolTable<Key, Value> {
    private class Node {
        Key key;
        Value val;
        Node next;

        Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (o == null) return false;

            if (o.getClass() != this.getClass()) {
                return false;
            }

            Node object = (Node) o;
            return object.key == this.key;
        }
    }

    Node head;
    public UnorderedSymbolTable() {
        this.head = null;
    }


    // O(N) for inserts (put)
    @Override
    public void put(Key key, Value val) {
        Node newNode = new Node(key, val);

        Node node = this.head;

        while (node != null) {
            if (node.equals(newNode)) {
                node.val = newNode.val;
                break;
            }
            node = node.next;
        }

        if (node == null) {
            // Did not find the node
            newNode.next = this.head;
            this.head = newNode;
        }
    }

    // O(N) for search (get); O(N/2) average case after N randomized inserts
    @Override
    public Value get(Key key) {
        Node node = this.head;
        Node searchNode = new Node(key, null);
        while (node != null) {
            if (node.equals(searchNode)) {
                return node.val;
            }
            node = node.next;
        }

        return null;
    }

    // O(N)
    @Override
    public void delete(Key key) {
        Node node = this.head;
        Node prevNode = null;
        Node searchNode = new Node(key, null);

        while (node != null) {
            if (node.equals(searchNode)) {
                break;
            }
            prevNode = node;
            node = node.next;
        }

        if (node != null) {
            // Element found
            if (prevNode == null) {
                // First in the list
                this.head = node.next;
            } else {
                prevNode.next = node.next;
            }
        }
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        Node node = this.head;
        int size = 0;

        while (node != null) {
            size += 1;
            node = node.next;
        }
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        List<Key> keyList = new ArrayList<>();
        Node node = this.head;
        while (node != null) {
            keyList.add(node.key);
            node = node.next;
        }
        return keyList;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        Node node = this.head;
        while(node != null) {
            result.append(node.key).append(":").append(node.val).append(" ");
            node = node.next;
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        UnorderedSymbolTable<String, Integer> st = new UnorderedSymbolTable<>();
        System.out.println(st);
        st.put("S", 0);
        System.out.println(st);
        System.out.println(st.isEmpty());
        System.out.println(st.contains("S"));
        st.put("E", 1);
        st.put("A", 2);
        st.put("R", 3);
        st.put("C", 4);
        st.put("H", 5);
        System.out.println(st);
        System.out.println(st.get("C"));
        st.delete("C");
        System.out.println(st);
        st.put("H", 10);
        System.out.println(st);
    }
}
