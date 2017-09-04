import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int inf=400*400/2*350;
        Scanner in = new Scanner(System.in);
        int vertex=in.nextInt();
        int edge=in.nextInt();
        int[][] dp=new int[vertex][vertex];
        for(int i=0;i<vertex;i++){
            for(int j=0;j<vertex;j++){
                if (i==j) dp[i][j]=0;
                else dp[i][j]=inf;
            }
        }
        for(int i=0;i<edge;i++){
            int start=in.nextInt()-1;
            int end=in.nextInt()-1;
            int distance=in.nextInt();
            dp[start][end]=distance;
        }
        for(int k=0;k<vertex;k++){
            for(int i=0;i<vertex;i++){
                for(int j=0;j<vertex;j++){
                    dp[i][j]=Math.min(dp[i][k]+dp[k][j],dp[i][j]);
                }
            } 
        }
        int query=in.nextInt();
        for(int i=0;i<query;i++){
            int start=in.nextInt()-1;
            int end=in.nextInt()-1;
            if (dp[start][end]==inf) System.out.println(-1);
            else System.out.println(dp[start][end]);
        }
    }
}