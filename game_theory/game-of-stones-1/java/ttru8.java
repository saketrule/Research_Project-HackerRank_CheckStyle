import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static boolean[] getWinnerTable(int n) {
        // true = player 1 wins; false = player 2 wins
        boolean[] winnerTable = new boolean[n+1];
        for(int i = 0; i <=n; i++) {
            boolean take2Result = (i >= 2) ? !winnerTable[i-2] : false;
            boolean take3Result = (i >= 3) ? !winnerTable[i-3] : false;
            boolean take5Result = (i >= 5) ? !winnerTable[i-5] : false;
            winnerTable[i] = take2Result || take3Result || take5Result;
        }
        return winnerTable;
    }

    public static void main(String[] args) {
        boolean[] winnerTable = getWinnerTable(100);
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++) {
            System.out.println(winnerTable[sc.nextInt()] ? "First" : "Second");
        }
    }
}