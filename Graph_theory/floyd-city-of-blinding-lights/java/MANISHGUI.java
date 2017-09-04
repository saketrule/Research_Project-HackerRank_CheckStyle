import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int[][] dist;
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] graph= new int[n][n];
        int i,j;
        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
                graph[i][j]= Integer.MAX_VALUE;
        for(i=0;i<n;i++)
            graph[i][i]=0;
        for(i=0;i<m;i++)
        {
            int u= in.nextInt()-1;
            int v= in.nextInt()-1;
            int dist=in.nextInt();
            graph[u][v]=dist;
        }
        floydWarshal(graph,n);
        int q= in.nextInt();
        for(i=0;i<q;i++)
        {
            int a= in.nextInt()-1;
            int b= in.nextInt()-1;
            System.out.println(dist[a][b]);
        } 
    }
    public static void floydWarshal(int[][] graph, int n )
    {
        dist= new int[n][n];
        int i,j,k;
        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
                dist[i][j]=graph[i][j];
        for(k=0;k<n;k++)
            for(i=0;i<n;i++)
                for(j=0;j<n;j++)
                    if(dist[i][k]!=Integer.MAX_VALUE && dist[k][j]!=Integer.MAX_VALUE && dist[i][k]+dist[k][j]<dist[i][j])
                        dist[i][j]=dist[i][k]+dist[k][j];
        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
                if(dist[i][j]==Integer.MAX_VALUE)
                    dist[i][j]=-1;
    }
}