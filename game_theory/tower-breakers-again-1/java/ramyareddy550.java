import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int p=1;
        
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int c[]=new int[n];
        for(int i=0;i<n;i++)
            {
            c[i]=0;
             int m=sc.nextInt();
             for(int j=0;j<m;j++)
                 {
                 int k=sc.nextInt();
                
                 while((k/p>1)&&(k/p==m)&&(p<k))
                     {
                     c[i]++;
                     p++;
                 }
                 
             }
        }
        for(int i=0;i<n;i++)
            {
            if(c[i]%2==0)
                System.out.println(2);
            else
                System.out.println(1);
        }
             
    }
}