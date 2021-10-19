//import java.util.PriorityQueue;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Collections;
//
//class Vertex implements Comparable<Vertex>
//{
//    public final String name;
//    public Edge[] adjacencies;
//    public double minDistance = Double.POSITIVE_INFINITY;
//    public Vertex previous;
//    public Vertex(String argName) { name = argName; }
//    public String toString() { return name; }
//    public int compareTo(Vertex other)
//    {
//        return Double.compare(minDistance, other.minDistance);
//    }
//
//}
//
//
//class Edge
//{
//    public final Vertex target;
//    public final double weight;
//    public Edge(Vertex argTarget, double argWeight)
//    { target = argTarget; weight = argWeight; }
//}
//
//class Dijkstra
//{
//    public static void computePaths(Vertex source)
//    {
//        source.minDistance = 0.;
//        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
//        vertexQueue.add(source);
//
//        while (!vertexQueue.isEmpty()) {
//            Vertex u = vertexQueue.poll();
//
//            // Visit each edge exiting u
//            for (Edge e : u.adjacencies)
//            {
//                Vertex v = e.target;
//                double weight = e.weight;
//                double log = -(Math.log(weight));
//                double distanceThroughU = u.minDistance + log;
//                if (distanceThroughU < v.minDistance) {
//                    vertexQueue.remove(v);
//
//                    v.minDistance = distanceThroughU ;
//                    v.previous = u;
//                    vertexQueue.add(v);
//                }
//            }
//        }
//    }
//
//    public static List<Vertex> getShortestPathTo(Vertex target)
//    {
//        List<Vertex> path = new ArrayList<Vertex>();
//        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
//            path.add(vertex);
//
//        Collections.reverse(path);
//        return path;
//    }
//
//    public static void main(String[] args)
//    {
//        // mark all the vertices
//        Vertex emma = new Vertex("Emma");
//        Vertex dave = new Vertex("Dave");
//        Vertex fred = new Vertex("Fred");
//        Vertex carl = new Vertex("Carl");
//        Vertex bill = new Vertex("Bill");
//        Vertex anna = new Vertex("Anna");
//
//
//        // set the edges and weight
//        emma.adjacencies = new Edge[]{ new Edge(dave, 0.8) };
//        dave.adjacencies = new Edge[]{ new Edge(emma, 0.8) };
//
////        fred.adjacencies = new Edge[]{ new Edge(emma, 0.7) };
////        emma.adjacencies = new Edge[]{ new Edge(fred, 0.7) };
//
////        emma.adjacencies = new Edge[]{ new Edge(carl, 0.2) };
////        carl.adjacencies = new Edge[]{ new Edge(emma, 0.2) };
//
//        dave.adjacencies = new Edge[]{ new Edge(carl, 0.3) };
//        carl.adjacencies = new Edge[]{ new Edge(dave, 0.3) };
//
//        dave.adjacencies = new Edge[]{ new Edge(bill, 0.4) };
//        bill.adjacencies = new Edge[]{ new Edge(dave, 0.4) };
//
//        bill.adjacencies = new Edge[]{ new Edge(anna, 0.5) };
//        anna.adjacencies = new Edge[]{ new Edge(bill, 0.5) };
//
//        carl.adjacencies = new Edge[]{ new Edge(anna, 0.4) };
//        anna.adjacencies = new Edge[]{ new Edge(carl, 0.4) };
//
//
//        computePaths(emma); // run Dijkstra
//        System.out.printf("Distance to " + anna + ": %.3f %n",  anna.minDistance);
//        List<Vertex> path = getShortestPathTo(anna);
//        System.out.println("Path: " + path);
//
//    }
//}