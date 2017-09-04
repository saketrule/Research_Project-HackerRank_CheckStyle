import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int x = 0;
            int numNonOnes = 0;
            for (int j = 0; j < n; j++) {
                int s = sc.nextInt();
                if (s > 1)
                    numNonOnes++;
                x ^= s;
            }
            if ((x == 0) ^ (numNonOnes == 0))
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
}