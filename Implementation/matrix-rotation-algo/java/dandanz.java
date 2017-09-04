import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void rotate(int[][] matrix, int M,int N,int R){
       int row=-1,col=0,m=M,n=N;
       int[] level=new int[M*N];   
       int j=0;
       int temp=matrix[0][0];
       while(true){
           int start=j;
           for(int i=0;i<M;++i){
               level[j++]=matrix[++row][col];
           }
           if(--N==0) {
            //rotate(level,R%(j-start),start,j-1);
            break;
           }
           for(int i=0;i<N;++i){
               level[j++]=matrix[row][++col];
           }
           if(--M==0) {
            //rotate(level,R%(j-start),start,j-1);
            break;
           }
           for(int i=0;i<M;++i){
               level[j++]=matrix[--row][col];
           }
           if(--N==0) {
            rotate(level,R%(j-start),start,j-1); 
            break;
           }
           for(int i=0;i<N;++i){
               level[j++]=matrix[row][--col];
           }
           rotate(level,R%(j-start),start,j-1);
           if(--M==0) {
            break;
           }
       }
       j=0;M=m;N=n;row=-1;col=0;
       while(true){
        for(int i=0;i<M;++i){
               matrix[++row][col]=level[j++];
           }
           if(--N==0) break;
           for(int i=0;i<N;++i){
               matrix[row][++col]=level[j++];
           }
           if(--M==0) break;
           for(int i=0;i<M;++i){
               matrix[--row][col]=level[j++];
           }
           if(--N==0) break;
           for(int i=0;i<N;++i){
               matrix[row][--col]=level[j++];
           }
           if(--M==0) break;
       }
   }
   private static void rotate(int[] level, int R, int start, int end){
    reverse(level,start,end);
    reverse(level,start,R-1+start);
    reverse(level,R+start,end);   
   }
   private static void reverse(int[] level,int start,int end){
    while(start<end){
     int temp=level[start];
     level[start++]=level[end];
     level[end--]=temp;
    }
   }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int M=sc.nextInt(), N=sc.nextInt(),R=sc.nextInt();
        int[][] grid=new int[M][N];
        for(int i=0;i<M;++i){
            for(int j=0;j<N;++j){
                grid[i][j]=sc.nextInt();
                
            }
        }
        rotate(grid,M,N,R);
        for(int i=0;i<M;++i){
            for(int j=0;j<N;++j){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
        
    }
}