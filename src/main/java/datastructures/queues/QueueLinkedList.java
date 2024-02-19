package datastructures.queues;

import datastructures.linkedlists.LinkedList;

public class QueueLinkedList<Item> extends LinkedList<Item> implements Queue<Item> {
    Node head;
    Node tail;
    public QueueLinkedList(){
        this.head = null;
        this.tail = null;
    }

    private static class Node<Item> {
        Item value;
        Node next;

        Node(Item value) {
            this.value = value;
            this.next = null;
        }
    }

    @Override
    public void enqueue(Item data) {
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
    public Item dequeue() {
        // Empty queue
        if (this.head == null) {
            return null;
        }
        Node node = this.head;
        this.head = node.next;
        return (Item) node.value;
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
