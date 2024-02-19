package datastructures.graphs.undirected;

import java.util.Arrays;

public class DepthFirstTraversal {
    private boolean[] marked;
    public int[] edgeTo;
    private int source;

    public DepthFirstTraversal(Graph graph, int source) {
        this.source = source;
        marked = new boolean[graph.V];
        edgeTo = new int[graph.V];
        for (int i = 0; i < graph.V; i++) {
            edgeTo[i] = -1;
        }
        edgeTo[source] = source;
        dfs(graph, source);
    }

    private void dfs(Graph graph, int vertex) {
        System.out.println(vertex);
        marked[vertex] = true;
        for (int w: graph.adj(vertex)) {
            if (!marked[w]) {
                dfs(graph, w);
                edgeTo[w] = vertex;
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public String path(int v) {
        if (hasPathTo(v)) {
            StringBuilder path = new StringBuilder();
            path.append(v);
            int x = edgeTo[v];
            while (x != source) {
                path.append("<------");
                path.append(x);
                x = edgeTo[x];
            }
            path.append("<------").append(source);
            return path.toString();
        }
        return null;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,6);
        graph.addEdge(0,5);
        graph.addEdge(6,4);
        graph.addEdge(4,3);
        graph.addEdge(4,5);
        graph.addEdge(3,5);
        graph.addEdge(7,8);
        graph.addEdge(9,10);
        graph.addEdge(9,11);
        graph.addEdge(9,12);
        graph.addEdge(11,12);
        System.out.println(graph);

        DepthFirstTraversal dfs = new DepthFirstTraversal(graph, 0);
        System.out.println(Arrays.toString(dfs.marked));
        System.out.println(Arrays.toString(dfs.edgeTo));
        String pathPlaceholder = "%s is connected to %s : %s. Path: %s";
        System.out.println(String.format(pathPlaceholder, dfs.source, 10, dfs.hasPathTo(10), dfs.path(10)));
        System.out.println(String.format(pathPlaceholder, dfs.source, 6, dfs.hasPathTo(6), dfs.path(6)));
    }
}
