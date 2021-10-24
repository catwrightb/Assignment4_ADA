package CliqueFinder.Graph;

import java.util.Objects;

public class Edge  {
    private final Vertex source;
    private final Vertex destination;
    private final Double weight;

    public Edge(Vertex source, Vertex destination, Double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    public Double getWeight() {
        return weight;
    }

    /**
     * Edge A to B == Edge B to A to cut down edges by half.
     * Does not take into account the weight as there will be no duplicate edges in our graph anyway.
     * Hence, it uses the weight of the first non-duplicated edge.
     * @param o is the object to compare
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (source.equals(edge.source) && destination.equals(edge.destination))
                || (source.equals(edge.destination) && destination.equals(edge.source));
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, weight);
    }

    @Override
    public String toString() {
        return source + " - " + destination + " (" + weight +")";
    }

    /**
     * Clone current edge so it uses a different reference
     * @return cloned edge
     */
    public Edge getCopy() { return new Edge(source.getCopy(), destination.getCopy(), weight); }
}