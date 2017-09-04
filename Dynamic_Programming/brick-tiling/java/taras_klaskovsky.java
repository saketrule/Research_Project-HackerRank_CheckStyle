import java.awt.Point;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import static java.lang.Math.*;

public class Solution implements Runnable {

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Thread(null, new Solution(), "", 256 * (1L << 20)).start();
    }

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);

           // in = new BufferedReader(new FileReader("src/storage/input.txt"));


            Locale.setDefault(Locale.US);
            solve();
            in.close();
            out.close();
            long t2 = System.currentTimeMillis();
            System.err.println("Time = " + (t2 - t1));
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    String readString() throws IOException {
        while (!tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    int readInt() throws IOException {
        return Integer.parseInt(readString());
    }

    long readLong() throws IOException {
        return Long.parseLong(readString());
    }

    double readDouble() throws IOException {
        return Double.parseDouble(readString());
    }

    // solution
    void solve() throws IOException {
        int ct = readInt();
        while (ct-- > 0) {
            solvets();
        }
    }
    static final long modulo = 1000000007;
    long[][][][] f;

    void solvets() throws IOException {
        int n = readInt();
        int m = readInt();
        boolean[][] free = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String s = in.readLine();
            for (int j = 0; j < m; j++) {
                free[i][j] = (s.charAt(j) == '.');
            }
        }

        f = new long[n + 1][m + 1][1 << m][1 << m];
        f[0][0][(1 << m) - 1][(1 << m) - 1] = 1;
        for (int i = 0; i <= n; i++) {
            for (int cur = 0; cur < m; cur++) {
                for (int mask1 = 0; mask1 < (1 << m); mask1++) {
                    for (int mask2 = 0; mask2 < (1 << m); mask2++) {
                        f[i][cur][mask1][mask2] %= modulo;
                        if (f[i][cur][mask1][mask2] == 0) {
                            continue;
                        }
                    //    System.out.println(i + " " + cur + " " + mask1 + " " + mask2 + " " + f[i][cur][mask1][mask2]);
                        if (i == n) {
                            continue;
                        }
                        buildSimpleAndHopefullyNotVerySlowModel(cur, m, mask1, mask2, f[i][cur][mask1][mask2], i, free);
                    }
                }
            }
            //   System.out.println("take " + m);
            if (i < n) {
                for (int mask1 = 0; mask1 < (1 << m); mask1++) {
                    for (int mask2 = 0; mask2 < (1 << m); mask2++) {
                        f[i + 1][0][mask1][mask2] = f[i][m][mask1][mask2];
                    }
                }
            }
        }
        System.out.println(f[n][0][(1 << m) - 1][(1 << m) - 1] % modulo);
    }

    void debug(Object... args) {
        System.out.println(Arrays.deepToString(args));
    }

    private void attempt(boolean[][] a, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, long inc, int di) {
        if (x1 < 0 || x2 < 0 || x3 < 0 || x4 < 0) {
            return;
        }
        if (x1 >= a.length || x2 >= a.length || x3 >= a.length || x4 >= a.length) {
            return;
        }
        if (a[x1][y1] || a[x2][y2] || a[x3][y3] || a[x4][y4]) {
            return;
        }
        a[x1][y1] = true;
        a[x2][y2] = true;
        a[x3][y3] = true;
        a[x4][y4] = true;
        int max = 0;
        if (y1 == 0) {
            max = Math.max(max, x1);
        }
        if (y2 == 0) {
            max = Math.max(max, x2);
        }
        if (y3 == 0) {
            max = Math.max(max, x3);
        }
        if (y4 == 0) {
            max = Math.max(max, x4);
        }
        for (int i = max; i >= 0; i--) {
            if (a[i][2] == false) {
                attempt(a, i, 2, i + 1, 0, i + 1, 1, i + 1, 2, inc, di);
                a[x1][y1] = false;
                a[x2][y2] = false;
                a[x3][y3] = false;
                a[x4][y4] = false;
                return;
            }
        }

        int mask1 = 0;
        int mask2 = 0;
        for (int i = 0; i <= max; i++) {
            if (a[i][0]) {
                mask1 += (1 << i);
            }
            if (a[i][1]) {
                mask2 += (1 << i);
            }
        }
        for (int i = max + 1; i < a.length; i++) {
            if (a[i][1]) {
                mask1 += (1 << i);
            }
            if (a[i][2]) {
                mask2 += (1 << i);
            }
        }
        // System.out.println("add something");
        f[di][max + 1][mask1][mask2] += inc;
        a[x1][y1] = false;
        a[x2][y2] = false;
        a[x3][y3] = false;
        a[x4][y4] = false;
    }

    private void addnothing(boolean[][] a, int cur, long inc, int di) {
        // System.out.println("try to add nothing: " + a.length);
        int mask1 = 0;
        int mask2 = 0;
        if (!a[cur][2]) {
            attempt(a, cur, 2, cur + 1, 0, cur + 1, 1, cur + 1, 2, inc, di);
            //     System.out.println("fail to add nothing : not empty cell at " + cur + " " + 2);
            return;
        }
        cur++;
        for (int i = 0; i < cur; i++) {
            if (a[i][0]) {
                mask1 += (1 << i);
            }
            if (a[i][1]) {
                mask2 += (1 << i);
            }
        }
        for (int i = cur; i < a.length; i++) {
            if (a[i][1]) {
                mask1 += (1 << i);
            }
            if (a[i][2]) {
                mask2 += (1 << i);
            }
        }
        // System.out.println("add nothing to " + mask1 + " " + mask2 + " and change cur to " + (cur + 1));
        f[di][cur][mask1][mask2] += inc;
    }

    private void buildSimpleAndHopefullyNotVerySlowModel(int cur, int n, int mask1, int mask2, long inc, int di, boolean[][] free) {
        boolean[][] a = new boolean[n][3];
        for (int i = 0; i < cur; i++) {
            a[i][0] = (mask1 & (1 << i)) > 0;
            a[i][1] = (mask2 & (1 << i)) > 0;
            a[i][2] = true;
        }
        for (int i = cur; i < n; i++) {
            a[i][0] = false;
            a[i][1] = (mask1 & (1 << i)) > 0;
            a[i][2] = (mask2 & (1 << i)) > 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                if (di - j >= 0 && !free[di - j][i]) {
                    a[i][j] = true;
                }
            }
        }
     //   debug("model with cur", cur);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
         //       System.out.print(a[j][i] + " ");
            }
        //    System.out.println();
        }
        attempt(a, cur, 0, cur, 1, cur, 2, cur - 1, 0, inc, di);
        attempt(a, cur, 0, cur, 1, cur, 2, cur + 1, 0, inc, di);
        attempt(a, cur, 0, cur, 1, cur, 2, cur - 1, 2, inc, di);
        attempt(a, cur, 0, cur, 1, cur, 2, cur + 1, 2, inc, di);

        attempt(a, cur, 0, cur, 1, cur - 1, 0, cur - 2, 0, inc, di);
        attempt(a, cur, 0, cur, 1, cur + 1, 0, cur + 2, 0, inc, di);
        attempt(a, cur, 0, cur, 1, cur - 1, 1, cur - 2, 1, inc, di);
        attempt(a, cur, 0, cur, 1, cur + 1, 1, cur + 2, 1, inc, di);
        addnothing(a, cur, inc, di);

    }
}