package datastructures.stacks;

public class StackArray implements StringStack {
    String[] stack;
    int size;

    StackArray() {
        stack = new String[1];
        size = 0;
    }

    // Amortized Timed complexity becomes constant O(1) for N items.
    // Worst case Time complexity for insertion of Nth element can be O(N)
    // Faster than LinkedList implementation for most cases, however slow for powers of 2 insertions.
    // To be used when every insertion does not need to be fast
    private void resizeArray(int capacity) {
        String[] copy = new String[capacity];
        for (int i= 0; i < size; i++) {
            copy[i] = stack[i];
        }
        stack = copy;
    }

    @Override
    public void push(String elem) {
        if (size == stack.length) {
            // Double the size
            resizeArray(2*size);
        }
        stack[size] = elem;
        size++;
    }

    @Override
    public String pop() {
        String pop = stack[size-1];
        stack[size - 1] = null;
        size--;

        // < 25% capacity
        if (size < stack.length / 4) {
            resizeArray(stack.length / 4);
        }
        return pop;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void print() {
        for (int i=0; i < size; i++) {
            System.out.print(stack[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StackArray stack = new StackArray();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.print();
        stack.pop();
        stack.print();
        System.out.println(stack.stack.length);
        System.out.println(stack.size);
    }
}
