package Graph;

import java.util.List;
import java.util.stream.Collectors;

public class Graph {
    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertices = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Graph getCopy() {
        List<Vertex> clonedVertices =  vertices
                .stream()
                .map(vertex -> vertex.getCopy())
                .collect(Collectors.toList());
        List<Edge> clonedEdges = edges
                .stream()
                .map(edge -> edge.getCopy())
                .collect(Collectors.toList());
        return new Graph(clonedVertices, clonedEdges);
    }
}