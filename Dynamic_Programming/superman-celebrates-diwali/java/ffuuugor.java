import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception{
//        Scanner in = new Scanner(new File("input/superman"));
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        int h = in.nextInt();
        int heightLoose = in.nextInt();

        int[][] people = new int[n][h];
        int[] maxDp = new int[h];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < h; k++) {
                people[j][k] = 0;
            }
        }

        for (int i = 0; i < h; i++) {
            maxDp[i] = 0;
        }

        for (int j = 0; j < n; j++) {
            int peopleOnTheFloor = in.nextInt();
            for (int k = 0; k < peopleOnTheFloor; k++) {
                int currFloor = in.nextInt();
                people[j][currFloor - 1]++;
            }
        }

        int[][] dp = new int[n][h];


        for (int i = 0; i < n; i++) {
            dp[i][0] = people[i][0];

            if (people[i][0] > maxDp[0]) {
                maxDp[0] = people[i][0];
            }
        }

        for (int j = 1; j < heightLoose; j++) {
            for (int i = 0; i < n; i++) {
                dp[i][j] = dp[i][j-1] + people[i][j];

                if (dp[i][j] > maxDp[j]) {
                    maxDp[j] = dp[i][j];
                }
            }
        }

        for (int j = heightLoose; j < h; j++) {
            for (int i = 0; i < n; i++) {
                dp[i][j] = Math.max(dp[i][j-1], maxDp[j - heightLoose]) + people[i][j];

                if (dp[i][j] > maxDp[j]) {
                    maxDp[j] = dp[i][j];
                }
            }
        }

        System.out.println(maxDp[h-1]);
    }
}