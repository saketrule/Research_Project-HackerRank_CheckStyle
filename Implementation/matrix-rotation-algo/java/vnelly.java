import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int rotate(int[][] matrix, int k, int m, int n, int i, int j) {
        int skyband = Math.min(Math.min(i, j), Math.min(m - i - 1, n - j - 1));
        int perimeter = (m - 2*skyband - 1) * 2 + (n - 2*skyband - 1) * 2;
        k = k % perimeter;
        int row = i;
        int col = j;
        for (; k > 0; k--) {
            if (col == skyband && row != skyband) {
                row -= 1;
            } else if (row == skyband && col != n - skyband - 1) {
                col += 1;
            } else if (col == n - skyband - 1 && row != m - skyband - 1) {
                row += 1;
            } else {
                col -= 1;
            }
        }
        return matrix[row][col];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int r = in.nextInt();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(rotate(matrix, r, m, n, i, j) + " ");
            }
            System.out.println();
        }
    }
}