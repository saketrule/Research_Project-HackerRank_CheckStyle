import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] adjs = new int[m][3];
        Set<Integer> cities = new TreeSet<Integer>();
        for (int i = 0; i < m; i++) {
            adjs[i][0] = scanner.nextInt();
            cities.add(adjs[i][0]);
            adjs[i][1] = scanner.nextInt();
            cities.add(adjs[i][1]);
            adjs[i][2] = (int)Math.pow(2, scanner.nextInt());
        }
        scanner.close();

        List<Vertex> vertices = new ArrayList<Vertex>();
        for (Integer city : cities) {
            Vertex v = new Vertex(String.valueOf(city.intValue()));
            vertices.add(v);
        }

        for (Vertex v : vertices) {
            v.adjacencies = assignAdjacencies(vertices, v, adjs);
        }

        Map<Vertex, Map<Vertex, Integer>> traversals = new HashMap<Vertex, Map<Vertex,Integer>>();
        for (Vertex v : vertices) {
            Dijkstra.computePaths(v);
            Map<Vertex, Integer> value = new HashMap<Vertex, Integer>();
            for (Vertex v1 : vertices) {
                if (v1 != v) {
                 Map<Vertex, Integer> map = traversals.get(v1);
                 if (map != null && !map.isEmpty()) {
                  if (!map.keySet().contains(v)) value.put(v1, (int)v1.minDistance);
                 }
                 else {
                  value.put(v1, (int)v1.minDistance);
                 }
                }
            }
            traversals.put(v, value);
            reset(vertices);
        }
        
        int total = 0;
        for (Vertex v : traversals.keySet()) {
         Map<Vertex, Integer> map = traversals.get(v);
         for (Vertex v1 : map.keySet()) {
          total += map.get(v1);
         }
        }
        
        System.out.println(Integer.toBinaryString(total));

    }

    private static Edge[] assignAdjacencies(List<Vertex> vertices, Vertex v, int[][] adjs) {
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < adjs.length; i++) {
            String name = String.valueOf(adjs[i][0]);
            if (v.name.equals(name)) {
                Vertex t = Vertex.findVertex(vertices, adjs[i][1]);
                if (t != null) {
                    Edge edge = new Edge(t, adjs[i][2]);
                    edges.add(edge);
                }
            }
            name = String.valueOf(adjs[i][1]);
            if (v.name.equals(name)) {
                Vertex t = Vertex.findVertex(vertices, adjs[i][0]);
                if (t != null) {
                    Edge edge = new Edge(t, adjs[i][2]);
                    edges.add(edge);
                }
            }
        }

        return edges.toArray(new Edge[edges.size()]);
    }
    
    private static void reset(List<Vertex> vertices) {
     for (Vertex v : vertices) {
      v.minDistance = Double.POSITIVE_INFINITY;;
      v.previous = null;
     }
    }
}

class Vertex implements Comparable<Vertex> {
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex(String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }
    
    public boolean equals(Object o) {
     if (this == o) return true;
     if (o instanceof Vertex) {
      return this.name.equals(((Vertex)o).name);
     }
     
     return false;
    }
    
    public int hashCode() {
     return name.hashCode();
    }

    public static Vertex findVertex(Collection<Vertex> vertices, String name) {
        if (name != null && vertices != null && !vertices.isEmpty()) {
            for (Vertex vertex : vertices) {
                if (vertex.name.equals(name)) {
                    return vertex;
                }
            }
        }

        return null;
    }

    public static Vertex findVertex(Collection<Vertex> vertices, int vertexNumber) {
        if (vertexNumber > 0 && vertices != null && !vertices.isEmpty()) {
            String name = String.valueOf(vertexNumber);
            for (Vertex vertex : vertices) {
                if (vertex.name.equals(name)) {
                    return vertex;
                }
            }
        }

        return null;
    } 

}

class Edge {
    public final Vertex target;
    public final double weight;

    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
    
    public String toString() {
     return "->" + target + " (" + weight + ")";
    }
}

class Dijkstra {

    public static void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

}