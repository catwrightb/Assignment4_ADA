/*
* Resources:
*
* https://en.wikipedia.org/wiki/Complete-linkage_clustering
* https://www.youtube.com/playlist?list=PLBv09BD7ez_6VX12puSF0Ep6NluI9vwoX
* https://www.youtube.com/watch?v=urbalppysi0&list=PLBv09BD7ez_6VX12puSF0Ep6NluI9vwoX&index=1
* https://github.com/lbehnke/hierarchical-clustering-java/tree/master/src/main/java/com/apporiented/algorithm/clustering
*
*
* A dendrogram is a diagram that shows the hierarchical relationship between objects.
* It is most commonly created as an output from hierarchical clustering.
* The main use of a dendrogram is to work out the best way to allocate objects to clusters.
 * */

package DendrogamCluster;

public class DendrogramClusterMain {


    public static void main(String[] args) {
        Edge linkage = new Edge();

        String[] names = new String[] { "anna", "bill", "carl", "dave", "emma", "fred" };
        //pass in a double array to create connection edges
        double[][] distances = new double[][] {
                {0, 0.5, 0.4, 0, 0, 0},
                {0.5, 0, 0, 0.4, 0, 0},
                {0.4, 0, 0, 0.3, 0.5, 0},
                {0, 0.4, 0.3, 0, 0.8, 0},
                {0, 0, 0.5, 0.8, 0, 0.7},
                {0, 0, 0, 0, 0.7, 0}};

        ClusterAlgorithm alg = new ClusterAlgorithm();
        Cluster cluster = alg.performClustering(distances, names,
                linkage);

        System.out.println("Print Dendrogram of Network:");

        System.out.println( cluster.toString(0));

    }


}