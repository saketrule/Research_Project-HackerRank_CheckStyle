import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  
 public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        long r = in.nextLong();
     
     long arr[][] = new long[m][n];
     long newArr[][] = new long[m][n];
     
     
     for(int i = 0; i<m;i++){
         for(int j = 0; j<n; j++){
             arr[i][j] = in.nextLong();
         }
     }
     
     for(int i = 0; i<m;i++){
           for(int j = 0; j<n; j++){
               int ii = i;
               int jj = j;
               int depth = Math.min(Math.min(ii,jj),Math.min(m-ii -1, n-jj - 1));
               long rot = r%(m*2 + n*2 -4 - (depth) *  8);
               while(rot > 0){
                   if(ii == depth && jj != depth){
                       jj--;
                   }else if(jj == n - 1-depth && ii != depth - 1){
                       ii--;
                   }else if(ii == m -1- depth && jj != n - depth -1){
                       jj++;
                   }else if(jj == depth && ii != m - depth - 1){
                       ii++;
                   } 
                   rot--;
                //   System.out.println(ii);
                //System.out.println(jj);
               }
            //  System.out.println(depth);
                
               newArr[ii][jj] = arr[i][j];
           }
     }
     
      for(int i = 0; i<m;i++){
           for(int j = 0; j<n; j++){
               System.out.print(newArr[i][j] + " ");
           }
          System.out.println();
      }
   }
}