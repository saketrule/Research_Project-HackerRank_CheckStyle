import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int T= sc.nextInt();
        while(T-->0){
            int n=sc.nextInt();
            int k=sc.nextInt();
            int c=0;
            while(n-->0){
                c^=sc.nextInt();
            }
            if(c==0)
                System.out.println("Second");
            else System.out.println("First");
        }
        
    }
}