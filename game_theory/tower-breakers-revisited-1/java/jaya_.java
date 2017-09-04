import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] h = new int[n];
            for (int i = 0; i < n; i++) {
                h[i] = in.nextInt();
            }
            int answer = solve(h);
            System.out.println(answer);
        }
    }

    static int solve(int[] h) {
        int nimSum = 0;
        for (int hi : h) {
            int nimVal = numOfPrimeFactors(hi);
            nimSum ^= nimVal;
        }
        return (nimSum != 0) ? 1 : 2;
   }

    private static int numOfPrimeFactors(int n) {
       int numOfPrimeFactors = 0;
        while (n % 2 == 0) {
            numOfPrimeFactors++;
            n /= 2;
        }
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                numOfPrimeFactors++;
                n /= i;
            }
        }
        if (n > 2) {
            numOfPrimeFactors++;
        }
        return numOfPrimeFactors;
    }
}