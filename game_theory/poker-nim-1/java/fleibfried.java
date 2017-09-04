import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i=0; i<t; i++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int b = 0;
            for (int j=0; j<n; j++) {
                b ^= scanner.nextInt();
            }
            if (b==0) System.out.println("Second");
            else System.out.println("First");
        }
    }
}