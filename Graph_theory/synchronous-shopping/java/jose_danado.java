import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
/**
 * @author jdanado
 *
 */
public class Solution {
 private class DirectedEdge {
  private final int v;
  private final int w;
  private final double weight;

  public DirectedEdge(int v, int w, double weight) {
   if (v < 0)
    throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
   if (w < 0)
    throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
   if (Double.isNaN(weight))
    throw new IllegalArgumentException("Weight is NaN");
   this.v = v;
   this.w = w;
   this.weight = weight;
  }

  public int from() {
   return v;
  }

  public int to() {
   return w;
  }

  public double weight() {
   return weight;
  }

  public String toString() {
   return v + "->" + w + " " + String.format("%5.2f", weight);
  }
 }

 public class EdgeWeightedDigraph {
  private final int V; // number of vertices in this digraph
  private int E; // number of edges in this digraph
  private Set<DirectedEdge>[] adj; // adj[v] = adjacency list for vertex v
  private int[] indegree; // indegree[v] = indegree of vertex v

  public EdgeWeightedDigraph(int V) {
   if (V < 0)
    throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
   this.V = V;
   this.E = 0;
   this.indegree = new int[V];
   adj = (Set<DirectedEdge>[]) new Set[V];
   for (int v = 0; v < V; v++)
    adj[v] = new HashSet<DirectedEdge>();
  }

  public int V() {
   return V;
  }

  public int E() {
   return E;
  }

  private void validateVertex(int v) {
   if (v < 0 || v >= V)
    throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V - 1));
  }

  public void addEdge(DirectedEdge e) {
   int v = e.from();
   int w = e.to();
   validateVertex(v);
   validateVertex(w);
   adj[v].add(e);
   indegree[w]++;
   E++;
  }

  public Iterable<DirectedEdge> adj(int v) {
   validateVertex(v);
   return adj[v];
  }

  public int outdegree(int v) {
   validateVertex(v);
   return adj[v].size();
  }

  public int indegree(int v) {
   validateVertex(v);
   return indegree[v];
  }

  public Iterable<DirectedEdge> edges() {
   Set<DirectedEdge> list = new HashSet<DirectedEdge>();
   for (int v = 0; v < V; v++) {
    for (DirectedEdge e : adj(v)) {
     list.add(e);
    }
   }
   return list;
  }

  public String toString() {
   StringBuilder s = new StringBuilder();
   s.append(V + " " + E + '\n');
   for (int v = 0; v < V; v++) {
    s.append(v + ": ");
    for (DirectedEdge e : adj[v]) {
     s.append(e + "  ");
    }
    s.append('\n');
   }
   return s.toString();
  }
 }

 private class Triple {
  private int node;
  private int fish;
  private int cost;

  public Triple(int node, int fish, int cost) {
   this.node = node;
   this.cost = cost;
   this.fish = fish;
  }

  public int getNode() {
   return node;
  }

  public void setNode(int node) {
   this.node = node;
  }

  public int getFish() {
   return fish;
  }

  public void setFish(int fish) {
   this.fish = fish;
  }

  public int getCost() {
   return cost;
  }

  public void setCost(int cost) {
   this.cost = cost;
  }
        @Override
        public int hashCode() {
            return node | fish;
        }

        @Override
  public boolean equals(Object other) {
   if (other instanceof Triple) {
    return (((Triple) other).node == this.node && ((Triple) other).fish == this.fish);
   } else
    return false;
  }
 }

 private class ShoppingQueue {
  private LinkedList<Triple> queue = new LinkedList<Triple>();
  private int[][] shortPath;

  public ShoppingQueue(int[][] shortPath) {
   this.shortPath = shortPath;
  }

  public void add(int node, int fish, int cost) {
   if (shortPath[node][fish] <= cost) {
    return;
   }
   Triple triple = new Triple(node, fish, cost);
   shortPath[node][fish] = cost;
   queue.addFirst(triple);
  }

  public Triple remove() {
            Triple res = queue.removeLast();
            while(shortPath[res.node][res.fish] !=res.cost)
                res = queue.removeLast();
   return res;
  }

  public boolean isEmpty() {
   return queue.isEmpty();
  }
 }

 private class DijkstraSP {
  int[][] shortPath;
  ShoppingQueue queue;

  public DijkstraSP(EdgeWeightedDigraph dg, int src, int[] shops, int k) {
   this.shortPath = new int[dg.V()][(1<< k)];
   for (int i = 0; i < dg.V(); i++) {
    for (int j = 0; j < (1<< k); j++) {
     shortPath[i][j] = Integer.MAX_VALUE >> 1;
    }
   }
   queue = new ShoppingQueue(shortPath);
   queue.add(src, shops[src], 0);
   while (!queue.isEmpty()) {
    Triple current = queue.remove();
    for (DirectedEdge e : dg.adj(current.node)) {
     queue.add(e.to(), current.getFish() | shops[e.to()], current.getCost() + ((int) e.weight()));
    }
   }

  }

  public int getTime() {
   int time = Integer.MAX_VALUE;
   int cities = shortPath.length -1;
   int fish = shortPath[0].length;
   for (int i = 0; i < fish; i++) {
    for (int j = i; j < fish; j++) {
     if ((i | j) == (fish - 1)) {
      time = Math.min(time, Math.max(shortPath[cities][i], shortPath[cities][j]));
     }
    }
   }
   return time;
  }
 }

 /**
  * @param args
  */
 public static void main(String[] args) {
  Solution shopping = new Solution();
  try {
   int[] shops;
   Scanner sc = new Scanner(System.in);
   int n = sc.nextInt();
   int m = sc.nextInt();
   int k = sc.nextInt();
   shops = new int[n];
   for (int i = 0; i < n; i++) {
    int n_type = sc.nextInt();
    for (int j = 0; j < n_type; j++) {
     int type = sc.nextInt();
     shops[i] |= 1 << (type - 1);
    }

   }
   EdgeWeightedDigraph dg = shopping.new EdgeWeightedDigraph(n);

   for (int i = 0; i < m; i++) {
    int x = sc.nextInt();
    int y = sc.nextInt();
    int weight = sc.nextInt();
    dg.addEdge(shopping.new DirectedEdge(x - 1, y - 1, weight));
    dg.addEdge(shopping.new DirectedEdge(y - 1, x - 1, weight));
   }
   DijkstraSP dijkstra = shopping.new DijkstraSP(dg, 0, shops, k);
   System.out.println(dijkstra.getTime());
  } catch (Exception e) {
   e.printStackTrace();
   return;
  }
 }
}