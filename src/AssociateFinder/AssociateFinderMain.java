/**
 * References:
 *
 *https://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 * https://stackoverflow.com/questions/49734206/finding-the-shortest-path-between-two-pointsbfs
 *https://www.softwaretestinghelp.com/java-graph-tutorial/
 *
 *
 * Dijkstra’s Algorithms describes how to find the shortest path from one node to another node in a directed weighted graph.
 * Algorithm and graph needs to be processed to find the paths from a source node which enables a findPath method to be used
 * with a start and end index in a graph. Will print the shortest total and the path.
 *
 */

package AssociateFinder;
import java.util.ArrayList;
import java.util.LinkedList;

class AssociateFinderMain {

    private static ArrayList<Vertex> nodes;
    private static ArrayList<Edge> edges;

    public static void main(String[] args) {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();


        /*
        * add vertex
        * using id and name as matching
        * but could use id's to create vertex with same names
        * */
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


        /*
        * need to add edge between vertex
        * since the graph is undirected need matching edges with weights between the vertexs
        * */
        addEdge("emma-carl", emma, carl, .5);
        addEdge("2emma-carl", carl, emma, .5);
        addEdge("emma-dave", emma, dave, .8);
        addEdge("2emma-dave", dave, emma, .8);
        addEdge("emma-fred", emma, fred, .7);
        addEdge("2emma-fred", fred, emma, .7);
        addEdge("dave-carl", dave, carl, .3);
        addEdge("2dave-carl", carl, dave, .3);
        addEdge("dave-bill", dave, bill, .4);
        addEdge("2dave-bill", bill, dave, .4);
        addEdge("carl-anna", carl, anna, .4);
        addEdge("2carl-anna", anna, carl, .4);
        addEdge("bill-anna", bill, anna, .5);
        addEdge("2bill-anna", anna, bill, .5);


        //pass in the arraylist to create the graph
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);



        //start dijksta algorithm by processing the graph with a source node
        // then pass in the start index and end index for shortest distance between
        dijkstra.processGraph(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPathBetweenNodes(nodes.get(5), nodes.get(0));

        System.out.println("Path of least : " + path);

    }


    private static void addEdge(String laneId, Vertex sourceLocNo, Vertex destLocNo, Double duration) {
        Edge lane = new Edge(laneId, sourceLocNo, destLocNo, duration );
        edges.add(lane);
    }
}