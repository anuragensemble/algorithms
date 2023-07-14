package threesum;

import sorting.InsertionSort;

import java.util.*;

public class ThreeSumSlidingPointers {
    public static int[][] threeSum(int[] integers) {
        InsertionSort.sort(integers); // [-4, -1, -1, 0 , 1, 2]
        int N = integers.length;

        Set<String> tripletsHash = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int left = i + 1;
            int right = N - 1;
            while (left < right) {
                int complement = integers[left] + integers[right];
                if (-integers[i] == complement) {
                    tripletsHash.add(integers[i] + "," + integers[left] + "," + integers[right]);
                    left++;
                    right--;
                } else if (-integers[i] > complement) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        List<String> triplets = new ArrayList<>(tripletsHash);

        int[][] result = new int[triplets.size()][3];
        for (int i = 0; i < triplets.size(); i++) {
            String[] elems = triplets.get(i).split(",");
            for (int j = 0; j < elems.length; j++) {
                result[i][j] = Integer.parseInt(elems[j]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] testCase = {-1,0,1,2,-1,-4};
        int[][] triplets = ThreeSumSlidingPointers.threeSum(testCase);
        System.out.println(Arrays.deepToString(triplets));

        testCase = new int[]{0, 1, 1};
        triplets = ThreeSumSlidingPointers.threeSum(testCase);
        System.out.println(Arrays.deepToString(triplets));

        testCase = new int[]{0, 0, 0};
        triplets = ThreeSumSlidingPointers.threeSum(testCase);
        System.out.println(Arrays.deepToString(triplets));
    }
}
