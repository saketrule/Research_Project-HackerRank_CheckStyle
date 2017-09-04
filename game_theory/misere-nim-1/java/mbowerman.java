import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int testCase = 0; testCase < t; testCase++) {
            int n = in.nextInt();
            int xorSum = 0;
            boolean anyGreaterThanOne = false;
            for (int i = 0; i < n; i++) {
                int s = in.nextInt();
                if (s > 1) {
                    anyGreaterThanOne = true;
                }
                xorSum = xorSum ^ s;
            }
            if (anyGreaterThanOne) {
                if (xorSum == 0) {
                    System.out.println("Second");
                }
                else {
                    System.out.println("First");
                }
            }
            else {
                if (xorSum == 1) {
                    System.out.println("Second");
                }
                else {
                    System.out.println("First");
                }
            }
        }
    }
}