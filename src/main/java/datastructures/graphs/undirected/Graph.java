package datastructures.graphs.undirected;

import datastructures.bag.Bag;

public class Graph {
    public final int V;
    private Bag<Integer>[] adj;

    public Graph(int v) {
        this.V = v;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.adj.length; i++) {
            result.append(String.format("%s ---- %s\n", i, this.adj[i].toString()));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(5,7);
        System.out.println(graph);
    }
}
