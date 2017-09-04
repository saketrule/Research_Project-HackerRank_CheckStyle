import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i=0;i<t;i++) {
            int n = sc.nextInt();
            int x = 0;
            for (int j=0;j<n;j++) {
                int s = sc.nextInt();
                x ^= s;
            }
            System.out.println(x==0?"Second":"First");
        }
    }
}