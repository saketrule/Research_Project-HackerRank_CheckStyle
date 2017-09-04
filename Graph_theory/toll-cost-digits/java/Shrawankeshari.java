import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        int[][] a = new int[n][n];
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            a[x-1][y-1] = r;
            a[y-1][x-1] = 1000-r;
        }
        int[] d = new int[10];
        
        for(int i = 0; i < n; i++){
            boolean[] visited = new boolean[n];
            visited[i] = true;
            dfs(i,a,visited,d,n,0);
        }
        
        for(int i = 0; i < 10; i++){
            System.out.println(d[i]);
        }
    }
    
    static void dfs(int x,int[][] a,boolean[] visited,int[] d,int n,int dist){
        for(int i = 0; i < n; i++){
            if(a[x][i]!=0 && visited[i]==false){
                visited[i] = true;
                dist += a[x][i];
                int y = dist%10;
                d[y] += 1;
                dfs(i,a,visited,d,n,dist);
                visited[i] = false;
                dist -= a[x][i];
            }
        }
    }
}