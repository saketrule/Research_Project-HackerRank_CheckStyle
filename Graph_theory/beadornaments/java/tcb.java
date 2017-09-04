import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static long MOD = 1000000007;
    
    private static long power(long result, long x, int p) {
        while (p < 0) {
            result /= x;
            ++p;
        }
        
        for (int i = 0; i < p; ++i) {
            result = (result * x) % MOD;
        }
        
        return result;
    }
    
    private static long combinations(int n, int [] beads) {
        long result = 1;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            result = (result * beads[i]) % MOD;
            result = power(result, beads[i], beads[i] - 2);
            sum += beads[i];
        }
        result = power(result, sum, n - 2);
        return result;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner;
        if (args.length == 0) {
            scanner = new Scanner(new BufferedInputStream(System.in));
        }
        else {
            scanner = new Scanner(new BufferedInputStream(new FileInputStream(args[0])));
        }
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            int [] beads = new int [n];
            for (int j = 0; j < n; ++j) {
                beads[j] = scanner.nextInt();
            }
            System.out.println(combinations(n, beads));
        }
    }
}