import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        for(int a0 = 0; a0 < t; a0++){
        
            int n = in.nextInt();
            int m = in.nextInt();
                
            System.out.println(findWinner(n, m));
        
        }    
            
    }
    
    public static int findWinner(int n, int m){
        
        if(m == 1){
            return 2;
        }
        else{
            return (n+1)%2 + 1;
        }

    }
    
    public static boolean isPrime(int a){
        if(a <= 3){
            return true;
        }
        for(int i = 2; i < a/2; i++){
            if(a%i == 0){
                return false;
            }
        }
        return true;
    }
}