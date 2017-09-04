import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {

    private StreamTokenizer in;
    private PrintWriter out;

    private int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }

    private void run() throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        int n = nextInt();
        long[][] f = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], (long)Integer.MAX_VALUE);
            f[i][i] = 0;
        }
        int m = nextInt();
        for (int i = 0; i < m; i++) {
            int v = nextInt() - 1;
            int u = nextInt() - 1;
            f[v][u] = nextInt();
            //f[u][v] = f[v][u];
        }
//        for (int i = 0; i < n; i++) {
//            f[i][i] = 0;
//        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
//                    long d = (long)(f[i][k] + f[k][j]);
//                    if (f[i][j] > d) {
//                        f[i][j] = d;
//                    }
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j]);
                }
            }
        }
        int T = nextInt();
        for (int t = 0; t < T; t++) {
            int v = nextInt() - 1;
            int u = nextInt() - 1;
            out.println(f[v][u] == Integer.MAX_VALUE ? -1 : f[v][u]);
        }
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }
}