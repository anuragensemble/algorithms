package datastructures.stacks;

import datastructures.linkedlists.LinkedList;

public class StackLinkedList extends LinkedList<Integer> {
    StackLinkedList() {
        super();
    }

    public static void main(String[] args) {
        StackLinkedList stack = new StackLinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print();
        stack.pop();
        stack.print();
    }

}
