import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static ArrayList<Integer> dp = new ArrayList<Integer>() ;
    static int c = 0;
    
    private static boolean match(int n)
        {
        if(c <= n-2)
            {
            for(int i=c ; i<=n-2 ; i++)
                {
                if( dp.get(i-2)==0 || dp.get(i-3)==0 || dp.get(i-5)==0 )
                    dp.add(i,1);
                else
                    dp.add(i,0);
                c++;
            }
        }
        
        if( n < 5 )
            {
            if( dp.get(n) == 1 )
                return true ;
        }
        else
            if( dp.get(n-2)==0 || dp.get(n-3)==0 || dp.get(n-5)==0 )
                return true ;
            
        return false ;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int t = sc.nextInt() ;
        
        dp.add(0,0);
        dp.add(1,0);
        dp.add(2,1);
        dp.add(3,1);
        dp.add(4,1);
        c = 5;
        
        for(int ti=0 ; ti<t ; ti++ )
            {
            int n = sc.nextInt() ;
            if( match(n) )
                System.out.println("First");
            else
                System.out.println("Second");
        }
    }
}