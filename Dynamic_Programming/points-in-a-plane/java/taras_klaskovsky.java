import java.awt.Point;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import static java.lang.Math.*;

public class Solution {

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Solution().run();
        // new Thread(null, new StringReduction(), "", 64 * (1L << 20)).start();
    }
    static final boolean debug = false;

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
            if (!debug) {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
            } else {
                in = new BufferedReader(new FileReader("input.txt"));
                out = new PrintWriter("output.txt");
            }
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
            solveTestcase();
        }
    }
    static final int modulo = 1000000007;

    private void solveTestcase() throws IOException {
        // System.out.println("new test");
        int n = readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = readInt();
            y[i] = readInt();
        }

        //  if (n == 3)
        //  System.out.println("delete test " + deleteLine(7, 0, 1, x, y));
        //   if (true)
        //       return;

        int nPow2 = 1 << n;

        int[][] collinearWith = new int[n][n];//f[v1][v2] = mask of points on one line with r1 and r2 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                collinearWith[i][j] = deleteLine((nPow2) - 1, i, j, x, y);
            }
        }

        int[] numberOfWays = new int[nPow2];
        int[] f = new int[nPow2];
        int[] lastRedirect = new int[nPow2];
        int[] lastTimeUsed = new int[nPow2];
        numberOfWays[0] = 1;


        //calculate F
        for (int mask = 0; mask < (nPow2); mask++) {
            if (mask != 0) {
                f[mask] = Integer.MAX_VALUE / 2;
            }
            for (int v1 = 0; v1 < n; v1++) {
                if ((mask & (1 << v1)) > 0) {
                    for (int v2 = v1; v2 < n; v2++) {
                        int setOfDeleted = collinearWith[v1][v2] & mask;
                        int newMask = mask - setOfDeleted;
                        if (f[newMask] + 1 < f[mask]) {
                            f[mask] = f[newMask] + 1;
                        }
                    }
                }
            }
        }
        //    System.out.println(mask + " " + f[mask] + " " + numberOfWays[mask]);

        //calculate elements that will be visited if we will start from (nPow2 - 1)
        boolean[] needToBeCalculated = new boolean[nPow2];//nice problem!
        needToBeCalculated[nPow2 - 1] = true;
        for (int mask = nPow2 - 1; mask >= 0; mask--) {
            if (!needToBeCalculated[mask]) {
                continue;
            }
            for (int v1 = 0; v1 < n; v1++) {
                if ((mask & (1 << v1)) > 0) {
                    for (int v2 = v1; v2 < n; v2++) {
                        if (lastTimeUsed[collinearWith[v1][v2]] == mask) {
                            continue;
                        }
                        lastTimeUsed[collinearWith[v1][v2]] = mask;
                        int setOfDeleted = mask & collinearWith[v1][v2];
                        for (int deleteNow = setOfDeleted; deleteNow > 0; deleteNow = (deleteNow - 1) & setOfDeleted) {
                            int newMask = mask - deleteNow;
                            if (f[newMask] + 1 == f[mask]) {
                                needToBeCalculated[newMask] = true;
                            }
                        }
                    }
                }
            }
            //    System.out.println(mask + " " + f[mask] + " " + numberOfWays[mask]);
        }


        lastRedirect = new int[nPow2];
        lastTimeUsed = new int[nPow2];
        numberOfWays[0] = 1;
        //calculate number of ways
        for (int mask = 0; mask < nPow2; mask++) {
            if (!needToBeCalculated[mask]) {
                continue;
            }
            for (int v1 = 0; v1 < n; v1++) {
                if ((mask & (1 << v1)) > 0) {
                    for (int v2 = v1; v2 < n; v2++) {
                        if (lastTimeUsed[collinearWith[v1][v2]] == mask) {
                            continue;
                        }
                        lastTimeUsed[collinearWith[v1][v2]] = mask;
                        int setOfDeleted = mask & collinearWith[v1][v2];
                        for (int deleteNow = setOfDeleted; deleteNow > 0; deleteNow = (deleteNow - 1) & setOfDeleted) {
                            int newMask = mask - deleteNow;
                            if (lastRedirect[newMask] != mask && newMask != mask) {
                                lastRedirect[newMask] = mask;
                                if (f[newMask] + 1 == f[mask]) {
                                    numberOfWays[mask] = (numberOfWays[newMask] + numberOfWays[mask]);
                                    if (numberOfWays[mask] >= modulo) {
                                        numberOfWays[mask] -= modulo;
                                    }
                                }
                            }
                        }
                    }
                }
            }
         //   System.out.println(mask + " " + f[mask] + " " + numberOfWays[mask]);
        }
        out.println(f[(nPow2) - 1] + " " + numberOfWays[(nPow2) - 1]);
    }

    private int deleteLine(int mask, int point1, int point2, int[] x, int[] y) {
        int rx = x[point1] - x[point2];
        int ry = y[point1] - y[point2];

        if (rx * rx + ry * ry == 0) {
            if ((mask & (1 << point1)) > 0) {
                return (1 << point1);
            } else {
                return 0;
            }
        }

        int newMask = 0;
        for (int i = 0; i < x.length; i++) {
            if ((mask & (1 << i)) > 0) {
                if (collinear(rx, ry, x[i] - x[point1], y[i] - y[point1])) {
                    newMask += (1 << i);
                }
            }
        }
        return newMask;
    }

    private boolean collinear(int x1, int y1, int x2, int y2) {
        return (x1 * y2 == x2 * y1);
    }
}