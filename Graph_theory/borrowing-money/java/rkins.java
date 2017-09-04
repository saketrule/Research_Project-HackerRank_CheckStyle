import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Graph g = new Graph(n);
        int numEdges = in.nextInt();
        for (int i=0; i<n; i++) {
            g.addMoney(i, in.nextInt());
        }
        for (int i=0; i<numEdges; i++) {
            g.addEdge(in.nextInt()-1, in.nextInt()-1);
        }
        g.demandMondey();
    }
    
    public static class Graph {
        int[][] graph;
        int n;
        int[] money;
        
        public Graph(int n) {
            this.n = n;
            graph = new int[n][n];
            money = new int[n];
        }
        
        public void addEdge(int u, int v) {
            graph[u][v] = 1;
            graph[v][u] = 1;
        }
        
        public void addMoney(int i, int m) {
            money[i] = m;
        }
        
        public void demandMondey() {
            int maxTotalMoney = Integer.MIN_VALUE;
            int freq = 0;
            for (long i=0L; i<Math.pow(2, n); i++) {
                int index = 0;
                Set<Integer> subset = new HashSet<Integer>();
                long j = i;
                while (j>0) {
                    if ((j & 1L) == 1L) {
                        // include index
                        subset.add(index);
                    }
                    j = j>>1L;
                    index++;
                }
                
                boolean include = true;
                int totalMoney = 0;
                for (int l : subset) {
                    totalMoney+=money[l];
                    for (int m : subset) {
                        if (graph[l][m] == 1) {
                            // do not include this subset
                            include = false;
                            break;
                        }
                    }
                    if (!include) break;
                }
                if (include) {
                    if (totalMoney == maxTotalMoney) {
                        freq++;
                    } else if (totalMoney > maxTotalMoney) {
                        maxTotalMoney = totalMoney;
                        freq = 1;
                    }
                }
            }
            
            System.out.println(maxTotalMoney + " " + freq);
        }
    }
    
}