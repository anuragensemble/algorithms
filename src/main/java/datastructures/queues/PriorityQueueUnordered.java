package datastructures.queues;

import java.util.Arrays;

public class PriorityQueueUnordered<Key extends Comparable<Key>> implements PriorityQueue<Key>{

    Key[] queue;
    int IN;

    // Unordered PQ means we need N space. We can't use M(M elem PQ) as the space bound.
    PriorityQueueUnordered() {
        queue = (Key[]) new Comparable[10]; // Let's start with 10
        IN = 0;
    }

    @Override
    public void insert(Key value) { // O(1) amortized time complexity for inserts
        if (IN >= queue.length) {
            resizeArray(2 * queue.length);
        }
        queue[IN] = value;
        IN++;
    }

    @Override
    public Key delMax() {
        int maxIdx = findMaxIdx();
        Key value = queue[maxIdx];
        queue[maxIdx] = queue[IN-1];
        queue[IN-1] = null;
        IN--;

        if (IN < (queue.length / 4)) {
            resizeArray(queue.length / 2);
        }

        return value;
    }

    @Override
    public boolean isEmpty() {
        return IN == 0;
    }

    private void resizeArray(int capacity) {
        Key[] copy = (Key[]) new Comparable[capacity];
        for (int i = 0; i < queue.length; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    private int findMaxIdx() { // O(N) to find the max element
        Key max = queue[0];
        int maxIdx = 0;
        for (int i=1; i < IN; i++) {
            if (max.compareTo(queue[i]) < 0) {
                max = queue[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    private void print() {
        System.out.println(Arrays.toString(queue));
    }

    public static void main(String[] args) {
        PriorityQueueUnordered<Integer> pq = new PriorityQueueUnordered<>();
        pq.insert(1);
        pq.insert(2);
        pq.insert(3);
        pq.print();
        pq.delMax();
        pq.print();
        PriorityQueueUnordered<String> pqs = new PriorityQueueUnordered<>();
        pqs.insert("P");
        pqs.insert("Q");
        pqs.insert("E");
        pq.print();
        System.out.println(pqs.delMax());
        pqs.insert("X");
        pqs.insert("A");
        pqs.insert("M");
        pq.print();
        System.out.println(pqs.delMax());
        pqs.insert("P");
        pqs.insert("L");
        pqs.insert("E");
        pq.print();
        System.out.println(pqs.delMax());
    }
}
