package sorting;

import shuffle.KnuthShuffle;

import java.util.Arrays;

// The time complexity for QuickSort can become O(N^2) if there are duplicate keys.
// Edsger Dijkstra's 3 way partitioning used to make QuickSort faster
public class QuickSort3WayPartition extends Sorting{
    @Override
    public void sort(Comparable[] array) {
        KnuthShuffle.Shuffle(array);
        quickSortDijkstra(array, 0, array.length-1);
    }

    private void quickSortDijkstra(Comparable[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low + 1;
        int lt = low;
        int gt = high;
        Comparable pivot = array[low];

        while (i <= gt) {
            int cmp = array[i].compareTo(pivot);
            if (cmp < 0) {
                swap(array, lt, i);
                i++;
                lt++;
            } else if (cmp > 0) {
                swap(array, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        quickSortDijkstra(array, low, lt-1);
        quickSortDijkstra(array, gt+1, high);
    }

    public static void Sort(Comparable[] array) {
        QuickSort3WayPartition qs = new QuickSort3WayPartition();
        qs.sort(array);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{12, 31, 25, 8, 32, 17, 32, 32, 32};
        System.out.println("Input array : " + Arrays.toString(array));
        QuickSort3WayPartition.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
        System.out.println();

        array = new Integer[]{ 98, 54, 53, 18, 21, 12 };
        System.out.println("Input array : " + Arrays.toString(array));
        QuickSort3WayPartition.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
        System.out.println();

        String[] stringArray = new String[]{"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        System.out.println("Input array : " + Arrays.toString(stringArray));
        QuickSort3WayPartition.Sort(stringArray);
        System.out.println("Sorted array : " + Arrays.toString(stringArray));
    }
}
