package datastructures.queues;

import java.util.Arrays;

public class QueueCircularArray<Item> implements Queue<Item> {
    Item[] queue = (Item[]) new Object[10];
    int In = 0;
    int Out = 0;
    @Override
    public void enqueue(Item data) {
        if (queue.length == 0) {
            queue = (Item[]) new Object[10];
        }
        if (In >= queue.length) {
            // Overflow about to happen. Resize double
            resizeQueue(2*queue.length);
        }
        queue[In] = data;
        In +=1;
        Out = 0;
    }

    @Override
    public Item dequeue() {
        // Empty queue
        if (queue.length == 0) {
            return null;
        }
        Item val = queue[Out];
        if (size() < (queue.length / 4)) {
            resizeQueue(queue.length / 2);
        } else {
            Out += 1;
        }
        return val;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return In-Out;
    }

    void print() {
        System.out.println("IN: " + In + " ; Out : " + Out + "; Size : " + size());
        System.out.println(Arrays.toString(queue));
    }

    private void resizeQueue(int capacity) {
        int start = Out;
        int end = In;
        int idx = 0;
        Item[] copy = (Item[]) new Object[capacity];
        while (start < end) {
            copy[idx] = queue[start];
            start++;
            idx++;
        }
        In = idx;
        Out = 0;
        queue = copy;
    }

    public static void main(String[] args) {
        QueueCircularArray q = new QueueCircularArray();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.enqueue(9);
        q.enqueue(10);
        q.print();
        q.dequeue();
        q.dequeue();
        q.print();
        q.enqueue(11);
        q.print();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.print();
        q.dequeue();
        q.print();
        q.dequeue();
        q.print();
    }
}
