import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int i = 0;
        String first = "First";
        String second = "Second";
        while (i < n) {

            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String winner = play(x, y, first, second);
            System.out.println(winner);
            i++;
        }
    }

    public static String play(int x, int y, String p1, String p2) {

        Integer[][] moves = possibleMoves(x, y);

        if (moves[0] != null && moves[0].length > 0) {
            for (int i = 0; i < 4; i++) {
                if (moves[0][i] != null && moves[1][i] != null) {
                    String m = play(moves[0][i], moves[1][i], p2, p1);
                    if (m.equals(p1)) {
                        return p1;
                    }
                }
            }
            return p2;
        } else {
            return p2;
        }


    }

    public static Integer[][] possibleMoves(int x, int y) {
        Integer[][] moves = new Integer[2][4];


        int i = 0;
        if (canMove(x - 2, y + 1)) {
            moves[0][i] = x - 2;
            moves[1][i] = y + 1;
            i++;
        }

        if (canMove(x - 2, y - 1)) {
            moves[0][i] = x - 2;
            moves[1][i] = y - 1;
            i++;
        }
        if (canMove(x + 1, y - 2)) {
            moves[0][i] = x + 1;
            moves[1][i] = y - 2;
            i++;
        }
        if (canMove(x - 1, y - 2)) {
            moves[0][i] = x - 1;
            moves[1][i] = y - 2;
            i++;
        }
        return moves;

    }

    public static boolean canMove(int x, int y) {
        return x >= 1 && x <= 15 && y >= 1 && y <= 15;
    }
}