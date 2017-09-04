import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static boolean[][] bp = new boolean[ 1007 ][ 1007 ]; 
    public static int N, M, n, m;
    public static ArrayList< Node > house = new ArrayList<>();
    public static class Node
    {
        public int p;
        public int q;
        public Node( int x, int y )
        {
            p = x;
            q = y;
        }
    }
    public static void func()
    {
        for( int i = 0; i < 1007; i++ )
        {
            for( int j = 0; j < 1007; j++ )
                bp[ i ][ j ] = false;
        }
        N = n;
        M = m;
    }
    public static boolean bpm( int u, boolean[] visited, int[] same )
    {
        for( int i = 0; i < N; i++ )
        {
            if( bp[ u ][ i ] && !visited[ i ] )
            {
                visited[ i ] = true;
                if( same[ i ] < 0 || bpm( same[ i ], visited, same ) )
                {
                    same[ i ] = u;
                    return true;
                }
            }
        }
        return false;
    }
    public static int maxBPM()
    {
        int[] same = new int[ N ];
        
        for( int i = 0; i < N; i++ )
            same[ i ] = -1;
        
        int res = 0;
        for( int i = 0; i < M; i++ )
        {
            boolean[] visited = new boolean[ N ];
            for( int j = 0; j < N; j++ )
                visited[ j ] = false;
            if( bpm( i, visited, same ) )
                res++;
        }
        return res;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input = new Scanner( System.in );
        n = input.nextInt();
        m = input.nextInt();
        func();
        for( int i = 0; i < n; i++ )
        {
            int x = input.nextInt();
            int y = input.nextInt();
            Node node = new Node( x, y );
            house.add( node );
        }
        for( int i = 0; i < m; i++ )
        {
            int x = input.nextInt();
            int y = input.nextInt();
            for( int j = 0; j < n; j++ )
            {
                if( house.get( j ).p < x && house.get( j ).q >= y )
                    bp[ i ][ j ] = true;
            }
        }
        System.out.println( maxBPM() );
    }
}