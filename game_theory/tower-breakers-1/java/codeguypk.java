import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static boolean match(int n , int m )
        {
        if( m == 1 || n%2 == 0 )
            return false ;
        
        return true ;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;
        int t = sc.nextInt() ;
        
        for(int ti=0 ; ti<t ; ti++ )
            {
            int n = sc.nextInt() , m = sc.nextInt() ;
            if( match(n,m) )
                System.out.println("1");
            else
                System.out.println("2");
        }
    }
}