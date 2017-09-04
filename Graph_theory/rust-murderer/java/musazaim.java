import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static class Graph {
        Map<Integer, Set<Integer>> notConnected; 
        int N;
        Graph(int n) {
            N = n;
            notConnected = new HashMap<Integer, Set<Integer>>();
        }
        
        public void addEdge(int from, int to) {
            if (!notConnected.containsKey(from)) notConnected.put(from, new HashSet<Integer>());
            notConnected.get(from).add(to);
            if (!notConnected.containsKey(to)) notConnected.put(to, new HashSet<Integer>());
            notConnected.get(to).add(from);
        }
        
        public boolean isConnected(int from, int to) {
            if (!notConnected.containsKey(from)) return true;
            return !notConnected.get(from).contains(to);
        }
        
        public int N() {
            return N;
        }
    }
    
    public static int[] shortestPath(Graph g, int source) {
        int N = g.N();
        
        int[] distTo = new int[N];
        for (int i = 0; i < N; i++) {
            distTo[i] = N;
        }
        distTo[source] = 0;
        
        
        Deque<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        
        int found = 1;
        while (found < N && queue.size() > 0) {
            int v = queue.removeFirst();
            
            for (int w = 0; w < N; w++) {
                if (!g.isConnected(v, w)) continue;
                if (distTo[w] == N) {
                    distTo[w] = distTo[v] + 1;
                    found++;
                    if (found == N) break;
                    queue.addLast(w);
                } 
            }
        }
        return distTo;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            int M = in.nextInt();
            Graph G = new Graph(N);
            for (int j = 0; j < M; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                G.addEdge(x-1, y-1);
            }
            int S = in.nextInt();
            int[] distTo = shortestPath(G, S-1);
            StringBuilder sb = new StringBuilder();
            for (int v = 0; v < N; v++) if (v != S-1) sb.append(distTo[v]).append(" ");
            System.out.println(sb.toString());
        }
    }
}