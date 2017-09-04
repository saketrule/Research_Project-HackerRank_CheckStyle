import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int g = scan.nextInt();
        int n;
        for (int tc=0; tc<g; tc++){
            n = scan.nextInt();
            int s[]=new int[n];
            for (int i=0; i<n;i++){
               s[i] = scan.nextInt();
            }
            int nim_sum=s[0];
            for (int i=1; i<n;i++){
                nim_sum=nim_sum ^ s[i];
            }
            if (nim_sum==0){
                System.out.println("Second");
            } 
            else {
                System.out.println("First");

            }
        } 
            
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}