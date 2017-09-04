import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static boolean allOnes(int[] ar) {
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] != 1) return false;
        }
        return true;
    }
    
    static String determineWinner(int[] s) {
        if (allOnes(s)) {
            if (s.length % 2 == 0) {
                return "First";
            } else {
                return "Second";
            }
        } else {
            int nimsum = 0;
            for (int i = 0; i < s.length; i++) {
                nimsum ^= s[i];
            }
            if (nimsum == 0) {
                return "Second";
            } else {
                return "First";
            }
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int[] s = new int[n];
            for (int j = 0; j < n; j++) {
                s[j] = in.nextInt();
            }
            System.out.println(determineWinner(s));
        }
    }
}