import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int games = sc.nextInt();
        for(int t = 0; t < games; t++){
            int n = sc.nextInt();
            int[] stones = new int[n];
            for(int i = 0; i < n; i++){
                stones[i] = sc.nextInt();
            }
            int xor = 0;
            for(int num : stones){
                    xor = xor ^ num;
            }
            if(xor != 0){
                System.out.println("First");
            } else{
                System.out.println("Second");
            }
        }
    }
}