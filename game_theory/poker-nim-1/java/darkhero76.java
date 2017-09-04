import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan =new Scanner(System.in);
        int t = scan.nextInt();
        for(int z=0;z<t;z++){
            int n = scan.nextInt();
            int limit = scan.nextInt();
            int[] ar = new int[n];
            for(int i=0;i<n;i++){
                ar[i] = scan.nextInt();
            }
            limit = 0;            
            for(int num : ar){
                limit ^= num;
            }
            if(limit ==0){
                System.out.println("Second");
            }else{
                System.out.println("First");
            }
        }
    }
}