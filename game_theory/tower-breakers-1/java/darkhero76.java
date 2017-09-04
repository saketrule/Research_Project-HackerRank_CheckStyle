import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
         /*Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan =new Scanner(System.in);
        int t = scan.nextInt();
        for(int z=0;z<t;z++){
            int n = scan.nextInt();
            int height = scan.nextInt();
            System.out.println( height == 1 || n % 2 == 0 ? 2 : 1 );
        }
    }
}