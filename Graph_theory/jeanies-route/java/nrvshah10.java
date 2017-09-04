import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        Integer N=sc.nextInt();
        Integer K=sc.nextInt();
        Integer letter[]=new Integer[K];
        Integer[][] matrix=new Integer[10][4];
        for(int i=0;i<K;i++)   
            letter[i]=sc.nextInt();
          
        for(int i=1;i<=N-1;i++)
            {
            
            int l=sc.nextInt();
            int m=sc.nextInt();
            matrix[l][m]=sc.nextInt();
            
        }
       System.out.print(Arrays.toString(matrix));
       
    }
}