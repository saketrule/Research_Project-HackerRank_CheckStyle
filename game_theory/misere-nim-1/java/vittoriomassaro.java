import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int xx = 0; xx < t; xx++){
            int n = in.nextInt();
            int s = 0;
            int max = 0;
            for(int i = 0; i < n; i++) {
                int _s = in.nextInt();
                s ^= _s;
                max = Math.max(max,_s);                
            }
            System.out.println((max < 2 && s == 1)||(max > 1 && s == 0)?"Second":"First");
        }
    }
}