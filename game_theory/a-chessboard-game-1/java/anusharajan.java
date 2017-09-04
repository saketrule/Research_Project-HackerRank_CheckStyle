import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    
    public static String winner(int x, int y) {
        x=x%4; 
        y=y%4;
        if((y==0)||(y==3)||(x==0)||(x==3)) return "First";
        return "Second";
    }  

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        int[][] testcases = new int[tests][2];
        for (int i=0; i< tests; i++) {
            testcases[i][0] = sc.nextInt();
            testcases[i][1] = sc.nextInt();
        }
        for (int i=0; i< tests; i++) {

            System.out.println(winner(testcases[i][0], testcases[i][1]));

        }
        
    }
}