import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
     private static class Graph {
        private final HashSet<Integer>[] adjacent;
        private final int n;

        public Graph(int n) {
            this.n = n;
            adjacent = new HashSet[this.n];
        }

        public void addEdge(int v, int w) {
            if (adjacent[v] == null) adjacent[v] = new HashSet<Integer>();
            if (adjacent[w] == null) adjacent[w] = new HashSet<Integer>();
            adjacent[v].add(w);
            adjacent[w].add(v);
        }

        public boolean hasEdge(Integer v, Integer w) {
            if (adjacent[v] == null || adjacent[w] == null) return false;
            return adjacent[v].contains(w);
        }
    }

    private static class BFS {
        private final Graph graph;
        private boolean[] marked;
        private int[] dist;
        private final LinkedList<Integer> notdiscovered;

        public BFS(Graph g, int s) {
            graph = g;
            marked = new boolean[graph.n];
            dist = new int[graph.n];
            notdiscovered = new LinkedList<Integer>();

            for(int i = 0; i < graph.n; i++) {
                if (i != s) notdiscovered.add(i);
            }

            marked[s] = true;
            dist[s] = 0;
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(s);

            while (!queue.isEmpty()) {
                Integer v = queue.remove();

                Iterator<Integer> it = notdiscovered.iterator();
                while(it.hasNext()) {
                    Integer w = it.next();
                    if (!marked[w] && !graph.hasEdge(v, w)) {
                        marked[w] = true;
                        dist[w] = dist[v] + 1;
                        queue.add(w);
                        it.remove();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            Graph g = new Graph(n);
            for(int j = 0; j < m; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                g.addEdge(x-1, y-1);
            }
            int s = in.nextInt();
            BFS bfs = new BFS(g, s-1);

            StringBuilder sb = new StringBuilder(n-1);
            for(int j = 0; j < s-1; j++) {
                sb.append(bfs.dist[j]);
                sb.append(' ');
            }
            for(int j = s; j < n-1; j++) {
                sb.append(bfs.dist[j]);
                sb.append(' ');
            }
            if (s-1 != n-1) sb.append(bfs.dist[n-1]);
            System.out.println(sb);
        }
    }
}