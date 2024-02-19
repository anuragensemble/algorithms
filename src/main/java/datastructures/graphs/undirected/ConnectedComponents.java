package datastructures.graphs.undirected;

import datastructures.queues.QueueLinkedList;

import java.util.Arrays;

public class ConnectedComponents {
    private boolean[] marked;
    private int[] ids;

    private int count;

    ConnectedComponents(Graph graph) {
        marked = new boolean[graph.V];
        ids = new int[graph.V];
        count = 0;

        for (int i = 0; i < graph.V; i++) {
            marked[i] = false;
            ids[i] = -1;
        }

        for (int i = 0; i < graph.V; i++) {
            if (!marked[i]) {
//                dfs(graph, i, count);
                bfs(graph, i, count);
                count++;
            }
        }
    }

    private int noOfConnectedComponents() {
        return count;
    }

    private void dfs(Graph graph, int vertex, int id) {
        marked[vertex] = true;
        ids[vertex] = id;
        for (int w : graph.adj(vertex)) {
            if (!marked[w]) {
                dfs(graph, w, id);
            }
        }
    }

    private void bfs(Graph graph, int vertex, int id) {
        System.out.println("====== BFS called for " + vertex);
        QueueLinkedList<Integer> queue = new QueueLinkedList<>();
        queue.enqueue(vertex);
        marked[vertex] = true;
        ids[vertex] = id;

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    queue.enqueue(w);
                    marked[w] = true;
                    ids[w] = id;
                }
            }
//            System.out.printf("======== Vertex : %s. Marked: %s%n", v, Arrays.toString(marked));
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 6);
        graph.addEdge(0, 5);
        graph.addEdge(6, 4);
        graph.addEdge(4, 3);
        graph.addEdge(4, 5);
        graph.addEdge(3, 5);
        graph.addEdge(7, 8);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(9, 12);
        graph.addEdge(11, 12);
        System.out.println(graph);

        ConnectedComponents cc = new ConnectedComponents(graph);
        System.out.println(Arrays.toString(cc.marked));
        System.out.println(Arrays.toString(cc.ids));
        System.out.println(cc.noOfConnectedComponents());
    }
}
