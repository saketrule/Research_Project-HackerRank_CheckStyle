import java.io.*;
import java.util.Scanner;
public class Solution {

 static long[] primeNum = new long[1000000];
    static int primeCount = 1;
    
    public static void main(String[] args) {
     primeNum[0] = 2;
     for(int w=3;w<1000000;w++)
      if(Prime(w)){
       primeNum[primeCount] = w;
       primeCount++;
      }
     
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
     int t,i,n,xor,s;
     Scanner in = new Scanner(System.in);
      t = in.nextInt();
      while(t>0){
       xor = 0;
       n = in.nextInt();
       for(i=0;i<n;i++){
        s = in.nextInt();
        if(s>1)
         xor = xor ^ NoOfEvenlyDivide(s);
       }
       
       if(xor==0)
        System.out.println("2");
       else
        System.out.println("1");
       
       t--;
      }
    
    }
    public static int NoOfEvenlyDivide(int n){
     int count=0,temp,i=0;
     temp=n;
     if(Prime(n)){
      return 1;
     }
     else{
      while(temp!=1 && i<primeCount){
          if(temp%primeNum[i]==0){
           temp /= primeNum[i];
           count++;
          }
          else
           i++;
         }
         
         if(count==0)
          return 1;
         else
          return count;
     }     
    }
  public static boolean Prime(long n)
     {
   long sqRoot = (long)Math.sqrt(n)+1;
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

     public static void FindAllPrimeToCheck(long n)
     {
         if (primeNum[primeCount] < n)
         {
             for (long i = primeNum[primeCount - 1] + 1; i <= n; i++)
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