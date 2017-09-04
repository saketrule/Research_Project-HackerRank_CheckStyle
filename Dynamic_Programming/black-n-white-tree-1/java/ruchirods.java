import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int M=sc.nextInt();
        
        int N=sc.nextInt();
        int a[][]=new int[M+1][2];
        a[0][0]=N;
        a[0][1]=M;
        for(int i=1;i<M;i++)
            {
            a[i][0]=sc.nextInt();
            a[i][1]=sc.nextInt();
        }
         for(int i=0;i<M;i++)
            {
            System.out.println(a[i][0]+"  "+a[i][1] );    
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}