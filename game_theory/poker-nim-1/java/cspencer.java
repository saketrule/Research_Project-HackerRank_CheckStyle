import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        for (int test = 0; test < testCases; ++test) {
            int n = in.nextInt();
            int k = in.nextInt();
            int xor = 0;
            for (int i = 0; i < n; ++i) {
                int num = in.nextInt();
                xor ^= num;
            }
            System.out.println(xor > 0 ? "First" : "Second");
        }
    }
}