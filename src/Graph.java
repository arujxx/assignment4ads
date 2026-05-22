import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private HashMap<Integer, Vertex> vertices;
    private HashMap<Integer, ArrayList<Edge>> adjacencyList;

    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        vertices.put(v.getId(), v);
        adjacencyList.put(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }

    public void addEdge(int from, int to, int weight) {
        if (vertices.containsKey(from) && vertices.containsKey(to)) {
            Vertex source = vertices.get(from);
            Vertex destination = vertices.get(to);

            Edge edge1 = new Edge(source, destination, weight);
            Edge edge2 = new Edge(destination, source, weight);

            adjacencyList.get(from).add(edge1);
            adjacencyList.get(to).add(edge2);
        }
    }

    public void printGraph() {
        System.out.println("Graph adjacency list:");

        for (int vertex : adjacencyList.keySet()) {
            System.out.print(vertex + ": ");

            ArrayList<Edge> edges = adjacencyList.get(vertex);

            for (Edge edge : edges) {
                System.out.print(edge.getDestination().getId() + "(" + edge.getWeight() + ") ");
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

            ArrayList<Edge> edges = adjacencyList.get(current);

            for (Edge edge : edges) {
                int neighbor = edge.getDestination().getId();

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

        ArrayList<Edge> edges = adjacencyList.get(current);

        for (Edge edge : edges) {
            int neighbor = edge.getDestination().getId();

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

            ArrayList<Edge> edges = adjacencyList.get(current);

            for (Edge edge : edges) {
                int neighbor = edge.getDestination().getId();

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

        ArrayList<Edge> edges = adjacencyList.get(current);

        for (Edge edge : edges) {
            int neighbor = edge.getDestination().getId();

            if (!visited.get(neighbor)) {
                dfsSilentHelper(neighbor, visited);
            }
        }
    }

    public void dijkstra(int start) {
        HashMap<Integer, Integer> distance = new HashMap<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();

        for (int vertex : vertices.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
            visited.put(vertex, false);
        }

        distance.put(start, 0);

        for (int i = 0; i < vertices.size(); i++) {
            int current = getMinimumDistanceVertex(distance, visited);

            if (current == -1) {
                break;
            }

            visited.put(current, true);

            ArrayList<Edge> edges = adjacencyList.get(current);

            for (Edge edge : edges) {
                int neighbor = edge.getDestination().getId();
                int weight = edge.getWeight();

                if (!visited.get(neighbor) && distance.get(current) != Integer.MAX_VALUE) {
                    int newDistance = distance.get(current) + weight;

                    if (newDistance < distance.get(neighbor)) {
                        distance.put(neighbor, newDistance);
                    }
                }
            }
        }

        System.out.println("Dijkstra shortest distances from vertex " + start + ":");

        for (int vertex : distance.keySet()) {
            System.out.println(start + " -> " + vertex + " = " + distance.get(vertex));
        }
    }

    private int getMinimumDistanceVertex(HashMap<Integer, Integer> distance, HashMap<Integer, Boolean> visited) {
        int minDistance = Integer.MAX_VALUE;
        int minVertex = -1;

        for (int vertex : vertices.keySet()) {
            if (!visited.get(vertex) && distance.get(vertex) < minDistance) {
                minDistance = distance.get(vertex);
                minVertex = vertex;
            }
        }

        return minVertex;
    }
}