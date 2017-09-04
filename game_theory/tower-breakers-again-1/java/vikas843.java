import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int factor ( int n)
        {
        int cn=0,i;
        if(n==1)
            return 0;
         for(i=2;i*i<=n;i++)
             {
             if(n%i==0)
                 cn=cn+2;
         }
        if(cn==0)
            return 1;
        else
            return cn;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int t,n,i;
        int[] arr = new int[101];
        Scanner scan = new Scanner(System.in);
        t=scan.nextInt();
        while(t>0)
            {
            n= scan.nextInt();
            for(i=0;i<n;i++)
                {
                arr[i] = scan.nextInt();
                
            }
            int sum=0;
            for(i=0;i<n;i++)
                {
                sum=sum + factor(arr[i]);
            }
            if(sum%2==1)
                System.out.println(1);
            else
                System.out.println(2);
            
            
            t--;
        }
    }
}