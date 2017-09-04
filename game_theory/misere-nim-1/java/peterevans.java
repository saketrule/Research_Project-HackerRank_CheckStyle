import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. 
        
        For SECOND to win, one of the following conditions must be true:
        1) (if the number of stones in EACH pile is <= 1) AND (XOR of the number of stones in each pile is 1)
        2) (if the number of stones in ANY pile is > 1) AND (XOR of the number of stones in each pile is 0)
    */
        Scanner scanner = new Scanner(System.in);
        int g = scanner.nextInt();
        for (int i=0;i<g;i++){
            int n = scanner.nextInt();
            int xor = 0;
            Boolean gtOne = false;
            for (int j=0;j<n;j++){
                int s = scanner.nextInt();
                xor^=s;
                if (s>1) gtOne=true;
            }
            if ( (!gtOne & xor==1) | (gtOne & xor==0) ){
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
        }
    }
}