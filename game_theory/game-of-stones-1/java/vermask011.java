import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int i = 0;
        while (i < n) {
            String p = hackerRankQuestion6(scanner.nextInt(), "First", "Second");
            System.out.println(p);
            i++;
        }
       
    }

    public static String hackerRankQuestion6(int n, String p1, String p2) {
        if (n < 2) {
            return p2;
        } else {
            if (n >= 2 && n <= 5)
                return p1;
            else {
                String s1 = hackerRankQuestion6(n - 5, p2, p1);
                if (s1.equals(p1))
                    return p1;
                else {
                    String s2 = hackerRankQuestion6(n - 3, p2, p1);
                    if (s2.equals(p1))
                        return p1;
                    else {
                        String s3 = hackerRankQuestion6(n - 2, p2, p1);
                        if (s3.equals(p1))
                            return p1;
                    }

                }

            }
        }
        return p2;

    }

}