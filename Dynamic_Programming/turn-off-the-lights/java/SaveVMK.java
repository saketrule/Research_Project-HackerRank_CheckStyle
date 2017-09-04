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
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt();
        }
        long ans = 1000000000000000000l;
        for (int i = 0; i <= Math.min(n-1, k); i++) {
            int maxCovered = i+k;
            long partialAns = c[i];
            boolean possible = true;
            while (maxCovered < n-1) {
                int button = maxCovered+k+1;
                if (button >= n) {
                    possible = false;
                    break;
                }
                partialAns += c[button];
                maxCovered = button+k;
            }
            if (!possible)
                continue;
            ans = Math.min(partialAns, ans);
        }
        System.out.println(ans);
    }
}