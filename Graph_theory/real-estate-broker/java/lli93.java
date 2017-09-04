import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        boolean[][] g = new boolean[n][m];
        int[] a = new int[n];
        int[] p = new int[n];
        for(int i=0; i<n; i++){
            a[i] = scan.nextInt();
            p[i] = scan.nextInt();
        }
        for(int j=0; j<m; j++){
            int x = scan.nextInt();
            int y = scan.nextInt();
            for(int i=0; i<n; i++){
                if(x>a[i] && p[i]>=y){
                    g[i][j] = true;
                }
            }
        }
        int res = 0;
        int[] match = new int[m];
        for(int j=0; j<m; j++){
            match[j] = -1;
        }
        for(int i=0; i<n; i++){
            boolean[] visited = new boolean[m];
            if(dfs(i, visited, match, g, m)){
                res++;
            }
        }
        System.out.println(res);
    }
    public static boolean dfs(int client, boolean[] visited, int[] match, boolean[][] g, int m){
        for(int j=0; j<m; j++){
            if(g[client][j] && !visited[j]){
                visited[j] = true;
                if(match[j]==-1 || dfs(match[j], visited, match, g, m)){
                    match[j] = client;
                    return true;
                }
            }
        }
        return false;
    }
}