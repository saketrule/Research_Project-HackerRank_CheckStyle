import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int M = sc.nextInt();
  int N = sc.nextInt();
  int R = sc.nextInt();
  int mat[][] = new int[M][N];
  
  for(int i=0; i<M; i++){
   for(int j=0; j<N; j++){
    mat[i][j] = sc.nextInt();
   }
  }
  int min = M<N?M:N;
  for(int i=0; i<(min/2); i++){
   int elements = (M-i*2)*2+(N-i*2)*2-4;
   int rotations =R%elements;
   int circleElements[] = new int[elements];  
   
   for(int j=i; j<(N-i-1);j++){
    circleElements[j-i] = mat[i][j];
   }
   for(int j=i; j<(M-i-1); j++){
    circleElements[(N-(i*2)-2)+j-i+1] = mat[j][N-1-i];
   }
   for(int j=N-i-1; j>i; j--)
    circleElements[(N-(i*2)-2)+(M-(i*2)-1)+(N-i-j)] = mat[M-1-i][j];
   
   for(int j=M-i-1; j>i;j--)
    circleElements[(N-(i*2)-2)+(N-(i*2)-1)+(M-(i*2)-1)+(M-i-j)]=mat[j][i]; 
   
   /*for(int k=0; k<circleElements.length; k++)
    System.out.print(circleElements[k]+" ");
   System.out.println();*/
   for(int j=i; j<(N-i-1);j++){
    mat[i][j] = circleElements[(j-i+R)%circleElements.length];
   }
   for(int j=i; j<(M-i-1); j++){
    mat[j][N-1-i] = circleElements[((N-(i*2)-2)+j-i+1+R)%circleElements.length] ;
   }
   for(int j=N-i-1; j>i; j--)
    mat[M-1-i][j] = circleElements[((N-(i*2)-2)+(M-(i*2)-1)+(N-i-j)+R)%circleElements.length];
   
   for(int j=M-i-1; j>i;j--)
    mat[j][i]=circleElements[((N-(i*2)-2)+(N-(i*2)-1)+(M-(i*2)-1)+(M-i-j)+R)%circleElements.length];
   
  }
  
  for(int i=0; i<M; i++){
   for(int j=0; j<N; j++){
    System.out.print(mat[i][j]+" ");
   }
   System.out.println();
  }
  
  
  /*System.out.print(" ");*/
  
    }
}