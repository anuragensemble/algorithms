package datastructures.queues;

public interface Queue<Item> {
    void enqueue(Item data);
    Item dequeue();
    boolean isEmpty();
    int size();
}
