import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long [] c = new long [n];
        long min = 10000000000L;
        for(int i=0;i<n;i++)
            {
            c[i] = sc.nextLong();
            min = Math.min(min,c[i]);
        }
        if(n<=k)
            System.out.println(min);
        else
        {
            
            int idx = k;
            long ans = 0;
            while(idx < n)
                {
                ans += c[idx];
                idx += 2*k+1;
            }
            System.out.println(ans);
        }
        
    }
}