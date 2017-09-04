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
        for (int i=0; i<t; i++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            helper(n, m);
        }
    }
    
    private static void helper(int n, int m)
    {
        int result = (m == 1 || n % 2 == 0) ? 2 : 1;
        System.out.println(result);
    }
}