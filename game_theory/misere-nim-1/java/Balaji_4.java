import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
     Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int ti=0;ti<t;ti++){
            int n = s.nextInt();
            int a[] = new int[n];
            
            for(int i=0;i<n;i++){
                a[i] = s.nextInt();
                
            }
            int count=0;
            int ai =0;
            
            for(int i=0;i<n;i++){
                ai = ai ^ a[i];
                if(ai<=1){
                    count++;
                }
                
            }
            if((count==n&&ai==1)||(count<n&&ai==0)){
                System.out.println("Second");
            } else{
                System.out.println("First");
            }
            
        }}
}