import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author aircube
 */
public class Solution {
 public static void main(String[] args) {
  InputStream inputStream = System.in;
  OutputStream outputStream = System.out;
  FastInputReader in = new FastInputReader(inputStream);
  PrintWriter out = new PrintWriter(outputStream);
  Pts solver = new Pts();
  int testCount = Integer.parseInt(in.next());
  for (int i = 1; i <= testCount; i++)
   solver.solve(i, in, out);
  out.close();
 }
}

class Pts {
    private static final int INF = Integer.MAX_VALUE / 2;
    private int modulo = 1000000000 + 7;

    public void solve(int testNumber, FastInputReader in, PrintWriter out) {
        int n = in.nextInt();
        Point[]p = new Point[n];
        for (int i = 0; i < n; ++i) {
            p[i] = new Point(in.nextInt(),in.nextInt());
        }
        int[] minTurns = new int[1 << n];
        Arrays.fill(minTurns, INF);
        int[] count = new int[1 << n];
        minTurns[0] = 0;
        count[0] = 1;
        boolean [] isCollinear = build(p);
        for (int mask = 1; mask < (1 << n); ++mask) {
            int curAns = INF;
            int curCount = 0;
            for (int submask = mask; submask > 0; submask = (submask - 1) & mask) {
                if (isCollinear[submask]) {
                    if (curAns > 1 + minTurns[mask ^ submask]) {
                        curAns = 1 + minTurns[mask ^ submask];
                        curCount = count[mask ^ submask];
                    } else if (curAns == 1 + minTurns[mask ^ submask]) {
                        curCount = (curCount + count[mask ^ submask]) % modulo;
                    }
                }
            }
            minTurns[mask] = curAns;
            count[mask] = curCount;
        }
        out.println(minTurns[(1 << n) - 1] + " " + count[(1 << n) - 1]);
    }

    private boolean[] build(Point[] p) {
        int n = p.length;
        boolean []res = new boolean[1 << n];
        for (int i = 0; i < (1 << n); ++i) {
            int v1 = -1;
            int v2 = -1;
            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) != 0) {
                    if (v1 == -1)
                        v1 = j;
                    else if (v2 == -1)
                        v2 = j;
                }
            }
            if (v1 == -1 || v2 == -1)
                res[i] = true;
            else {
                res[i] = true;
                for (int j = 0; j < n; ++j)
                    if ((i & (1 << j)) != 0) {
                        res[i] &= GeometryUtils.collinear(p[v1], p[v2], p[j]);
                    }
            }
        }
        return res;
    }
}

class FastInputReader {
    private StringTokenizer tokenizer;
    public BufferedReader reader;

    public FastInputReader(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        tokenizer = null;
    }

    public String nextToken() {
        try {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
        } catch (IOException e) {
            System.out.println("There is no more tokens");
            return null;
        }
        return tokenizer.nextToken();
    }
    public String next() {
        return nextToken();
    }
    public int nextInt() {
        return Integer.parseInt(nextToken());
    }

    }

class Point {

    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point subtract(Point other) {
        return new Point(x - other.x, y - other.y);
    }
    public long multiply(Point other) {
        return GeometryUtils.determinant(x, y, other.x, other.y);
    }

    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    }

class GeometryUtils {
    private GeometryUtils() {

    }
    public static long determinant(long a, long b, long c, long d) {
        return a * d - b * c;
    }


    public static boolean collinear(Point a, Point b, Point c) {
        return Math.abs(b.subtract(a).multiply(c.subtract(b))) == 0;
    }

    }