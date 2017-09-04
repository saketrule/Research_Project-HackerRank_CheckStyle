import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        new Solution().solve();
    }
    
    private void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        boolean[][][] path = new boolean[n][n][10];
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int r = in.nextInt() % 10;
            path[x][y][r] = true;
            path[y][x][10 - r] = true;
        }
        boolean found = true;
        while(found) {
            found = false;
            for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int ci = 0; ci < 10; ci++)
                for (int j = 0; j < n; j++)
                for (int cj = 0; cj < 10; cj++)
                        if (!path[i][j][(ci + cj) % 10]) {
                            path[i][j][(ci + cj) % 10] = path[i][k][ci] && path[k][j][cj];
                            if (path[i][j][(ci + cj) % 10]) {
                                found = true;
                            }
                        }
                }
        int[] res = new int[10];
        for (int c = 0; c < 10; c++) {
            for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j && path[i][j][c]) {
                //System.err.println("path from " + (i+1) + " to " + (j+1) + " with cost " + c);
                res[c]++;
            }
                System.out.println(res[c]);
        }
    }
}