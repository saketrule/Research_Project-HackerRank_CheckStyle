import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

     public static void main(String[] args) {
        int m,n;
   Scanner sc=new Scanner(System.in);
   m=sc.nextInt();
   n=sc.nextInt();
   
   long r=sc.nextLong();
   long[][] mat=new long[m][n];
   
   for(int a=0;a<m;a++){
    for(int b=0;b<n;b++){
     mat[a][b]=sc.nextLong();
    }
   }
   
   getSolution(mat,r);
  }

  private static void getSolution(long[][] mat, long r) {
   long m=mat.length;
   long n=mat[0].length;
   
    int k=0;
    for(int row=0,col=0;k<Math.min(m, n)/2 ;){
     k++;
     int i=row;
     int j=col;
     long start_long=mat[row][col+1];
     long ter=(2*(m-(2*row)))+(2*(n-(2*col)))-4;
     int loop=(int) r;
     if(r>ter)loop=(int) (r%ter);
     while(loop>0){
       i=row;
       j=col;
        start_long=mat[row][col+1];
       
     long start=mat[i][j];
     i++;
     while(i<(m-row-1)){
      long f=mat[i][j];
      mat[i][j]=start;
      start=f;i++;
     }
     
     while(j<(n-col-1)){
      long f=mat[i][j];
      mat[i][j]=start;
      start=f;j++;
     }
          
     while(i>=row){
      long f=mat[i][j];
      mat[i][j]=start;
      start=f;
      if(i==row)break;
      i--;
     }
         
     j--;
     while(j>=col){
      long f=mat[i][j];
      mat[i][j]=start;
      start=f;
      j--;
     }
     loop--;
                             mat[row][col]=start_long;

     }
     
     if(col<Math.min(m, n)/2)col++;
     if(row<Math.min(m, n)/2)row++;
    }
    
    
    r--;
   
   for(int a=0;a<m;a++){
    for(int b=0;b<n;b++){
     System.out.print(mat[a][b]+" ");
    }
    System.out.println("");
   }
   
   
  
 }
}