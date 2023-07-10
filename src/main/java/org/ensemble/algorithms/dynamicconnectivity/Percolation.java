package org.ensemble.algorithms.dynamicconnectivity;

import java.util.*;

public class Percolation extends WeightedQuickUnionPC{
    Set<Integer> openSites;
    int gridDimension;
    Percolation(int n) {
        super((n*n) + 2);
        // Initialize grid of nodes
        nodes[0] = 0;
        for (int i=1; i < nodes.length; i++) {
            if (i <= n) {
                // First row. Set the root as 0th virtual node
                nodes[i] = nodes[0];
            } else if (i > ((n*n) - n) && i <= (n*n)) {
                nodes[i] = (n*n) + 1;
            } else {
                nodes[i] = i;
            }
        }
        gridDimension = n;
        openSites = new HashSet<>();
    }

    void openSite(int node) {
        if (node <= 0 || node >= (nodes.length - 1)) {
            return;
        }
        if (openSites.contains(node)) {
            // Already open
            return;
        }
        openSites.add(node);
        // Union with any open sites: [top, bottom, left, right]
        // First row (node [1:n+1]) will connect with left, right, and bottom
        // Last row (node[n*n - n: n*n + 1]) will connect to left, right and top
        // First column will connect with right, top, and bottom
        // Last column will connect with left, top, and bottom

        int top = node - gridDimension;
        int bottom = node + gridDimension;
        int left = node - 1;
        int right = node + 1;

        Map<String, Runnable> taskMap = new HashMap<>();
        taskMap.put("top", () -> checkDimensionsAndUnionWithOpenSite(node, top));
        taskMap.put("bottom", () -> checkDimensionsAndUnionWithOpenSite(node, bottom));
        taskMap.put("left", () -> checkDimensionsAndUnionWithOpenSite(node, left));
        taskMap.put("right", () -> checkDimensionsAndUnionWithOpenSite(node, right));

        if (node % gridDimension == 0) {
            // Right most column
            taskMap.remove("right");
        } else if (node % gridDimension == 1) {
            // Left most column
            taskMap.remove("left");
        }

        for (Map.Entry<String, Runnable> f : taskMap.entrySet()) {
            Runnable task = f.getValue();
            task.run();
        }
    }

    private void checkDimensionsAndUnionWithOpenSite(int node, int adjacent) {
        if (adjacent > 0 && adjacent < nodes.length - 1) {
            // Union with other open adjacent sites
            if (openSites.contains(adjacent)) {
                union(node, adjacent);
            }
        }
    }

    private int getRandomNode(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) {
        int gridSize = 1000;
        int simulations = 3;
        double pThresh = 0.0;
        for (int i = 0; i < simulations; i++) {
            Percolation pc = new Percolation(gridSize);
            System.out.println("Simulation " + (i+1) + "...");
            while (!pc.connected(0, (gridSize*gridSize) + 1)) {
                int randomNode = pc.getRandomNode(1, pc.gridDimension*pc.gridDimension);
                pc.openSite(randomNode);
            }
            double percolationThreshold = (double) pc.openSites.size() / (pc.gridDimension*pc.gridDimension);
            pThresh += percolationThreshold;
            System.out.println("Percolation threshold : " + percolationThreshold);
        }

        System.out.println("Average percolation threshold " + pThresh / simulations);
    }
}
