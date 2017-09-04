import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(), M = scanner.nextInt();
        int[][] graph = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt(), to = scanner.nextInt();
            int weight = scanner.nextInt();
            graph[from][to] = weight;
        }
        
        int[][][] dp = new int[N + 1][N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] != 0) {
                    dp[0][i][j] = graph[i][j];
                } else {
                    dp[0][i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        for (int k = 0; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                dp[k][i][i] = 0;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dp[k - 1][i][k] != Integer.MAX_VALUE && dp[k - 1][k][j] != Integer.MAX_VALUE) {
                        dp[k][i][j] = Math.min(dp[k - 1][i][j], dp[k -1][i][k] + dp[k -1][k][j]);
                    } else {
                        dp[k][i][j] = dp[k - 1][i][j];
                    }
                }
            }
        }

        int Q = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int from = scanner.nextInt(), to = scanner.nextInt();
            int res = dp[N][from][to] == Integer.MAX_VALUE ? -1 : dp[N][from][to];
            sb.append(res).append('\n');
        }
        System.out.println(sb.toString());
    }
}