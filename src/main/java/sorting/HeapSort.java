package sorting;

import java.util.Arrays;

public class HeapSort extends Sorting{
    @Override
    public void sort(Comparable[] array) {
        constructMaxHeap(array);
        sortDown(array);
    }

    private void constructMaxHeap(Comparable[] array) {
        int N = array.length;
        for (int i = (N / 2) - 1 ; i >= 0 ; i--) {
            sink(array, i, N);
        }
    }

    private void sortDown(Comparable[] array) {
        int last = array.length - 1;
        while (last >= 0) {
            swap(array, 0, last);
            sink(array, 0, last);
            System.out.println("Sort Down; last = " + last + "; heap : " + Arrays.toString(array));
            last--;
        }
    }

    private void sink(Comparable[] array, int root, int N) {
        int leftChild = (root * 2) + 1;
        int rightChild = (root * 2) + 2;

        while (leftChild < N && rightChild < N) {
            int biggestChildIdx;
            if (less(array[leftChild], array[rightChild])) {
                biggestChildIdx = rightChild;
            } else {
                biggestChildIdx = leftChild;
            }

            if (less(array[root], array[biggestChildIdx])) {
                swap(array, root, biggestChildIdx);
                root = biggestChildIdx;
                leftChild = (2 * root) + 1;
                rightChild = (2 * root) + 2;
            } else {
                return;
            }
        }
    }


    public static void Sort(Comparable[] array) {
        HeapSort hs = new HeapSort();
        hs.sort(array);
    }
    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        String[] array = new String[] {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        System.out.println("Input array : " + Arrays.toString(array));
        hs.sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));

    }
}
