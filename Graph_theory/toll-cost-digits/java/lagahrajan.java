import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
public static int[][] adj;
    public static  int[] ans=new int[10];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        adj=new int[n][n];
        for(int i=0;i<n;i++)
            Arrays.fill(adj[i],-1);
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            int r = in.nextInt();
            
            adj[x][y]=r;
            adj[y][x]=1000-r;
            
        }
    intCount(n);
        floyd(n);
        for(int i=0;i<10;i++)
            System.out.println(ans[i]);
       
    }
    public static void intCount(int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                if(adj[i][j]!=-1)
                ans[adj[i][j]%10]++;
        }
        
    }
    public static void floyd(int n){
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(adj[i][k]!=-1 && adj[k][j]!=-1 && i!=j)
                        ans[(adj[i][k]+adj[k][j])%10]++;
                }
            }
        }
    }
}