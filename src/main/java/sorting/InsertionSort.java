package sorting;

import java.util.Arrays;

public class InsertionSort {
    public static void sort(int[] array) {
        int N = array.length;
        for (int i = 0; i < N-1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{12, 31, 25, 8, 32, 17};
        System.out.println("Input array : " + Arrays.toString(array));
        InsertionSort.sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
        System.out.println();

        array = new int[]{ 98, 54, 53, 18, 21, 12 };
        System.out.println("Input array : " + Arrays.toString(array));
        InsertionSort.sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
    }
}
