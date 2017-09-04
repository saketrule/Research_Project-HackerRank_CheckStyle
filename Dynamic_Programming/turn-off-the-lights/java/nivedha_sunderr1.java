import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long c[] = new long[n];
        for(int i = 0 ; i<n;i++){
            c[i] = sc.nextInt();
        }
        long cost = 0;
        int val = k*2+1;
        cost = n/val + n%val;
        System.out.println(cost);
        
    }
}