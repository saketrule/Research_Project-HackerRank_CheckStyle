import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static String solve(int n){
        if(n<=1){
            return "Bob";
        }
        int countPrime = countPrimes(n+1);
        if(countPrime%2 == 1){
            return "Alice";
        }else{
            return "Bob";
        }
    }
    public static int countPrimes(int n) {
 if (n <= 2)
  return 0;
 
 // init an array to track prime numbers
 boolean[] primes = new boolean[n];
 for (int i = 2; i < n; i++)
  primes[i] = true;
 
 for (int i = 2; i <= Math.sqrt(n - 1); i++) {
 // or for (int i = 2; i <= n-1; i++) {
  if (primes[i]) {
   for (int j = i + i; j < n; j += i)
    primes[j] = false;
  }
 }
 
 int count = 0;
 for (int i = 2; i < n; i++) {
  if (primes[i])
   count++;
 }
 
 return count;
}
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            System.out.println(solve(n));
            // your code goes here
        }
    }
}