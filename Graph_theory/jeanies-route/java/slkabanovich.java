import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 static class Node {
  int id;
  boolean relevant = true;
  boolean delivery = false;
  Map<Integer, Integer> transitions = new HashMap<Integer, Integer>();
  
  Node(int id) {
   this.id = id;
  }
  
  void validate() {
   if(!relevant || delivery || transitions.size() > 1) return;
   relevant = false;
   if(!transitions.isEmpty()) {
    int v = transitions.keySet().iterator().next();
    transitions.clear();
    nodes[v].transitions.remove(id);
    nodes[v].validate();
   }
  }
 }
 
 static int n;
 static Node[] nodes;
 
 static void setN(int n) {
  Solution.n = n;
  nodes = new Node[n];
  for (int i = 0; i < n; i++) {
   nodes[i] = new Node(i);
  }
 }
 
 static void reduce() {
  for (int i = 0; i < nodes.length; i++) {
   nodes[i].validate();
  }
 }
 
 static long sumAllDistances() {
  long result = 0;
  for (int i = 0; i < nodes.length; i++) {
   if(nodes[i].relevant) {
    for (int d: nodes[i].transitions.values()) {
     result += d;
    }
   }
  }
  return result;
 }
 
 static class Longest {
  int from;
  int to;
  long distance;
  Longest(int from, int to, long distance) {
   this.from = from;
   this.to = to;
   this.distance = distance;
  }
 }
 
 static Longest findLongestPath(int from) {
  long longest = 0;
  int vL = -1;
  long[] ps = new long[nodes.length];
  for (int i = 0; i < nodes.length; i++) {
   ps[i] = -1;
  }
  ps[from] = 0;
  List<Integer> stack = new ArrayList<>();
  stack.add(from);  
  int c = 0;
  while(c < stack.size()) {
   int u = stack.get(c);
   for (int v: nodes[u].transitions.keySet()) {
    int d = nodes[u].transitions.get(v);
    if(ps[v] < 0 || ps[u] + d < ps[v]) {
     ps[v] = ps[u] + d;
     if(ps[v] > longest) {
      longest = ps[v];
      vL = v;
     }
     stack.add(v);
    }
   }
   c++;
  }
  return new Longest(from, vL, longest);
 }
 
 static long findLongestPath() {
  int from = -1;
  for (int u = 0; u < nodes.length; u++) {
   if(nodes[u].relevant && nodes[u].transitions.size() > 1) {
    from = u;
    break;
   }
  }
  if(from < 0) return 0;
  Longest l = findLongestPath(from);
  l = findLongestPath(l.to);
  return l.distance;
 }
 
 static long solve() {
  return sumAllDistances() - findLongestPath();
 }
 
 static void run() {
  Scanner scanner = new Scanner(System.in);
  int n = scanner.nextInt();
  setN(n);
  int k = scanner.nextInt();
  for (int i = 0; i < k; i++) {
   int u = scanner.nextInt();
   nodes[u - 1].delivery = true;
  }
  for (int i = 0; i < n - 1; i++) {
   int u = scanner.nextInt();
   int v = scanner.nextInt();
   int d = scanner.nextInt();
   nodes[u - 1].transitions.put(v - 1, d);
   nodes[v - 1].transitions.put(u - 1, d);
  }
  reduce();
  System.out.println(solve());
  scanner.close();
 }

    public static void main(String[] args) {
        run();
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}