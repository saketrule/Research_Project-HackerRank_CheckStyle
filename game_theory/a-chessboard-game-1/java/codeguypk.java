import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;
        int t = sc.nextInt() ;
        
        for(int ti=0 ; ti<t ; ti++ )
            {
            int x = sc.nextInt() , y = sc.nextInt() ;
            
            winner(x,y) ;
        }
    }
    
    private static void winner(int x , int y)
        {
        x = x%4 ;
        y = y%4 ;
        
        if(y==0||y==3||x==0||x==3)
            f();
        else
            s() ;
    }
    
    private static void f()
        {
        System.out.println("First");
    }
    
    private static void s()
        {
        System.out.println("Second");
    }
}