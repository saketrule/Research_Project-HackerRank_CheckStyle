//package hack101_44;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 *
 * link to problem:
 * https://www.hackerrank.com/contests/101hack44/challenges/alice-and-bobs-silly-game
 */
public class B {

    int g, n;
    final int maxN = 100_000;
    StringBuilder sb = new StringBuilder();
    List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) {
        new B().solve();
    }

    private void solve() {
        fillPrimeList(maxN);
        Scanner in = new Scanner(System.in);
        g = in.nextInt();
        for (int i = 0; i < g; i++) {
            int val = in.nextInt();
            if (isAliceWin(val)) {
                sb.append("Alice\n");
            } else {
                sb.append("Bob\n");
            }
        }
        System.out.print(sb.toString());
    }

//checks whether an int is prime or not.
    private boolean isPrime(long n) {
        //check if n is a multiple of 2
        if (n % 2 == 0) {
            return false;
        }
        //if not, then just check the odds
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void fillPrimeList(long till) {
        primes.add(2);
        for (int i = 3; i <= till; i += 2) {
            if (isPrime(i)) {
                primes.add(i);
            }

        }
    }

    private boolean isAliceWin(int val) {
        int count = 0;
        for (Integer prime : primes) {
            if (prime > val) {
                break;
            }
            count++;
        }
        if (count == 0) {
            return false;   //no primes Bob win
        }
        if (count % 2 == 0) {
            return false;   //Bob wins on odd numbers of prime
        }
        return true;    //Alice wins on even prime numbers
    }

}