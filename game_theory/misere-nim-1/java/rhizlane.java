import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            int n= in.nextInt();
            int [] s= new int [n];
            int sum =0;
            int count =0;
            for(int i=0; i<n; i++){
                s[i] = in.nextInt();
                if(s[i]<=1)
                    count++;
                sum ^=s[i];
            }
            
            if(count == n && sum==1) 
                System.out.println("Second");
            else if(count < n && sum==0)
                System.out.println("Second");
            else
                System.out.println("First");
        }
        
    }
}