package datastructures.stacks;

import datastructures.linkedlists.LinkedList;

public class StackLinkedList extends LinkedList implements IntegerStack {
    StackLinkedList() {
        super();
    }

    @Override
    public int size() {
        return length();
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
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
