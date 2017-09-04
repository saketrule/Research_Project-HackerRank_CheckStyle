import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        SynchronousShopping solver = new SynchronousShopping();
        solver.solve(1, in, out);
        out.close();
    }

    static class SynchronousShopping {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int N = in.nextInt();
            int M = in.nextInt();
            int K = in.nextInt();
            int[] kinds = new int[N];
            for (int i = 0; i < N; ++i) {
                int tk = in.nextInt();
                for (int j = 0; j < tk; ++j) kinds[i] |= 1 << (in.nextInt() - 1);
            }
            class Edge {
                int a, b, time;

                public Edge(int a, int b, int time) {
                    this.a = a - 1;
                    this.b = b - 1;
                    this.time = time;
                }
            }
            Edge[] edges = new Edge[M * 2];
            for (int i = 0; i < M * 2; i += 2) {
                int a = in.nextInt();
                int b = in.nextInt();
                int time = in.nextInt();
                edges[i] = new Edge(a, b, time);
                edges[i + 1] = new Edge(b, a, time);
            }
            int FROM = 0, TO = N - 1;
            int S = (1 << K) - 1;
            int[][] dp = new int[S + 1][N];
            for (int s = 0; s <= S; ++s) {
                Arrays.fill(dp[s], Integer.MAX_VALUE);
                if (s == 0) dp[s][0] = 0;

                for (Edge e : edges) {
                    int res = dp[s][e.b];
                    if ((kinds[e.b] & s) != 0) {
                        res = Math.min(res, dp[s & ~kinds[e.b]][e.a] + e.time);
                        res = Math.min(res, dp[s & ~kinds[e.b]][e.b]);
                    }
                    dp[s][e.b] = res;
                }

                for (int i = 0; i < N; ++i) {
                    boolean upd = false;
                    for (Edge e : edges) {
                        if (dp[s][e.a] != Integer.MAX_VALUE && dp[s][e.b] > dp[s][e.a] + e.time) {
                            dp[s][e.b] = dp[s][e.a] + e.time;
                            upd = true;
                        }
                    }
                    if (!upd) break;
                    if (i == N - 1 && upd) throw new RuntimeException();
                }

            }

            int ret = Integer.MAX_VALUE;
            for (int i = 0; i <= S; ++i) {
                ret = Math.min(ret, Math.max(dp[i][N - 1], dp[S & ~i][N - 1]));
            }
            out.println(ret);
        }

    }
}