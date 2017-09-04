import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

 /**
  * @param args
  */
 public static void main(String[] args) {
  Solution m = new Solution();
  m.start();
 }

 public void start() {
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt();
  int m = sc.nextInt();

  Node[] graph = new Node[n + 1];
  int counter = 0;
  for ( int a = 1 ; a < n + 1 ; a++){
   graph[a] = new Node();
  }
  
  while (counter < m && sc.hasNextInt()) {
   int a = sc.nextInt();
   int b = sc.nextInt();
   /*if (graph[a] == null) {
    graph[a] = new Node();
   }
   if (graph[b] == null) {
    graph[b] = new Node();
   }*/
   graph[a].adj.add(b);
   counter++;
  }

  Deque<Node> re = dfs(graph);
  Iterator<Node> ite = re.descendingIterator();
  
  while( ite.hasNext() ){
   Node v = ite.next();
   for ( Integer u : v.adj){
    if (u == n){
     v.value++;
    }else{
     v.value += graph[u].value;
    }
    if (v.value >=  1000000000){
     v.value = v.value % 1000000000;
    }
   }
  }
  System.out.println(graph[1].value);
 }

 public Deque<Node> dfs(Node[] graph) {
  Deque<Node> result = new LinkedList<Solution.Node>();
  for (Node node : graph) {
   if (node == null) {
    continue;
   }
   if (node.color == 0) {
    dfsVisit(graph, node, result);
   }
  }
  return result;
 }

 /**
  * returns wether or not there is a connection to the endpoint
  * @param graph
  * @param u
  * @param topological
  * @return
  */
 public boolean dfsVisit(Node[] graph, Node u, Deque<Node> topological) {
  u.color = 1;
  boolean cycle = false;
  boolean toEnd = false;
  for (Integer v : u.adj) {
   if ( v == graph.length-1){
    toEnd=true;
   }
   // WHITE
   if (graph[v].color == 0) {
    graph[v].parent = u;
    toEnd =  dfsVisit(graph, graph[v], topological) || toEnd;
   }
   // GRAY
   if (graph[v].color == 1) {
    cycle = true;
   }

  }
  //BLACK, DONE
  u.color = 2;
  topological.addFirst(u);
  if ( toEnd && cycle){
   System.out.println("INFINITE PATHS");
   System.exit(0);
  }
  return toEnd;
 }

 class Node {
  int value = 0;
  byte color = 0;
  Node parent = null;
  List<Integer> adj = new ArrayList<Integer>();  
 }

}