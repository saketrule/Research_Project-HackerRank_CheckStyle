import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


// Need to traverse mst to get total edge cost of the path
// not just the mst cost


class Edge implements Comparable<Edge>{
 int from, to, edgeWeight;
 
 public Edge (int x, int y, int z) {
  from = x;
  to = y;
  edgeWeight = z;
 }
 
 public String toString() {
  return String.format("From: %d, To: %d, Weight: %d%n", from,to, edgeWeight);
 }
 
 @Override
 public int compareTo(Edge e) {
  return this.edgeWeight - e.edgeWeight;
 }
}

public class Solution {

 public static ArrayList<Edge>[] mustHitGraph;
 public static boolean[] visited;
 public static int totalCost = 0;
 public static void dfs(Edge curEdge) {
  if (visited[curEdge.to]) {
   return;
  }
  visited[curEdge.to] = true;
  int minEdgeDest = 0;
  int minEdgeWeight = Integer.MAX_VALUE;
  boolean minFound = false;
  for (Edge e: mustHitGraph[curEdge.to]) {
   if (!visited[e.to]) {
    if (e.edgeWeight < minEdgeWeight) {
     minEdgeWeight = e.edgeWeight;
     minEdgeDest = e.to;
     minFound = true;
    }
   }
  }
  if (minFound) {
   totalCost += minEdgeWeight;
   dfs(new Edge(curEdge.to, minEdgeDest, minEdgeWeight));
  }
 }
 
 public static void cost(int[][] mstDistances, ArrayList<Integer> mustHitPoints) {
  mustHitGraph = (ArrayList<Edge>[]) new ArrayList[mstDistances[0].length];
  for (int i = 0; i < mustHitGraph.length; i++) {
   mustHitGraph[i] = new ArrayList<Edge>();
  }
  for (int i = 0; i < mustHitPoints.size(); i++) {
   for (int j = i + 1; j < mustHitPoints.size(); j++) {
    int from = mustHitPoints.get(i);
    int to = mustHitPoints.get(j);
    int weight = mstDistances[i][to];
    mustHitGraph[from].add(new Edge(from, to, weight));
    mustHitGraph[to].add(new Edge(to, from, weight));
   }
  }
  int bestTotal = Integer.MAX_VALUE;
  for (int i = 0; i < mustHitPoints.size(); i++) {
   totalCost = 0;
   visited = new boolean[mustHitGraph.length];
   dfs(new Edge(0,mustHitPoints.get(i), 0));
   if (totalCost <  bestTotal) {
    bestTotal = totalCost;
   }
  }
  totalCost = bestTotal;
  return;
 }
 
 public static void dijkstras(ArrayList<Edge>[] graph, ArrayList<Integer> mustHitPoints) {
  int[][] mstDistances = new int[mustHitPoints.size()][graph.length];
  for (int p = 0; p < mustHitPoints.size(); p++) {
   int source = mustHitPoints.get(p);
   int[] dist = new int[graph.length];
   Arrays.fill(dist, 2500000);
   dist[source] = 0;
   PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
   pq.offer(new Edge(0,source,0));
   while (!pq.isEmpty()) {
    Edge cur = pq.poll();
    if (cur.edgeWeight > dist[cur.to]) continue;
    for (Edge e: graph[cur.to]) {
     if (dist[cur.to] + e.edgeWeight < dist[e.to]) {
      dist[e.to] = dist[cur.to] + e.edgeWeight;
      pq.offer(e);
     }
    }
   }
   mstDistances[p] = dist;
  }
  cost(mstDistances, mustHitPoints);
  return;
 }
 
 
 public static void main(String[] args) {
  
  Scanner kb = new Scanner(System.in);
  int numberOfCities = kb.nextInt();
  int numberMustHit = kb.nextInt();
  ArrayList<Integer> mustPass = new ArrayList<Integer>();
  for (int i = 0; i < numberMustHit; i++) {
   mustPass.add(kb.nextInt());
  }
  ArrayList<Edge>[] graph = (ArrayList<Edge>[]) new ArrayList[numberOfCities + 1];
  for (int i = 0; i < graph.length; i++) {
   graph[i] = new ArrayList<Edge>();
  }
  for (int i = 0; i < numberOfCities - 1; i++) {
   int from = kb.nextInt();
   int to = kb.nextInt();
   int weight = kb.nextInt();
   graph[from].add(new Edge(from, to, weight));
   graph[to].add(new Edge(to,from,weight));
  }
  dijkstras(graph, mustPass);
  System.out.println(totalCost);
  
 
 }

}