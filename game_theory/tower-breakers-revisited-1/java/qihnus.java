import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static int np(int n) {
        int c = 0, p = 2;
        while (n >= p) {
            if (n % p == 0) {
                c++; n /= p;
            } else p++;
        }
        return c;
    }
    private static boolean solve(int[] a) {
        int p = 0;
        for (int x: a) {
            p ^= np(x);
        }
        return p != 0;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        for (int T = in.nextInt(); T > 0; T--) {
            int N = in.nextInt();
            int[] h = new int[N];
            for (int i = 0; i < N; i++) h[i] = in.nextInt();
            System.out.println(solve(h) ? 1 : 2);
        }
    }
}