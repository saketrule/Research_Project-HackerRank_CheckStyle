import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();


        int[][] adj = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    adj[i][j] = 0;
                else
                    adj[i][j] = Integer.MAX_VALUE / 4;
            }
        }

        for (int g = 0; g < m; g++) {
            int x = in.nextInt(); x--;
            int y = in.nextInt(); y--;
            int r = in.nextInt();

            adj[x][y] = r;
        }

        for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                    if (adj[i][k] < Integer.MAX_VALUE / 4 && adj[k][j] < Integer.MAX_VALUE / 4)
                        adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        int q = in.nextInt();

        while (q -- > 0) {
            int x = in.nextInt(); x--;
            int y = in.nextInt(); y--;

            int ans = adj[x][y];
            if (ans >= 350 * 200 * 399)
                System.out.println(-1);
            else
                System.out.println(adj[x][y]);
        }


    }

}