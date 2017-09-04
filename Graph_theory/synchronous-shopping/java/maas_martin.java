import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static class Node implements Comparable<Node> {
        int node;    
        int dist;
        int fish;
        
        public int compareTo(Node o) {
            return Integer.compare(dist, o.dist);
        }
        
        public Node(int node, int dist, int fish) {
            this.node = node;
            this.dist = dist;
            this.fish = fish;
        }
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int K = scan.nextInt();
        
        int kOpts = (1<<K);
        int[] fish = new int[N];
        
        // Store fish configuration as bitmask.
        for (int i = 0; i < N; i++) {
            int T = scan.nextInt();
            for (int j = 0; j < T; j++) {
                fish[i] |= (1<<(scan.nextInt()-1));
            }
        }
        
        // 0 = no path? otherwise path + 1
        int[][] dist = new int[N][1024];        
        dist[0][fish[0]] = 1;
        
        int[][] cost = new int[N][N];
        
        ArrayList<Integer>[] adj = new ArrayList[N];
        
        for (int i = 0; i < N; i++)
            adj[i] = new ArrayList<Integer>();
        
        for (int i = 0; i < M; i++) {
            int u = scan.nextInt()-1;
            int v = scan.nextInt()-1;
            adj[u].add(v);
            adj[v].add(u);
            cost[u][v] = scan.nextInt();
            cost[v][u] = cost[u][v];
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(0, 1, fish[0]));
        
        while(!pq.isEmpty()) {
            Node n = pq.remove();
            for (Integer i : adj[n.node]) {
                int newFish = n.fish | fish[i];
                if (dist[i][newFish] == 0 || n.dist + cost[n.node][i] < dist[i][newFish]) {
                    dist[i][newFish] = n.dist + cost[n.node][i];
                    pq.add(new Node(i, dist[i][newFish], newFish));
                }
            }
        }
        
        //System.out.println(fish[0]);
        
        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int start = u[j];
                int end = v[j];
                
                for (int k = 0; k < distCount[start]; k++) {
                    if (dist[start][k] != 0) {
                        if ((dist[end][k | fish[end]] == 0) ||
                            (dist[end][k | fish[end]] > dist[start][k] + cost[j])) {
                                //System.out.println(end + " " + (k | fish[end]));
                                dist[end][k | fish[end]] = dist[start][k] + cost[j];
                        }
                    }
                    
                    if (dist[end][k] != 0) {
                        if ((dist[start][k | fish[start]] == 0) ||
                            (dist[start][k | fish[start]] > dist[end][k] + cost[j])) {
                                //System.out.println(start + " " + (k | fish[start]));
                                dist[start][k | fish[start]] = dist[end][k] + cost[j];
                        }
                    }
                }
            }
        }*/
        
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < kOpts; i++) {
            for (int j = 0; j < kOpts; j++) {
                if (((i | j) & (kOpts-1)) == (kOpts-1)) {
                    //System.out.println("#" + i + " " + dist[N-1][i]);
                    if (dist[N-1][i] != 0 && dist[N-1][j] != 0) {
                        //System.out.println("#" + i + " " + dist[N-1][i]);
                        //System.out.println("#" + j + " " + dist[N-1][i]);
                        min = Math.min(min, Math.max(dist[N-1][i], dist[N-1][j])-1);
                    }
                }
            }
        }
        
        System.out.println(min);
    }
}