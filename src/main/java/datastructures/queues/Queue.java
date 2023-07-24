package datastructures.queues;

public interface Queue {
    void enqueue(int data);
    Integer dequeue();
    boolean isEmpty();
    int size();
}
