package sorting;

import shuffle.KnuthShuffle;

import java.util.Arrays;

public class QuickSort extends Sorting{
    @Override
    public void sort(Comparable[] array) {
//        KnuthShuffle.Shuffle(array); // To get to average complexity of ~1.89 N LogN
        quickSort(array, 0, array.length-1);
    }

    private int partition(Comparable[] array, int low, int high) {
        int i = low+1;
        int j = high;

        while (true) {
            while (less(array[i], array[low])) {
                i++;
                if (i >= high) { // Use >= instead of ==. This caused issues due to increment happening above.
                    break;
                }
            }

            while (less(array[low], array[j])) {
                j--;
                if (j <= low) { // Use <= instead of ==. This caused issues due to decrement happening above.
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            swap(array, i, j);
        }
        swap(array, low, j);
        return j;
    }

    private void quickSort(Comparable[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int p = partition(array, low, high);
        quickSort(array, low, p-1);
        quickSort(array, p+1, high);
    }

    public static void Sort(Comparable[] array) {
        QuickSort qs = new QuickSort();
        qs.sort(array);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{12, 31, 25, 8, 32, 17};
        System.out.println("Input array : " + Arrays.toString(array));
        QuickSort.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
        System.out.println();

        array = new Integer[]{ 98, 54, 53, 18, 21, 12 };
        System.out.println("Input array : " + Arrays.toString(array));
        QuickSort.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
        System.out.println();

        String[] stringArray = new String[]{"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        System.out.println("Input array : " + Arrays.toString(stringArray));
        QuickSort.Sort(stringArray);
        System.out.println("Sorted array : " + Arrays.toString(stringArray));
    }
}
