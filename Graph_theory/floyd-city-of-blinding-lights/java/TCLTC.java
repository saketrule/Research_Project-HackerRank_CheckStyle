import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Node implements Comparable<Node>{
        int id;
        //Neighbor->distance
        HashMap<Node,Integer> Children; 
        int distance;
        
        public Node(int id){
            this.id=id;
            this.Children=new HashMap<Node,Integer>();
            //distance is a large enough number
            this.distance=65536000;
        }
        
        //smaller distance is smaller
        public int compareTo(Node N){
            return this.distance-N.distance;
        }
    }
 
 //return an array of length N denoting the distance of each node to the source node 
 public static int[] Dijkstra(Node source, Node[] A){
  int[] output=new int[A.length];
  //B is the bitmap that B[i]==1 iff i-Node is in frontier or inside 
  boolean[] B =new boolean[A.length];
  PriorityQueue<Node> Q=new PriorityQueue<Node>();
  source.distance=0;
  B[source.id]=true;
  Q.add(source);
  while (Q.size()>0){
   Node headNode=Q.poll();
   for (Node N0:headNode.Children.keySet()){
    //whether to add the node to the queue
                if (!B[N0.id]){
                    //enqueue
                    Q.add(N0);
                    //bitmap modify
                    B[N0.id]=true;
                }
                //treat its distance
                if (N0.distance> headNode.distance+headNode.Children.get(N0)){
                    //if (N0.id==6) System.out.println("alert");//test
                 N0.distance=headNode.distance+headNode.Children.get(N0);
                 //Q.remove(N0);
                 Q.add(N0);
                 //if (N0.id==6) System.out.println("new distance is "+N0.distance);//test
                }
   }
   
  }
  for (int i=0;i<output.length;i++){output[i]=A[i].distance;}
  for (int i=0;i<A.length;i++){A[i].distance=65536000;}
  return output;
 }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        int N=in.nextInt();int M=in.nextInt();
        Node[] A=new Node[N];
        for (int i=0;i<N;i++){A[i]=new Node(i);}
        //init edges
        for (int e=0;e<M;e++){
                int i=in.nextInt()-1;int j=in.nextInt()-1;
                int weight=in.nextInt();
                Node N1=A[i];Node N2=A[j];
                N1.Children.put(N2,weight);
                
        }
        //distanceMatrix[i][j] is the distance from i to j
        int[][] distanceMatrix=new int[N][N];
        for (int i=0;i<N;i++){
            Node startNode=A[i];
            int[] distances=Dijkstra(startNode,A);
            //post-processing
            for (int j=0;j<N;j++){if (distances[j]==65536000)distances[j]=-1;}
            for (int j=0;j<N;j++){distanceMatrix[i][j]=distances[j];}
        }
        int Q=in.nextInt();
        for (int q=0;q<Q;q++){
            int a=in.nextInt()-1;
            int b=in.nextInt()-1;
            System.out.println(distanceMatrix[a][b]);
            
        }
        
        
    }
}