import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int[] s = new int[n];
            int sum = 0;
            for (int i =0; i < n; i++) {
                s[i] = scan.nextInt();
                sum ^= s[i];
            }
            String result = "First";
            if (sum == 0) {
                result = "Second";
            }
            System.out.println(result);
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}