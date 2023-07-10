package org.ensemble.algorithms.dynamicconnectivity;

import java.util.Arrays;

public class WeightedQuickUnionPC extends WeightedQuickUnion {
    WeightedQuickUnionPC(int number) {
        super(number);
    }

    int root(int idx) {
        while (nodes[idx] != idx) {
            nodes[idx] = nodes[nodes[idx]]; // Grandparent
            idx = nodes[idx];
        }
        return idx;
    }

    public static void main (String[] args) {
        // Initialize 10 nodes
        WeightedQuickUnionPC wqu = new WeightedQuickUnionPC(10);
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
