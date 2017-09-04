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
            if (canxor()) {
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
        }
    }
    
    public static boolean canxor() {
        int cnt=0,cnt2=0;
        for(int i=0;i<n;i++) {
            if (p[i]>0) {
                cnt2++;
            }
            if (p[i]>1) {
                cnt++;
            }
        }
        if (cnt==0 && cnt2%2==1) {
            return false;
        } else if (cnt==0 && cnt2%2==0) {
            return true;
        } else if (cnt==1) {
            return true;
        }
        for(int i=0;i<n;i++) {
            int x = 0;
            for(int j=0;j<n;j++) {
                if (j!=i) {
                    x = x^p[j];
                }
            }
            if (x<p[i]) {
                return true;
            }
        }
        return false;
    }
}