package AssociateFinder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Double> distance;
    public Double total = 0.0;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    /*
    * processes the graph with a source vertex passed in
    * */
    public void processGraph(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Double>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0.0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinDistances(node);
        }
    }

    /*
    * finds the minDistance between the node and its adjacent nodes using a for loop
    * */
    private void findMinDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target))
            {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    /*
    * gets distance of edge between the two nodes
    * returns as a -log
    * */
    private Double getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return -(Math.log(edge.getWeight()));
            }
        }
        throw new RuntimeException("Should not happen");
    }


    /*
    * gets the neighbors to node
    * */
    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isVertexSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    /*
    * checks if vertex has been settled or not by chekcing settledNode list
    * */
    private boolean isVertexSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }


    /*
    * gets the shortest distance to destination
    * */
    private Double getShortestDistance(Vertex destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists at all
     */
    public LinkedList<Vertex> getPathBetweenNodes(Vertex target, Vertex start) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        Double d = 0.0;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            Vertex o = step;
            step = predecessors.get(step);
            path.add(step);
            d = getDistance(o,step);
            total += d;
        }
        // Put it into the correct order
        Collections.reverse(path);
        System.out.printf("Distance from " + start.getName() + " to " + target.getName() + ": %.3f %n",  total);

        return path;
    }

}