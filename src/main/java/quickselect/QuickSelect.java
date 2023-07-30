package quickselect;

import shuffle.KnuthShuffle;

import java.util.Arrays;

public class QuickSelect{
    public static Comparable kthLargest(Comparable[] array, int k) {
        QuickSelect qs = new QuickSelect();
        return qs.quickSelect(array, array.length - k);
    }

    public static Comparable kthSmallest(Comparable[] array, int k) {
        QuickSelect qs = new QuickSelect();
        return qs.quickSelect(array, k-1);
    }

    private Comparable quickSelect(Comparable[] array, int k) {
        KnuthShuffle.Shuffle(array);
        int low = 0;
        int high = array.length - 1;

        while (low < high) {
            int j = partition(array, low, high);
            if (k < j) {
                high = j - 1;
            } else if (k > j) {
                low = j + 1;
            } else {
                return array[k];
            }
        }
        return array[k];
    }

    public int partition(Comparable[] array, int low, int high) {
        int i = low + 1;
        int j = high;

        while (true) {
            while (less(array[i], array[low])) {
                i++;
                if (i >= high) {
                    break;
                }
            }

            while (less(array[low], array[j])) {
                j--;
                if (j <= low) {
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

    private boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    private void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{12, 31, 25, 8, 32, 17};
        System.out.println("Input array : " + Arrays.toString(array));
        System.out.println("3rd Largest : " + QuickSelect.kthLargest(array, 3) + " ; Smallest : " + QuickSelect.kthSmallest(array, 1));
        System.out.println();

        array = new Integer[]{ 98, 54, 53, 18, 21, 12 };
        System.out.println("Input array : " + Arrays.toString(array));
        System.out.println("2nd Largest : " + QuickSelect.kthLargest(array, 2) + " ; 2nd Smallest : " + QuickSelect.kthSmallest(array, 2));
        System.out.println();

        String[] stringArray = new String[]{"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        System.out.println("Input array : " + Arrays.toString(stringArray));
        System.out.println("Largest : " + QuickSelect.kthLargest(stringArray, 1) + " ; 3rd Smallest : " + QuickSelect.kthSmallest(stringArray, 3));
    }
}
