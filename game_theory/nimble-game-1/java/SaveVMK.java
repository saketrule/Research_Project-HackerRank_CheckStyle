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
            for (int j = 0; j < n; j++) {
                if (sc.nextInt()%2==1)
                    x ^= j;
            }
            if (x==0)
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
}