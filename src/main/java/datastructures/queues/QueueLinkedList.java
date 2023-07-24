package datastructures.queues;

import datastructures.linkedlists.LinkedList;

public class QueueLinkedList extends LinkedList<Integer> implements Queue {
    Node head;
    Node tail;
    QueueLinkedList(){
        this.head = null;
        this.tail = null;
    }

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    @Override
    public void enqueue(int data) {
        Node node = new Node(data);

        // Empty Queue
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            return;
        }

        Node tailNode = this.tail;
        tailNode.next = node;
        this.tail = node;
    }

    @Override
    public Integer dequeue() {
        // Empty queue
        if (this.head == null) {
            return 0;
        }
        Node node = this.head;
        this.head = node.next;
        return node.value;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        int count = 0;
        Node node = this.head;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public void print() {
        Node node = this.head;
        if (this.head != null && this.tail != null) {
            System.out.println("Head : " + this.head.value + "; Tail : " + this.tail.value + "; Size : " + size());
        }
        while (node != null) {
            System.out.print(node.value);
            System.out.print(" ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueLinkedList q = new QueueLinkedList();
        q.print();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.dequeue();
        q.print();
    }
}
