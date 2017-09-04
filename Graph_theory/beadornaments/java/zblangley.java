import java.util.*;

class Solution {
    private static final long MOD = 1000000007L;
    
    public static long pow(long x, long c, long n) {
        long z = 1;
        // only will be passed ints
        for (int i = 31; i >= 0; i--) {
            z = z*z % n;
            if ((c & (1L << i)) != 0)
                z = z*x % n;
        }
        return z;
    }
        
    static long[] cache = new long[31];
    static { cache[1] = 1; }

    // number of labeled trees on n vertices
    public static long t(int n) {
        if (cache[n] != 0)
            return cache[n];
        return (cache[n] = pow(n, n - 2, MOD));
    }

    // binomial coefficient
    static int[][] bin = new int[31][31];
    static {
        bin[0][0] = 1;
        for (int i = 1; i < bin.length; i++) {
            bin[i][0] = 1;
            for (int j = 1; j <= i; j++)
                bin[i][j] = bin[i-1][j-1] + bin[i-1][j];
        }
    }

    public static long occur(int[] c) {
        long count = 1;
        int places = c.length;
        for (int i = 0; i < c.length; i++){
            count *= bin[places-2][c[i]];
            places -= c[i];
        }
        return count;
    }

    public static long count(int[] b, int i, int target, int[] c) {
        if (i == b.length - 1) {
            c[i] = target;
            long ret = occur(c);
            for (int j = 0; j < c.length; j++) {
                ret *= (pow(b[j], c[j] + 1, MOD) * t(b[j])) % MOD;
                ret %= MOD;
            }
            return ret;
        } else {
            long sum = 0;
            for (int j = 0; j <= target; j++) {
                c[i] = j;
                sum += count(b, i + 1, target - j, c);
                sum %= MOD;
            }
            return sum;
        }    
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            
            int[] b = new int[n];
            for (int i = 0; i < n; i++)
                b[i] = scan.nextInt();
            
            if (n == 1)
                System.out.println(t(b[0]));
            else if (n == 2) {
                long prod = t(n);
                for (int i = 0; i < n; i++) {
                    prod *= b[i] * t(b[i]);
                }
                System.out.println(prod % MOD);
            } else {
                System.out.println(count(b, 0, b.length - 2, new int[n]));
            }
        }
    }
}