package sorting;

import java.util.Arrays;

public class MergeSort extends Sorting{

    private void mergeSort(Comparable[] array, Comparable[] aux, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSort(array, aux, low, mid);
        mergeSort(array, aux, mid+1, high);
        merge(array, aux, low, mid, high);
    }

    public void merge(Comparable[] array, Comparable[] aux, int low, int mid, int high) {
        for (int k = low; k <= high; k++) {
            aux[k] = array[k];
        }

        int i = low;
        int j = mid+1;

        for (int k = low; k <= high; k++) {
            if (i > mid) {
                // Left array already added
                array[k] = aux[j];
                j++;
            } else if (j > high) {
                // Right array already added
                array[k] = aux[i];
                i++;
            } else if (less(aux[j], aux[i])) {
                array[k] = aux[j];
                j++;
            } else {
                array[k] = aux[i];
                i++;
            }
        }
    }

    @Override
    public void sort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];
        mergeSort(array, aux, 0, array.length-1);
    }

    public static void Sort(Comparable[] array) {
        MergeSort ms = new MergeSort();
        ms.sort(array);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{12, 31, 25, 8, 32, 17};
        System.out.println("Input array : " + Arrays.toString(array));
        MergeSort.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
        System.out.println();

        array = new Integer[]{ 98, 54, 53, 18, 21, 12 };
        System.out.println("Input array : " + Arrays.toString(array));
        MergeSort.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
    }
}
