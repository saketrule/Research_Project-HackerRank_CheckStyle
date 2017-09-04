import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Solution {

    static long MOD = 1000000007;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        while (cases-- > 0) {
            int n = in.nextInt();
            long[] a = new long[n];
            long[] trees = new long[n];
            long all = 0;
            long res = 1;
            for (int i = 0; i < n; i++) {
                a[i] = (long) in.nextInt();
                if (a[i] > 1) {
                    trees[i] = PowerMod(a[i], a[i] - 2, MOD);
                } else {
                    trees[i] = 1;
                }
                all = (all + a[i]) % MOD;
                res = ( (res * a[i] % MOD) * trees[i]) % MOD;
            }

            res = ( res * PowerMod(all, n - 2, MOD) ) % MOD;
            if (n > 1) {
                System.out.println(res);
            } else {
                System.out.println(trees[0]);
            }

        }
    }
    static long PowerMod(long a, long b, long m) {
        long tmp;
        if (b == 0 ) {
            tmp =  1;
        } else if (b == 1) {
            tmp = a;
        } else {
            long tmp1 = PowerMod(a,b/2,m);
            if (b % 2 == 0) {
                tmp = (tmp1*tmp1)%m;
            } else {
                tmp = ((tmp1*tmp1)%m)*a%m;
            }
        }
        return tmp;
    }
}