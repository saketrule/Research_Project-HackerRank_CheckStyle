import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
    public static long mincost(int[]c, int start, int end ) {
        if (start==end) {return c[start];}
        return (c[start]>c[end])?c[end]:c[start];
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        int k=in.nextInt();
        int [] c = new int [n];
        for (int i=0;i<n;i++) {
            c[i]=in.nextInt();
        }
        System.out.println(mincost(c,0,n-1));

    }
}