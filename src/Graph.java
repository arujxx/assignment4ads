import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private HashMap<Integer, Vertex> vertices;
    private HashMap<Integer, ArrayList<Integer>> adjacencyList;

    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        vertices.put(v.getId(), v);
        adjacencyList.put(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        if (vertices.containsKey(from) && vertices.containsKey(to)) {
            adjacencyList.get(from).add(to);


            adjacencyList.get(to).add(from);
        }
    }

    public void printGraph() {
        System.out.println("Graph adjacency list:");

        for (int vertex : adjacencyList.keySet()) {
            System.out.print(vertex + ": ");

            ArrayList<Integer> neighbors = adjacencyList.get(vertex);

            for (int neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }

            System.out.println();
        }
    }

    public void bfs(int start) {
        HashMap<Integer, Boolean> visited = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int vertex : vertices.keySet()) {
            visited.put(vertex, false);
        }

        visited.put(start, true);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.remove();
            System.out.print(current + " ");

            ArrayList<Integer> neighbors = adjacencyList.get(current);

            for (int neighbor : neighbors) {
                if (!visited.get(neighbor)) {
                    visited.put(neighbor, true);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println();
    }

    public void dfs(int start) {
        HashMap<Integer, Boolean> visited = new HashMap<>();

        for (int vertex : vertices.keySet()) {
            visited.put(vertex, false);
        }

        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int current, HashMap<Integer, Boolean> visited) {
        visited.put(current, true);
        System.out.print(current + " ");

        ArrayList<Integer> neighbors = adjacencyList.get(current);

        for (int neighbor : neighbors) {
            if (!visited.get(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }
    public void bfsSilent(int start) {
        HashMap<Integer, Boolean> visited = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int vertex : vertices.keySet()) {
            visited.put(vertex, false);
        }

        visited.put(start, true);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.remove();

            ArrayList<Integer> neighbors = adjacencyList.get(current);

            for (int neighbor : neighbors) {
                if (!visited.get(neighbor)) {
                    visited.put(neighbor, true);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void dfsSilent(int start) {
        HashMap<Integer, Boolean> visited = new HashMap<>();

        for (int vertex : vertices.keySet()) {
            visited.put(vertex, false);
        }

        dfsSilentHelper(start, visited);
    }

    private void dfsSilentHelper(int current, HashMap<Integer, Boolean> visited) {
        visited.put(current, true);

        ArrayList<Integer> neighbors = adjacencyList.get(current);

        for (int neighbor : neighbors) {
            if (!visited.get(neighbor)) {
                dfsSilentHelper(neighbor, visited);
            }
        }
    }
}