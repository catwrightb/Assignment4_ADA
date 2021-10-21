package Cluster;

public class Dendrogram{


    public static void main(String[] args) {
        LinkageStrategy strategy = new Linkage();
        createSampleCluster(strategy);

    }


    private static void createSampleCluster(LinkageStrategy strategy) {

        String[] names = new String[] { "anna", "bill", "carl", "dave", "emma", "fred" };
        double[][] distances = new double[][] {
                {0, 0.5, 0.4, 0, 0, 0},
                {0.5, 0, 0, 0.4, 0, 0},
                {0.4, 0, 0, 0.3, 0.5, 0},
                {0, 0.4, 0.3, 0, 0.8, 0},
                {0, 0, 0.5, 0.8, 0, 0.7},
                {0, 0, 0, 0, 0.7, 0}};

        ClusterAlgorithm alg = new ClusterAlgorithm();
        Cluster cluster = alg.performClustering(distances, names,
                strategy);

        System.out.println( cluster.toString(0));

    }
}