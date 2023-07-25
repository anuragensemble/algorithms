package sorting;

import java.util.Arrays;

public class ShellSort extends Sorting{
    @Override
    public void sort(Comparable[] array) {
        // Calculate increment sequence 'h'
        int h = 1;
        int N = array.length;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
//        System.out.println(h);
        while (h >= 1) {
            // h-sort the array
            for (int i = 0; i < N; i++) {
                for (int j = i; j >= h; j-=h) {
                    if (less(array[j], array[j-h])) {
                        swap(array, j, j-h);
                    }
                }
//                System.out.println("i = " + i + "; Array = " + Arrays.toString(array));
            }
            h /= 3;
        }
    }

    public static void Sort(Comparable[] array) {
        ShellSort ss = new ShellSort();
        ss.sort(array);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{12, 31, 25, 8, 32, 17};
        System.out.println("Input array : " + Arrays.toString(array));
        ShellSort.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
        System.out.println();

        array = new Integer[]{ 98, 54, 53, 18, 21, 12 };
        System.out.println("Input array : " + Arrays.toString(array));
        ShellSort.Sort(array);
        System.out.println("Sorted array : " + Arrays.toString(array));
    }
}
