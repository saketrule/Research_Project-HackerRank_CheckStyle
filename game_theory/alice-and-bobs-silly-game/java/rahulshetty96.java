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
           
            int m = primes(n);
            if(m%2 == 0)
            System.out.println("Bob");
            else
            System.out.println("Alice");
        }
    }
    
    static int getNoOfPrimes(int N)
    {   
        int primeNos = 0;
        int[] list = new int[N+1];
        for(int i=2;i<=N;i++){
            list[i] = i;
        }
        
        for(int i=2;i<=N;i+=2)
        {
            for(int j=i+i;j<=N;j+=i)
                list[j] = 0;
            
        }
        
        for(int i=2;i<=N;i++)
        {
            if(list[i]!=0){
                primeNos++;
            }
        }
        
        return primeNos;
    }
    
    static int primes(int n) { 
        
        boolean[] isPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
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
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes++;
        }
        
        return primes;
        
    }
}