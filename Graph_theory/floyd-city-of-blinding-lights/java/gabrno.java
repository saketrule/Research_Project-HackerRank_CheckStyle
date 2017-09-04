import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static final int inf = 99999999;
        
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
     int n = sc.nextInt(), m = sc.nextInt();
        
        int[][] dist = new int[n+1][n+1];
        
        for (int i=1;i<n+1;i++) {
            for (int j=1;j<n+1;j++) {
                dist[i][j] = inf;
            }
            dist[i][i]=0;
        }
        
        while (m --> 0) {
            int x = sc.nextInt(), y = sc.nextInt(), r = sc.nextInt();
            dist[x][y]=r;
        }
        
        int q = sc.nextInt();
        
        for (int k=1;k<=n;k++) {
         for (int i=1;i<=n;i++) {
                for (int j=1;j<=n;j++) {
                    int temp = dist[i][k]+dist[k][j];
                    if (dist[i][j]>temp) dist[i][j]=temp;
                }
         }
        }
        
        while(q-->0) {
            int from = sc.nextInt(), to = sc.nextInt();
            System.out.println(dist[from][to]==inf?-1:dist[from][to]);
        }
         
    }
}