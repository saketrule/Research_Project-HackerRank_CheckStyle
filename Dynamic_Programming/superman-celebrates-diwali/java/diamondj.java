import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int h = in.nextInt();
        int p = in.nextInt();
        int[][] arr = new int[h][n];
        for (int i=0; i<n; i++) {
            int ppl = in.nextInt();
            for (int j=0; j< ppl; j++) {
                int temp = in.nextInt()-1;
                arr[temp][i]++;
            }
        }
        int[][]dp = new int[h][n];
        int[] dp2 = new int[h];
        for (int i=h-1; i>=0; i--) {
            //System.out.println();
            for (int j=0; j<n; j++) {
                    if (i==h-1) {
                        dp[i][j] = arr[i][j];
                        dp2[i] = Math.max(dp2[i], dp[i][j]);
                    }
                    else {
                        dp[i][j] = Math.max(dp[i+1][j]+arr[i][j], dp[i][j]);
                        if (i+p <h) {
                            dp[i][j] = Math.max(arr[i][j]+dp2[i+p], dp[i][j]);
                        }
                        dp2[i] = Math.max(dp2[i], dp[i][j]);
                }
                //System.out.print(dp[i][j]+" ");
            }
        }
        int maxp = 0;
        for (int i=0; i<n; i++) {
            //System.out.println(dp[0][i]);
            maxp = Math.max(maxp, dp[0][i]);
        }
        System.out.println(maxp+"");
    }
}