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
            for(int i = 0; i < n; i++){
                if (in.nextInt() % 2 == 1){
                    s ^= i;
                }
            }
            System.out.println(s == 0?"Second":"First");
        }
    }
}