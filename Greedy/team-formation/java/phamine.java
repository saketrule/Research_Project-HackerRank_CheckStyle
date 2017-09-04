import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int t = in.nextInt(); t > 0; t--) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = in.nextInt();

            Arrays.sort(a);
            long team[][] = new long[2][n];

            int end = 0;
            for (int i = 0; i < n; i++) {
                int t1 = getTeamToAdd(a[i], team, 0, end);
                if (t1+1 > end) end = t1+1;
                team[0][t1] = a[i];
                team[1][t1]++;
            }

            if (n == 0)
                System.out.println(0);
            else {
                Arrays.sort(team[1], 0, end);
                System.out.println(team[1][0]);
            }
        }
    }

    // pre & post: team[0] sorted asc
    private static int getTeamToAdd(long val, long team[][], int fromIdx, int toIdx){
        int valIdx = Arrays.binarySearch(team[0], fromIdx, toIdx, val - 1);
        if (valIdx < 0)
            valIdx = toIdx;
        else {
            // since bsearch can return any idx if there are duplicates, get the
            // last most idx of the range of duplicates to keep team[0] sorted asc
            while (valIdx + 1 < toIdx && team[0][valIdx + 1] == val - 1) valIdx++;
        }
        return valIdx;
    }
}