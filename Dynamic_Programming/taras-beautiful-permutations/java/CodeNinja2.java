import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long MOD = 1000000007;
    static long[] dp = new long[2001];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        for (int i=0; i<=2000; i++) {
            dp[i] = fact(i);
        }
        
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i=0; i<n; i++) {
                a[i]=in.nextInt();
            }
            long result = solve(n, a);
            System.out.println(result);
        }
   }// main
    
    public static long solve(int n, int[] a) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i=0; i<n; i++) {
            set.add(a[i]);        
        }
        int size = set.size();
        if (a.length==set.size()) {   
            return fact(n)%MOD; 
        }
        return fact(size);
        
    }//solve
    
    public static long fact(int n) {
 
    if (n == 0 || n == 1) {
        return 1;
    } else {
        if (dp[n] != 0)
            return dp[n];
        else
            return dp[n] = (n * fact(n - 1));
    }
 
}
    
}//SOlution