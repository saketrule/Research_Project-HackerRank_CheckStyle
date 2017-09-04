import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static boolean[] bS(int n){
         
        boolean[] isPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        for (int factor = 2; factor*factor <= n; factor++) {
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }
        return isPrime;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        boolean[] s  = bS(100000);
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            int count =0;
            for(int i = n;i>0;i--){
                if(s[i])count++;
            }
            if(count%2==0){
                System.out.println("Bob");
            }
            else{
                System.out.println("Alice");                
            }
        }
    }
}