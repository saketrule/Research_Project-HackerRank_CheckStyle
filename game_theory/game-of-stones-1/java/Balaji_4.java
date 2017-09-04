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
            boolean first = getWinner(n,true);
            String winner;
            if(first){
            winner = "First";
        } else{
            winner = "Second";
        }
            
            System.out.println(winner);
        }
    }
    static boolean getWinner(int n){
        boolean first = false;
        if(n==1) {
            return false;
        } 
        if(n<7){
            return true;
        } 
        
        while(n>=2){
            if((n-2)<=1){
                n=n-2;
            } else if((n-3)<=1){
                n=n-3;
            } else{
                n=n-5;
            }
            first = !first;
            
        }
        return first;
        
        
    }
    
    static boolean getWinner(int n, boolean ini){
        
        if(n%7>1) {
            return true;
        }else{
            return false;
        }
         
        
        
    }
}