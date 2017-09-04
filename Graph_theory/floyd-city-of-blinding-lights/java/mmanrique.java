import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int V = scanner.nextInt();
        int[][] distances = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
            distances[i][i] = 0;
        }
        int E = scanner.nextInt();
        for (int e = 0; e < E; e++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weight = scanner.nextInt();
            distances[from][to] = weight;
        }

        solve(distances);
        int Q = scanner.nextInt();
        for (int q = 0; q < Q; q++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int distance = distances[from][to];
            System.out.println(distance == Integer.MAX_VALUE ? -1 : distance);

        }

    }

    private static void solve(int[][] distances) {
        for (int k = 0; k < distances.length; k++) {
            for (int i = 0; i < distances.length; i++) {
                for (int j = 0; j < distances.length; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE && distances[i][j] > distances[i][k] + distances[k][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
    }

}