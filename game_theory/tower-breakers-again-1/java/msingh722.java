import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int[] primeNum = new int[1000000];
    static int primeCount = 1;
    
    public static void main(String[] args) {
        primeNum[0] = 2;
     for(int w=3;w<100000;w++)
      if(Prime(w)){
       primeNum[primeCount] = w;
       primeCount++;
      }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int t,n,i;
        int xor,s;
        Scanner in = new Scanner(System.in);
        t = in.nextInt();
        while(t>0){
            xor=0;
            n = in.nextInt();
            for(i=0;i<n;i++){
                s = in.nextInt();
                if(s>1)
                    xor = xor ^ getTotalBuildings(s);
            }
            if(xor!=0)
                System.out.println("1");
            else
                System.out.println("2");
            t--;
        }
    }
    
    public static int getTotalBuildings(int n){
     int count=0,temp,i=0;
     temp=n;
     if(Prime(n)){
      return 1;
     }
     else{
      int[] primeFactors = new int[n];
      while(temp!=1 && i<primeCount){
          if(temp%primeNum[i]==0){
           primeFactors[count]  = primeNum[i];
           temp /= primeNum[i];
           count++;
          }
          else
           i++;
         }
         int k,sum=0;
         if(count>1)
          sum = primeFactors[count-1];
         for(k=count-2;k>0;k--)
          sum = sum*(primeFactors[k] + 1);
         if(sum==0)
          return 1;
         else
          return sum;
     }     
    }
    
    
     public static boolean Prime(long n)
     {
   int sqRoot = (int)Math.sqrt(n)+1;
         if (sqRoot > primeNum[primeCount - 1])
             FindAllPrimeToCheck(sqRoot);
         boolean flag = true;
         for (int i = 0; primeNum[i] <= sqRoot && primeNum[i]!=0; i++)
         {
             if (n % primeNum[i] == 0)
             {
                 flag = false;
                 break;
             }
         }
         if (n == 1)
             return false;
         else
             return flag;
     }

     public static void FindAllPrimeToCheck(int n)
     {
         if (primeNum[primeCount] < n)
         {
             for (int i = primeNum[primeCount - 1] + 1; i <= n; i++)
             {
                 if (Prime(i))
                 {
                     primeNum[primeCount] = i;
                     primeCount++;
                 }
             }
         }
     }
}