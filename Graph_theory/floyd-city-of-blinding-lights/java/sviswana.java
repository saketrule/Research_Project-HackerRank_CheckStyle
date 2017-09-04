import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Solution {
 static class Pair {
  int dist = Integer.MAX_VALUE;
  int vertex;
 }
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int n = in.nextInt();

  int numEdges = in.nextInt();
  int[][] graph = new int[n+1][n+1];
  //ArrayList<int[]>[] graph = (ArrayList<int[]>[]) new ArrayList[n + 1];
  /*for (int j = 0; j < graph.length; j++) {
    graph[j] = new ArrayList<int[]>();
   }*/
  for (int i = 0; i < n+1; i++) {
   for (int j = 0; j < n+1; j++) {
    if (i==j) {
     graph[i][j] = 0;
    }
    else {
     graph[i][j] = 99999;
    }
   }
  }
  for (int i = 0; i < numEdges; i++) {
   int a = in.nextInt();
   int b = in.nextInt();
   int dist = in.nextInt();    
   graph[a][b] = dist;    
  }
  for (int k = 0; k < n+1; k++)
  {
   // Pick all vertices as source one by one
   for (int i = 0; i < n+1; i++)
   {
    // Pick all vertices as destination for the
    // above picked source
    for (int j = 0; j < n+1; j++) {

     if (graph[i][k] + graph[k][j] < graph[i][j]) {
      graph[i][j] = graph[i][k] + graph[k][j];
      
     }
     
    }
   }
  }

  int numQueries = in.nextInt();

  while (numQueries-- >0) {
      int start = in.nextInt();
   int end = in.nextInt();
   if (graph[start][end] >= 99999) {
    
    System.out.println(-1);
   }
   else {
    System.out.println(graph[start][end]);
   }
   


   //bfs(start, adj, n, visited,distance);

  }


 }
}