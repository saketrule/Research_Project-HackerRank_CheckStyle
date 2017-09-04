import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution
{

    public static void main( String[] args )
    {
        Scanner in = new Scanner( System.in );
        boolean[] game = new boolean[100001];
        game[1] = false;
        for ( int i = 2; i <= 100000; i++ )
            if ( prime( i ) )
                game[i] = !game[i - 1];
            else
                game[i] = game[i - 1];
        int g = in.nextInt();
        for ( int a0 = 0; a0 < g; a0++ )
        {
            int n = in.nextInt();
            if ( game[n] )
                System.out.println( "Alice" );
            else
                System.out.println( "Bob" );
        }
    }

    public static boolean prime( int n )
    {
        for ( int i = 2; i <= Math.sqrt( n ); i++ )
            if ( n % i == 0 )
                return false;
        return true;
    }
}