package sorting;

public abstract class Sorting {
    boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public abstract void sort(Comparable[] array);
}
