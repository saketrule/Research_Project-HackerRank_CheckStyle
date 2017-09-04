import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    //The Edge class keeps track of the opposite node and the cost of the edge
    public static class Edge {
        int opposite;
        int cost;
        public Edge(int b, int c) {
            opposite = b;
            cost = c;
        }
    }
    //Node includes a list of all edges connected to the node, and an array of all possible costs
    public static class Node {
        ArrayList<Edge> edges = new ArrayList<>();
        boolean[] visited = new boolean[1024];
    }    
    
    public static Node[] nodes;
    
    public static void initArrays(int N) {
        nodes = new Node[N + 1];
        for(int i = 1; i < N + 1; i++) {
            nodes[i] = new Node();
        }
    }
    
    public static int bfs(int A, int B) {
        int answer = -1;
        //A queue of integers, the vertex and the cost associated with it
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.add(A);
        bfsQueue.add(0);
        
        while(!bfsQueue.isEmpty()) {
            int vertex = bfsQueue.poll();
            int cost = bfsQueue.poll();
            //We have visited the specific cost value for this vertex
            nodes[vertex].visited[cost] = true;
            
            //Check every edge connected to the vertex
            for(int i = 0; i < nodes[vertex].edges.size(); i++) {
                //Get the opposite vertex number and cost of the edge
                int opposite = nodes[vertex].edges.get(i).opposite;
                int edgeCost = nodes[vertex].edges.get(i).cost;
                
                if(!nodes[opposite].visited[edgeCost | cost]) {
                    nodes[opposite].visited[edgeCost | cost] = true;
                    bfsQueue.add(opposite);
                    bfsQueue.add(edgeCost | cost);
                }
                
            }
        }
        
        //For each possible cost, find the lowest one attached to the target node
        for(int k = 0; k < 1024; k++) {
            if(nodes[B].visited[k] == true) {
                answer = k;
                break;
            }
        }
        return answer;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        
        initArrays(N);
        
        for(int i = 0; i < M; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            
            nodes[a].edges.add(new Edge(b, c));
            nodes[b].edges.add(new Edge(a, c));
            
        }
        
        int A = in.nextInt();
        int B = in.nextInt();
        
        System.out.println(bfs(A, B));
        
 
    }
}