package AssociateFinder;
import java.util.ArrayList;
import java.util.LinkedList;

class TestDijkstraAlgorithm {

    private static ArrayList<Vertex> nodes;
    private static ArrayList<Edge> edges;

    public static void main(String[] args) {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();


        Vertex emma = new Vertex("emma", "emma");
        nodes.add(emma);
        Vertex fred = new Vertex("fred", "fred");
        nodes.add(fred);
        Vertex dave = new Vertex("dave", "dave");
        nodes.add(dave);
        Vertex carl = new Vertex("carl", "carl");
        nodes.add(carl);
        Vertex bill = new Vertex("bill", "bill");
        nodes.add(bill);
        Vertex anna = new Vertex("anna", "anna");
        nodes.add(anna);


        addLane("emma-carl", emma, carl, .5);
        addLane("2emma-carl", carl, emma, .5);
        addLane("emma-dave", emma, dave, .8);
        addLane("2emma-dave", dave, emma, .8);
        addLane("emma-fred", emma, fred, .7);
        addLane("2emma-fred", fred, emma, .7);
        addLane("dave-carl", dave, carl, .3);
        addLane("2dave-carl", carl, dave, .3);
        addLane("dave-bill", dave, bill, .4);
        addLane("2dave-bill", bill, dave, .4);
        addLane("carl-anna", carl, anna, .4);
        addLane("2carl-anna", anna, carl, .4);
        addLane("bill-anna", bill, anna, .5);
        addLane("2bill-anna", anna, bill, .5);


        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);



        //start index and end index
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(5), nodes.get(0));

        System.out.println("Path: " + path);

    }


    private static void addLane(String laneId, Vertex sourceLocNo, Vertex destLocNo, Double duration) {
        Edge lane = new Edge(laneId, sourceLocNo, destLocNo, duration );
        edges.add(lane);
    }
}