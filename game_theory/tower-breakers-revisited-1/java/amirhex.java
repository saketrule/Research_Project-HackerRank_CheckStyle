import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int numPows(int x){
        if(x == 1)
            return 0;
        for(int p = 2; p <= (int) Math.floor(Math.sqrt(x)); p++){
            if(x % p == 0)
                return 1 + numPows(x / p);
        }
        
        return 1;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while( T > 0){
            int n = in.nextInt();
            int[] h = new int[n];
            int sum = 0;
            for(int i = 0; i < n; i++){
                h[i] = in.nextInt();
                sum ^= numPows(h[i]);
            }
            
            if(sum != 0){
                System.out.println(1);
            }
            else
                System.out.println(2);
            T--;
        }
    }
}