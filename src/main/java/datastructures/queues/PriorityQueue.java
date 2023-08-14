package datastructures.queues;

public interface PriorityQueue<Key extends Comparable<Key>> {
    void insert(Key value);
    Key delMax();
    boolean isEmpty();
}
