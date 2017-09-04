import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static int[] primes = new int[100001];
    
    private static void generatePrime(){
        
        primes[1] = 1;
        for( int i = 4 ; i<=100000; i+=2 ){
            primes[ i ] = 1; 
        }
        
        int root = (int)Math.sqrt( 100002 );
        for( int i = 3; i <= root; i++ ){
            
            if( primes[ i ] == 0 ){
                
                for( int j = i*i; j <= 100000; j += i*2 ){
                    primes[j] = 1;
                }
            }
        }
        return;
    }
    
    public static void main(String[] args) {
        
        generatePrime();
        
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            int count = 0;
            for( int i = 1; i <= n; i++ ){
                
                if( primes[ i ] == 0 )
                    count++;
            }   
            
            //System.out.println( count );
            
            if( count % 2 == 0 )
                System.out.println( "Bob" );
            else
                System.out.println( "Alice" );
        }
    }
}