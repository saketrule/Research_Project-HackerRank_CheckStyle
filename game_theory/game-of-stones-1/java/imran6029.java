import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static boolean ans(boolean b[],int n,boolean p)
        {
       // if(n < 1)
         //   return false;
        
        //if(n <= 5)
          //  return b[n];
       // else 
         //   {p=!p;
           // return ans(b,n-2,p) && ans(b,n-3,p) && ans(b,n-5,p);}
        if(n<6)
            return b[n];
        else
            {
            for(int i=6;i<=n;i++)
                {
                if(b[i-2] == b[i-3] && b[i-5]== b[i-3])
                    b[i]=!b[i-2];
                else
                     b[i]=true;
            }
        }
        return b[n];
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         Scanner s= new Scanner(System.in);
        int t=s.nextInt();
         boolean b[];
        for(int i=0;i<t;i++)
        {
            int n=s.nextInt();
            b=new boolean[n+1];
            int j;
            for(j=0;j<=n;j++)
                b[j]=false;
            if(n>=2)
                b[2]=true;
            if(n>=3)
                b[3]=true;
            if(n>=4)
                b[4]=true;
            if(n>=5)
                b[5]=true;
                
           if(ans(b,n,true)==true)
            System.out.println("First");
            else
                 System.out.println("Second");
            }
        } 
     
}