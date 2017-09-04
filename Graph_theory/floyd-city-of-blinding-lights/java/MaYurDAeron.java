import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        long graph[][] = new long[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
            if(i==j)
                graph[i][j] = 0;
            else
                graph[i][j] = Integer.MAX_VALUE;
        }
        for(int i=0;i<e;i++)
            graph[sc.nextInt()-1][sc.nextInt()-1] = sc.nextLong();
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(graph[i][k]+graph[k][j]<graph[i][j])
                        graph[i][j] = graph[i][k]+graph[k][j];
                }
            }
        }
        int t = sc.nextInt();
        for(int i=0;i<t;i++){
            int s = sc.nextInt()-1;
            int d = sc.nextInt()-1;
            long v = graph[s][d];
            if(v==Integer.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(v);
        }
    }
}