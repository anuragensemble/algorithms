package datastructures.queues;

import java.util.Arrays;

public class PriorityQueueOrdered<Key extends Comparable<Key>> implements PriorityQueue<Key> {
    Key[] queue;
    int IN;
    PriorityQueueOrdered() {
        queue = (Key[]) new Comparable[10];
        IN = 0;
    }

    @Override
    public void insert(Key value) { // O(N) to maintain order
        if (IN >= queue.length) {
            resizeArray(2* queue.length);
        }
        queue[IN] = value;
        // queue[0..IN-1] is already sorted
        for (int i = IN; i > 1; i--) {
            if (queue[i].compareTo(queue[i-1]) < 0) {
                Key temp = queue[i];
                queue[i] = queue[i-1];
                queue[i-1] = temp;
            }
        }
        IN++;
    }

    @Override
    public Key delMax() { // O(1)
        Key value = queue[IN-1];
        queue[IN-1] = null;
        IN--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return IN == 0;
    }

    private void print() {
        System.out.println(Arrays.toString(queue));
    }

    private void resizeArray(int capacity) {
        Key[] copy = (Key[]) new Comparable[capacity];
        for (int i = 0; i < IN; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    public static void main(String[] args) {
        PriorityQueueOrdered<Integer> pq = new PriorityQueueOrdered<>();
        pq.insert(1);
        pq.insert(2);
        pq.insert(3);
        pq.print();
        pq.delMax();
        pq.print();
        PriorityQueueOrdered<String> pqs = new PriorityQueueOrdered<>();
        pqs.insert("P");
        pqs.insert("Q");
        pqs.insert("E");
        pqs.print();
        System.out.println(pqs.delMax());
        pqs.insert("X");
        pqs.insert("A");
        pqs.insert("M");
        pqs.print();
        System.out.println(pqs.delMax());
        pqs.insert("P");
        pqs.insert("L");
        pqs.insert("E");
        pq.print();
        System.out.println(pqs.delMax());
    }
}
