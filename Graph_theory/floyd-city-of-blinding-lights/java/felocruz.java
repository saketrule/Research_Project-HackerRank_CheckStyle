import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static private class Edge implements Comparable<Edge> { 

        private int v;
        private int w;
        private int weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int weight() {
            return weight;
        }

        public int from() {
            return v;
        }

        public int to() {
            return w;
        }

        public int compareTo(Edge that) {
            if      (this.weight() < that.weight()) return -1;
            else if (this.weight() > that.weight()) return +1;
            else                                    return  0;
        }

        @Override
        public String toString() {
            return v + "->" + w + " " + String.format("%5.2f", weight);
        }
    }

    static private class Graph {
        private int V;
        private List<Edge>[] adj;

        @SuppressWarnings("unchecked")
        public Graph(int v) {
            this.V = v;

            this.adj = new List[V];

            for (int i = 0; i < V; i++) {
                this.adj[i] = new ArrayList<Edge>();
            }
        }

        public void addEdge(int v, int w, int weight) {
            Edge e = new Edge(v, w, weight);
            adj[v].add(e);
        }

        public Iterable<Edge> adj(int v) {
            if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
            return adj[v];
        }

        public Iterable<Edge> edges() {
            List<Edge> list = new ArrayList<Edge>();
            for (int v = 0; v < V; v++) {
                for (Edge e : adj(v)) {
                    list.add(e);
                }
            }

            return list;
        }    
    }
    
 private static double[][] distTo;
 private static Edge[][] edgeTo;

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);

  int V = in.nextInt();
  int E = in.nextInt();
   
  Graph G = new Graph(V);

  // Read the edges
  for (int j = 0; j < E; j++) {
   G.addEdge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
  }
   
  distTo = new double[V][V];
        edgeTo = new Edge[V][V];

        // initialize distances to infinity
        for (int v = 0; v < V; v++) {
            for (int w = 0; w < V; w++) {
                distTo[v][w] = Double.POSITIVE_INFINITY;
            }
        }

        // initialize distances using edge-weighted digraph's
        for (int v = 0; v < V; v++) {
            for (Edge e : G.adj(v)) {
                distTo[e.from()][e.to()] = e.weight();
                edgeTo[e.from()][e.to()] = e;
            }
            // in case of self-loops
            if (distTo[v][v] >= 0.0) {
                distTo[v][v] = 0.0;
                edgeTo[v][v] = null;
            }
        }

        // Floyd-Warshall updates
        for (int i = 0; i < V; i++) {
            // compute shortest paths using only 0, 1, ..., i as intermediate vertices
            for (int v = 0; v < V; v++) {
                if (edgeTo[v][i] == null) continue;  // optimization
                for (int w = 0; w < V; w++) {
                    if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
                        distTo[v][w] = distTo[v][i] + distTo[i][w];
                        edgeTo[v][w] = edgeTo[i][w];
                    }
                }
            }
        }
         
  int Q = in.nextInt();
  
  // Determine the shortest path for each input
  for (int i = 0; i < Q; i++) {
   int v = in.nextInt() - 1;
   int w = in.nextInt() - 1;
   
   if (distTo[v][w] < Double.POSITIVE_INFINITY) System.out.println("" + (int)distTo[v][w]);
   else                                         System.out.println("-1");
  }
 }
    

}