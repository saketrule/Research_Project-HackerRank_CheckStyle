import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 static class PrimeSieve {
  byte[] sieve;
  
  public PrimeSieve(int size) {
   sieve = new byte[size + 1];
   sieve[0] = sieve[1] = 1; //not prime;
   int limit = (int)Math.sqrt(size) + 1;
   for (int i = 2; i < limit; i++) {
    if(sieve[i] == 0) {
     int j = i * i;
     while(j <= size) {
      sieve[j] = 1;
      j += i;
     }
    }
   }
  } 
 }
  
 static PrimeSieve sieve = new PrimeSieve(100005);
 static int[] countOfPrimes = new int[100005];
 
 static {
  countOfPrimes[0] = 0;
  for (int i = 1; i < countOfPrimes.length; i++) {
   countOfPrimes[i] = countOfPrimes[i - 1];
   if(isPrime(i)) countOfPrimes[i]++;
  }
 }
 
 static boolean isPrime(int n) {
  return sieve.sieve[n] == 0;
 }
 
 static void solve(int n) {
  int c = countOfPrimes[n];
  if(c % 2 == 1) {
   System.out.println("Alice");
  } else {
   System.out.println("Bob");
  }
 }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            solve(n);
            // your code goes here
        }
    }
}