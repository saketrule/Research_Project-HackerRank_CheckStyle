import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        
        for(int i = 0; i < T; i++){
          
            int n = scan.nextInt();
            
            int s[] = new int[n];
            
            for(int j = 0; j < n; j++){
                   s[j] = scan.nextInt();
            }
            
            int result = 0;
                
            for(int j = 0; j < n; j++){
                 result = result ^ s[j];
            }
            
            int checkForOnes = 1;
            
            for(int j = 0; j < s.length; j++){
                    if(s[j] > 1){
                     checkForOnes = 0;
                    }   
            }
            
            //System.out.println(result);
            
            //for(int j = 0; j < n; j++){
                
                 if(result == checkForOnes){
                     System.out.println("Second");
                     //break;
                 }
                 else{
                     System.out.println("First");
                     //break;
                 }
            }         
        }
    
    }