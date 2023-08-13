package datastructures.queues;

import shuffle.KnuthShuffle;

import java.util.*;

public class RandomizedQueue<Item> implements Queue<Item>, Iterable<Item> {

    Item[] queue;
    int HEAD;
    int TAIL;
    // We use a circular array to implement the queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[5]; // Start with size 10
        HEAD = 0;
        TAIL = 0;
    }

    @Override
    public void enqueue(Item data) {
        if (queue.length == 0) {
            queue = (Item[]) new Object[1];
        }
        if (TAIL >= queue.length) {
            resizeArray(2* queue.length);
        }
        queue[TAIL] = data;
        TAIL++;
    }

    @Override
    public Item dequeue() {
        int idx = getRandomIndex();
        Item val = queue[idx];

        if (idx != TAIL - 1) {
            queue[idx] = queue[TAIL-1];
            queue[TAIL-1] = null;
        }
        TAIL--;

        if (size() < (queue.length / 4)) { // < 25% occupancy
            resizeArray(queue.length / 2);
        }
        return val;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return TAIL - HEAD;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public Item sample() {
        return queue[getRandomIndex()];
    }

    private void resizeArray(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int start = HEAD;
        int end = TAIL;
        int idx = 0;
        while (start < end) {
            copy[idx] = queue[start];
            start++;
            idx++;
        }
        HEAD = 0;
        TAIL = idx;
        queue = copy;
    }

    private void print() {
        System.out.println("HEAD: " + HEAD + " ; TAIL : " + TAIL + "; Size : " + size());
        System.out.println(Arrays.toString(queue));
    }

    private int getRandomIndex() {
        Random rand = new Random();
        return rand.nextInt(TAIL-HEAD+1) + HEAD;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int currentIdx = 0;
        Item[] shuffledItems;

        public RandomizedQueueIterator() {
            shuffledItems = (Item[]) new Object[size()];
            System.arraycopy(queue, 0, shuffledItems, 0, size());
            for (int i = 1; i < size(); i++) {
                Random rand = new Random();
                int randIdx = rand.nextInt(i);
                Item temp = shuffledItems[randIdx];
                shuffledItems[randIdx] = shuffledItems[i];
                shuffledItems[i] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return currentIdx < size();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to iterate");
            }
            return shuffledItems[currentIdx++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.print();

        for (Object elem : q) {
            System.out.println(elem);
        }

        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.print();

        for (Object elem : q) {
            System.out.println(elem);
        }
    }
}
