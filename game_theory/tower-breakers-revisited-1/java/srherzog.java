import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> primes = new ArrayList<Integer>();
        HashMap<Integer, Integer> primeFactors = new HashMap<Integer, Integer>();
        primes.add(2);
        primes.add(3);
        for (int i = 5; i <= 100000; i += 2) {
            boolean isPrime = true;
            for (int j = 3; j <= Math.sqrt(i); j += 2) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
                primeFactors.put(i, 1);
            }
        }
        
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int sum = 0;
            int N = input.nextInt();
            for (int j = 0; j < N; j++) {
                int next = input.nextInt();
                if (next > 1) {
                    sum = sum ^ primeFactors(primes, primeFactors, next);
                }
            }
            if (sum == 0) {
                System.out.println(2);
            } else {
                System.out.println(1);
            }
        }
    }
    static int primeFactors(ArrayList<Integer> primes, HashMap<Integer, Integer> memo, int n) {
        int factors = 1;
        int i = n;
        boolean finished = false;
        while (!finished) {
            for (Integer prime : primes) {
                if (prime > Math.sqrt(i)) {
                    finished = true;
                    break;
                }
                if (i % prime == 0) {
                    if (memo.containsKey(i / prime)) {
                        factors += memo.get(i / prime);
                        finished = true;
                        break;
                    } else {
                        i /= prime;
                        factors++;
                        break;
                    }
                }
            }
        }
        if (!memo.containsKey(n)) {
            memo.put(n, factors);
        }
        return factors;
    }
}