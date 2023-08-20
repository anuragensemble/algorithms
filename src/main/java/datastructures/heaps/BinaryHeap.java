package datastructures.heaps;

public class BinaryHeap<Item extends Comparable> {
    int size;
    int IN;
    Item[] elements;

    public BinaryHeap() {
        elements = (Item[]) new Comparable[11]; // Shrink and grow array as per need.
        IN = 1;
    }

    public BinaryHeap(int capacity) {
        if (capacity < 1) {
            throw new RuntimeException("Binary heap capacity must be greater than 0");
        }
        size = capacity;
        elements = (Item[]) new Comparable[capacity+1];
        IN = 1;
    }

    protected void resizeArray(int capacity) {
        Item[] copy = (Item[]) new Comparable[capacity];
        for (int i = 0; i < IN; i++) {
            copy[i] = elements[i];
        }
        elements = copy;
    }

    protected boolean less(int i, int j) {
        return elements[i].compareTo(elements[j]) < 0;
    }

    protected boolean great(int i, int j) { return elements[i].compareTo(elements[j]) > 0; }
    protected void swap(int i, int j) {
        Item temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
