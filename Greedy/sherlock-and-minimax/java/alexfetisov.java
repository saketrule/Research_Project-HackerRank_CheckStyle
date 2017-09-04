import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Solution {
 public static void main(String[] args) {
  InputStream inputStream = System.in;
  OutputStream outputStream = System.out;
  InputReader in = new InputReader(inputStream);
  PrintWriter out = new PrintWriter(outputStream);
  TaskB solver = new TaskB();
  solver.solve(1, in, out);
  out.close();
 }
}

class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = in.nextInt();
        }
        P = in.nextInt();
        Q = in.nextInt();
        Arrays.sort(a);

        result = a[0];
        if (n == 1) {
            check(P);
            check(Q);
        } else {
            check(P);
            check(Q);
            for (int i = 1; i < n; ++i) {
                int t = a[i] + a[i-1];
                check(t / 2);
                check(t / 2 + 1);
            }
        }
        for (int i = 0; i < n; ++i) {
            check(a[i] - maxValue);
            check(a[i] + maxValue);
        }
        out.println(result);
    }

    private void check(int x) {
        if (x < P || x > Q) {
            return;
        }
        int cur = Integer.MAX_VALUE;
        for (int t : a) {
            cur = Math.min(cur, Math.abs(t - x));
        }
        if (cur > maxValue) {
            maxValue = cur;
            result = x;
        } else if (cur == maxValue) {
            result = Math.min(result, x);
        }
    }

    int[] a;
    int maxValue = 0;
    int result = Integer.MAX_VALUE;
    int P, Q;
}

class InputReader {
    private BufferedReader reader;
    private StringTokenizer stt;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
    }

    public String nextLine() {
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            return null;
        }
    }

    public String nextString() {
        while (stt == null || !stt.hasMoreTokens()) {
            stt = new StringTokenizer(nextLine());
        }
        return stt.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(nextString());
    }

}