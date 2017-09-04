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
            int numPrimes = numPrimes(n);
            String aliceOrBob = numPrimes % 2 == 0 ? "Alice" : "Bob";
            System.out.println(aliceOrBob);
        }
    }
    
    private static int numPrimes(int n) {
        // initially assume all integers are prime
        boolean[] isPrime = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= n; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }

        // count primes
        int primes = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime[i]) primes++;
        }
        return primes;
    }
}