import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
 
 
    public static void main(String[] args) throws IOException{
     BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
     final int MAX = 10000000;
     
     
     String line;
     line = bi.readLine();
     String[] T1 = line.split(" ");
     int x,y,r,a,b,N = Integer.parseInt(T1[0]);
     int M = Integer.parseInt(T1[1]);
     
     Graph G = new Graph(N);
     
     for (int i = 0; i<M; i++){
      line = bi.readLine();
      
      String[] T2 = line.split(" ");
      x = Integer.parseInt(T2[0]);
      y = Integer.parseInt(T2[1]);
      r = Integer.parseInt(T2[2]);
      
      G.AddEdge(x-1,y-1,r);
     }
    
     G.Floyd();
     
     int Q = Integer.parseInt(bi.readLine());
     
     for (int i = 0; i<Q; i++){
      line = bi.readLine();
      String[] T3 = line.split(" ");
      
      a = Integer.parseInt(T3[0]);
      b = Integer.parseInt(T3[1]);
      
      if (G.Cost[a-1][b-1] != MAX)
       System.out.println(G.Cost[a-1][b-1]);
      else
       System.out.println(-1);
     }
    }
    
}

class Graph{
 int V;
 int[][] Cost;
 final int MAX = 10000000;
 
 public Graph(int V){
  this.V = V;
  Cost = new int[V][V];
  
  for (int i = 0; i<V; i++)
   Cost[i][i] = 0;
  
  for (int i = 0; i<V; i++){
   for (int j = 0; j<V; j++){
    if (i != j){
     Cost[i][j] = MAX;
    }
   }
  }
 }
 
 public void AddEdge(int U,int V,int C){
  Cost[U][V] = C;
 }
 
 public void Floyd(){
  for (int k = 0; k<V; k++)
      for (int i = 0; i<V; i++)
         for (int j = 0; j<V; j++)
            if (Cost[i][j] > Cost[i][k] + Cost[k][j]) 
               Cost[i][j] = Cost[i][k] + Cost[k][j];
 }
 
 public void print(){
  for (int i = 0; i<V; i++){
   for (int j = 0; j<V; j++)
    System.out.format("%d ",Cost[i][j]);
   System.out.println();
  }
 }
 
}