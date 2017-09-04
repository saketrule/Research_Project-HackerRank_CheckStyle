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
            int xorSum = 0;
            int onesCount = 0;
            boolean onlyOnes = true;
            for (int i = 0; i < n; ++i) {
                int num = in.nextInt();
                xorSum ^= num;
                if (onlyOnes && num == 1) {
                    ++onesCount;
                }
                else {
                    onlyOnes = false;
                }
            }
            if (onlyOnes) {
                System.out.println(onesCount % 2 == 0 ? "First" : "Second");
            }
            else {
                System.out.println(xorSum > 0 ? "First" : "Second");
            }
        }
    }
}