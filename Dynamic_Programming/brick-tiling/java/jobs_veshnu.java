import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Solution {

 public static void addToMap( HashMap<Integer, Long> count, int key, long value ) {
  if( count.containsKey( key )  ) {
   count.put( key , value + count.get( key ) );
  } else {
   count.put(key, value);
  }
  if( key == 0 ) {
   key = key;
  }
 }
 public static void tile(boolean mat[][]) {
  int rows = mat.length;
  int cols = mat[0].length;
  HashMap<Integer, Long> count = new HashMap<Integer, Long>();
  int mask = 0;
  for( int i = 1; i >= 0; --i ) {
   for( int j = cols-1; j >= 0; --j ) {
    mask = ( mask << 1 ) | ( mat[i][j] ? 1: 0 );
   }
  }
  count.put(mask, 1l);
  HashMap<Integer, Long> countMap2 = new HashMap<Integer, Long>();
  for( int i = 0; i < rows - 1; ++i ) {
   mask = 0;
   if( i == rows - 2 ) {
    mask = ( 1 << cols ) - 1;
   } else {
    for( int j = cols - 1; j >= 0; --j ) {
     mask = ( mask << 1 ) | ( mat[i + 2][j] ? 1 : 0 );
    }
   }
   for( Map.Entry<Integer, Long> entry: count.entrySet() ) {
    int m = entry.getKey();
    m |= ( mask << ( 2 * cols ) );
    
    if( ( m & ( ( 1 << cols ) - 1 ) ) ==  ( 1 << cols ) - 1  ) {
     m >>= cols;
     addToMap(countMap2, m, entry.getValue());
     continue;
    }
    recurse( cols, m, entry.getValue(), countMap2 );
   }
   count.clear();
   count.putAll( countMap2 );
   countMap2.clear();
  }
  mask = ( 1 << ( 2 * cols ) ) - 1;
  if( count.containsKey( mask ) ) {
   System.out.println( count.get( mask ) % 1000000007 );
  } else {
   System.out.println( 0 );
  }
 }
 
 private static int[][][] offsets = { { {0, 0}, {0, 1}, {0,2}, {1, 2} },
           { {0,0}, {1,0}, {2,0}, {2,-1} },
           { {0,0}, {1,0}, {2,0}, {2,1} }, 
           { {0,0}, {0,1}, {1,1}, {2,1} },
           { {0,0}, {1,0}, {1,1}, {1,2} },
           { {0,0}, {0,1}, {1,0}, {2,0} },
           { {0,0}, {1,0}, {0,1}, {0,2} },
           { {0,0}, {1,0}, {1,-1}, {1,-2} },
           };
 public static void recurse( int cols, int mask, long count, HashMap<Integer, Long> countMap ) {
  if( ( mask & ( ( 1 << cols ) -1 ) ) == ( 1 << cols ) - 1 ) {
   
   addToMap(countMap, mask >> cols, count);
   return;
  }
  int i = 0;
  
  for( i = 0; i < cols; ++i ) {
   if( ( ( mask >> i ) & 1 ) == 1 ) {
    continue;
   }
   
   for( int j = 0; j < offsets.length; ++j ) {
    int umask = 0;
    for( int k = 0; k < 4; ++k ) {
     int rd = offsets[j][k][0];
     int cd = offsets[j][k][1];
     if( i + cd < 0 || i + cd >= cols ) {
      umask = 0;
      break;
     } 
     int off = rd * cols + ( i + cd );
     if( ( ( mask >> off ) & 1 ) == 1 ) {
      umask = 0;
      break;
     }
     umask |= ( 1 << off );
    }
    if( umask != 0 && ( umask & mask ) == 0 ) {
     recurse( cols, mask | umask, count, countMap );
    }
   }
   break;
  }
 }
 
 public static void main(String[] args) {
  Scanner scanner = new Scanner( System.in );
  int T = scanner.nextInt();
  for( int i = 0;i < T; ++i ) {
   int N = scanner.nextInt();
   int M = scanner.nextInt();
   boolean tile[][] = new boolean[N][M];
   scanner.nextLine();
   for( int j = 0;j < N; ++j ) {
    String line = scanner.nextLine();
    for( int k = 0; k < M; ++k ) {
     if( line.charAt(k) == '#' ) {
      tile[j][k] = true;
     }
    }
   }
   tile(tile);
  }
  
 }

}