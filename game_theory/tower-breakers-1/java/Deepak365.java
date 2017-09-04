import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i=0 ;i<T ;i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int a = ((M==1||N%2==0)?2:1);
            System.out.println(a);
        }
    }
}