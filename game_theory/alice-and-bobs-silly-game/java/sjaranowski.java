import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static final int N = 100001;

    private static final boolean[] primes = new boolean[N];
    private static final int[] pi = new int[N];
    
    private static void sieve() {
        for (int i = 0; i < N; ++i) {
            primes[i] = true;
        }
        
        primes[0] = false;
        primes[1] = false;
        for (int n = 2; n * n < N; ++n) {
            for (int m = n + n; m < N; m += n) {
                primes[m] = false;
            }    
        }
    }
    
    private static void precomputePi() {
        for (int n = 2; n < N; ++n) {
            if (primes[n]) {
                pi[n] = pi[n - 1] + 1;
            } else {
                pi[n] = pi[n - 1];
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        sieve();
        precomputePi();
        
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            System.out.println(pi[n] % 2 == 0 ? "Bob" : "Alice");
        }
        
        //for (int i = 0; i < )
    }
}