import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int num_primes(int n){
        boolean[] marked = new boolean[n + 1];
        for(int i = 0; i < n+1; i++){
            marked[i] = true;
        }
        int i = 2;
        int count = 0;
        while(i <= n){
            if(marked[i]){
                count++;    
                int l = n/i;
                for(int j = 1; j <= l; j++){
                    marked[j * i] = false;
                }
            }                        
            i++;
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            // your code goes here
            int num_primes = num_primes(n);
            //System.out.println(num_primes);
            if(num_primes % 2 == 1){
                System.out.println("Alice");
            }else{
                System.out.println("Bob");
            }
        }
    }
}