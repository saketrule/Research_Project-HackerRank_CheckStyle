import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
  int n = in.nextInt();
  Node[] nodes = new Node[n+1];
  Graph g = new Graph();
  Graph.instance = g;
  for (int i=0, id=1; i<n; i++) {
   String type = in.next();
   int x = in.nextInt();
   if (type.equals("A")) {
    Node node = new Node();
    node.id = id;
    node.number = x;
    nodes[id] = node;
    node.relates.add(node);
    node.top.add(node);
    g.sets.add(node.top);
    id++;
   } else if (type.equals("B")) {
    int y = in.nextInt();
    Edge edge = new Edge();
    edge.x = nodes[x];
    edge.y = nodes[y];
    g.edges.add(edge);
    nodes[x].directs.add(nodes[y]);
    nodes[y].directs.add(nodes[x]);
    
    nodes[x].relates.addAll(nodes[y].relates);
    for (Node node : nodes[y].relates)
     node.relates = nodes[x].relates;
    g.sets.remove(nodes[y].top);
    nodes[x].top.add(nodes[y]);
    nodes[y].top = nodes[x].top;
   } else {
    g.sets.remove(nodes[x].top);
    Node node = new Node();
    nodes[id] = node;
    node.id = id;
    node.number = 0;
    node.children.addAll(nodes[x].relates);
    node.relates = nodes[x].relates;
    nodes[x].relates.add(node);
    node.top.add(node);
    g.sets.add(node.top);
    id++;
   }
  }
  in.close();
        System.out.println(find(g));
    }
    
    public static int find(Graph graph) {
  int sum = 0;
  for (Set<Node> set : graph.sets) {
   sum += find(set);
  }
  return sum;
 }
 
 public static int find(Set<Node> set) {
  int max = 0;
  for (Node node : set) {
   if (node.max==-1) {
    if (node.children.size() > 0)
     node.max = find(node.children);
    else
     node.max = node.number;
   }
  }
  
  for (Node node : set) {
   Set<Node> set1 = new HashSet<Node>();
   set1.addAll(set);
   set1.remove(node);
   Set<Node> set2 = new HashSet<Node>();
   set2.addAll(set1);
   
   int max1 = find(set1);
   int max2 = findMax(set2, node);
   return Math.max(max1, max2);
  }
  return max;
 }
 
 public static int findMax(Set<Node> set, Node node) {
  int max = node.max;
  for (Node direct : node.directs)
   set.remove(direct);
  max += find(set);
  return max;
 }
}

class Graph {
 public static Graph instance = null; 
 List<Set<Node>> sets = new ArrayList<Set<Node>>();
 List<Edge> edges = new ArrayList<Edge>();
}

class Edge {
 Node x = null;
 Node y = null;
}

class Node {
 int id = 0;
 int number = 0;
 int max = -1;
 Set<Node> children = new HashSet<Node>();
 Set<Node> relates = new HashSet<Node>();
 Set<Node> top = new HashSet<Node>();
 Set<Node> directs = new HashSet<Node>();
}