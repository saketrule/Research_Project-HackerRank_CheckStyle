import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int[][] winningPositions = new int[15][15];
        while (containsZeros(winningPositions)) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (winningPositions[i][j] != 0) {
                        continue;
                    }
                    if (!(i - 2 < 0 || j + 1 >= 15 || winningPositions[i - 2][j + 1] > 0)) {
                        continue;
                    }
                    if (!(i - 2 < 0 || j - 1 < 0 || winningPositions[i - 2][j - 1] > 0)) {
                        continue;
                    }
                    if (!(i - 1 < 0 || j - 2 < 0 || winningPositions[i - 1][j - 2] > 0)) {
                        continue;
                    }
                    if (!(i + 1 >= 15 || j - 2 < 0 || winningPositions[i + 1][j - 2] > 0)) {
                        continue;
                    }
                    winningPositions[i][j] = -1;
                    if (i + 2 < 15 && j - 1 >= 0) {
                        winningPositions[i + 2][j - 1] = 1;
                    }
                    if (i + 2 < 15 && j + 1 < 15) {
                        winningPositions[i + 2][j + 1] = 1;
                    }
                    if (i + 1 < 15 && j + 2 < 15) {
                        winningPositions[i + 1][j + 2] = 1;
                    }
                    if (i - 1 >= 0 && j + 2 < 15) {
                        winningPositions[i - 1][j + 2] = 1;
                    }
                }
            }
        }
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (winningPositions[x][y] > 0) {
                System.out.println("First");
            }
            else {
                System.out.println("Second");
            }
        }
    }
    
    public static boolean containsZeros(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            int[] b = a[i];
            for (int j = 0; j < b.length; j++) {
                if (b[j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}