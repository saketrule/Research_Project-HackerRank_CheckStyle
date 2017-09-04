import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
 public static void main(String[] args) {
  try (Scanner scanner = new Scanner(System.in)) {
   int T = scanner.nextInt();
   
   for(int t = 0; t < T; t++) {
    int V = scanner.nextInt()+1;
    int E = scanner.nextInt();

    LinkedList<Integer>[] nodes = new LinkedList[V];
    
    int v1, v2;
    
    for(int i = 0; i < E; i++) {
     v1 = scanner.nextInt();
     v2 = scanner.nextInt();
     
     if(nodes[v1] == null) {
      nodes[v1] = new LinkedList<>();
     }
     
     if(nodes[v2] == null) {
      nodes[v2] = new LinkedList<>();
     }
     
     nodes[v1].add(v2);
     nodes[v2].add(v1);
    }
    
    int S = scanner.nextInt();
    
    int[] shortestPath = new int[V];
    
    findShortestPath(nodes, shortestPath, S);
    
    for(int i = 1; i < shortestPath.length; i++) {
     if(i != S) {
      System.out.print(shortestPath[i] + " ");
     }
    }
    System.out.println();
   }
  }
 }

 private static void findShortestPath(LinkedList<Integer>[] nodes, int[] shortestPath, int S) {
  Queue<Integer> queue = new PriorityQueue<>();
  boolean[] visited = new boolean [nodes.length];

  queue.add(S);
  visited[S] = true;
  
  int currentNode;
  Iterator<Integer> neighbors;
  
  boolean[] connected;
  
  int count = 2;
  
  while(!queue.isEmpty() && count < nodes.length) {
   currentNode = queue.poll();
   connected = new boolean[nodes.length];
   
   if(nodes[currentNode] != null) {
    neighbors = nodes[currentNode].iterator();
    
    while(neighbors.hasNext()) {
     connected[neighbors.next()] = true;
    }
   }
   
   for(int i = 1; i < connected.length; i++) {
    if(!visited[i] && !connected[i]) {
     queue.add(i);
     visited[i] = true;
     shortestPath[i] = shortestPath[currentNode]+1;
     count++;
    }
   }
  }
 }
}