import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        for(int xx = 0; xx < t; xx++){
            int n = in.nextInt();
            int s = 0;
            for(int i = 0; i < n; i++) s ^= prime_div(in.nextInt());
            System.out.println(s == 0?"2":"1");
        }
    }
    
    private static int prime_div(int n){
        int ans = 0;
        while(n % 2 == 0){
            ans = 1;
            n /= 2;
        }
        
        if (n == 1) return ans;
        
        for(int i = 3; i < Math.sqrt(n) + 1; i += 2){
            while(n % i == 0){
                ans++;
                n /= i;
            }
        }
        
        if ( n > 1 ) ans++;
        
        return ans;
    }
}