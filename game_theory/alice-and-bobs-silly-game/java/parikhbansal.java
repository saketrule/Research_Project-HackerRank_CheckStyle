import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean primes[] = new boolean[100001];
        int sum[] = new int[100001];
        for (int i=2;i<=100000;i++){
            primes[i] = true;
        }
        
        for (int i=2;i<=100000;i++){
            if (primes[i]){
                for (int j = i*2;j<=100000;j+=i){
                    primes[j]=false;
                }
            }
            
            sum[i] = sum[i-1] + (primes[i]?1:0);
        }
        int g = in.nextInt();
        
        while( g-- > 0){
            int n = in.nextInt();
            int s = sum[n];
            if (s%2==0){
                System.out.println("Bob");
            }
            else{
                 System.out.println("Alice");
            }
        }
    }
}