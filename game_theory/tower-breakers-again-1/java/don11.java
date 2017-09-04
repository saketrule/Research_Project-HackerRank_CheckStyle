import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static int computeNim(int n) {
        int nim = 0;
        if ((n&1) == 0) {
            nim = 1;
            while ((n&1) == 0) {
                n = n >>> 1;
            }
        }
        
        int divisor = 3;
        while (n > 1) {
            while ((n%divisor) == 0) {
                nim += 1;
                n = n/divisor;
            }
            divisor += 2;
        }
        return nim;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int tests = in.nextInt();
        for (int test = 0; test < tests; test++) {
            int n = in.nextInt();
            int xor = 0;
            for (int i = 0; i < n; i++) {
                int height = in.nextInt();
                xor = xor ^ computeNim(height);
            }
            
            if (xor == 0) {
                System.out.println(2);
            } else {
                System.out.println(1);
            }
        }
    }
}