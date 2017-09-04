import java.util.Scanner;


public class Solution {


 public static void main(String[] args){



  Scanner sc = new Scanner(System.in);

  int TC = 1 ;//sc.nextInt() ;

  for(int i = 0 ; i < TC ; i++) {

   int n = sc.nextInt() ;
   int m = sc.nextInt() ; 

   int[][] graph  = new int[n][n];

   for(int j =  0 ; j < n ; j++) {
    for(int k =  0 ; k < n ; k++) {
     graph[j][k] = -1;
    }
   }
   for(int j =  0 ; j < m ; j++) {
    int u = sc.nextInt() -1 ;
    int v = sc.nextInt() -1 ; 
    int w = sc.nextInt() ; 
    graph[u][v] = w ;

   }


   int Q = sc.nextInt() ; 
   int[][] distance  = new int[n][n];

   for(int j =  0 ; j < n ; j++) {
    for(int k =  0 ; k < n ; k++) {
     distance[j][k] = -1;
    }
   }

   for(int j = 0 ; j < Q ; j++) {

    int s = sc.nextInt() -1 ; 
    int d = sc.nextInt() -1 ; 
    if(distance[s][s] != 0) {
     Dijkastra1 dist = new Dijkastra1(graph, n);
     dist.getShortedPathFromGivenSource(s,distance);
    }
    if(distance[s][d] == Integer.MAX_VALUE || distance[s][d] < 0)
     System.out.println( "-1 ");
    else
     System.out.println(distance[s][d]+ " ");
   }
  }
 }
}

class Dijkastra1 {


 int[][] mat ;
 int N ;
 boolean[] visited ;
 int[] dist ;

 public Dijkastra1(int[][] m , int n) {

  mat = m ;
  N = n ;
  visited = new boolean[N];
  dist = new int[N] ;
  for(int i = 0 ; i < N ; i++) {
   dist[i] = Integer.MAX_VALUE ;
  }
 }

 public int[] getShortedPathFromGivenSource(int s,int[][] distance) {

  dist[s] = 0 ;
  distance[s][s] = 0 ;

  for(int i = 0 ; i < N-1 ; i++) {

   int u = min() ;
   visited[u] = true ;

   for(int v = 0 ; v < N ; v++){
    if(!visited[v] && mat[u][v]!= -1 && dist[v] > dist[u] + mat[u][v] ) {
     dist[v] = dist[u] + mat[u][v] ;
     distance[s][v] = dist[v];
    } 
   }
  }
  return dist ;
 }

 private int min() {

  int min = Integer.MAX_VALUE ;
  int index = 0 ;

  for(int i = 0 ; i < N ; i++) {
   if(!visited[i] && min > dist[i]) {
    index = i ;
    min = dist[i] ;
   }
  }

  return index;
 }




}