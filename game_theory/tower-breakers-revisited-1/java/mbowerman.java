import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int testCase = 0; testCase < t; testCase++) {
            int n = in.nextInt();
            int xorSum = 0;
            for (int i = 0; i < n; i++) {
                xorSum = xorSum ^ numPrimeFactors(in.nextInt());
            }
            if (xorSum == 0) {
                System.out.println(2);
            }
            else {
                System.out.println(1);
            }
        }
    }
    
    public static int numPrimeFactors(int n) {
        if (n <= 1) {
            return 0;
        }
        int temp = n;
        int firstPrimeFactor = getFirstPrimeFactor(temp);
        List<Integer> primeFactors = new ArrayList<>();
        primeFactors.add(firstPrimeFactor);
        while (firstPrimeFactor != temp) {
            temp = temp / firstPrimeFactor;
            firstPrimeFactor = getFirstPrimeFactor(temp);
            primeFactors.add(firstPrimeFactor);
        }
        return primeFactors.size();
    }
    
    public static int getFirstPrimeFactor(int n) {
        if (n % 2 == 0) {
            return 2;
        }
        if (n % 3 == 0) {
            return 3;
        }

        int i = 5;
        int w = 2;

        while (i * i <= n) {
            if (n % i == 0) {
                return i;
            }

            i += w;
            w = 6 - w;
        }

        return n;
    }
}