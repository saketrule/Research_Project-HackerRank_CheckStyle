import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        //ans = new long[n];
        long[] c = new long[n];
        
        for(int i = 0; i < n; i++){
            c[i] = in.nextLong();
        }
        
        long ans = Long.MAX_VALUE;
        
        for(int i = 0; i < Math.min(k + 1, n); i++){
            
            long t = 0;
            int cur = i;
            
            while(cur < n){
                t += c[cur];
                if(cur + k < n - 1 && cur + 2 * k + 1 >= n){
                    t = Long.MAX_VALUE;
                    break;
                }
                cur = cur + 2 * k + 1;
            }
                        
            ans = Math.min(ans, t);
        }
        
        System.out.println(ans);
    }
}