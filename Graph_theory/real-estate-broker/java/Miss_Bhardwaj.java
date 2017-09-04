import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
         int m=sc.nextInt();
        int a[]=new int[n];
        int p[]=new int[n];
        for(int i=0;i<n;i++)
            {
             a[i]=sc.nextInt();
           p[i]=sc.nextInt();
        }
        int x[]=new int[m];
        int y[]=new int[m];
        for(int i=0;i<m;i++)
            {
             x[i]=sc.nextInt();
           y[i]=sc.nextInt();
        }
        int r=0;
        for(int i=0;i<m;i++)
            {
            for(int j=0;j<n;j++)
                {
                if(x[i]>=a[j]&&y[i]<=p[j])
                    {
                    r++;
                    break;
                }
            }
        }
        System.out.println(r);
        
    }
}