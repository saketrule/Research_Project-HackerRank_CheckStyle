import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
   static int n; //max prime
 static  int[] primes;
 
 public static void set(int k){
  primes[k/32]|=((1<<(k%32)));
 }
 
 public static void off(int k){
  primes[k/32]&=(~(1<<(k%32)));
 }
 
 public static boolean test(int k){
  return (primes[k/32]&((1<<(k%32)))) !=0;
 }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
      n=100001;
      primes = new int[ 1+(n/32)];
         for (int i = 2; i <= n; i++) {
             set(i);
         }

         // mark non-primes <= n using Sieve of Eratosthenes
         for (int factor = 2; factor*factor <= n; factor++) {

             // if factor is prime, then mark multiples of factor as nonprime
             // suffices to consider mutiples factor, factor+1, ...,  n/factor
             if (test(factor)) {
                 for (int j = factor; factor*j <= n; j++) {
                     off(factor*j);
                 }
             }
         }
      int[] c=new int[n+1];
      int total=0;
      for (int i = 2; i <= n; i++) {
             if(test(i))++total;
                c[i]=total;
              
         }
        for(int a0 = 0; a0 < g; a0++){
            int p = in.nextInt();
            String s= c[p]%2==0?"Bob":"Alice";
          System.out.println(s);
            // your code goes here
        }
    }
}