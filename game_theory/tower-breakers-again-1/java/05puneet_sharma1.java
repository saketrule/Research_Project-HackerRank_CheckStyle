import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
    static int countOddPrimeFactor(int num) {
        int count = 0;
        if (num % 2 == 0) count++;
        
        while (num % 2 == 0) {
            num /= 2;
        }
        for (int i = 3; i*i <= num; i++) {
            while (num % i == 0) {
                num /= i;
                if (i % 2 != 0) count++;
            }
        }
        if (num > 2) count++;
        return count;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            int nim = 0;
            for (int j = 0; j < m; j++) {
                int k = in.nextInt();
                nim ^= countOddPrimeFactor(k);
            }
            if (nim == 0)
                System.out.println("2");
            else
                System.out.println("1");
        }
    }
}