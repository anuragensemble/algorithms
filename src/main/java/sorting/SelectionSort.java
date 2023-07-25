package sorting;

import java.util.Arrays;

public class SelectionSort extends Sorting{

    @Override
    public void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array[j], array[min])) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
    }

    public static void Sort(Comparable[] array) {
        SelectionSort sc = new SelectionSort();
        sc.sort(array);
    }

    public static void main(String[] args) {
        SelectionSort sc = new SelectionSort();
        Integer[] unsorted = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        sc.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }
}
