package org.ensemble.algorithms.dynamicconnectivity;

public abstract class DynamicConnectivity {
    int[] nodes;

    DynamicConnectivity(int number) {
        nodes = new int[number];
        int i = 0;
        while (i < number) {
            nodes[i] = i;
            i++;
        }
        // O(N) time complexity to initialize 'N' nodes
        // O(N) space complexity to store 'N' nodes
    }

    public abstract void union(int a, int b);
    public abstract boolean connected(int a, int b);
}
