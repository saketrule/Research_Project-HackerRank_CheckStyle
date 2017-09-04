import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
public class Solution {
 
    private static int solve(int[] a, int p, int q) {
        int maxmin = 0;
        int maxm = -1;
        int m = check(a, p, q, p);
        if (m > maxmin) { maxmin = m; maxm = p; }
        m = check(a, p, q, q);
        if (m > maxmin) { maxmin = m; maxm = q; }
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int t = (a[i] + a[j]) / 2;
                m = check(a, p, q, t);
                if (m > maxmin) { maxmin = m; maxm = t; }
            }
        }
        return maxm;
    }
 
    private static int check(int[] a, int p, int q, int x) {
        if (x < p || x > q) return -1;
        int minm = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            int m = Math.abs(a[i] - x);
            minm = Math.min(minm, m);
        }
        return minm;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        int p = in.nextInt();
        int q = in.nextInt();
        System.out.println(solve(a, p, q));
    }
}