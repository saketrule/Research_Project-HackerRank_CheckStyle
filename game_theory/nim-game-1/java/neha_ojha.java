import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        while (testCases-- > 0) {
            int piles = scanner.nextInt();
            int result = 0;
            while (piles-->0) {
                result ^= scanner.nextInt();
            }
            System.out.println(result==0?"Second":"First");
        }
    }
}