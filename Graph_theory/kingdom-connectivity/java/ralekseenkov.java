import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    // leave empty to read from stdin/stdout
    private static final String TASK_NAME_FOR_IO = "";

    // file names
    private static final String FILE_IN = TASK_NAME_FOR_IO + ".in";
    private static final String FILE_OUT = TASK_NAME_FOR_IO + ".out";

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tokenizer = new StringTokenizer("");

    public static void main(String[] args) {
        new Solution().run();
    }

    int n, nCond;

    boolean[] used;
    int[] order, componentIdx, componentVCount;
    int orderCnt;

    void dfs1(int u) {
        used[u] = true;
        for (int v : original.g[u].list)
            if (!used[v]) {
                dfs1(v);
            }
        order[orderCnt++] = u;
    }

    void dfs2(int u, int compIdx) {
        used[u] = true;
        componentIdx[u] = compIdx;
        componentVCount[compIdx]++;
        for (int v : original.gRev[u].list)
            if (!used[v]) {
                dfs2(v, compIdx);
            }
    }

    void dfs3(int u) {
        used[u] = true;
        for (int v : condensed.g[u].list)
            if (!used[v]) {
                dfs3(v);
            }
        order[orderCnt++] = u;
    }

    class VList {
        List<Integer> list = new ArrayList<Integer>();
    }

    class Graph {

        VList[] g, gRev;

        Graph(int n) {
            g = new VList[n];
            gRev = new VList[n];
            for (int i = 0; i < n; i++) {
                g[i] = new VList();
                gRev[i] = new VList();
            }
        }

        public void add(int u, int v) {
            g[u].list.add(v);
            gRev[v].list.add(u);
        }

    }

    Graph original, condensed;
    static final int MOD = 1000000000;

    private void solve() throws IOException {
        n = nextInt();
        int m = nextInt();
        original = new Graph(n);
        for (int k = 0; k < m; k++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            original.add(u, v);
        }

        used = new boolean[n];
        order = new int[n];
        orderCnt = 0;
        for (int i = 0; i < n; ++i)
            if (!used[i]) {
                dfs1(i);
            }

        used = new boolean[n];
        componentIdx = new int[n];
        componentVCount = new int[n];
        nCond = 0;
        for (int i = 0; i < n; ++i) {
            int v = order[n - 1 - i];
            if (!used[v]) {
                dfs2(v, nCond++);
            }
        }

        condensed = new Graph(nCond);
        for (int u = 0; u < n; u++)
            for (int v : original.g[u].list) {
                condensed.add(componentIdx[u], componentIdx[v]);
            }

        int src = componentIdx[0];
        int dst = componentIdx[n - 1];
        used = new boolean[nCond];
        order = new int[nCond];
        orderCnt = 0;
        dfs3(src);

        int[] d = new int[nCond];
        boolean[] dInf = new boolean[nCond];
        for (int i = 0; i < orderCnt; ++i) {
            int v = order[orderCnt - 1 - i];

            if (componentVCount[v] > 1) {
                dInf[v] = true;
            }

            if (v == src) {
                d[v] = 1;
            } else {

                for (int u : condensed.gRev[v].list) {
                    d[v] += d[u];
                    if (d[v] >= MOD) {
                        d[v] -= MOD;
                    }
                    dInf[v] |= dInf[u];
                }

            }

        }

        if (dInf[dst]) {
            out.println("INFINITE PATHS");
        } else {
            out.println(d[dst]);
        }
    }

    public void run() {
        long timeStart = System.currentTimeMillis();

        boolean fileIO = TASK_NAME_FOR_IO.length() > 0;
        try {

            if (fileIO) {
                in = new BufferedReader(new FileReader(FILE_IN));
                out = new PrintWriter(new FileWriter(FILE_OUT));
            } else {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(new OutputStreamWriter(System.out));
            }

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        long timeEnd = System.currentTimeMillis();

        if (fileIO) {
            System.out.println("Time spent: " + (timeEnd - timeStart) + " ms");
        }
    }

    private String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            String line = in.readLine();
            if (line == null) {
                return null;
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    private int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    private BigInteger nextBigInt() throws IOException {
        return new BigInteger(nextToken());
    }

    private long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    private double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

}