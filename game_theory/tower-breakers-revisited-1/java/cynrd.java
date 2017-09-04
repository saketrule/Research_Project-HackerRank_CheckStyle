import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static void handle(Scanner scan) {
        int n = scan.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
            int curr = a[i];
            for (int j = 2; j <= curr; j++) {
                while (curr % j == 0) {
                    b[i]++;
                    curr /= j;
                }
            }
            sum = sum ^ b[i];
        }
        if (sum == 0) {
            System.out.println(2);
        } else {
            System.out.println(1);
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            handle(scan);
        }
    }
}