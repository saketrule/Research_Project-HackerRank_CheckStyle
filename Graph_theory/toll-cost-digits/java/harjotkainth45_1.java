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
        int[][] a = new int[n+1][n+1];
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            a[x][y] = r%10;
            a[y][x] = 10-(r%10);
        }
        long[] d = new long[10];
        boolean[] visited = new boolean[n+1];
        for(int i = 1; i <= n; i++){
            visited[i] = true;
            dfs(i,a,visited,d,n,0);
            visited[i] = false;
        }
        
        for(int i = 0; i < 10; i++){
            System.out.println(d[i]);
        }
    }
    
    static void dfs(int x,int[][] a,boolean[] visited,long[] d,int n,int dist){
        for(int i = 1; i <= n; i++){
            if(a[x][i]>0 && visited[i]==false){
                visited[i] = true;
                d[(dist+a[x][i])%10] = d[(dist+a[x][i])%10]+1L;
                dfs(i,a,visited,d,n,(dist+a[x][i])%10);
                visited[i] = false;
            }
        }
    }
}