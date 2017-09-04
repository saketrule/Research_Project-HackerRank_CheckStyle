import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            // your code goes here
          compute(n);
        }
    }
  
  public static void compute(int n) {
    n = n + 1;
    boolean[] primes = new boolean[n];
    for (int i = 2; i <= Math.sqrt(n); i++) {
      int multiple = 0;
      int product = 0;
      while (product < n) {
        product = (i * i) + (multiple * i);
        if (product < n) {
          primes[product] = true;
        }
        multiple++;
      }
    }
    List<Integer> compressedPrimes = new ArrayList<>();
    for (int i = 2; i < primes.length; i++) {
      if (!primes[i]) {
        compressedPrimes.add(i);
      }
    }
   // System.out.println("compressedPrimes: " + compressedPrimes);
    if (compressedPrimes.size() % 2 == 0) {
      System.out.println("Bob");
    } else {
      System.out.println("Alice");
    }
  }
}