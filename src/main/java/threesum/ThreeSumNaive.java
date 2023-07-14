package threesum;

import sorting.InsertionSort;

import java.util.*;

public class ThreeSumNaive {
    public static int[][] threeSum(int[] integers) {
        int[][] result = new int[][]{};
        Set<String> triplets = new HashSet<>();
        int N = integers.length;
        InsertionSort.sort(integers);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    if (integers[i] + integers[j] + integers[k] == 0) {
                        triplets.add(integers[i] + " " + integers[j] + " " + integers[k]);
                    }
                }
            }
        }

        List<String> tripletsList = new ArrayList<>(triplets);
        result = new int[tripletsList.size()][];
        for (int t = 0; t < tripletsList.size(); t++) {
            String[] elems = tripletsList.get(t).split(" ");
            result[t] = new int[3];
            for (int e = 0; e < 3; e++) {
                result[t][e] = Integer.parseInt(elems[e]);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] testCase = {-1,0,1,2,-1,-4};
        int[][] triplets = ThreeSumNaive.threeSum(testCase);
        System.out.println(Arrays.deepToString(triplets));

        testCase = new int[]{0, 1, 1};
        triplets = ThreeSumNaive.threeSum(testCase);
        System.out.println(Arrays.deepToString(triplets));

        testCase = new int[]{0, 0, 0};
        triplets = ThreeSumNaive.threeSum(testCase);
        System.out.println(Arrays.deepToString(triplets));
    }
}
