import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static Vector<Integer> primes = new Vector<Integer>();
    
    static {          
        primes.add(2);
        primes.add(3);
    }
    
    private static int getPrime(int index) {
        if (primes.size() > index) {
            return primes.get(index);
        }
        
        for (int i = primes.size()-1; i < index; i++) {
            int prime = primes.get(i);
            prime += 2;
            while (!isPrime(prime)) {
                prime += 2;
            }
            primes.add(prime);
        }
        
        return primes.get(index);
    }

    private static boolean isPrime(int num) {
        if ((num%2) == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(num)+1; i += 2) {
            if ((num%i) == 0) {
                return false;
            }
        }
        return true;
    }
    
    private static int countNFactors(int num) {
        int count = 0;
        int primeIndex = 0;
        
        while (num > 1) {
            int prime = getPrime(primeIndex);
            
            while ((num%prime) == 0) {
                num = num/prime;
                count++;
            }
            
            primeIndex += 1;
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
      
        
        int tests = in.nextInt();
        for (int test = 0; test < tests; test++) {
            int xor = 0;

            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int height = in.nextInt();
                
                int factors = countNFactors(height);
                xor = xor^factors;
            }

            if (xor != 0) {
                System.out.println(1);                
            } else {
                System.out.println(2);
            }

        }
        
    }
}