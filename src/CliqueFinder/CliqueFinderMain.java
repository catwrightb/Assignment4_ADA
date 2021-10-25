/**
 * Main testing class for Bron-Kerbosch Algorithm.
 */
package CliqueFinder;

import CliqueFinder.Graph.Graph;

public class CliqueFinderMain {

    public static void main(String[] args) {
        MockGraphs mockData = MockGraphs.getInstance(); // Contains mock data sets.
        Graph graph = mockData.getAllGraphs().get(0); // Use the first mock graph.

        // Print current graph.
        System.out.println(graph);

        // Get all maximal cliques from graph with a threshold of .4.
        BronKerboschAlgorithm bronKerboschAlgorithm = new BronKerboschAlgorithm();
        bronKerboschAlgorithm.findMaximalCliques(graph, 0.4);

        // Print all found maximal cliques
        System.out.println("===============================================================================");
        System.out.println("All found maximal cliques with threshold above .4:");
        bronKerboschAlgorithm.getCliques().forEach(clique -> {
            System.out.print("  * ");
            clique.forEach(vertex -> System.out.print(vertex.getName() + " "));
            System.out.println();
        });
        System.out.println();

        // Print maximum clique
        System.out.println("===============================================================================");
        System.out.print("Maximum Clique: ");
        bronKerboschAlgorithm.getMaximumClique().forEach(vertex -> System.out.print(vertex.getName() + " "));
        System.out.println();
    }
}

