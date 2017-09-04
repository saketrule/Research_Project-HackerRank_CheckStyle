import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for(int i=1;i<=t;i++)
        {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            char[10][10] input;
            for(int j=0;j<m;j++)
            {
                for(int k=0;k<n;k++)
                {
                    char[j][k] = scanner.next().charAt(0);
                }
            }
            
            
        }
    }
}