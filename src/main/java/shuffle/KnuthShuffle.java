package shuffle;

import java.util.Arrays;
import java.util.Random;

public class KnuthShuffle{
    public void shuffle(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            Random random = new Random();
            int randomNum = random.nextInt(i);
            Comparable temp = array[randomNum];
            array[randomNum] = array[i];
            array[i] = temp;
        }
    }

    public static void Shuffle(Comparable[] array) {
        KnuthShuffle ks = new KnuthShuffle();
        ks.shuffle(array);
    }

    public static void main(String[] args) {
        Integer[] sorted = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        KnuthShuffle ks = new KnuthShuffle();
        ks.shuffle(sorted);
        System.out.println(Arrays.toString(sorted));
    }
}
