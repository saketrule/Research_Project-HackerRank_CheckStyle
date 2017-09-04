import java.io.*;
import java.util.*;

public class Solution {

    final static int MOD = 1000000007;

    public static void solve(Input in, PrintWriter out) throws IOException {
        int m = in.nextInt();
        int n = in.nextInt();
        BitSet f0 = new BitSet(n * m);
        for (int i = 0; i < m; ++i) {
            String s = in.next();
            for (int j = 0; j < n; ++j) {
                if (s.charAt(j) == '#') {
                    f0.set(j + i * n);
                }
            }
        }
        final int[][][] ds = {
            {{0, 0}, {1, 0}, {2, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
            {{0, 0}, {0, 1}, {-1, 1}, {-2, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            {{0, 0}, {0, 1}, {0, 2}, {-1, 2}},
            {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
        };
        HashMap<BitSet, Integer> d = new HashMap<BitSet, Integer>();
        PriorityQueue<BitSet> q = new PriorityQueue<BitSet>(n * m, new Comparator<BitSet>() {
            @Override
            public int compare(BitSet o1, BitSet o2) {
                int i1 = o1.nextClearBit(0);
                int i2 = o2.nextClearBit(0);
                return i1 - i2;
            }
        });
        q.add(f0);
        d.put(f0, 1);
        while (!q.isEmpty()) {
            BitSet f = q.poll();
            int val = d.get(f);
            int i0 = f.nextClearBit(0);
            if (i0 == n * m) {
                out.println(val);
                return;
            }
            int x = i0 % n, y = i0 / n;
            loop: for (int[][] ps : ds) {
                BitSet f1 = (BitSet) f.clone();
                for (int[] p : ps) {
                    int xx = x + p[0];
                    int yy = y + p[1];
                    if (xx < 0 || xx >= n || yy < 0 || yy >= m || f.get(xx + yy * n)) {
                        continue loop;
                    }
                    f1.set(xx + yy * n);
                }
                if (!d.containsKey(f1)) {
                    d.put(f1, 0);
                    q.add(f1);
                }
                d.put(f1, (d.get(f1) + val) % MOD);
            }
        }
        out.println(0);
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        Input in = new Input(new BufferedReader(new InputStreamReader(System.in)));
        int tests = in.nextInt();
        for (int test = 0; test < tests; ++test) {
            solve(in, out);
        }
        out.close();
    }

    static class Input {
        BufferedReader in;
        StringBuilder sb = new StringBuilder();

        public Input(BufferedReader in) {
            this.in = in;
        }

        public Input(String s) {
            this.in = new BufferedReader(new StringReader(s));
        }

        public String next() throws IOException {
            sb.setLength(0);
            while (true) {
                int c = in.read();
                if (c == -1) {
                    return null;
                }
                if (" \n\r\t".indexOf(c) == -1) {
                    sb.append((char)c);
                    break;
                }
            }
            while (true) {
                int c = in.read();
                if (c == -1 || " \n\r\t".indexOf(c) != -1) {
                    break;
                }
                sb.append((char)c);
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}