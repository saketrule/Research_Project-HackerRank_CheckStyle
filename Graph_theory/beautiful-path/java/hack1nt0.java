import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author DY
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        MinimumPenaltyPath solver = new MinimumPenaltyPath();
        solver.solve(1, in, out);
        out.close();
    }

    static class MinimumPenaltyPath {
        int MINC = 1;
        Graph G;
        boolean[] vis;

        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int N = in.nextInt();
            int M = in.nextInt();
            G = new Graph(N);
            for (int i = 0; i < M; ++i) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                int c = in.nextInt();
                if (a == b) continue;
                G.addE(a, b, c);
                G.addE(b, a, c);
            }
            int S = in.nextInt() - 1;
            int T = in.nextInt() - 1;
            int L = MINC - 1;
            vis = new boolean[N];
            Arrays.fill(vis, false);
            int R = dfs(S, T);
            if (R == -1) {
                out.println(-1);
                return;
            }

            while (true) {
                if (L + 1 >= R) break;
                int penalty = L + (R - L) / 2;
                Arrays.fill(vis, false);
                if (C(S, T, penalty)) R = penalty;
                else L = penalty;
            }

            out.println(R);
        }

        private boolean C(int cur, int T, int penalty) {
            if (cur == T) return true;
            if (vis[cur]) return false;
            vis[cur] = true;
            for (Graph.Edge e : G.adj[cur]) {
                if ((e.cost | penalty) != penalty) continue;
                boolean chdres = C(e.b, T, penalty);
                if (chdres) return true;
            }
            return false;
        }

        private int dfs(int cur, int T) {
            if (cur == T) return 0;
            if (vis[cur]) return -1;
            vis[cur] = true;
            for (Graph.Edge e : G.adj[cur]) {
                int chdres = dfs(e.b, T);
                if (chdres != -1) {
                    return e.cost | chdres;
                }
            }
            return -1;
        }

    }

    static class Graph {
        int N;
        int M;
        public List<Graph.Edge>[] adj;

        public Graph(int N) {
            this.N = N;
            //d = new long[N][N];
            adj = new List[N];
            for (int i = 0; i < N; ++i) adj[i] = new ArrayList<Graph.Edge>();
        }

        public void addE(int a, int b, int cost) {
            //d[a][b] = cost;
            adj[a].add(new Graph.Edge(b, cost));
            M++;
        }

        public static class Edge {
            public int b;
            public int cost;

            public Edge(int b, int cost) {
                this.b = b;
                this.cost = cost;
            }

        }

    }
}