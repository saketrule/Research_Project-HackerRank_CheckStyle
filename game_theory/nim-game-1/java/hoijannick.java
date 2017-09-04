import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int[] p;
    public static int n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int t=0;t<T;t++) {
            n = in.nextInt();
            p = new int[n];
            for(int i=0;i<n;i++) {
                p[i] = in.nextInt();
            }
            if (xor()) {
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
        }
    }
    
    public static boolean xor() {
        int b = p[0];
        for(int i=1;i<n;i++) {
            b = b^p[i];
        }
        return (b>0);
    }
}