import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        boolean[][] board = new boolean[15][15];
        ArrayDeque<Integer> xq = new ArrayDeque<Integer>();
        ArrayDeque<Integer> yq = new ArrayDeque<Integer>();
        xq.add(0);
        xq.add(0);
        xq.add(1);
        xq.add(1);
        yq.add(0);
        yq.add(1);
        yq.add(0);
        yq.add(1);
        while (!xq.isEmpty()) {
            int x = xq.removeFirst();
            int y = yq.removeFirst();
            if (!(x>=0&&y>=0&&x<=14&&y<=14))
                continue;
            boolean newBoard = false;
            if (x>=2&&y<=13&&!board[x-2][y+1])
                newBoard = true;
            if (x>=2&&y>=1&&!board[x-2][y-1])
                newBoard = true;
            if (y>=2&&x<=13&&!board[x+1][y-2])
                newBoard = true;
            if (y>=2&&x>=1&&!board[x-1][y-2])
                newBoard = true;
            if (!board[x][y] || !newBoard) {
                board[x][y] = newBoard;
                xq.add(x+2);
                xq.add(x+2);
                xq.add(x+1);
                xq.add(x-1);
                yq.add(y-1);
                yq.add(y+1);
                yq.add(y+2);
                yq.add(y+2);
            }
        }
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            if (board[y][x])
                System.out.println("First");
            else
                System.out.println("Second");
        }
    }
}