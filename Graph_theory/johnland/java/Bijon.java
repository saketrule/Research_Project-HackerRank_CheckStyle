import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 public static int findMin(int[] distance, boolean[] visited){
  int min = Integer.MAX_VALUE;
  int k = -1;
  for (int i = 0; i < distance.length; i++) {
   if(min>distance[i] && visited[i] == false){
    min = distance[i];
    k = i;
   }
  }
  return k;
 }
 
 public static int findShortestDist(int source, int[][] adjMat){
  int totalDist = 0;
  int[] dist = new int[adjMat.length];
  boolean[] visited = new boolean[adjMat.length];
  for (int j = 0; j < dist.length; j++) {
   dist[j] = Integer.MAX_VALUE;
  }
  dist[source] = 0;

  int currNode;
  int[] ngbrList = new int[adjMat.length];
  
  for (int j = 0; j < dist.length; j++) {
   currNode = findMin(dist, visited);
   if(currNode < 0)
    continue;
   visited[currNode] = true;
   
   ngbrList = adjMat[currNode];
   for (int k = 0; k < ngbrList.length; k++) {
    if(ngbrList[k] > 0){
     dist[k] = Math.min(dist[k], dist[currNode]+adjMat[currNode][k]);
    }
   }

  }

  for (int i = source; i < dist.length; i++) {
   totalDist += dist[i];
  }
  return totalDist;
 }

 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt();
  int m = sc.nextInt();
  int[][] adjMat = new int[n][n];
  
  int u, v, d;
  
  for (int j = 0; j < adjMat.length; j++) {
   for (int j2 = 0; j2 < adjMat[0].length; j2++) {
    adjMat[j][j2] = Integer.MAX_VALUE;
   }
  }
  
  for (int i = 0; i < m; i++) {
   u = sc.nextInt()-1;
   v = sc.nextInt()-1;
   d = sc.nextInt();
   
   adjMat[u][v] = Math.min(adjMat[u][v], (int)Math.pow(2, d));
   adjMat[v][u] = Math.min(adjMat[u][v], adjMat[v][u]);
  }
  
  for (int j = 0; j < adjMat.length; j++) {
   for (int j2 = 0; j2 < adjMat[0].length; j2++) {
    if(adjMat[j][j2] == Integer.MAX_VALUE)
    adjMat[j][j2] = 0;
   }
  }
  
  
  int totalDist = 0;
  int sDist = 0;
  
  for (int i = 0; i < n -1; i++) {
   sDist = findShortestDist(i, adjMat);
   totalDist += sDist;
  }
  System.out.println(Integer.toBinaryString(totalDist));
    }
}