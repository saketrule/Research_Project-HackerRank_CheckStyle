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
        int a = P * Q + 1;
        System.out.println(a + " " + a);
        int i = 1;
        while (i < a - 1) {
            System.out.println(i + " " + (i + 1));
            i++;
        }
        System.out.println(i + " " + 1);
        System.out.println(1 + " " + a);
    }
    
}