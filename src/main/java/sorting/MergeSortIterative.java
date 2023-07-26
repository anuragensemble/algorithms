package sorting;

import java.util.Arrays;

public class MergeSortIterative extends MergeSort{

    private void mergeSortIterative(Comparable[] array, Comparable[] aux) {
        for (int i = 1; i < array.length; i=2*i) { // i = 1, 2, 4
            for (int j = 0; j < array.length - i; j+=2*i) { // j = 0; j=0,
                merge(array, aux, j, j+i-1, Math.min(j+ 2*i - 1, array.length - 1));
            }
        }
    }

    public void sort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];
        mergeSortIterative(array, aux);
    }

    public static void Sort(Comparable[] array) {
        MergeSortIterative msi = new MergeSortIterative();
        msi.sort(array);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{12, 31, 25, 8, 32, 17};
        System.out.println("Input array : " + Arrays.toString(array));
        MergeSortIterative.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
        System.out.println();

        array = new Integer[]{ 98, 54, 53, 18, 21, 12 };
        System.out.println("Input array : " + Arrays.toString(array));
        MergeSortIterative.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
    }
}
