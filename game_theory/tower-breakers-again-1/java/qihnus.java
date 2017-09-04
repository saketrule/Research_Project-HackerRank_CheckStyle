import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static int primeFactors(int n) {
        int res = 0, p = 2;
        while (n >= p) {
            if (n % p == 0) {
                if (p % 2 != 0) res++;
                n /= p;
            } else p++;
        }
        return res;
    }
    private static int solve(int[] a) {
        int res = 0;
        for (int x: a) {
            int k = primeFactors(x);
            if (x % 2 == 0) res ^= k+1;
            else res ^= k;
        }
        return res != 0 ? 1 : 2;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        for (int T = in.nextInt(); T > 0; T--) {
            int N = in.nextInt();
            int[] a = new int[N];
            for (int i = 0; i < N; i++) a[i] = in.nextInt();
            System.out.println(solve(a));
        }
    }
}