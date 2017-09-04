import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class WeightedEdge {
  public int node;
  public int weight;

  public WeightedEdge(int node, int weight) {
   this.node = node;
   this.weight = weight;
  }
 }

 public static class WeightedNode {
  int value;
  boolean unvisited = true;
  HashSet<Integer> distances = new HashSet<Integer>();

  Queue<WeightedEdge> edges = new ArrayDeque<WeightedEdge>();

  public WeightedNode(int value) {
   this.value = value;
  }
 }

 public static class WeightedQueueNode {
  int node;
  int distance;
 }

 public static void beautifyPath(Scanner scanner) {
  final int nodeCount = scanner.nextInt();
  final int edgeCount = scanner.nextInt();

  ArrayList<WeightedNode> graph = new ArrayList<WeightedNode>();

  for (int i = 0; i < nodeCount; ++i) {
   graph.add(new WeightedNode(i));
  }

  for (int i = 0; i < edgeCount; ++i) {
   final int edgeA = scanner.nextInt() - 1;
   final int edgeB = scanner.nextInt() - 1;
   final int weight = scanner.nextInt();

   graph.get(edgeA).edges.add(new WeightedEdge(edgeB, weight));
   graph.get(edgeB).edges.add(new WeightedEdge(edgeA, weight));
  }

  final int s = scanner.nextInt() - 1;

  Queue<WeightedQueueNode> q = new ArrayDeque<WeightedQueueNode>();

  WeightedNode sNode = graph.get(s);
  sNode.unvisited = false;
  WeightedQueueNode sQNode = new WeightedQueueNode();
  sQNode.node = s;
  sQNode.distance = 0;

  q.add(sQNode);

  while (!q.isEmpty()) {
   WeightedQueueNode qp = q.poll();
   WeightedNode p = graph.get(qp.node);

   for (WeightedEdge r : p.edges) {
    WeightedNode n = graph.get(r.node);

    int newDistance = qp.distance | r.weight;

    if (!n.unvisited && n.distances.contains(newDistance))
     continue;

    n.unvisited = false;
    n.distances.add(newDistance);

    WeightedQueueNode newNode = new WeightedQueueNode();
    newNode.distance = newDistance;
    newNode.node = r.node;
    q.add(newNode);
   }
  }

  final int targetNode = scanner.nextInt() - 1;
  int distance = -1;
  for (Integer i : graph.get(targetNode).distances)
   distance = distance == -1 ? i : Math.min(i, distance);

  System.out.println(distance);
 }

 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);

  beautifyPath(scanner);

  scanner.close();
 }
}