/**
 * Singleton class to provide mock data for graphs.
 *
 */
package CliqueFinder;

import CliqueFinder.Graph.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockGraphs {

    private static MockGraphs instance = null;
    private final List<Graph> graphs = new ArrayList<Graph>();

    private MockGraphs() {
        populateGraphs();
    }

    public static MockGraphs getInstance() {
        if (instance == null) instance = new MockGraphs();
        return instance;
    }

    private void populateGraphs() {
        graphs.add(getFirstDataSet());
    }

    /**
     * Mock data set.
     * Majority of weight will be above 0.4
     * Maximum clique is 5 (John, Luke, Jacob, Angela, and Blake)
     *
     * Note: Some duplicate edges in this data set that should be filtered using
     * the addEdge method for Graph class as Graph class does not allow duplicates.
     * Also weights of duplicated edges are not accurate in some cases, but it will
     * not affect the Graph class as it does not allow duplicates, so it uses the weight
     * of the first non-duplicated edge it receives. This works as equals method of
     * Edge on takes into account the source and destination vertex and does not include
     * the weight.
     *
     * @return mock graph
     */
    private Graph getFirstDataSet() {
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex("John"));
        vertices.add(new Vertex("Luke"));
        vertices.add(new Vertex("Jacob"));
        vertices.add(new Vertex("Angela"));
        vertices.add(new Vertex("Blake"));
        vertices.add(new Vertex("James"));
        vertices.add(new Vertex("Joshua"));
        vertices.add(new Vertex("Hazel"));

        List<Edge> edges = new ArrayList<>();
        // Maximum clique - 5 x 1
        // John Luke Jacob Angela Blake
        edges.add(new Edge(vertices.get(0), vertices.get(1), .6));
        edges.add(new Edge(vertices.get(0), vertices.get(2), .5));
        edges.add(new Edge(vertices.get(0), vertices.get(3), .4));
        edges.add(new Edge(vertices.get(0), vertices.get(4), .5));

        edges.add(new Edge(vertices.get(1), vertices.get(0), .4));
        edges.add(new Edge(vertices.get(1), vertices.get(2), .6));
        edges.add(new Edge(vertices.get(1), vertices.get(3), .4));
        edges.add(new Edge(vertices.get(1), vertices.get(4), .7));

        edges.add(new Edge(vertices.get(2), vertices.get(1), .8));
        edges.add(new Edge(vertices.get(2), vertices.get(0), .4));
        edges.add(new Edge(vertices.get(2), vertices.get(3), .6));
        edges.add(new Edge(vertices.get(2), vertices.get(4), .4));

        edges.add(new Edge(vertices.get(3), vertices.get(1), .9));
        edges.add(new Edge(vertices.get(3), vertices.get(2), .4));
        edges.add(new Edge(vertices.get(3), vertices.get(0), .8));
        edges.add(new Edge(vertices.get(3), vertices.get(4), .7));

        edges.add(new Edge(vertices.get(4), vertices.get(1), .5));
        edges.add(new Edge(vertices.get(4), vertices.get(2), .6));
        edges.add(new Edge(vertices.get(4), vertices.get(3), .9));
        edges.add(new Edge(vertices.get(4), vertices.get(0), .8));


        // Maximal clique 3 * 2
        // Angela James Joshua
        edges.add(new Edge(vertices.get(3), vertices.get(5), .5));
        edges.add(new Edge(vertices.get(3), vertices.get(6), .6));
        edges.add(new Edge(vertices.get(5), vertices.get(3), .5));
        edges.add(new Edge(vertices.get(5), vertices.get(6), .6));
        edges.add(new Edge(vertices.get(6), vertices.get(5), .5));
        edges.add(new Edge(vertices.get(6), vertices.get(3), .6));

        // Jacob Joshua Hazel
        edges.add(new Edge(vertices.get(2), vertices.get(6), .3));
        edges.add(new Edge(vertices.get(2), vertices.get(7), .2));
        edges.add(new Edge(vertices.get(6), vertices.get(2), .3));
        edges.add(new Edge(vertices.get(6), vertices.get(7), .6));
        edges.add(new Edge(vertices.get(7), vertices.get(2), .5));
        edges.add(new Edge(vertices.get(7), vertices.get(6), .6));

        // Maximal clique 2 * 3
        // John Hazel
        edges.add(new Edge(vertices.get(0), vertices.get(7), .4));
        edges.add(new Edge(vertices.get(7), vertices.get(0), .4));

        // Blake James
        edges.add(new Edge(vertices.get(4), vertices.get(5), .5));
        edges.add(new Edge(vertices.get(5), vertices.get(4), .5));

        // Luke Joshua
        edges.add(new Edge(vertices.get(1), vertices.get(6), .2));
        edges.add(new Edge(vertices.get(6), vertices.get(1), .2));

        return new Graph(vertices, edges);
    }

    /**
     * Made a deep clone of each graph so we can reuse the data in different test cases.
     * @return deep clone of graphs
     */
    public List<Graph> getAllGraphs() {
        return graphs
                .stream()
                .map(Graph::getCopy)
                .collect(Collectors.toList());
    }

}
