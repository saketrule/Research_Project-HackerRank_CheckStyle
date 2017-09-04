import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        int Q = in.nextInt();
        
        int ns = Q * 3;
        int n = P * ns;
        int e = P * (ns + 1);
        System.out.println(n + " " + e);
        for (int i = 0; i < P; i++) {
            for (int j = 1; j <= ns; j++) {
                if (j == ns)
                    System.out.println((i * ns + j) + " " + (i * ns + 1));
                else
                    System.out.println((i * ns + j) + " " + (i * ns + j + 1));
            }
            int a = Q / 2 + 1;
            int b =  2 * Q + 1 - Q / 2; 
            System.out.println((i * ns + a) + " " + (i * ns + b));
        }
    }
}