import java.util.Scanner;

import org.omg.CORBA.INTERNAL;


public class CityOfLights {

 /**
  * @param args
  */
 
 //FLOYD WARSHELL ALGO
 public static int max = 99999; 
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  Scanner sc = new Scanner(System.in);
  int i, j, k, w, V, E, Q;
  V = sc.nextInt();
  E = sc.nextInt();
  int [][] graph = new int[V][V];  
  for (i = 0; i < E; i++) {
   j = sc.nextInt();
   k = sc.nextInt();
   w = sc.nextInt();   
   graph[j-1][k-1] = w;
  }
  
  int [][] dist = floydWarshell(graph, V);
  Q = sc.nextInt();
  int [][] qArr = new int[Q][2];
  for (int l = 0; l < Q; l++) {
   qArr[l][0] = sc.nextInt();
   qArr[l][1] = sc.nextInt();
  }
  for (int l = 0; l < Q; l++) {
   if (dist[qArr[l][0]-1][qArr[l][1]-1] == max)
    System.out.println(-1);
   else
    System.out.println(dist[qArr[l][0]-1][qArr[l][1]-1]);
  }
  
  sc.close();  
 }
 
 public static int [][] floydWarshell(int [][] graph, int V){
  int [][] dist = new int[V][V];
  /* Initialize the solution matrix same as input graph matrix. Or 
        we can say the initial values of shortest distances are based
        on shortest paths considering no intermediate vertex. */
  for (int i = 0; i < V; i++) {
   for (int j = 0; j < V; j++) {
    dist[i][j] = (graph[i][j]>0)?graph[i][j]:(i==j)?0:max; 
   }   
  }
  
   /* Add all vertices one by one to the set of intermediate vertices.
       ---> Before start of a iteration, we have shortest distances between all
       pairs of vertices such that the shortest distances consider only the
       vertices in set {0, 1, 2, .. k-1} as intermediate vertices.
       ----> After the end of a iteration, vertex no. k is added to the set of
       intermediate vertices and the set becomes {0, 1, 2, .. k} */
   for (int k = 0; k < V; k++) {
    // Pick all vertices as source one by one
       for (int i = 0; i < V; i++) {
        // Pick all vertices as destination for the above picked source
        for (int j = 0; j < V; j++) {
           // If vertex k is on the shortest path from
           // i to j, then update the value of dist[i][j]
         if (dist[i][k] + dist[k][j] < dist[i][j])
          dist[i][j] = dist[i][k] + dist[k][j];
        }
       }
   }
   
   return dist;
 } 
}