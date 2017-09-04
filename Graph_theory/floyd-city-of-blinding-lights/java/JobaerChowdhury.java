import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        readInputAndSolve();
    }

    private static void readInputAndSolve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Edge> edges = new ArrayList<Edge>();

        Edge[][] seen = new Edge[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            if (seen[from][to] != null) {
                edges.remove(seen[from][to]);
            }
            Edge e = new Edge(from, to, weight);
            edges.add(e);
            seen[from][to] = e;
        }

        int[][] paths = runFloyd(n, edges);

        int nq = sc.nextInt();
        for (int i = 0; i < nq; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int result = shortest(paths, x, y);
            System.out.println(result);
        }

    }

    private static int[][] runFloyd(int n, List<Edge> edges) {
        int[][] dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (Edge e : edges) {
            dist[e.from][e.to] = e.weight;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int a = dist[i][k] + dist[k][j];
                    if (a < 0) a = Integer.MAX_VALUE;
                    int b = dist[i][j];
                    dist[i][j] = Math.min(a, b);
                }
            }
        }


        for (int i = 1; i <= n; i++) {
            dist[i][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = dist[i][j] == Integer.MAX_VALUE ? -1 : dist[i][j];
            }
        }

        return dist;
    }

    private static int shortest(int[][] paths, int x, int y) {
        return paths[x][y];
    }

    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

}