package datastructures.graphs.directed;

import java.util.Arrays;

public class DFS {
    private boolean[] marked;
    private int[] edgeTo;
    private int source;

    public DFS(Digraph graph, int source) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.source = source;
        for (int i =0; i< graph.V(); i++) {
            marked[i] = false;
            edgeTo[i] = -1;
        }
        edgeTo[source] = source;
        dfs(graph, source);
    }

    private void dfs(Digraph graph, int vertex) {
        marked[vertex] = true;
        for (int adjacent : graph.adj(vertex)) {
            if (!marked[adjacent]) {
                dfs(graph, adjacent);
                edgeTo[adjacent] = vertex;
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public String path(int vertex) {
        if (hasPathTo(vertex)) {
            StringBuilder path = new StringBuilder();
            path.append(vertex);
            int x = edgeTo[vertex];
            while (x != source) {
                path.insert(0,x + " -----> ");
                x = edgeTo[x];
            }
            path.insert(0, source + " -----> ");
            return path.toString();
        }
        return null;
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
        System.out.println(graph);

        DFS dfs = new DFS(graph, 0);
        System.out.println(Arrays.toString(dfs.marked));
        System.out.println(dfs.path(1));
        System.out.println(dfs.path(2));
        System.out.println(dfs.path(3));
        System.out.println(dfs.path(4));
        System.out.println(dfs.path(5));
        System.out.println(dfs.path(6));
    }
}
