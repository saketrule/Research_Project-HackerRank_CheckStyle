import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        while (g-- > 0){
            int n = in.nextInt();
            int xor = 0;
            while (n-- > 0)
                xor ^= primeFactorCount(in.nextInt());
            System.out.println(xor == 0 ? 2 : 1);
        }
    }
    
    private static int primeFactorCount(int n){
        int count = 0;
        for (int p = 2; p * p <= n; p++){
            while (n % p == 0){
                n /= p;
                count++;
            }
        }
        if (n > 1) count++;
        return count;
    }
}