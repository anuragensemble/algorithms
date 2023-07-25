package sorting;

import java.util.Arrays;

public class InsertionSort extends Sorting{
    public void sort(Comparable[] array) {
        int N = array.length;
        for (int i = 0; i < N-1; i++) {
            for (int j = i; j > 0; j--) {
                if (less(array[j], array[j-1])) {
                    swap(array, j, j-1);
                }
            }
        }
    }

    public static void Sort(Comparable[] array) {
        InsertionSort is = new InsertionSort();
        is.sort(array);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{12, 31, 25, 8, 32, 17};
        System.out.println("Input array : " + Arrays.toString(array));
        InsertionSort.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
        System.out.println();

        array = new Integer[]{ 98, 54, 53, 18, 21, 12 };
        System.out.println("Input array : " + Arrays.toString(array));
        InsertionSort.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
    }
}
