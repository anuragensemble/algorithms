package org.ensemble.algorithms.dynamicconnectivity;

import java.util.Arrays;

public class QuickUnion extends DynamicConnectivity{
    QuickUnion(int number) {
        super(number);
    }

    @Override
    public void union(int a, int b) {
        int t1 = root(a);
        int t2 = root(b);
        // We arbitrarily chose to make t1 as the root of t2
        nodes[t2] = t1;
    }

    @Override
    public boolean connected(int a, int b) {
        return root(a) == root(b);
        // O(N) time complexity: cost of finding roots But iterations/updates reduced than union() of QuickFind
    }

    int root(int idx) {
        while (nodes[idx] != idx) {
            idx = nodes[idx];
        }
        return idx;
    }

    public static void main (String[] args) {
        // Initialize 10 nodes
        QuickUnion qu = new QuickUnion(10);
        System.out.println(qu.connected(1, 5));
        qu.union(1,5);
        qu.union(5, 9);
        qu.union(8,7);
        qu.union(8,9);
        System.out.println(qu.connected(1, 5));
        System.out.println(qu.connected(1, 9));
        System.out.println(qu.connected(7, 8));
        System.out.println(qu.connected(7, 1));
        System.out.println(qu.connected(5, 9));
        System.out.println(Arrays.toString(qu.nodes));
    }
}
