import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static void pasteTessalation(boolean[][] board, int r, int c) {
        board[r][c] = true;
        board[r][c+1] = true;
        board[r+1][c] = true;
        board[r+1][c+1] = true;
    }

    public static void main(String[] args) {
        boolean[][] loss = new boolean[16][16];
        for (int i=0; i<16; i+=4) {
            for (int j=0; j<16; j+=4) {
                pasteTessalation(loss,i,j);
            }
        }
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t=0; t<T; t++) {
            int r = sc.nextInt()-1;
            int c = sc.nextInt()-1;
            if (loss[r][c]) {
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
        }
        
    }
    
}