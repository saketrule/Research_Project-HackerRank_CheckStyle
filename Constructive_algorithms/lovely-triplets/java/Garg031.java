import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
static ArrayList<Node> nodes = new ArrayList<>();
 static ArrayList<Edge> edges = new ArrayList<>();
 static ArrayList<Node> endNodes = new ArrayList<>();

 static void createCircle(Node a,  int d) {
  Node prev = a;
  for (int i = 0; i < d ; i++) {
   Node curr = new Node();
   nodes.add(curr);
   edges.add(new Edge(prev, curr));
   prev = curr;
  }
  edges.add(new Edge(prev, a));
 }

 static void createLine(Node s, int d) {
  Node prev = s;
  for (int i = 0; i < d; i++) {
   Node curr = new Node();
   nodes.add(curr);
   edges.add(new Edge(prev, curr));
   prev = curr;
  }
  endNodes.add(prev);
 }

 static void createGraph(int p, int q) {
  if (q % 2 == 0) {
   createEven(p, q);
  } else {
   createOdd(p, q);
  }
  printGraph();
 }

 private static void createOdd(int p, int q) {
  Node a = new Node();
  nodes.add(a);
  createCircle(a, 2*((q/2)+1)+q-1);
  for(int i = 0; i < p; i++)
  createLine(a, q/2);
 }

 private static void printGraph() {
  System.out.println(nodes.size() + " " + edges.size());
  for (Edge a : edges) {
   System.out.println(a.startNode + " " + a.endNode);
  }
 }

 static private void createEven(int p, int q) {
  if (endNodes.size() == 0) {
   Node a = new Node();
   nodes.add(a);
   createLine(a, q / 2);
   createLine(a, q / 2);
   createLine(a, q / 2);
  }
  for (int i = 1; i < p; i++) {
   Node n = endNodes.get(endNodes.size() - 1);
   createLine(n, q / 2);
   createLine(n, q / 2);
  }
 }

 public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        int Q = in.nextInt();
    createGraph(P,Q);
    }
}
class Node {
 static int seq;
 int id;
 ArrayList<Node> adjecentNodes = new ArrayList<>();

 Node() {
  seq++;
  id = seq;
 }

 @Override
 public String toString() {
  return id + "";
 }
}

class Edge {
 Node startNode;
 Node endNode;

 Edge(Node s, Node e) {
  this.startNode = s;
  this.endNode = e;
  s.adjecentNodes.add(e);
  e.adjecentNodes.add(s);
 }
}