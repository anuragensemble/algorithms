package datastructures.graphs.undirected;

import java.util.Arrays;
import java.util.Stack;

public class DepthFirstSearchIterative {

    private boolean[] marked;
    private int[] edgeTo;
    int source;

    DepthFirstSearchIterative(Graph g, int source) {
        this.source = source;
        int noOfVertices = g.V;
        marked = new boolean[noOfVertices];
        edgeTo = new int[noOfVertices];

        for (int i = 0; i < noOfVertices; i++) {
            marked[i] = false;
            edgeTo[i] = -1;
        }
        dfsIterative(g);
    }

    private void dfsIterative(Graph g) {
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        edgeTo[source] = source;
        int currentNode;
        while (!stack.isEmpty()) {
            currentNode = stack.pop();
            if (!marked[currentNode]) {
                marked[currentNode] = true;
            }
            for (int adjacent : g.adj(currentNode)) {
                if (!marked[adjacent]) {
                    stack.push(adjacent);
                    edgeTo[adjacent] = currentNode;
                }
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public String path(int vertex) {
        if (hasPathTo(vertex)) {
            StringBuilder pathStr = new StringBuilder();
            pathStr.append(vertex);
            int from = edgeTo[vertex];
            while (from != source) {
                pathStr.insert(0, from + " -----> ");
                from = edgeTo[from];
            }
            pathStr.insert(0, source + " -----> ");
            return pathStr.toString();
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

        DepthFirstSearchIterative dfs = new DepthFirstSearchIterative(graph, 0);
        System.out.println(Arrays.toString(dfs.marked));
        System.out.println(Arrays.toString(dfs.edgeTo));
        String pathPlaceholder = "%s is connected to %s : %s. Path: %s";
        System.out.println(String.format(pathPlaceholder, dfs.source, 10, dfs.hasPathTo(10), dfs.path(10)));
        System.out.println(String.format(pathPlaceholder, dfs.source, 3, dfs.hasPathTo(3), dfs.path(3)));
    }
}
