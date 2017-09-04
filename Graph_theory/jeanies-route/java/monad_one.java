import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node{
    int id;
    List<Edge> neighbors;
    
    public Node(int id){
        this.id=id;
    }
    public void AddUndirectedEdge(Node destination, int weight){
        this.neighbors.add(new Edge(destination,weight));
        destination.neighbors.add(new Edge(this,weight));
    }
    private static class Edge{
        public Node destination;
        public int weight;
        
        public Edge(Node destination,int weight){
            this.destination=destination;
            this.weight=weight;
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int K=sc.nextInt();
        ArrayList<Node> graph=new ArrayList<Node>();
        int[] arr=new int[K];
        for(int i=0;i<K;i++)
            arr[i]=sc.nextInt();
        for(int i=1;i<=N;i++)
            graph.add(new Node(i));
        for(int i=0;i<N-1;i++){
            System.out.println(i);
            Node temp=graph.get(sc.nextInt());
            temp.AddUndirectedEdge(graph.get(sc.nextInt()),sc.nextInt());
        }
        for(int i=0;i<N;i++)
            System.out.println(graph.get(i).neighbors);
    }
}