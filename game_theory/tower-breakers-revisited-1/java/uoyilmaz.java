import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution
{
    public static void main( String[] args)
    {
        Scanner in = new Scanner( System.in);
        int noOfCases = in.nextInt();
        int n, result, height;
        int[] noOfFactors = calculateNoOfFactors();
        
        for( int i = 0; i < noOfCases; i++)
        {
            n = in.nextInt();
            result = 0;
            
            for( int j = 0; j < n; j++)
            {
                height = in.nextInt();
                result = result ^ noOfFactors[height];
            }
            
            if( result == 0)
            {
                System.out.println( 2);
            }
            else
            {
                System.out.println( 1);
            }
        }
        in.close();
    }
    
    public static int[] calculateNoOfFactors()
    {
        int[] noOfFactors = new int[1000001];
        int sqrt, num;
        
        for( int i = 2; i <= 1000000; i++)
        {
            noOfFactors[i] = 1;
            sqrt = (int)Math.sqrt(i);
            num = i;
            
            for( int j = 2; j <= sqrt && num > 1; j++)
            {
                while( num % j == 0)
                {
                    noOfFactors[num] = noOfFactors[num/j] + 1;
                    num = num/j;
                }
            }
        }
        
        return noOfFactors;
    }
}