package datastructures.queues;

public interface Queue {
    void enqueue(int data);
    int dequeue();
    boolean isEmpty();
    int size();
}
