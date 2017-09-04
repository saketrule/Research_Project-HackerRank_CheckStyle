import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static class Edge {
        int u;
        int v;
        int weight;
        
        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        Edge[] edge = new Edge[m];
        
        for (int i = 0; i < m; i++) edge[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        Floyd floyd = new Floyd(edge, n);
        
        int q = in.nextInt();
        for (int i = 0; i < q; i++) System.out.println(floyd.distanceFromTo(in.nextInt(), in.nextInt()));
    }
    
    public static class Floyd {
        int[][] distance;
        Edge[] edges;
        
        public Floyd(Edge[] edge, int n) {
            this.edges = edge;
            this.distance = new int[n+1][n+1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i != j) this.distance[i][j] = -1;
                    else        this.distance[i][j] = 0;
                }
            }
            this.calculateDistances();
        }
        
        private void calculateDistances() {
            for (Edge edge : this.edges) {
                this.distance[edge.u][edge.v] = edge.weight;
            }
            
            for (int k = 1; k < this.distance.length; k++) {
                for (int i = 1; i < this.distance.length; i++) {
                    for (int j = 1; j < this.distance.length; j++) {
                        if (this.distance[i][k] != -1 && this.distance[k][j] != -1) {
                            if (this.distance[i][j] == -1 || this.distance[i][j] > this.distance[i][k] 
                                + this.distance[k][j]) this.distance[i][j] = this.distance[i][k] + this.distance[k][j];
                        }
                    }
                }
            }
        }
        
        public int distanceFromTo(int i, int j) {
            return this.distance[i][j];
        }
    }
}