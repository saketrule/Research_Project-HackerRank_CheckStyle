import java.io.FileReader;
import java.util.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int label;
    int dist = Integer.MAX_VALUE;
    Map<Integer, List<Integer>> edges;
    Node(int l) {
        label = l;
        edges = new HashMap<>();
    }
}

public class Solution {
    static void bellmanford(Node[] graph, int N, int M, int start, int end) {
        
        graph[start].dist = 0;
        
        for(int i=0; i < N-1; i++) {
            for(int j=0; j < N; j++) {
                Node curNode = graph[j];
                for(Map.Entry<Integer, List<Integer>> entry : curNode.edges.entrySet()) {
                    Node toNode = graph[entry.getKey()];
                    for(Integer cost : entry.getValue()) {
                        if((curNode.dist | cost) < toNode.dist) {
                            toNode.dist = curNode.dist | cost;
                        }
                    }
                }
            }
        }
        
        System.out.println(graph[end].dist == Integer.MAX_VALUE ? -1 : graph[end].dist);
        
    }
    
    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int T = 1; //in.nextInt();
        for(int i=0; i < T; i++) {
            int N = in.nextInt();
            int M = in.nextInt();
            Node[] graph = new Node[N];
            
            for(int j=0; j < N; j++) {
                graph[j] = new Node(j);
            }
            
            for(int j=0; j < M; j++) {
                int start = in.nextInt()-1,
                    end = in.nextInt()-1,
                    cost = in.nextInt();
                if(!graph[start].edges.containsKey(end)) {
                    graph[start].edges.put(end, new ArrayList());
                    graph[end].edges.put(start, new ArrayList());
                }                
                graph[start].edges.get(end).add(cost);
                graph[end].edges.get(start).add(cost);
            }
            
            int S = in.nextInt()-1;
            int E = in.nextInt()-1;
            
            bellmanford(graph, N, M, S, E);
        }
        
        in.close();
        
    }
}