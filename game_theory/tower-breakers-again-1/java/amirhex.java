import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static long getPow(int h){
        if(h == 1){
            return 0;
        }
        
        if(h % 2 == 0){
            while(h % 2 == 0){
                h = h / 2;
            }
            return 1 + getPow(h);
        }
        
        for(int i = 3; i <= (int) Math.floor(Math.sqrt(h)); i++){
            if(h % i == 0)
                return 1 + getPow(h / i);
        }
        return 1;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T > 0){
            T--;
            int n = in.nextInt();
            int[] h = new int[n];
            long sum = 0;
            for(int i = 0; i < n; i++){
                h[i] = in.nextInt();
                sum ^= getPow(h[i]);
            }
            
            //System.out.println(getPow(9));
            if(sum == 0){
                System.out.println(2);
            }
            else 
                System.out.println(1);
        }
    }
}