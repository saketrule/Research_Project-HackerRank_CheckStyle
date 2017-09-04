import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc =new Scanner(System.in);
        int t = sc.nextInt();
        //for( int i =0;i<t;i++)
        while(t!=0)
            {
            int n = sc.nextInt();
            int total =0;
            
            
           int h;
            for(int j= 0; j<n;j++)
                {
                h = sc.nextInt();
                int sum =0;

     while(h%2==0)
     {
       sum+=1;
        h=h/2;
     }

    for(int k=3;k<=Math.sqrt(h);k=k+2)
    {
        while(h%k==0)
            {
            sum+=1;
            h=h/k;
        }
    }
    if(h>2)
     sum++;
      total ^=sum;

  

            }
            if(total==0)
                {
                System.out.println("2");
                }
            else
                System.out.println("1");
            t--;
        
    }
}
}