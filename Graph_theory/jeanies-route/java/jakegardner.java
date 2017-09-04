import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    class Edge {
        int dest;
        int distance;
        public Edge(int dst, int dist) {
            this.dest = dst;
            this.distance = dist;
        }
    }
    
    class Vertex {
        int key;
        int id;
        public Vertex(int k, int id) {
            this.key = k;
            this.id = id;
        }
    }
    
    static int[] shortestPaths(HashMap<Integer, ArrayList<Edge>> adj, int root) {
        // nodes start at 1
        int[] pathCost = new int[adj.size()+1];
        Arrays.fill(pathCost, Integer.MAX_VALUE);
        pathCost[0] = 0;
        pathCost[root] = 0;
        
        // start queue with root vertex
        PriorityQueue<Vertex> Q = new PriorityQueue<>(adj.size(), new Comparator<Vertex>() {
           public int compare(Vertex v1, Vertex v2) {
                return v1.key - v2.key; 
           }
        });
        Q.offer( new Solution().new Vertex(0, root) );
        
        while (!Q.isEmpty()) {
            Vertex v = Q.poll();
            for (Edge e: adj.get(v.id)) {
                // update path costs to adjacent vertices
                int weight = pathCost[v.id] + e.distance;
                if (weight < pathCost[e.dest]) {
                    pathCost[e.dest] = weight;
                    Q.offer( new Solution().new Vertex(weight, e.dest) );
                }
            }
        }
        
        return pathCost;   
    }
    
    static int calcTour(int[][] pathCosts, int source, int[] deliveries) {
        int tour = 0;
        int current = source;
        for (int d: deliveries) {
            if (d != source) {
                tour += pathCosts[current][d];
            }
        }
        return tour;
    }
    
    static int getMinTour(HashMap<Integer, ArrayList<Edge>> adj, int[] deliveries) {
        int[][] pathCosts = new int[adj.size()][adj.size()];
        for (int d: deliveries) {
            pathCosts[d] = shortestPaths(adj, d);
        }
        
        int min = Integer.MAX_VALUE;
        for (int d: deliveries) {
            int tour = calcTour(pathCosts, d, deliveries);
            if (tour < min) {
                min = tour;
            }
        }
        return min;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cities = in.nextInt();
        int letters = in.nextInt();
        
        int[] deliveries = new int[letters];
        for (int l = 0; l < letters; l += 1) {
            deliveries[l] = in.nextInt();
        }
        
        HashMap<Integer, ArrayList<Edge>> adj = new HashMap<>();
        for (int i = 0; i < cities-1; i += 1) {
            int source = in.nextInt();
            int dest = in.nextInt();
            int weight = in.nextInt();

            Edge outbound = new Solution().new Edge(dest, weight);
            ArrayList<Edge> outlist;
            if (!adj.containsKey(source)) {
                outlist = new ArrayList<>();
            } else {
                outlist = adj.get(source);
            }
            outlist.add(outbound);
            adj.put(source, outlist);

            Edge inbound = new Solution().new Edge(source, weight);
            ArrayList<Edge> inlist;
            if (!adj.containsKey(dest)) {
                inlist = new ArrayList<>();
            } else {
                inlist = adj.get(dest);
            }
            inlist.add(inbound);
            adj.put(dest, inlist); 
        }
        System.out.println( getMinTour(adj, deliveries) );
    }
}