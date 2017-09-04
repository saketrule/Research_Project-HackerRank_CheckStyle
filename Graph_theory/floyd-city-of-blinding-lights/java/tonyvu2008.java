import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     final String SPACES = "\\s+"; 
     
     String firstLine = scanner.nextLine();
     String[] graph = firstLine.split(SPACES);
     
     int n = Integer.parseInt(graph[0]);//Number of nodes
     int m = Integer.parseInt(graph[1]);//Number of edges
     
     int[][] weight = new int[n][n];
     for (int i = 0; i < n; i++) {
      for (int j = 0;j <n;j++) {
       weight[i][j] = Integer.MAX_VALUE;
      }
     }
     
     for (int i = 0; i< m;i++) {
      String line = scanner.nextLine();
      String[] lines = line.split(SPACES);
      int v1 = Integer.parseInt(lines[0]);
      int v2 = Integer.parseInt(lines[1]);
      int w = Integer.parseInt(lines[2]);
      
      weight[v1-1][v2-1] = w;
     }
     
     String questionLine = scanner.nextLine();
     int q = Integer.parseInt(questionLine);//Number of questions
     int[] vList1 =  new int[q]; 
     int[] vList2 =  new int[q]; 
     for (int i = 0; i < q; i++) {
      String line = scanner.nextLine();
      String[] lines = line.split(SPACES);
      vList1[i] = Integer.parseInt(lines[0])-1;
      vList2[i] = Integer.parseInt(lines[1])-1;
     }
     
     long[][] results = computeShortestPath(n, weight);
     
     for (int i=0;i<q;i++) {
      long result = results[vList1[i]][vList2[i]];
      if (result < Integer.MAX_VALUE) {
       System.out.println(result);
      } else {
       System.out.println("-1");
      }
     }
     
     scanner.close();
    }
    
    private static long[][] computeShortestPath(int n, int[][] weight) {
     long[][] dist = new long[n][n];
     
     for (int i = 0; i < n; i++) {
      for (int j = 0;j <n;j++) {
       if ( i == j) {
        dist[i][j] = 0;
       } else {
        dist[i][j] = weight[i][j];
       }
      }
     }
     
     for (int k = 0; k<n;k++) {
      for (int i = 0; i < n; i++) {
          for (int j = 0;j <n;j++) {
           if (dist[i][j] > dist[i][k] + dist[k][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
           }
          }
      }
     }
     
     return dist;
    }
}