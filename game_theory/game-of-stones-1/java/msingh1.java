import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            t--;
            
            int n = in.nextInt();
            if ((n-1) % 7 == 0 || n % 7 == 0) {
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
            
        }
    }
}