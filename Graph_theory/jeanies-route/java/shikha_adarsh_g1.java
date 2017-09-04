import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

   private final static int MAX = 1000000;
 private static int minPath = MAX;


 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int N = in.nextInt();
  int K = in.nextInt();
  int[] destinations = new int[K];
  for(int i=0; i<K;i++){
   destinations[i]=in.nextInt()-1;
  }

  int[][] graph = initializeGraph(in, N, N-1);
  int[][] distGraph = initializeGraph(in, N, N-1);
  for(int i=0; i<N-1;i++){
   int src = in.nextInt()-1;
   int dest = in.nextInt()-1;
   int weight = in.nextInt();
   graph[src][dest]=weight;
   graph[dest][src]=weight;
  }

  //findGraphDistancesFW(in, graph, N-1);


  for(int v: destinations){
   int[] dist = findGraphDistancesDJ( graph, v);
   for(int i =0; i<N; i++){
    if(distGraph[i][v]>dist[i]){
     distGraph[i][v]=dist[i];
     distGraph[v][i]=dist[i];
    }
   }
  }

  //findGraphDistancesFW(in, graph, N-1);

  /*for(int i=0; i<N; i++){
   for(int j=0; j<N;j++){
    if(distGraph[i][j]!=MAX)
    System.out.println(i+", "+j+": "+distGraph[i][j]+":"+graph[i][j]);
   }
  }*/

  //int minPath =MAX;
  //System.out.println((int)fact(K));
  int[][] permutations =  new int[1][1];

  permutations(destinations, 0, permutations, 0, distGraph);

  /*for(int[] path: permutations){
   int d= calculateDistance(distGraph, path);
   System.out.println(Arrays.toString(path)+": "+d);
   if(d<minPath){
    minPath =d;
   }
  }*/
  System.out.println(minPath);

 }

 private static int calculateDistance(int[][] distGraph, int[] path) {
  int dist =0;
  for(int i=1; i<path.length; i++){
   dist+=distGraph[path[i-1]][path[i]];
  }
  return dist;
 }

 private static void permutations(int[] destinations, int start, int[][] permutations, int index, int[][] distGraph) {
  int end = destinations.length;
  int K = end-start;

  if(K <=1){
   int path = calculateDistance(distGraph, destinations);
   
   if(path<minPath){
    //System.out.println(Arrays.toString(destinations)+" :"+path);
    minPath =path;
   }
  }else{
   for(int i=start; i<end; i++){
    swap(destinations, start, i);
    permutations(destinations, start+1, permutations, index, distGraph);
    swap(destinations, i, start);
   }
  }
 }

 private static void swap(int[] destinations, int start, int i) {
  int temp = destinations[start];
  destinations[start]= destinations[i];
  destinations[i]= temp;

 }

 private static long fact(int k) {
  long fact =1;
  for(int i=1; i<=k; i++){
   fact=fact*i;
  }
  return fact;
 }

 private static int[] findGraphDistancesDJ(int[][] graph, int v) {


  int N = graph.length;
  NQueue queue = new NQueue();
  int[] dist = new int[N];
  int[] previous = new int[N];
  for(int i=0; i<N; i++){
   dist[i]=MAX;
   previous[i]=-1;   
  }
  dist[v]=0;
  queue.enqueue(v);
  while(!queue.isEmpty()){
   QNode node = queue.dequeue();
   int val = node.elem;
   for(int i=0; i<N; i++){
    if(graph[val][i]!=0 && graph[val][i]!=MAX){
     if(dist[i]>dist[val]+graph[val][i]){
      dist[i] = dist[val]+graph[val][i];
      queue.enqueue(i);     
     }
    }
   }
  }
  return dist;

 }

 private static int findMinPath(int[][] graph, int[] destinations, int i2) {
  int[] traversed = new int[graph.length];
  int minPath =0;
  int i=0;

  int j =destinations[i2];
  while(i<destinations.length){
   System.out.print(j+" ");
   traversed[j]=1;
   int next= getNextNode(graph,traversed,destinations, j);
   if(next != -1){
    minPath+=graph[j][next];
    j=next;
    i++;
   }else{
    break;
   }   
  }


  return minPath;
 }

 private static int getNextNode(int[][] graph, int[] traversed, int[] destinations, int i) {
  int minI =MAX;
  int next =-1;
  for(int j: destinations){
   if(i!=j && traversed[j]==0){
    if(graph[i][j]<minI){
     minI = graph[i][j];
     next = j;
    }
   }
  }
  return next;
 }

 private static void findGraphDistancesFW(Scanner in, int[][] graph, int e) {
  int N = graph.length;

  for(int k =0; k<N;k++){
   for(int i=0; i<N; i++){
    for(int j=0; j<N; j++){
     if(graph[i][j]>graph[i][k]+graph[k][j]){
      graph[i][j]= graph[i][k]+graph[k][j];
     }
    }
   }
  }

 }

 private static int[][] initializeGraph(Scanner in, int N, int E) {
  int[][] graph= new int[N][N];
  for(int i=0; i<N; i++){
   for(int j =0; j<N; j++){
    if(i!=j){
     graph[i][j]= MAX;
    }
   }
  }


  return graph;
 }

}
class NQueue {
 QNode first;
 QNode last;
 void enqueue(int elem){
  QNode node = new QNode(elem);
  if(isEmpty()){
   first = node;
  }else{
   last.next = node;
  }
  last =node;
 }

 QNode dequeue(){
  QNode node =null;
  if(!isEmpty()){
   node = first;
   first = first.next;
   node.next =null;
  }
  return node;
 }

 boolean isEmpty(){
  if(first ==null){
   return true;
  }else
   return false;
 }
}
class QNode {
 public QNode(int elem) {
  this.elem =elem;
 }
 int elem; 
 QNode next;
}