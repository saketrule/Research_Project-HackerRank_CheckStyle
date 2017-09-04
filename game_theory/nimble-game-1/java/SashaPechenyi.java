import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        
        for (int test=0; test<T; test++) {
            int n = in.nextInt();
            int x = 0;
            in.nextInt();
            for (int i=1; i<n; i++) {
                int k = in.nextInt();
                
                if (k % 2 == 1) {
                    x ^= i;
                }
            }
            
            System.out.println(x != 0 ? "First" : "Second");
        }
    }
}