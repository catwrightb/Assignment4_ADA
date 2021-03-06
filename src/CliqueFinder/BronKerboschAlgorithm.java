/**
 * References:
 *
 * https://iq.opengenus.org/bron-kerbosch-algorithm/
 * https://www.youtube.com/watch?v=gjRcTH2p65c
 * https://www.youtube.com/watch?v=WTzpaSxHAys
 * https://github.com/ArjunRajJain/Graph-Theory/blob/master/BronKerbosch.java
 *
 */

package CliqueFinder;

import CliqueFinder.Graph.Graph;
import CliqueFinder.Graph.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BronKerboschAlgorithm {

    private Map<Vertex, List<Vertex>> adjacencyMap = new HashMap<>();
    private List<List<Vertex>> cliques;

    public List<List<Vertex>> findMaximalCliques(Graph graph, double threshold) {
        this.adjacencyMap = graph.getAdjacencyMapWithThreshold(threshold);
        this.cliques = new LinkedList<>();
        List<Vertex> potentialVertices = new LinkedList<>();
        List<Vertex> candidates = new LinkedList<>(adjacencyMap.keySet());
        List<Vertex> foundVertices = new LinkedList<>();
        findMaximalCliquesHelper(potentialVertices, candidates, foundVertices);
        return cliques;
    }

    /**
     * Recursive call for Bron-Kerbosch Algorithm to search all possible maximal cliques in a graph.
     *
     * @param potentialVertices collection of vertices that can form a potential maximal clique
     * @param candidates vertices to check for cliques
     * @param foundVertices actual maximal cliques found
     */
    private void findMaximalCliquesHelper(List<Vertex> potentialVertices, List<Vertex> candidates, List<Vertex> foundVertices) {
        List<Vertex> tempCandidates = new LinkedList<>(candidates);
        if (!finishedSearching(candidates, foundVertices)) {
            for (Vertex tempCandidate : tempCandidates) {
                List<Vertex> newCandidates = new LinkedList<>();
                List<Vertex> newFoundVertices = new LinkedList<>();

                potentialVertices.add(tempCandidate);
                candidates.remove(tempCandidate);

                for (Vertex newCandidate : candidates) {
                    if (adjacencyMap.get(tempCandidate).contains(newCandidate)) {
                        newCandidates.add(newCandidate);
                    }
                }

                for (Vertex newFoundVertex : foundVertices) {
                    if (adjacencyMap.get(tempCandidate).contains(newFoundVertex)) {
                        newFoundVertices.add(newFoundVertex);
                    }
                }

                if (newCandidates.isEmpty() && newFoundVertices.isEmpty()) {
                    cliques.add(new LinkedList<>(potentialVertices));
                } else {
                    findMaximalCliquesHelper(potentialVertices, newCandidates, newFoundVertices);
                }

                foundVertices.add(tempCandidate);
                potentialVertices.remove(tempCandidate);
            }
        }
    }

    /**
     * Method to indicate that we have finished traversing the graph
     * @param candidates
     * @param foundVertices
     * @return boolean value to indicate traversal state
     */
    private boolean finishedSearching(List<Vertex> candidates, List<Vertex> foundVertices) {
        for (Vertex foundVertex : foundVertices) {
            int counter = 0;
            for (Vertex candidate : candidates)
                if (adjacencyMap.get(foundVertex).contains(candidate))
                    counter++;

            if (counter == candidates.size())
                return true;
        }
        return false;
    }

    public List<List<Vertex>> getCliques() {
        return cliques;
    }

    /**
     * Gets the maximum clique based on all the maximal cliques available.
     *
     * @return maximum clique
     */
    public List<Vertex> getMaximumClique() {
        if (cliques.isEmpty()) return new LinkedList<>();
        return cliques
                .stream()
                .sorted((clique1, clique2) -> clique2.size() - clique1.size())
                .collect(Collectors.toList())
                .get(0);
    }
}
