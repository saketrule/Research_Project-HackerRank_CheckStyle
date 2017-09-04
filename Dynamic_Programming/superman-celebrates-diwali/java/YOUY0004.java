import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

   public static void main(String[] args) {
          Scanner myScan = new Scanner(System.in);
                 
          int N = myScan.nextInt();
          int H = myScan.nextInt();
          int I = myScan.nextInt();
          myScan.nextLine();
          
          int data[][] = new int[H][N];
          
          for(int i=0;i<N;i++){
           int u = myScan.nextInt();
           for(int j=0;j<u;j++){
            int temp = myScan.nextInt();
            data[H - temp][i]++;
           }
          }          
  
          int dpTable[][] = new int[H][N];
                int lineMax[] = new int[H];
                int linePosition[] = new int[H];
                int globalMax = 0;
          for(int i=0;i<H;i++)
           for(int j=0;j<N;j++){
            int max = -1;
            if(i==0){
             max = 0;
            }
            else{
             if(i < I){
               max = dpTable[i-1][j]; 
             }
             else{
              max = dpTable[i-1][j];
              if(lineMax[i-I] > max && linePosition[i-I] == j){
                for(int l=0;l<N;l++){
                 if(l==j)
                  continue;
                 if(dpTable[i-I][l]>max){
                  max = dpTable[i-I][l];
                 }
                 }
              }
              if(lineMax[i-I] > max && linePosition[i-I] !=j){
                max = dpTable[i-I][linePosition[i-I]];
              }
             }
            }
            dpTable[i][j] = data[i][j] + max;
            if(dpTable[i][j] > lineMax[i]){
             lineMax[i] = dpTable[i][j];
             linePosition[i] = j;
            }
            if(dpTable[i][j]>globalMax)
             globalMax = dpTable[i][j];
           }
          System.out.println(globalMax);
          myScan.close();
 }
}