package datastructures.graphs.directed;

import datastructures.bag.Bag;

import java.util.Iterator;
import java.util.LinkedList;

public class Digraph {
    private LinkedList<Integer>[] adjacencyList;
    private int vertices;
    private int edges;

    public Digraph(int V) {
        vertices = V;
        adjacencyList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adjacencyList[v].add(w);
        edges += 1;
    }

    public Iterable<Integer> adj(int v) {
        return adjacencyList[v];
    }

    public int V() {
        return vertices;
    }

    public int E() {
        return edges;
    }

    public Digraph reverse() {
        Digraph reversed = new Digraph(vertices);
        for (int i =0; i < vertices; i++) {
            for (int adjacent : adjacencyList[i]) {
                reversed.addEdge(adjacent, i);
            }
        }
        return reversed;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < vertices; i++) {
            string.append(i + ":" + adjacencyList[i] + "\n");
        }
        return string.toString();
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(13);
        graph.addEdge(0,1);
        graph.addEdge(0,5);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(3,2);
        graph.addEdge(3,5);
        graph.addEdge(4,2);
        graph.addEdge(4,3);
        graph.addEdge(5,4);
        graph.addEdge(6,0);
        graph.addEdge(6,4);
        graph.addEdge(6,8);
        graph.addEdge(6,9);
        graph.addEdge(7,6);
        graph.addEdge(7,9);
        graph.addEdge(8,6);
        graph.addEdge(9,10);
        graph.addEdge(9,11);
        graph.addEdge(10,12);
        graph.addEdge(11,4);
        graph.addEdge(11,12);
        graph.addEdge(12,9);
        System.out.println(graph.edges);
        System.out.println(graph);
        Digraph reversed = graph.reverse();
        System.out.println(reversed.edges);
        System.out.println(reversed);
    }
}
