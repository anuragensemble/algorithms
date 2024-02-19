package datastructures.bag;

import datastructures.linkedlists.LinkedList;
import org.w3c.dom.Node;

import java.util.Iterator;

public class Bag<Item> extends LinkedList<Item> implements Iterable<Item> {
    public Bag() {
        super();
    }

    public void add(Item X) {
        push(X);
    }

    int size() {
        return super.length();
    }
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        private Node current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.value;
            current = current.next;
            return item;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Item i : this) {
            result.append(i).append("\t");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Bag<Integer> b = new Bag<Integer>();
        b.push(1);
        b.push(2);
        b.push(3);
        for (Integer i : b) {
            System.out.println(i);
        }
    }
}
