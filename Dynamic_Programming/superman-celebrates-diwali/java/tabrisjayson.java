import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int H = in.nextInt();
        int I = in.nextInt();
        int list[][] = new int[N][H];
        int DP_list[][] = new int[H][N];
        int max_saving[] = new int[H];
        for(int i = 0 ; i < N; i++){
         for(int j = 0 ; j < H; j++){
             list[i][j] = 0;
            }
        }
        for(int i = 0 ; i < N; i++){
         int u = in.nextInt();
         for(int j = 0 ; j < u; j++){
             list[i][in.nextInt() - 1]++;
            }
        }
        for (int i = 0; i < H; i++) {
         for (int j = 0; j < N; j++) {
          if(i > 0) {
           DP_list[i][j] = DP_list[i-1][j];
          }

          if (i - I >= 0) {
           DP_list[i][j] = Math.max(DP_list[i][j], max_saving[i - I]);
          }

          DP_list[i][j] += list[j][i];

          max_saving[i] = Math.max(max_saving[i], DP_list[i][j]);

         }
        }
        System.out.println(max_saving[H-1]);
        
    }
}