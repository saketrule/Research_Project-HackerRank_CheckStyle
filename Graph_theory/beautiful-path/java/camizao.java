import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 static int[][] G;
 static int N, M, A, B;
 static boolean[][] DP;
 static boolean[] V;
 static long Answer = Integer.MAX_VALUE;
 
    static void dfs(int node, int penalty) {
     if (DP[node][penalty] != false || penalty >= Answer) {
      V[node] = false;
      return;
     } 
     if (node == B) {      
      if (penalty < Answer) {
       Answer = penalty;
      }
      V[node] = false;
      return;      
     }
     
     DP[node][penalty] = true;
     
     for (int i = 1; i <= N; i++) {
      if (G[node][i] != 0 && V[i] == false) {
       V[i] = true;
       dfs(i, penalty | G[node][i]);
      }
     }
     
     V[node] = false;
    }  
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
     
     N = in.nextInt();
     M = in.nextInt();
     
     G = new int[N + 1][N + 1];
     V = new boolean[N + 1];
        DP = new boolean[1024][1024];
     
     for (int i = 0; i < M; i++) {
      int u = in.nextInt();
      int v = in.nextInt();
      int c = in.nextInt();
      
      if (G[u][v] == 0 || G[u][v] > c) {
       G[u][v] = c;
       G[v][u] = c;
      }
     }
     
     A = in.nextInt();
     B = in.nextInt();
     
     V[A] = true;
     
     dfs(A, 0);
     
     System.out.println(Answer == Integer.MAX_VALUE ? -1 : Answer);
     
     in.close();
 }
}