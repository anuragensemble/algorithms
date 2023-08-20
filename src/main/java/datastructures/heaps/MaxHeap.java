package datastructures.heaps;

import java.security.Key;
import java.util.Arrays;

public class MaxHeap<Item extends Comparable> extends BinaryHeap<Item> {

    public MaxHeap() {
        super();
    }

    MaxHeap(int capacity) {
        super(capacity);
    }

    public void insert(Item key) {
        if (size > 0) {
            insertWithinBound();
        } else {
            insertUnbounded(key);
        }
    }

    public Item deleteMax() {
        if (IN < 1) {
            throw new RuntimeException("No element in heap");
        }
        Item max = elements[1];
        elements[1] = elements[IN-1];
        elements[IN - 1] = null;
        IN--;
        if (size > 0 && IN <= elements.length / 4) {
            resizeArray(elements.length / 2);
        }
        sink();
        return max;
    }

    public Item getMax() {
        return elements[1];
    }

    private void insertWithinBound() {
    }

    private void insertUnbounded(Item key) {
        if (IN >= elements.length) {
            resizeArray(2*elements.length);
        }
        elements[IN] = key;
        swim();
        IN++;
    }

    private void swim() {
        int last = IN;
        while (last > 1 && less(last / 2, last)) {
            swap(last, last / 2);
            last /= 2;
        }
    }

    private void sink() {
        int root = 1;
        int leftChild = 2 * root;
        int rightChild = (2 * root) + 1;
        while (leftChild < IN && rightChild < IN) {
            int biggerChildIdx;
            if (less(leftChild, rightChild)) {
                biggerChildIdx = rightChild;
            } else {
                biggerChildIdx = leftChild;
            }
            if (less(root, biggerChildIdx)) {
                swap(root, biggerChildIdx);
                root = biggerChildIdx;
                leftChild = 2* root;
                rightChild = (2*root) + 1;
            }
        }
    }

    private void print() {
        System.out.println("Max : " + getMax() + " in : " + Arrays.toString(elements));
    }
    public static void main(String[] args) {
        MaxHeap<String> heap = new MaxHeap<>();
        heap.insert("T");
        heap.print();
        heap.insert("R");
        heap.print();
        heap.insert("P");
        heap.print();
        heap.insert("H");
        heap.print();
        heap.insert("N");
        heap.print();
        heap.insert("E");
        heap.print();
        heap.insert("I");
        heap.print();
        heap.insert("G");
        heap.print();
        heap.insert("O");
        heap.print();
        heap.insert("A");
        heap.print();
        heap.insert("S");
        heap.print();
        System.out.println(heap.deleteMax());
        heap.print();
        System.out.println(heap.deleteMax());
        heap.print();
        heap.insert("S");
        heap.print();
    }
}
