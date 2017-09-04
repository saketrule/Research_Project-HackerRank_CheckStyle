import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class RoadsInHackerLand {
 public static class Edge implements Comparable<Edge> {
  public Edge(int u, int v, int weight) {
   this.u = u;
   this.v = v;
   this.weight = weight;
  }

  int u, v, weight;

  public int compareTo(Edge o) {
   return Integer.compare(this.weight, o.weight);
  }

 }

 public static class Graph {

  private int verticesCount = 0;
  private int edgeCount = 0;
  private List<Map<Integer, Integer>> adj;
  private Set<Edge> edges;

  public Graph(int vertices) {
   this.edges = new TreeSet<Edge>();
   this.verticesCount = vertices;
   this.adj = new ArrayList<Map<Integer, Integer>>();
   for (int vertex = 0; vertex < vertices; vertex++) {
    this.adj.add(new HashMap<Integer, Integer>());
   }
  }

  public Collection<Edge> getEdges() {
   return this.edges;
  }

  public int getVerticesCount() {
   return this.verticesCount;
  }

  public void addEdge(int u, int v, int weight) {

   // prevent parallel edges
   int minWeight = this.adj.get(u).containsKey(v) ? Math.min(this.adj.get(u).get(v), Integer.valueOf(weight))
     : weight;
   this.adj.get(u).put(v, minWeight);
   this.adj.get(v).put(u, minWeight);
   this.edges.add(new RoadsInHackerLand.Edge(u, v, weight));

   this.edgeCount++;
  }

  public int getEdgeCount() {
   return this.edgeCount;
  }

  public Collection<Integer> getAdj(int v) {
   return this.adj.get(v).keySet();
  }

  public int getWeight(int u, int v) {
   Integer weight = this.adj.get(u).get(v);
   return weight == null ? Integer.MAX_VALUE : weight;
  }

  public void clear() {
   this.adj.clear();
   this.edges.clear();
   this.verticesCount = 0;
   this.edgeCount = 0;

  }

 }

 public static class DisjointedSet {

  private int[] parents;
  private int[] rank;

  public DisjointedSet(int vertices) {
   parents = new int[vertices];
   rank = new int[vertices];
   for (int i = 0; i < vertices; i++) {
    parents[i] = i;
    rank[i] = 0;
   }
  }

  public void union(int u, int v) {
   int parentA = find(u);
   int parentB = find(v);
   if (parentA == parentB) return;
   if (rank[parentA] > rank[parentB]) {
    parents[parentB] = parentA;
    rank[parentA] += rank[parentB];
   } else {
    parents[parentA] = parentB;
    rank[parentB] += rank[parentA];
   }
  }

  public int find(int vertex) {
   int parent = vertex;
   while (parents[parent] != parent) {
    parents[parent] = parents[parents[parent]];
    parent = parents[parent];

   }
   return parent;
  }

  public boolean isConnected(int vertexA, int vertexB) {
   return find(vertexA) == find(vertexB);

  }

 }
 
 public static class MST extends Graph {

  public MST(Graph g) {
   super(g.verticesCount);
   // order edges to do MST generation
   Set<Edge> orderedEdges = g.edges;
   DisjointedSet disjointedSets = new DisjointedSet(g.verticesCount);
   for (Edge e : orderedEdges) {
    if (!disjointedSets.isConnected(e.u, e.v)) {
     disjointedSets.union(e.u, e.v);
     this.addEdge(e.u, e.v, e.weight);

    }
   }
  }
 }

 static long startTime;
 private static BigInteger totalDistanceTravelled = BigInteger.ZERO;
 public static void main(String[] args) throws InterruptedException {
  Scanner in = new Scanner(System.in);

  int cities = in.nextInt();
  int roads = in.nextInt();
  RoadsInHackerLand.MST tree = generateMst(in, cities,roads);
  long[] edgesUsage = new long[roads];
  findPathsUsage(tree, 0, -1, edgesUsage);
  for (long i : edgesUsage) {
   if (i < 0) System.out.println("here");
  }
  String output = generateBinaryString(edgesUsage);
  
  System.out.println(output);
  
 }
    // Due to poor performance of BigInteger, simply do binary math
    // Note, this only works since the roads are base 2 values.
 private static String generateBinaryString(long[] edgesUsage) throws InterruptedException {
  StringBuilder sb = new StringBuilder();
  Stack<Long> nums = new Stack<Long>();
  int idx = 1;
  long currentVal = edgesUsage[0];
  long nextVal = edgesUsage[1];
  boolean done = false;
  
  while (!done) {
   idx++;
            // push currentVal to the "left" n/2 times
            // 4 -> 100, so 1 is pushed to the right two times
   nextVal += currentVal / 2;
            // this will account for the push coming from nextVal in a previous iteration
   currentVal = currentVal % 2;
   nums.push(currentVal);
   if (idx < (edgesUsage.length)) {
    currentVal = nextVal;
    nextVal = edgesUsage[idx];
   } else {    
    currentVal = nextVal;
    nextVal = 0;
   }
   done = currentVal == 0 && idx > edgesUsage.length;
  }  
  boolean foundFirst = false;
  while (!nums.isEmpty()) {
   long num = nums.pop();
   if (num == 1 && !foundFirst) foundFirst = true;
   if (foundFirst) sb.append(num);
  }  
  return sb.toString();
 }

 private static MST generateMst(Scanner in, int cities, int roads) {
  Graph g = new Graph(cities);
  for (int road = 0; road < roads; road++) { 
   int cityA = in.nextInt() - 1;
   int cityB = in.nextInt() - 1;
   int dist = in.nextInt();
   g.addEdge(cityA, cityB, dist);
  }
  startTime = Calendar.getInstance().getTimeInMillis();
  return new RoadsInHackerLand.MST(g);
 }

 private static int findPathsUsage(Graph g, int v, int blocking, long[] pathsCnt) {
  int vChildren = 1;
  for (int other : g.getAdj(v)) {
   if (other != blocking) {
    
    long otherChildren = findPathsUsage(g, other, v, pathsCnt);
    vChildren += otherChildren;
    int weight = g.getWeight(other, v);
    pathsCnt[weight] += (g.verticesCount - otherChildren) * otherChildren;
   }
  }
  return vChildren;
 }

}