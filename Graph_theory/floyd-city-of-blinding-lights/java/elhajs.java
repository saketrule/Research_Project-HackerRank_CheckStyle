import java.util.Scanner;

public class Solution {
 
 
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int N = in.nextInt();
  int M = in.nextInt();
  
  int[][] graph = new int[N][N];
  for (int i = 0; i < graph.length; i++) {
   for (int j = 0; j < graph.length; j++) {
    graph[i][j] = 1000000;
   }
   graph[i][i] = 0;
  }
  for (int i = 0; i < M; i++) {
   int x = in.nextInt() - 1;
   int y = in.nextInt() - 1;
   int r = in.nextInt();
      graph[x][y] = r;
   
  }
  
  
  for (int k = 0; k < N; k++) {
   for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
     if (graph[i][j] > graph[i][k] + graph[k][j] )
        graph[i][j] = graph[i][k] + graph[k][j];
    }
   }
  }
  int Q = in.nextInt();
  for (int i = 0; i < Q; i++) {
   int x = in.nextInt() - 1;
   int y = in.nextInt() - 1;
   if (graph[x][y] == 1000000) {
    System.out.println(- 1);
    
   }
   else System.out.println(graph[x][y]);
  }
  
  
 }
}