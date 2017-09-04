import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int edges=input.nextInt();
        long[][] adj=new long[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                adj[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int i=0;i<edges;i++){
            int start=input.nextInt()-1;
            int end=input.nextInt()-1;
            adj[start][end]=Math.min(adj[start][end],input.nextLong());
            adj[end][start]=adj[start][end];
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if ((adj[i][k] | adj[k][j]) < adj[i][j])
                         adj[i][j] = adj[i][k] | adj[k][j];
                }
            }
        }
        int start=input.nextInt()-1;
        int end=input.nextInt()-1;
        long value=adj[start][end];
        if(value>=Integer.MAX_VALUE){
            value=-1;
        }
        System.out.println(value);
    }
}