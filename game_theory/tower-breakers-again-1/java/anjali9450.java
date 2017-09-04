import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0)
            {
               int n = in.nextInt();
               int a [] = new int[n];
               int sum=0;
               for(int i =0;i<n;i++)
                   {
                   a[i] = in.nextInt();
               }
               for(int i =0;i<n;i++)
                   {
                   
                   sum+=  a[i];
               }
              if((sum%2==1)||(n==1))
                  System.out.println("1");
              else
                  System.out.println("2");
             }
    }
}