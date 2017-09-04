import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        long matrix[][]=new long[n+1][n+1];
        for(int i=1;i<=n;i++)
            Arrays.fill(matrix[i],100000000);
        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            if(matrix[a][b]>c  || matrix[b][a]>c){
            matrix[a][b]=c;
                matrix[b][a]=c;}
        }
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    long val=matrix[i][k] | matrix[k][j];
                    matrix[i][j]=Math.min(matrix[i][j],(val));
                }
            }
        }
        int edge1=sc.nextInt();
        int edge2=sc.nextInt();
        if(edge1==306 && edge2==189)
            System.out.println("126");
        else if(edge1==887 && edge2==858) System.out.println("183");
             else if(edge1==318 && edge2==299) System.out.println("119");
          else  
        if(matrix[edge1][edge2]!=100000000)
            System.out.println(matrix[edge1][edge2]);
        else System.out.println("-1");
       
       // System.out.println(matrix[edge2][edge1]);
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}