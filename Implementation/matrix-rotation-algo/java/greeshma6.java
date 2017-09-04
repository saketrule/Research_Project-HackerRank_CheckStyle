import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        Solution s = new Solution();
        int M = in.nextInt();
        int N = in.nextInt();
        int R = in.nextInt();
        int i,j,k,l;
        int[ ][ ] matrix = new int[M][N];
        for(i=0;i<M;i++)
            for(j=0;j<N;j++)
               matrix[i][j] = in.nextInt();
        int min = Math.min(M,N);
        min=min/2;
        k=M;
        l=N;
            for(int y=0;y<min;y++){
                int t = R%(k*2+(l-2)*2);
               for(int x=0;x<t;x++){
                s.function(matrix,M,N,y,y);
               }
                k=k-2;
                l=l-2;
            }                            
        for(i=0;i<M;i++){
            for(j=0;j<N;j++)
            {
              System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
            
    }
        
    void function(int [][]a,int m,int n,int k,int l){
        int i,j;
        i=k;
        j=l;
        int temp=a[k][l];
        for(j=l+1;j<n-l;j++)
            a[i][j-1]=a[i][j];
        j--;
        for(i=k+1;i<m-k;i++){
            a[i-1][j]=a[i][j];
        }
        i--;
        for(;j>l;j--){
            a[i][j]=a[i][j-1];
        }
        for(;i>k+1;i--){
            a[i][j]=a[i-1][j];
        }
        a[i][j]=temp;
    }
}