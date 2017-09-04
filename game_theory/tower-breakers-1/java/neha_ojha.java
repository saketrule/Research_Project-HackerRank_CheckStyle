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
            int totalTowers = scanner.nextInt();
            int height = scanner.nextInt();
            if (height == 1) {
                System.out.println("2");
            } else {
                if ((totalTowers & 1) != 0) {
                    System.out.println("1");
                } else {
                    System.out.println("2");
                }
            }
        }
    }
}