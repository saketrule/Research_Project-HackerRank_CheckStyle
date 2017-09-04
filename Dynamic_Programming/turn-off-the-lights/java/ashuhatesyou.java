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
        long sum=0;
        long c[] = new long[n];
        for(int i=0;i<n;i++) {
            c[i] = in.nextLong();
        }
        for(int i=k;i<n;i+=k+1){    
            sum += c[i];
        }
        System.out.println(sum);
    }
}