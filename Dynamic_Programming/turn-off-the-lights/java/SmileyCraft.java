import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        long[] l = new long[n];
        for (int i = 0; i < n; i++)
            l[i] = in.nextLong();
        
        long min = Long.MAX_VALUE;
        for (int i = 0; i <= k && i < n; i++){
            long sum = 0;
            int last = i;
            for (int j = i; j < n; j += 2 * k + 1){
                sum += l[j];
                last = j;
            }
            if (last + k >= n - 1 && sum < min) min = sum;
        }
        
        System.out.println(min);
    }
}