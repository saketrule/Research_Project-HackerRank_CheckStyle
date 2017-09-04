import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static boolean isPrime(int num) {
        if (num == 1) return false;
        
        for (int i = 2; i*i <= num; i++) {
            if (num % i != 0)
                return false;
        }
        return true;
    }
    static int countPrimeFactors(int num) {
        int count = 0;
        while (num % 2 == 0) {
            num /= 2;
            count++;
        }
        for (int i = 3; i*i <= num; i++) {
            while(num % i == 0) {
                num /= i;
                count++;
            }
        }
        if (num > 2) count++;
        return count;
    }
    
    public static void main(String[] args) {
        boolean[] isPrime = new boolean[1000001];
        boolean[] ignore = new boolean[1000001];
        for (int i = 1; i <= 1000000; i++) {
            if (ignore[i]) continue;
            if (isPrime(i))
                isPrime[i] = true;
            for (int j = i; j > 3 && j <= 1000000; j+=i) {
                ignore[j] = true;
            }
        }
        
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int M = in.nextInt();
            long nim = 0;
            
            for (int j = 0; j < M; j++) {
                int m = in.nextInt();
                int count = countPrimeFactors(m);
                nim ^= count;
            }
            
            if (nim == 0)
                System.out.println("2");
            else
                System.out.println("1");
        }
    }
}