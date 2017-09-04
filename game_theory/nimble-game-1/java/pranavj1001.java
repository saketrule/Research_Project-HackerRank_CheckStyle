import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        while (testCases-- > 0) {
            int n = sc.nextInt();
            int value = 0;
            for (int i = 0; i < n; i++){ 
                if (sc.nextInt() % 2 == 1)value ^= i;
            }
            if (value == 0)
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
}