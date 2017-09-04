import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 static int k, n, m, states;
 static int ans = Integer.MAX_VALUE;
 static PriorityQueue<Path> q = new PriorityQueue();

 static class Path implements Comparable<Path> {
  final int distance;
  final Fishes tillNow;
  final Node current;

  public Path(int d, Fishes f, Node n) {
   distance = d;
   tillNow = f;
   current = n;
  }

  public boolean conclude() {
   if (current == nodes[n - 1]) {
    /*System.out.println("poped path to end with distance = "
      + distance);*/
    int temp = tillNow.toInt();
    int minimum = Integer.MAX_VALUE;
    for (int i = 0; i < states; i++) {
     if ((current.sD[i] < Integer.MAX_VALUE)
       && ((temp | i) >= (states - 1))) {
      minimum = Math.min(minimum, current.sD[i]) ;      
     }
    }
    if(minimum <= distance){
     ans = Math.max(distance, minimum);
    /*System.out.println("final ans . path distance = "
      + distance+" path fish = "+temp);*/
    return true;
    }
   }

   for (Edge e : current.edges) {
    int probable = distance + e.dist;
    if (probable < e.dest.sD[Fishes.jointoint(tillNow, e.dest.fish)]) {
     q.add(new Path(probable, new Fishes(tillNow, e.dest.fish),
       e.dest));
     /*System.out.println("cadded path with distance " + probable +" ending at "+e.dest.index+ "  with fishes  "
       +Fishes.jointoint(tillNow, e.dest.fish));*/
     e.dest.sD[Fishes.jointoint(tillNow, e.dest.fish)] = probable;
    }
   }
   return false;
  }

  @Override
  public int compareTo(Path arg0) {
   // TODO Auto-generated method stub
  /* if((current.index != n-1 && arg0.current.index!=n-1)|| (current.index == n-1 && arg0.current.index==n-1))
   return distance - arg0.distance;
   else if (current.index == n-1)
    return -1;
   else
    return 1;*/
   if(distance == arg0.distance){
     if (current.index == n-1)
      return -1;
   }
   return distance - arg0.distance;
  }
 }

 static class Fishes {
  boolean type[];

  public Fishes() {
   type = new boolean[k];
  }

  public Fishes(Fishes a) {
   type = new boolean[k];
   for (int i = 0; i < k; i++) {
    type[i] = a.type[i];
   }
  }

  public Fishes(int ar[]) {
   type = new boolean[k];
   for (int i = 0; i < ar.length; i++)
    type[ar[i] - 1] = true;
  }

  public Fishes(Fishes a, Fishes b) {
   type = new boolean[k];
   for (int i = 0; i < k; i++) {
    type[i] = a.type[i] || b.type[i];
   }
  }

  public int toInt() {
   String s = "";
   for (int i = k - 1; i >= 0; i--) {
    if (type[i])
     s = s.concat("1");
    else
     s = s.concat("0");
   }
   return Integer.parseInt(s, 2);
  }

  public static int jointoint(Fishes a, Fishes b) {
   Fishes f = new Fishes(a, b);
   int ans2 = f.toInt();
   f = null;
   return ans2;
  }

  public void print() {
   System.out.print(" : ");
   for (int i = 0; i < k; i++) {
    System.out.print(type[i] + " ");
   }
  }
 }

 static class Node {
  final int sD[]; // state distance from node 0
  Fishes fish;
  final ArrayList<Edge> edges;
  final int index;

  public Node(int in) {
   sD = new int[states];
   for (int i = 0; i < states; i++)
    sD[i] = Integer.MAX_VALUE;
   edges = new ArrayList();
   index = in;
  }

  public void addEdge(int distance, int dest) {
   edges.add(new Edge(distance, nodes[dest]));
  }

  public void print() {
   for (int i = 0; i < states; i++)
    System.out.print(sD[i] + " ");
   fish.print();
   System.out.println();
  }
 }

 static class Edge {
  final int dist;
  final Node dest;

  public Edge(int di, Node de) {
   dist = di;
   dest = de;
  }
 }

 static Node nodes[];

 public static void main(String[] args) {
  /*
   * fEnter your code here. Read input from STDIN. Print output to STDOUT.
   * Your class should be named Solution.
   */

  Scanner sc = new Scanner(System.in);
  n = sc.nextInt();
  m = sc.nextInt();
  k = sc.nextInt();
  states = (int) (Math.pow(2, k));

  nodes = new Node[n];
  for (int i = 0; i < n; i++) {
   nodes[i] = new Node(i);
  }
  for (int i = 0; i < n; i++) {
   int num = sc.nextInt();
   int ar[] = new int[num];
   for (int j = 0; j < num; j++) {
    ar[j] = sc.nextInt();
   }
   nodes[i].fish = new Fishes(ar);
  }
  int f, s, d;
  for (int i = 0; i < m; i++) {
   f = sc.nextInt();
   s = sc.nextInt();
   d = sc.nextInt();
   nodes[f - 1].addEdge(d, s - 1);
   nodes[s - 1].addEdge(d, f - 1);
  }
  nodes[0].sD[nodes[0].fish.toInt()] = 0;
  for (Edge e : nodes[0].edges) {
   q.add(new Path(e.dist, new Fishes(e.dest.fish, nodes[0].fish),
     e.dest));
   /*System.out.println("added path with distance " + e.dist
     + " ending at " + e.dest.index + " with fishes "
     + Fishes.jointoint(e.dest.fish, nodes[0].fish));*/
   e.dest.sD[Fishes.jointoint(e.dest.fish, nodes[0].fish)] = e.dist;
  }
  Path min = q.poll();

  while (min != null) {
   /*System.out.println("removed path with distance " + min.distance
     + " ending at " + min.current.index + " with fishes "
     + min.tillNow.toInt());*/
   if (min.conclude())
    break;
   min = q.poll();
  }
  // for(int i=0;i<n;i++)
  // nodes[i].print();
  System.out.println(ans);
//  nodes[n - 1].print();
 }
}