/*
 * This problem is awesome and I feel awesome.
 */

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
           // in = new BufferedReader(new FileReader("src/input.txt"));

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
            solveTestCase();
        }
    }

    public long pow(long x, long p, long modulo) {
        //no need for fast algorithm here(p < 30), so keeping it simple
        if (p <= 0) {
            return 1;
        }
        long res = 1;
        while (p-- > 0) {
            res = (res * x) % modulo;
        }
        return res;
    }
    public static final long modulo = 1000000007L;

    void solveTestCase() throws IOException {
        int n = readInt();
        long[] a = new long[n];
        long[] possibilities = new long[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            a[i] = readInt();
            total += a[i];
            possibilities[i] = pow(a[i], a[i] - 2, modulo);
        }
        if (n == 1) {
            System.out.println(possibilities[0]);
            return;
        }
        if (n == 2) {
            System.out.println(((possibilities[0] * possibilities[1]) % modulo * (a[0] * a[1]) % modulo) % modulo);
            return;
        }
        long differentTrees = 1;
        for (int i = 0; i < n; i++) {
            differentTrees = (differentTrees * possibilities[i]) % modulo;
        }
        
        long result = differentTrees;
        result = (result * pow(total, n - 2, modulo)) % modulo;
        for (int i = 0; i < n; i++)
        {
            result = (result * a[i]) % modulo;
        }
        out.println(result % modulo);
        out.flush();
    }
}