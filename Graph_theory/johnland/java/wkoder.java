import java.io.*;
import java.util.*;

public class Solution {
    private static List<Edge>[] tree;
    private static int[][] memo;

    private static int go(Edge edge) {
        int d = edge.a < edge.b ? 0 : 1;
        if (memo[d][edge.c] >= 0) {
            return memo[d][edge.c];
        }

        int w = 1;
        for (Edge e : tree[edge.b]) {
            if (e.b == edge.a) {
                continue;
            }
            w += go(e);
        }
        return memo[d][edge.c] = w;
    }

    @SuppressWarnings("unchecked")
    private static void solve() throws Exception {
        final int N = in.nextInt();
        final int M = in.nextInt();
        List<Edge>[] adj = new List[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int m = 0; m < M; m++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            int c = in.nextInt();
            Edge edge = new Edge(a, b, c);
            adj[a].add(edge);
            adj[b].add(edge.invert());
        }

        tree = new List[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        boolean[] inTree = new boolean[N];
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.addAll(adj[0]);
        inTree[0] = true;
        for (int i = 0; i < N-1; i++) {
            Edge edge;
            do {
                edge = queue.poll();
            } while (inTree[edge.b]);
            inTree[edge.b] = true;
            tree[edge.a].add(edge);
            tree[edge.b].add(edge.invert());
            queue.addAll(adj[edge.b]);
        }

        memo = new int[2][M];
        Arrays.fill(memo[0], -1);
        Arrays.fill(memo[1], -1);
        long[] C = new long[M + 100];
        for (int i = 0; i < N; i++) {
            for (Edge edge : tree[i]) {
                if (C[edge.c] > 0) {
                    continue;
                }
                C[edge.c] = ((long) go(edge)) * go(edge.invert());
            }
        }

        long carry = 0;
        for (int i = 0; i < C.length; i++) {
            carry += C[i];
            if ((carry & 1) > 0) {
                C[i] = 1;
            } else {
                C[i] = 0;
            }
            carry >>= 1;
        }
        if (carry > 0) {
            throw new RuntimeException("HA. HA.");
        }

        int len = C.length - 1;
        while (C[len] == 0 && len > 0) {
            len--;
        }
        for (int i = len; i >= 0; i--) {
            out.print(C[i]);
        }
        out.println();
    }

    private static class Edge implements Comparable<Edge> {
        int a, b, c;
        Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return c - o.c;
        }

        Edge invert() {
            return new Edge(b, a, c);
        }
    }

    private static Reader in = new Reader();
    private static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    public static void main(String[] args) throws Exception {
        solve();
        out.flush();
    }

    private static class Reader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        Reader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = new StringTokenizer("");
        }

        String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}