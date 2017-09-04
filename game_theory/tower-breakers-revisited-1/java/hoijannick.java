import java.io.*;
import java.util.*;

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
                p[i]=numdiv(in.nextInt());
            }
            if (xor()) {
                System.out.println(1);
            } else {
                System.out.println(2);
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
    
    public static int numdiv(int n) {
        if (n<=1) {
            return 0;
        } 
        int cnt = 0;
        while (n%2==0) {
            cnt++;
            n/=2;
        }
        for(int i=3;i<=Math.sqrt(n);i+=2) {
            while (n%i==0) {
                cnt++;
                n/=i;
            }
        }
        if (n>1) {
            return (cnt+1);
        }
        return cnt;
    }
}