import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt(), pIndex = 0, numPrime = 0;
        int[] primes = new int[10000];
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            // your code goes here
            if (n > 1) {
                if (pIndex > 0 && primes[pIndex-1] > n) {
                    numPrime = Arrays.binarySearch(primes, 0, pIndex, n);
                    if (numPrime < 0) numPrime = -numPrime - 1;
                    else numPrime++;
                } else {
                    for (int i = 2; i <= n; i++) {
                        boolean isPrime = true;
                        for (int j = 0; j < pIndex; j++) {
                            if (i % primes[j] == 0) {
                                isPrime = false;
                                break;
                            }
                        }
                        if (isPrime) primes[pIndex++] = i;
                    }
                    numPrime = pIndex;
                }
            }
            if (numPrime % 2 == 0) {
                System.out.println("Bob");
            } else {
                System.out.println("Alice");
            }
        }
    }
}