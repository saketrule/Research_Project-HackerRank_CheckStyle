import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    //Hi I got a small dick......guesss inches
    public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        BigInteger[][] graph = new BigInteger[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = BigInteger.ZERO;
            }
        }
        
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            BigInteger cost = BigInteger.valueOf(2).pow(sc.nextInt());
            graph[a - 1][b - 1] = cost;
            graph[b - 1][a - 1] = cost;
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && graph[i][j].compareTo(BigInteger.ZERO) == 0)
                    graph[i][j] = BigInteger.valueOf(2).pow(m + 1);
            }
        }


        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j].compareTo(graph[i][k].add(graph[k][j])) == 1)
                        graph[i][j] = graph[i][k].add(graph[k][j]);
                }
            }
        }
        
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++)
                sum = sum.add(graph[i][j]);
        }

        BigInteger t = BigInteger.valueOf(2);
        StringBuilder sb = new StringBuilder();
        while (sum.compareTo(BigInteger.ZERO) != 0) {
            sb.insert(0,sum.mod(t));
            sum = sum.divide(t);
        }
        System.out.println(sb.toString());
    }
}