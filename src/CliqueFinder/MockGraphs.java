package CliqueFinder;

import Graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockGraphs {

    private static MockGraphs instance = null;
    private final List<Graph> graphs = new ArrayList<Graph>();

    private MockGraphs() {

    }

    public static MockGraphs getInstance() {
        if (instance == null) instance = new MockGraphs();
        return instance;
    }

    private void populateGraphs() {

    }

    public List<Graph> getAllGraphs() {
        return graphs
                .stream()
                .map(graph -> graph.getCopy())
                .collect(Collectors.toList());
    }

}
