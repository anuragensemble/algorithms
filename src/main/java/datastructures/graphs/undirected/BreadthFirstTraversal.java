package datastructures.graphs.undirected;

import datastructures.queues.QueueLinkedList;

import java.util.Arrays;

public class BreadthFirstTraversal {
    private boolean[] marked;
    private int[] edgeTo;
    private int source;

    BreadthFirstTraversal(Graph graph, int source) {
        this.source = source;
        marked = new boolean[graph.V];
        edgeTo = new int[graph.V];

        for (int i=0; i < graph.V; i++) {
            marked[i] = false;
            edgeTo[i] = -1;
        }
        edgeTo[source] = source;
        bfs(graph, source);
    }

    private void bfs(Graph graph, int vertex) {
        QueueLinkedList<Integer> queue = new QueueLinkedList<>();
        queue.enqueue(vertex);
        marked[vertex] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    queue.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                }
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

        BreadthFirstTraversal bfs = new BreadthFirstTraversal(graph, 0);
        System.out.println(Arrays.toString(bfs.marked));
        System.out.println(Arrays.toString(bfs.edgeTo));
        String pathPlaceholder = "%s is connected to %s : %s. Path: %s";
        System.out.println(String.format(pathPlaceholder, bfs.source, 10, bfs.hasPathTo(10), bfs.path(10)));
        System.out.println(String.format(pathPlaceholder, bfs.source, 6, bfs.hasPathTo(6), bfs.path(6)));
    }
}
