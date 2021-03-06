package DendrogamCluster;

import java.util.Collection;

/*
* distance and linkage classes for the distance the edges between clusters
* */
class Distance implements Comparable<Distance>, Cloneable {

    private Double distance;
    private Double weight;

    public Distance() {
        this(0.0);
    }

    public Distance(Double distance) {
        this(distance, 1.0);
    }

    public Distance(Double distance, Double weight) {
        this.distance = distance;
        this.weight = weight;
    }

    public Double getDistance() {
        return distance;
    }


    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


    @Override
    public int compareTo(Distance distance) {
        return distance == null ? 1 : getDistance().compareTo(distance.getDistance());
    }

    @Override
    public String toString() {
        return String.format("distance : %.2f, weight : %.2f", distance, weight);
    }
}


public class Edge {

    public Distance calculateDistance(Collection<Distance> distances) {
        double max = Double.NaN;

        for (Distance dist : distances) {
            if (Double.isNaN(max) || dist.getDistance() > max)
                max = dist.getDistance();
        }
        return new Distance(max);
    }
}



