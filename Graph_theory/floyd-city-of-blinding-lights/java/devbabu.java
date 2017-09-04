import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        int N = sin.nextInt();
        int M = sin.nextInt();
        int [][]arr = new int[N+1][N+1];
        for(int i = 0; i < N+1; i++){
            arr[i][0] = i;
            arr[i][i] = 0;
            arr[0][i] = i;
        }
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < N+1; j++){
                if(i == j)
                    arr[i][j] = 0;
                else
                    arr[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0; i < M; i++){
            int s = sin.nextInt();
            int e = sin.nextInt();
            int r = sin.nextInt();
            arr[s][e] = r;
        }
        
        for(int k = 1; k < N+1; k++){
            for(int i = 1; i < N+1;i++){
                for(int j = 1; j < N+1; j++){                    
                    if (arr[i][k] != Integer.MAX_VALUE && arr[k][j] != Integer.MAX_VALUE)
                        arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                }
            }
        }
        int Q = sin.nextInt();
        for(int i = 0; i < Q; i++){
            int s = sin.nextInt();
            int e = sin.nextInt();
            System.out.println(arr[s][e] == Integer.MAX_VALUE ? -1 : arr[s][e]);
        }
    }
}