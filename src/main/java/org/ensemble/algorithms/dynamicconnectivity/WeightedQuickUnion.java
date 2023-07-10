package org.ensemble.algorithms.dynamicconnectivity;

import java.util.Arrays;

public class WeightedQuickUnion extends QuickUnion {
    int[] size;
    WeightedQuickUnion(int number) {
        super(number);
        size = new int[number];
        for (int idx = 0; idx < number; idx++) {
            size[idx] = 1;
        }
    }

    public void union(int a, int b) {
        int t1 = root(a);
        int t2 = root(b);

        if (size[t1] >= size[t2]) {
            nodes[t2] = t1;
            size[t1] += size[t2];
        } else {
            nodes[t1] = t2;
            size[t2] += size[t1];
        }
        // This ensures that the subsequent tree has a height of <= log(N). Hence the cost of finding roots is O(logN)
        // Time complexity is O(logN)
    }

    public static void main (String[] args) {
        // Initialize 10 nodes
        WeightedQuickUnion wqu = new WeightedQuickUnion(10);
        System.out.println(wqu.connected(1, 5));
        wqu.union(1,5);
        wqu.union(5, 9);
        wqu.union(8,7);
        wqu.union(8,9);
        System.out.println(wqu.connected(1, 5));
        System.out.println(wqu.connected(1, 9));
        System.out.println(wqu.connected(7, 8));
        System.out.println(wqu.connected(7, 1));
        System.out.println(wqu.connected(5, 9));
        System.out.println(Arrays.toString(wqu.nodes));
    }
}
