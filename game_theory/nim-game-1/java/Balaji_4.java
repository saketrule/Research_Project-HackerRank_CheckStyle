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
            int count=a[0];
            
            for(int i=1;i<n;i++){
                count = count ^ a[i];
                
            }
            if(count==0){
                System.out.println("Second");
            } else{
                System.out.println("First");
            }
            
        }
    }
}