package threesum;

import sorting.InsertionSort;

import java.util.*;

public class ThreeSumWithExtraSpace {
    public static int[][] threeSum(int[] integers) {
        HashMap<Integer, Integer> firstElem = new HashMap<>();
        int N = integers.length;
        for (int i = 0; i < integers.length; i++) {
            firstElem.put(integers[i], i); // [-1, 0, 1, 2, -4]
        }
//        InsertionSort.sort(integers);

        Set<String> tripletsHash = new HashSet<>();
        for (int i=0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int complement = -(integers[i] + integers[j]);
                if (firstElem.containsKey(complement) && firstElem.get(complement) > j) { // Comparing the index of complement to be > j to avoid duplication between triplet members
                    Integer[] tripletArray = new Integer[]{complement, integers[i], integers[j]};
                    InsertionSort.Sort(tripletArray);
                    tripletsHash.add(tripletArray[0] + "," + tripletArray[1] + "," + tripletArray[2]);
                }
            }
        }

        List<String> tripletsList = new ArrayList<>(tripletsHash);
        int[][] triplets = new int[tripletsList.size()][];
        for (int i = 0; i < tripletsList.size(); i++) {
            String[] elems = tripletsList.get(i).split(",");
            triplets[i] = new int[3];
            for (int j = 0; j < elems.length; j++) {
                triplets[i][j] = Integer.parseInt(elems[j]);
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        int[] testCase = {-1,0,1,2,-1,-4};
        int[][] triplets = ThreeSumWithExtraSpace.threeSum(testCase);
        System.out.println(Arrays.deepToString(triplets));

        testCase = new int[]{0, 1, 1};
        triplets = ThreeSumWithExtraSpace.threeSum(testCase);
        System.out.println(Arrays.deepToString(triplets));

        testCase = new int[]{0, 0, 0};
        triplets = ThreeSumWithExtraSpace.threeSum(testCase);
        System.out.println(Arrays.deepToString(triplets));
    }
}
