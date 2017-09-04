import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    private static final Map<Long, long[]> cache = new HashMap<>();
    private static int N;
    private static int M;
    private static int[] C;
    private static long[] R;

    public static void main(String[] args) {
        N = readInt();
        M = readInt();
        C = new int[N];
        for (int i = 0; i < N; i++) {
            C[i] = readInt();
        }
        R = new long[N];
        for (int i = 0; i < M; i++) {
            int a = readInt() - 1,
                b = readInt() - 1;
            R[a] |= 1L << b;
            R[b] |= 1L << a;
        }
        long mask = (1L << N) - 1;
        long[] result = solve(mask, 0L);
        System.out.println(result[0] + " " + result[1]);
    }

    private static long[] solve(long mask, long sum) {
        if (mask == 0) {
            return new long[] { sum, 1 };
        }
        Long maskL = mask;
        long[] cached = cache.get(maskL);
        if (cached != null) {
            return new long[] { cached[0] + sum, cached[1] };
        }
        int town = getTown(mask);
        mask &= ~(1L << town);

        long[] s1 = solve(mask, sum);
        long[] s2 = solve(mask & ~R[town], sum + C[town]);

        if (s1[0] == s2[0]) {
            cached = new long[] { s1[0], s1[1] + s2[1] };
        } else if (s1[0] > s2[0]) {
            cached = s1;
        } else {
            cached = s2;
        }
        cache.put(maskL, new long[] { cached[0] - sum, cached[1] });
        return cached;
    }

    private static int getTown(long l) {
        return Long.bitCount(Long.highestOneBit(l) - 1);
    }

    static InputStream in = new BufferedInputStream(System.in);

    static int readInt() {
        try {
            int c = in.read();
            while (c <= 32) {
                c = in.read();
            }
            boolean minus = false;
            if (c == '-') {
                minus = true;
                c = in.read();
            }
            int result = (c - '0');
            c = in.read();
            while (c >= '0') {
                result = result * 10 + (c - '0');
                c = in.read();
            }
            return minus ? -result : result;
        } catch (IOException e) {
            return -1; // should not happen
        }
    }
}