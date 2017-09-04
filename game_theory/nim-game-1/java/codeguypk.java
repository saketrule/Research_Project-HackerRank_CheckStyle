import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt() ;
        for(int ti=0;ti<t;ti++)
            {
            int n = sc.nextInt() ;
            int s = 0;
            
            int c = 0;
            for(int i=0;i<n;i++)
                {
                int si = sc.nextInt(); 
                if( i==0 )
                    s = si;
                else
                    s ^= si;
            }
            if( s != 0)
                System.out.println("First");
            else
                System.out.println("Second");
        }
    }
}