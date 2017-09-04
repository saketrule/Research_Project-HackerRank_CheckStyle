import java.io.*;
import java.math.BigInteger;
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

    int n, m, m2;
    int[] a;
    int[][] d, dNew;

    private void solve() throws IOException {
        // timing();

        int tc = nextInt();
        for (int tcIdx = 0; tcIdx < tc; tcIdx++) {
            n = nextInt();
            m = nextInt();
            a = new int[n];
            for (int i = 0; i < n; i++) {
                char[] c = nextToken().toCharArray();
                for (int j = 0; j < m; j++)
                    if (c[j] == '#') {
                        a[i] |= 1 << j;
                    }
            }
            out.println( solveFast() );
        }
    }

    private void timing() {
        /*
        n = 20;
        m = 8;
        a = new int[n];
        for (int k = 0; k < 50; k++) {
            solveFast();
        }
        */
    }

    int MOD = 1000000007;

    private int solveFast() {
        m2 = 1 << m;

        d = new int[m2][m2];
        d[m2 - 1][m2 - 1] = 1;
        for (int k = 0; k < n + 2; k++) {

            dNew = new int[m2][m2];
            for (int i = 0; i < m2; i++)
                for (int j = 0; j < m2; j++)
                    if (d[i][j] > 0) {

                        if (k < n) {
                            // process row
                            go(i, j, a[k], 0, d[i][j]);
                        } else {
                            // feed all '#', so we can finish entire process
                            go(i, j, m2 - 1, 0, d[i][j]);
                        }

                    }

            for (int i = 0; i < m2; i++) {
                System.arraycopy(dNew[i], 0, d[i], 0, m2);
            }
        }

        return d[m2 - 1][m2 - 1] % MOD;
    }

    //  Non-flipped:
    //  01    012    01     012
    // 0#    0###   0##    0  #
    // 1#    1#     1 #    1###
    // 2##          2 #
    //
    //  Flipped:
    //   0   012     01     012
    // 0 #  0#      0##    0###
    // 1 #  1###    1#     1  #
    // 2##          2#
    //
    private void go(int x0, int x1, int x2, int pos, int ways) {
        if (pos >= m) {

            if (x0 != m2 - 1) {
                throw new IllegalStateException("Row is not filled: " + x0 + " - " + x1 + " - " + x2 + " (m2 = " + m2 + ")");
            }

            dNew[x1][x2] += ways;
            if (dNew[x1][x2] >= MOD) {
                dNew[x1][x2] -= MOD;
            }

            return;
        }

        // skip '#' as we can't put anything on them
        if ((x0 & (1 << pos)) != 0) {
            go(x0, x1, x2, pos + 1, ways);
            return;
        }

        // A
        if ((pos + 1 < m) &&
            ((x0 & (1 << pos)) == 0) &&
            ((x1 & (1 << pos)) == 0) &&
            ((x2 & (1 << pos)) == 0) &&
            ((x2 & (1 << (pos + 1))) == 0)) {

            go(
                x0 | (1 << pos),
                x1 | (1 << pos),
                x2 | (1 << pos) | (1 << (pos + 1)),
                pos + 1,
                ways
            );

        }

        // A_DOWN
        if ((pos - 1 >= 0) &&
            ((x0 & (1 << pos)) == 0) &&
            ((x1 & (1 << pos)) == 0) &&
            ((x2 & (1 << pos)) == 0) &&
            ((x2 & (1 << (pos - 1))) == 0)) {

            go(
                x0 | (1 << pos),
                x1 | (1 << pos),
                x2 | (1 << pos) | (1 << (pos - 1)),
                pos + 1,
                ways
            );

        }

        // B
        if ((pos + 2 < m) &&
            ((x0 & (1 << pos)) == 0) &&
            ((x0 & (1 << (pos + 1))) == 0) &&
            ((x0 & (1 << (pos + 2))) == 0) &&
            ((x1 & (1 << pos)) == 0)) {

            go(
                x0 | (1 << pos) | (1 << (pos + 1)) | (1 << (pos + 2)),
                x1 | (1 << pos),
                x2,
                pos + 1,
                ways
            );

        }

        // B_DOWN
        if ((pos + 2 < m) &&
            ((x0 & (1 << pos)) == 0) &&
            ((x1 & (1 << pos)) == 0) &&
            ((x1 & (1 << (pos + 1))) == 0) &&
            ((x1 & (1 << (pos + 2))) == 0)) {

            go(
                x0 | (1 << pos),
                x1 | (1 << pos) | (1 << (pos + 1)) | (1 << (pos + 2)),
                x2,
                pos + 1,
                ways
            );

        }


        // C
        if ((pos + 1 < m) &&
            ((x0 & (1 << pos)) == 0) &&
            ((x0 & (1 << (pos + 1))) == 0) &&
            ((x1 & (1 << (pos + 1))) == 0) &&
            ((x2 & (1 << (pos + 1))) == 0)) {

            go(
                x0 | (1 << pos) | (1 << (pos + 1)),
                x1 | (1 << (pos + 1)),
                x2 | (1 << (pos + 1)),
                pos + 1,
                ways
            );

        }

        // C_DOWN
        if ((pos + 1 < m) &&
            ((x0 & (1 << pos)) == 0) &&
            ((x0 & (1 << (pos + 1))) == 0) &&
            ((x1 & (1 << pos)) == 0) &&
            ((x2 & (1 << pos)) == 0)) {

            go(
                x0 | (1 << pos) | (1 << (pos + 1)),
                x1 | (1 << pos),
                x2 | (1 << pos),
                pos + 1,
                ways
            );

        }

        // D
        if ((pos - 2 >= 0) &&
            ((x0 & (1 << pos)) == 0) &&
            ((x1 & (1 << pos)) == 0) &&
            ((x1 & (1 << (pos - 1))) == 0) &&
            ((x1 & (1 << (pos - 2))) == 0)) {

            go(
                x0 | (1 << pos),
                x1 | (1 << pos) | (1 << (pos - 1)) | (1 << (pos - 2)),
                x2,
                pos + 1,
                ways
            );

        }

        // D_DOWN
        if ((pos + 2 < m) &&
            ((x0 & (1 << pos)) == 0) &&
            ((x0 & (1 << (pos + 1))) == 0) &&
            ((x0 & (1 << (pos + 2))) == 0) &&
            ((x1 & (1 << (pos + 2))) == 0)) {

            go(
                x0 | (1 << pos) | (1 << (pos + 1)) | (1 << (pos + 2)),
                x1 | (1 << (pos + 2)),
                x2,
                pos + 1,
                ways
            );

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