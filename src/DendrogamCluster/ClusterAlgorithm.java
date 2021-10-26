
package DendrogamCluster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
* Cluster Algorithm using hireachical
* */
public class ClusterAlgorithm
{

    public Cluster performClustering(double[][] distances,
                                     String[] clusterNames, Edge linkageStrategy)
    {

        checkArguments(distances, clusterNames);

        List<Cluster> clusters = createClusters(clusterNames);
        MappedDistances linkages = createLinkages(distances, clusters);

        ClusterHierarchy builder = new ClusterHierarchy(clusters, linkages);
        while (!builder.isTreeComplete())
        {
            builder.agglomerate(linkageStrategy);
        }

        return builder.getRootCluster();
    }

    /*checks the distance and clusters passed in*/
    private void checkArguments(double[][] distances, String[] clusterNames)
    {
        if (distances == null || distances.length == 0
                || distances[0].length != distances.length)
        {
            throw new IllegalArgumentException("Invalid distance matrix");
        }
        if (distances.length != clusterNames.length)
        {
            throw new IllegalArgumentException("Invalid cluster name array");
        }
        int uniqueCount = new HashSet<String>(Arrays.asList(clusterNames)).size();
        if (uniqueCount != clusterNames.length)
        {
            throw new IllegalArgumentException("Duplicate names");
        }
    }

//    creates links for clusters
    private MappedDistances createLinkages(double[][] distances,
                                           List<Cluster> clusters)
    {
        MappedDistances linkages = new MappedDistances();
        for (int col = 0; col < clusters.size(); col++)
        {
            for (int row = col + 1; row < clusters.size(); row++)
            {
                ClusterPair link = new ClusterPair();
                Cluster lCluster = clusters.get(col);
                Cluster rCluster = clusters.get(row);
                link.setLinkageDistance(distances[col][row]);
                link.setlCluster(lCluster);
                link.setrCluster(rCluster);
                linkages.add(link);
            }
        }
        return linkages;
    }

    private List<Cluster> createClusters(String[] clusterNames)
    {
        List<Cluster> clusters = new ArrayList<Cluster>();
        for (String clusterName : clusterNames)
        {
            Cluster cluster = new Cluster(clusterName);
            cluster.addLeafName(clusterName);
            clusters.add(cluster);
        }
        return clusters;
    }


}
