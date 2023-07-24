package datastructures.linkedlists;

public class LinkedList {
    private static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    Node head;
    public LinkedList() {
        this.head = null;
    }

    public void push(int value) {
        Node node = new Node(value);
        node.next = this.head;
        this.head = node;
    }

    public void append(int value) {
        Node node = this.head;
        if (node == null) {
            // No elements. Equivalent to push
            push(value);
            return;
        }

        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node(value);
    }

    public void enqueue(int value) {
        append(value);
    }

    public int pop() {
        if (this.head == null) {
            return 0;
        }
        int pop = this.head.value;
        this.head = this.head.next;
        return pop;
    }

    public int dequeue() {
        return pop();
    }

    public int length() {
        int length = 0;
        Node node = this.head;
        while(node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    public void print() {
        Node node = this.head;
        while (node != null) {
            System.out.print(node.value);
            System.out.print(" ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.push(1);
        list.push(2);
        list.append(3);
        list.append(4);
        list.print();
        list.pop();
        list.print();
        System.out.println(list.length());
    }

}
