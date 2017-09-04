import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 
 static Scanner sc = new Scanner(System.in);
 
 public static int numprimes(int n){
  boolean[] isPrime = new boolean[n+1];
  for (int i = 2; i <= n; i++) {
   isPrime[i] = true;
  }
  // mark non-primes <= n using Sieve of Eratosthenes
  for (int factor = 2; factor*factor <= n; factor++) {
  
  // if factor is prime, then mark multiples of factor as nonprime
  // suffices to consider mutiples factor, factor+1, ..., n/factor
  if (isPrime[factor]) {
   for (int j = factor; factor*j <= n; j++) {
    isPrime[factor*j] = false;
    }
   }
  }
  // count primes
  int primes = 0;
  for (int i = 2; i <= n; i++) {
   if (isPrime[i]) primes++;
  }
  
  return primes;
 }
    
    public static void run(){
        int n = sc.nextInt();
        
        int numpr = numprimes(n);
        
        if(numpr%2 == 0){
         System.out.println("Bob");
        } else {
         System.out.println("Alice");
        }
    }

    public static void main(String[] args) {
        int g = sc.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            run();
            // your code goes here
        }
    }
}