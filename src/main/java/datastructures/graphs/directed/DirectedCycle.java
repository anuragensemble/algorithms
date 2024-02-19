package datastructures.graphs.directed;

import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;

    private Stack<Integer> cycle;

    public DirectedCycle(Digraph digraph) {
        marked = new boolean[digraph.V()];
        onStack = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        for (int i=0; i< digraph.V(); i++) {
            marked[i] = false;
            edgeTo[i] = -1;
            onStack[i] = false;
        }

        for (int i=0; i<digraph.V(); i++) {
            if(!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(Digraph graph, int vertex) {
        marked[vertex] = true;
        onStack[vertex] = true;

        for (int adjacent : graph.adj(vertex)) {
            if (cycle != null) {
                return;
            }
            if (!marked[adjacent]) {
                edgeTo[adjacent] = vertex;
                dfs(graph, adjacent);
            } else if (onStack[adjacent]) {
                cycle = new Stack<>();
                for (int x = vertex; x != adjacent; x=edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(adjacent);
                cycle.push(vertex);
            }
        }
        onStack[vertex] = false;
    }

    private boolean hasCycle() {
        return cycle != null;
    }

    private Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(13);
        graph.addEdge(0,1);
        graph.addEdge(0,5);
//        graph.addEdge(2,0);
//        graph.addEdge(2,3);
//        graph.addEdge(3,2);
        graph.addEdge(3,5);
        graph.addEdge(4,2);
//        graph.addEdge(4,3);
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
        System.out.println(graph);
        DirectedCycle directedCycle = new DirectedCycle(graph);
        System.out.println("Has Cycle : " + directedCycle.hasCycle());
        System.out.println("Cycle : " + directedCycle.cycle());
    }
}
