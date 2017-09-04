import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

       public static int mex(TreeSet<Integer> hs)
  {
   int k=0;
   for(Integer temp:hs)
   {
    if(temp<=k)
     k++;
   }
   return k;
  }
     public static boolean isPrime(int n)
  {
   for(int i=2;i<=n/2;i++)
   {
    if(n%i==0)
     return false;
   }
   return true;
  }
 
  public static int calculateGrundy(int n)
  {
   TreeSet<Integer> hs = new TreeSet<Integer>();
   int[] ar = new int[n+1];
  
   if(n==0)
   {
   return 0;
   }
   else if(n==1)
   return 0;
   else if(ar[n]>0)
    return ar[n];
              else if((n&(n-1))==0)
   {
   for(int i=1;i<n;i++)
   {
    if((int)(Math.pow(2,i))==n)
    {
     return i;
    }
   }
   }
            else if(isPrime(n))
    return 1;
   
   else
   {
   
    for(int i=1;i<n;i++)
    {
     if(n%i==0)
     {
      ar[i]=calculateGrundy(i);
      hs.add(ar[i]);
     }
    }
    
   }
   
   return mex(hs);
   
  }
    
    public static int findPrimeFactors(int no,int sum)
{
     while(no%2==0)
     {
       sum+=1;
        no=no/2;
     }

    for(int i=3;i<=Math.sqrt(no);i=i+2)
    {
        while(no%i==0)
            {
            sum=sum+1;
            no=no/i;
        }
    }
    if(no>2)
     sum++;

 return sum;   
}
    
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        //int size = in.nextInt();
        //int[] ar = new int[size];
        for(int i=0;i<s;i++)
            {
            int n=in.nextInt();
            int count=0;
            for(int k=0;k<n;k++)
                {
                int x=findPrimeFactors(in.nextInt(),0);
                count^=x;
            }
            if(count==0)
                System.out.println("2");
            else
                  System.out.println("1");
           }
    }
}