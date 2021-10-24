package CliqueFinder.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private final List<Vertex> vertices;
    private final List<Edge> edges;
    private final Map<Vertex, List<Vertex>> adjacencyMap;
    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.adjacencyMap = new HashMap<>();
    }

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this();
        vertices.forEach(this::addVertex);
        edges.forEach(this::addEdge);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Add non-duplicated vertices.
     *
     * @param vertex in a graph
     */
    public void addVertex(Vertex vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
    }

    /**
     * Add edges with a valid source and destination vertex.
     * Also ensure that there are no duplicate edges.
     *
     * @param edge of a graph
     */
    public void addEdge(Edge edge) {
        if (!edges.contains(edge) && vertices.contains(edge.getSource()) && vertices.contains(edge.getDestination())) {
            edges.add(edge);

            if (!adjacencyMap.containsKey(edge.getSource())) {
                adjacencyMap.put(edge.getSource(), new LinkedList<>());
            }

            if (!adjacencyMap.containsKey(edge.getDestination())) {
                adjacencyMap.put(edge.getDestination(), new LinkedList<>());
            }

            adjacencyMap.get(edge.getSource()).add(edge.getDestination());
            adjacencyMap.get(edge.getDestination()).add(edge.getSource());
        }
    }

    /**
     * Get a list collection of edges that satisfy the given weight threshold.
     * @param weight weight threshold
     * @return weight filtered edges
     */
    public List<Edge> getFilteredEdgesByWeight(double weight) {
        return edges
                .stream()
                .filter(edge -> edge.getWeight() >= weight)
                .collect(Collectors.toList());
    }

    /**
     * Get an adjacencyMap with edges that satisfy the weight threshold given.
     *
     * @param weight weight threshold
     * @return filtered adjacency map
     */
    public Map<Vertex, List<Vertex>> getAdjacencyMapWithThreshold(double weight) {
        Map<Vertex, List<Vertex>> map = new HashMap<>();

        getFilteredEdgesByWeight(weight).forEach(edge -> {
            if (!map.containsKey(edge.getSource())) {
                map.put(edge.getSource(), new LinkedList<>());
            }

            if (!map.containsKey(edge.getDestination())) {
                map.put(edge.getDestination(), new LinkedList<>());
            }

            map.get(edge.getSource()).add(edge.getDestination());
            map.get(edge.getDestination()).add(edge.getSource());
        });

        return map;
    }

    public List<Edge> getEdges() { return edges; }
    public Map<Vertex, List<Vertex>> getAdjacencyMap() { return adjacencyMap; }

    /**
     * Deep clone graph so it and all its objects uses a different reference.
     *
     * @return deep cloned graph.
     */
    public Graph getCopy() {
        List<Vertex> clonedVertices = vertices
                .stream()
                .map(Vertex::getCopy)
                .collect(Collectors.toList());
        List<Edge> clonedEdges = edges
                .stream()
                .map(Edge::getCopy)
                .collect(Collectors.toList());
        return new Graph(clonedVertices, clonedEdges);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Graph:\n");
        stringBuilder.append("  Vertices: ");
        stringBuilder.append(vertices);
        stringBuilder.append("\n  Edges:\n");
        edges.forEach(edge -> stringBuilder.append("    * ").append(edge).append("\n"));
        return stringBuilder.toString();
    }
}