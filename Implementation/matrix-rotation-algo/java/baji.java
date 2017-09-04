import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int r = in.nextInt();
        int a[][] = new int[m][n];
        
        for( int i = 0; i < m; i++ ) {
            for( int j = 0; j < n; j++ ) {
                int k = in.nextInt();
                a[i][j] = k;
            }
        }
        
        int ra[][] = new int[m][n];
        boolean rotated = false;
        
        int iStart = 0;
        int iEnd = m - 1;
        
        int jStart = 0;
        int jEnd = n - 1;
        
        while( !rotated ) {
         
         int fullRotation = ( 2 * (iEnd - iStart) ) + ( 2 * ( jEnd - jStart ) );
         
            int rotation = 0;
            
            if( fullRotation > 0 ) {
             rotation = r % fullRotation;
            }
         
         if( rotation > 0 ) { 
          for( int i = iStart; i <=iEnd; i++ ) {
              for( int j = jStart; j <=jEnd; j++ ) {
                  int[] np = findNewLocation(iStart, iEnd, jStart, jEnd, rotation, i, j);
                  ra[np[0]][np[1]] = a[i][j];
              }
          }
         }
         
         iStart++;
         iEnd--;
         jStart++;
         jEnd--;
         
         rotated = ( ( ( iEnd - iStart ) + 1 ) < 2 && ( ( jEnd - jStart ) + 1 ) < 2  );
        }
        
        for( int i = 0; i < m; i++ ) {
            for( int j = 0; j < n; j++ ) {
               System.out.print(ra[i][j]+" "); 
            }
            System.out.println(); 
        }
        
    }
    
    static int[] findNewLocation( int iStart, int m, int jStart, int n, int r, int i, int j ) {
     
     if( !( i == iStart || i == m || j == jStart || j == n ) ) {
      return new int[]{i, j};
     }
     
        int rSteps = m - iStart;
        int cSteps = n - jStart;

        while ( r > 0 ) {
            if( i == iStart && j > jStart ) {
             //right to left
             if( r < ( j - jStart ) ) {
                 j -= r;
                 r -= r;
             } else {
              r -= ( j - jStart );
                 j -= ( j - jStart );
             }
            } else if ( j == jStart && i < m  ) {
             //top to bottom
             if( r < ( rSteps - (i - iStart) ) ) {
                 i += r;
                 r -= r;
             } else {
              r -= ( rSteps - (i - iStart) );
                 i += ( rSteps - (i - iStart) );
             }
            } else if ( i == m && j < n ) {
                //left to right
                if( r < ( cSteps - (j - jStart) ) ) {
                    j += r;
                    r -= r;
                } else {
                    r -= ( cSteps - ( j - jStart ) );
                    j += ( cSteps - ( j - jStart ) );
                }
            } else if ( j == n && i > iStart ) {
             //bottom to top
          if( r < ( i - iStart ) ) {
              i -= r;
              r -= r;
          } else {
              r -= ( i - iStart );
              i -= ( i - iStart );
          }
            }
        }
        return new int[]{i, j};
    }
}