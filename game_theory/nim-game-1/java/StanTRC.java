import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int s = 0;
            for (int i=0; i<n; i++) {
                s ^= in.nextInt();
            }
            System.out.println((s == 0) ? "Second" : "First");
        }
    }
}