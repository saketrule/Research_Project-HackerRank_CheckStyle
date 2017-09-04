import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner in  = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int adj[][] = new int[n+1][n+1];
        
        for(int i=1;i<=n;i++)
            for(int j=1;j<=n;j++)
                if(i!=j)
                  adj[i][j]=Integer.MAX_VALUE;
        
        for(int i=0;i<m;i++)
        {
            int u = in.nextInt();
            int v = in.nextInt();
            adj[u][v]=in.nextInt();
        }
        
        int max=Integer.MAX_VALUE;
        for(int k=1;k<=n;k++)
          for(int i=1;i<=n;i++)
            for(int j=1;j<=n;j++)
                if (k != i && k != j && adj[i][k] != max && adj[k][j] != max && (adj[i][k] + adj[k][j] < adj[i][j]))
                    adj[i][j] = adj[i][k] + adj[k][j];
            
            int q = in.nextInt();
            
            for(int k=0;k<q;k++)
            {
                int x = in.nextInt();
                int y = in.nextInt();
                System.out.println(adj[x][y]==Integer.MAX_VALUE?-1:adj[x][y]);
            }
    }
}