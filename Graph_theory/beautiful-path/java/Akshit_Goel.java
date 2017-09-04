import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
     Scanner sc = new Scanner(System.in);
     int N = sc.nextInt();
     int M = sc.nextInt();
     Vertix[] graph = new Vertix[N+1];
     for(int i=1;i<=N;i++){
      graph[i] = new Vertix();
      graph[i].weight = Integer.MAX_VALUE;
      graph[i].linkedList = new LinkedList<Edge>();
     }
     for(int i=0;i<M;i++){
      int u = sc.nextInt();
      int v = sc.nextInt();
      int c = sc.nextInt();
      graph[u].linkedList.add(new Edge(v,c));
      graph[v].linkedList.add(new Edge(u,c));
     }
     int s = sc.nextInt();
     int t = sc.nextInt();
     graph[s].weight = 0;
     for(int j=1;j<N;j++){
      for(int i=1;i<=N;i++){
       Iterator<Edge> it = graph[i].linkedList.iterator();
       while(it.hasNext()){
        Edge current = it.next();
        if(graph[current.vertix].weight > (graph[i].weight | current.penalty)){
         graph[current.vertix].weight = (graph[i].weight | current.penalty);
        }
       }
      }
     }
     if(graph[t].weight == Integer.MAX_VALUE){
      System.out.println(-1);
     }
     else{
      System.out.println(graph[t].weight);
     }
    }
    
    static class Vertix{
     public int weight;
     public LinkedList<Edge> linkedList;
    }
    
    static class Edge{
     public int vertix;
     public int penalty;
     public Edge(int ver,int pen){
      vertix = ver;
      penalty = pen;
     }
    }
}