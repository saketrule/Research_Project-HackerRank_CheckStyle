import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node{
    int Index;
    Edge adj;
    
    Node(int name){
        this.Index = name;
        adj = null;
        
    }
}

class Edge{
    int v1;
    int v2;
    int weight;
    
    Edge(int city1, int city2, int dist){
        this.v1 = city1;
        this.v2 = city2;
        this.weight = dist;
    }
}

public class Solution {
    

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Node[] adjList = new Node[N];
        for(int i = 0; i < N; i++){
            adjList[i] = new Node(i);
        }
        int letters = sc.nextInt();
        List<Integer> destination = new ArrayList<Integer>(letters);
        for(int i = 0; i < letters; i++){
            destination.add(i, sc.nextInt());
        }
        
        for(int i = 0; i < N-1; i++){
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            int dist = sc.nextInt();
            Edge e1 = new Edge(u, v, dist);
            adjList[u].adj = e1;
            Edge e2 = new Edge(v, u, dist);
            adjList[v].adj = e1;
        }
        
        int distance = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < destination.size(); i++){
            int visited = letters - 1;
            while(visited != 0){
                distance+= adjList[destination.get(i)].adj.weight;
                if(destination.contains(adjList[destination.get(i)].adj.v2))
                     visited--;
            }  
         min = Math.min(min, distance);   
        }
        System.out.println(min);
    }
}