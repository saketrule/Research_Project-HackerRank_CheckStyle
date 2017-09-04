import java.util.*;

public class Solution {

    static int[][] dp;
    static int[][] a;
    static int I;
    static int n;
    static int k;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        n = scanner.nextInt();
        k = scanner.nextInt();
        I = scanner.nextInt();
        dp = new int[n + 1][k + 2];
        a = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            int nr = scanner.nextInt();
            for (int j = 0; j < nr; j++) {
                int nrr = scanner.nextInt();
                a[i][nrr]++;
            }
        }

        for (int floor = k; floor > -1; floor--) {
            int maximfloor = maxim(floor);
            for (int block = n; block > -1; block--) {
                dp[block][floor] = Math.max(dp[block][floor + 1], maximfloor) + a[block][floor];
            }
        }
        int max = 0;
        for (int block = n; block > -1; block--) {
            if (dp[block][0] > max) {
                max = dp[block][0];
            }
        }
        System.out.println(max);
    }

    private static int maxim(int floor) {
        int targetflight = floor + I;
        int max = 0;
        try {
            for (int i = 1; i <= n; i++) {
                if (dp[i][targetflight] > max) {
                    max = dp[i][targetflight];
                }

            }
        } catch (Exception ex) {
            return 0;
        }
        return max;
    }
}