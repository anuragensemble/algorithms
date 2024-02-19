package datastructures.linkedlists;

public class LinkedList<Item> {
    public class Node {
        public Item value;
        public Node next;
        Node(Item value) {
            this.value = value;
            this.next = null;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            Node start = this;
            while (start != null) {
                stringBuilder.append(start.value).append("--->");
                start = start.next;
            }
            stringBuilder.append("NULL");
            return stringBuilder.toString();
        }
    }

    public Node head;
    public LinkedList() {
        this.head = null;
    }

    public void push(Item value) {
        Node node = new Node(value);
        node.next = this.head;
        this.head = node;
    }

    public void append(Item value) {
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

    public void enqueue(Item value) {
        append(value);
    }

    public Item pop() {
        if (this.head == null) {
            return null;
        }
        Item pop = this.head.value;
        this.head = this.head.next;
        return pop;
    }

    public Item dequeue() {
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

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node node = this.head;
        while (node != null) {
            stringBuilder.append(node.value);
            stringBuilder.append("--->");
            node = node.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
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
