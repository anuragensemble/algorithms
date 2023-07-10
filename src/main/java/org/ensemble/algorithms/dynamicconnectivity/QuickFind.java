package org.ensemble.algorithms.dynamicconnectivity;

import java.util.Arrays;

public class QuickFind extends DynamicConnectivity{
    QuickFind(int number) {
        super(number);
    }

    @Override
    public void union(int a, int b) {
        // We arbitrarily decide to update b and all children of b to point to a
        if (!connected(a, b)) {
            int idx = 0;
            int aid = nodes[a]; // These can change in the loop. Be careful to extract these before loop.
            int bid = nodes[b];
            while(idx < nodes.length) {
                if (nodes[idx] == bid) {
                    nodes[idx] = aid;
                }
                idx++;
            }
        }
        // O(N) time complexity to connect 2 nodes
        // The system will constitute a forest of trees with at most 2 length, with each parent node having direct children.
    }

    @Override
    public boolean connected(int a, int b) {
        return nodes[a] == nodes[b];
        // O(1) time complexity for checking if 2 nodes are connected
    }

    public static void main (String[] args) {
        // Initialize 10 nodes
        QuickFind qf = new QuickFind(10);
        System.out.println(qf.connected(1, 5));
        qf.union(1,5);
        qf.union(5, 9);
        qf.union(8,7);
        qf.union(8,9);
        System.out.println(qf.connected(1, 5));
        System.out.println(qf.connected(1, 9));
        System.out.println(qf.connected(7, 8));
        System.out.println(qf.connected(7, 1));
        System.out.println(qf.connected(5, 9));
        System.out.println(Arrays.toString(qf.nodes));
    }
}
