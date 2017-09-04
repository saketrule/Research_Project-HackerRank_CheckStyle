import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static final int MAX_HEIGHT = 1_000_000;
    static BitSet primes = new BitSet(MAX_HEIGHT + 1);

    static {
        primes.set(0, MAX_HEIGHT);
        final int maxI = (int) Math.sqrt(MAX_HEIGHT);
        for (int i = 2; i <= maxI; i++) {
            if (primes.get(i)) {
                int maxJ = MAX_HEIGHT / i;
                for (int j = i; j <= maxJ; j++) {
                    primes.clear(i * j);
                }
            }
        }
    }

    static int convert(int height) {
        if (height == 1) {
            return 0;
        }
        if (primes.get(height)) {
            return 1;
        }
        int count = 0;
        for (int i = 3; i <= height; i++) {
            if (primes.get(i)) {
                while (height % i == 0) {
                    count ++;
                    height /= i;
                }
            }
        }
        if (height > 1) {
            count++;
        }
        return count;
    }

    static boolean check(int[] piles) {
        int sum = 0;
        for (int pile : piles) {
            sum ^= convert(pile);
        }
        return sum != 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[] piles = new int[n];
            for (int j = 0; j < n; j++) {
                piles[j] = sc.nextInt();
            }
            System.out.println(check(piles) ? "1" : "2");
        }
    }
}