import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int MAX = Integer.MAX_VALUE/2;
        int [][] graph = new int[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if(i == j)
                    graph[i][j] = 0;
                else if (graph[i][j] == 0)
                    graph[i][j] = MAX;
            }
        }
        
        for(int i = 0; i<M; i++){
            graph[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        }
        
        Floyd_Warshall(graph, N+1);
        int Q = sc.nextInt();
        for(int i = 0; i<Q; i++){
            int ans = graph[sc.nextInt()][sc.nextInt()] ;
            if(ans == MAX){
                System.out.println("-1");
            }else {
                System.out.println(ans);
            }
        }
    }
    
    private static void Floyd_Warshall(int[][] D, int N) {
        for (int k = 1; k < N; k++) {
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    if (D[i][j] > D[i][k] + D[k][j])
                        D[i][j] = D[i][k] + D[k][j];
                }
            }
        }
    }
}