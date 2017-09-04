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
            int k = in.nextInt();
            int x = 0;
            
            for (int i=0; i<n; i++) {
                int p = in.nextInt();
                
                x ^= p;
            }
            
            System.out.println(x != 0 ? "First" : "Second");
        }
    }
}