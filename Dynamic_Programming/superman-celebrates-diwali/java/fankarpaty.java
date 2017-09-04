import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int h = s.nextInt();
        int f = s.nextInt();
        int[][] p = new int[n+1][h+1];
        for (int i = 1; i <= n; i ++) {
            int c = s.nextInt();
            for (int j = 0; j < c; j++) {
                int x = s.nextInt();
                p[i][x]++;
            }
        }
        int[][] d = new int[n+1][h+1];
        int[] m = new int[h+1];
        for (int i = 1; i <= n; i++) {
            d[i][1] = p[i][1]; 
            if (p[i][1] > m[1])m[1] = p[i][1];
        }
        for (int j = 2; j<=h;j++) {
           for (int i = 1; i<=n;i++) {
               if (j - f > 0) d[i][j] = m[j - f];
               if (d[i][j-1] > d[i][j])d[i][j] = d[i][j-1];
               d[i][j] += p[i][j];
               if (d[i][j] > m[j])m[j] = d[i][j];
           } 
        }
        System.out.println(m[h]);
    }
}