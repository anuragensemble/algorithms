package datastructures.graphs.directed;

import java.util.Stack;

public class TopologicalSort {
    private boolean[] marked;
    private Stack<Integer> dfsOrder;

    public TopologicalSort(Digraph graph) {
        marked = new boolean[graph.V()];
        dfsOrder = new Stack<>();
        for (int i = 0; i < graph.V(); i++) {
            marked[i] = false;
        }
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    public void dfs(Digraph graph, int vertex) {
        marked[vertex] = true;
        for (int adjacent: graph.adj(vertex)) {
            if (!marked[adjacent]) {
                dfs(graph, adjacent);
            }
        }
        dfsOrder.push(vertex);
    }

    public Iterable<Integer> topologicalOrder() {
        return dfsOrder;
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(7);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,5);
        graph.addEdge(1,4);
        graph.addEdge(3,2);
        graph.addEdge(3,4);
        graph.addEdge(3,5);
        graph.addEdge(3,6);
        graph.addEdge(5,2);
        graph.addEdge(6,0);
        graph.addEdge(6,4);
        System.out.println(graph);
        TopologicalSort ts = new TopologicalSort(graph);
        System.out.println(ts.topologicalOrder());
    }
}
