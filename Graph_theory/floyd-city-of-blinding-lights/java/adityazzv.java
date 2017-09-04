import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        long[][] ar = new long[n+1][n+1];
        for(int i=0; i<n+1; i++){
            for(int j=0; j<n+1; j++){
                if(i!=j)
                    ar[i][j] = Integer.MAX_VALUE;
            }
        }
        
        while(m-- > 0){
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            ar[a][b] = c;
        }
        
        for(int k=1; k<n+1; k++){
            for(int i = 1; i<n+1; i++){
                for(int j = 1; j< n+1; j++){
                    ar[i][j] = Math.min(ar[i][j], ar[i][k] + ar[k][j]);
                }
            }
        }
        int q = sc.nextInt();
        while(q-- > 0){
            int x = sc.nextInt(), y = sc.nextInt();
            System.out.println((ar[x][y] == Integer.MAX_VALUE)?-1:ar[x][y]);
        }
    }
}