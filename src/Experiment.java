public class Experiment {
    private long smallBfsTime;
    private long smallDfsTime;

    private long mediumBfsTime;
    private long mediumDfsTime;

    private long largeBfsTime;
    private long largeDfsTime;

    public void runTraversals(Graph g) {
        System.out.print("BFS order: ");
        g.bfs(0);

        System.out.print("DFS order: ");
        g.dfs(0);
    }

    public void runMultipleTests() {
        Graph smallGraph = createGraph(10);
        Graph mediumGraph = createGraph(30);
        Graph largeGraph = createGraph(100);

        System.out.println("SMALL GRAPH");
        smallGraph.printGraph();
        runTraversals(smallGraph);

        smallBfsTime = measureBfsTime(smallGraph);
        smallDfsTime = measureDfsTime(smallGraph);

        mediumBfsTime = measureBfsTime(mediumGraph);
        mediumDfsTime = measureDfsTime(mediumGraph);

        largeBfsTime = measureBfsTime(largeGraph);
        largeDfsTime = measureDfsTime(largeGraph);

        printResults();
    }

    private Graph createGraph(int size) {
        Graph graph = new Graph();

        for (int i = 0; i < size; i++) {
            graph.addVertex(new Vertex(i));
        }

        for (int i = 0; i < size - 1; i++) {
            graph.addEdge(i, i + 1);
        }

        for (int i = 0; i < size - 2; i = i + 2) {
            graph.addEdge(i, i + 2);
        }

        return graph;
    }

    private long measureBfsTime(Graph graph) {
        long start = System.nanoTime();
        graph.bfsSilent(0);
        long end = System.nanoTime();

        return end - start;
    }

    private long measureDfsTime(Graph graph) {
        long start = System.nanoTime();
        graph.dfsSilent(0);
        long end = System.nanoTime();

        return end - start;
    }

    public void printResults() {
        System.out.println();
        System.out.println("Performance results:");
        System.out.println("10 vertices BFS: " + smallBfsTime + " ns");
        System.out.println("10 vertices DFS: " + smallDfsTime + " ns");
        System.out.println("30 vertices BFS: " + mediumBfsTime + " ns");
        System.out.println("30 vertices DFS: " + mediumDfsTime + " ns");
        System.out.println("100 vertices BFS: " + largeBfsTime + " ns");
        System.out.println("100 vertices DFS: " + largeDfsTime + " ns");
    }
}