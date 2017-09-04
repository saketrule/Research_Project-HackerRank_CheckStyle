import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.StringTokenizer;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author lwc626
 */
public class Solution {
 public static void main(String[] args) {
  InputStream inputStream = System.in;
  OutputStream outputStream = System.out;
  MyInputReader in = new MyInputReader(inputStream);
  MyOutputWriter out = new MyOutputWriter(outputStream);
  Points_in_a_Plane solver = new Points_in_a_Plane();
  int testCount = Integer.parseInt(in.next());
  for (int i = 1; i <= testCount; i++)
   solver.solve(i, in, out);
  out.close();
 }
}

class Points_in_a_Plane {
 public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
       int n = in.nextInt() ;
       int[] X = new int[ n ] , Y = new int[ n ] ;
       IOUtils.readIntArrays( in, X , Y );
       int limit = 1 << n ;
       boolean [][] isOnline = new boolean[ n ][ 1 << n ] ;

        long[][] dp = new long[2][ limit ] ;
        
        for( int i = 0 ; i < n ; i ++ ) Arrays.fill( isOnline [i], false );
        Arrays.fill( dp[0] , Integer.MAX_VALUE );
        for( int i = 1 ; i < limit ; i ++ ){
            ArrayList<Integer> al = new ArrayList<Integer>();
            for( int j = 0 ; j < n ; j ++ )if( (i &( 1 << j )) != 0 ){
                   al.add( j ) ;
            }
            if( is_line( al ,X,Y ) ){
                isOnline[al.get(0)][i] = true;
                if( al.get(0) == 0 ){
                    dp[0][ i ] = 1 ;
                    dp[1][ i ] = 1 ;
                }
            }
        }
        
        for( int i = 1 ; i < limit ; i ++ ) if( dp[0][i] < Integer.MAX_VALUE){
            for( int j = 0 ; j < n  ; j++ )if( ((1<<j) & i) == 0 ){
                int mask = (limit-1) & (~i) ;

                for( int k = mask ; k > 0 ; k = (k-1) & mask  )
                    if( isOnline[j][k] ){
                        int state = i | k  ;
                        if( dp[0][state] > dp[0][i] + 1){
                            dp[0][state] = dp[0][i] + 1;
                            dp[1][state] = dp[1][i];
                            //out.printLine(i, j , k);
                        }else if( dp[0][state] == dp[0][i] + 1){
                            dp[1][state] = (dp[1][state] + dp[1][i])%mod;
                        }
                    }
                break;
            }

        }
        //out.printLine( dp[0][limit-1] , dp[1][limit-1]  );
        for( int i = 1 ; i <= dp[0][limit-1] ; i ++ ){
            dp[1][limit-1] *= i ;
            dp[1][limit-1] %= mod ;
        }
        out.printLine( dp[0][limit-1] , dp[1][limit-1]  );
    }
    static final int mod = 1000000007;

    private boolean is_line(ArrayList<Integer> al , int[] X , int[] Y) {
        if( al.size() <= 2 ) return true;

        int a = al.get(0);
        int b = al.get(1);
        for( int i = 2 ; i < al.size() ; i ++ ){
            int c = al.get( i ) ;
            if( (X[c] - X[a])*(Y[b]-Y[a]) - (X[b]-X[a]) *(Y[c]-Y[a]) != 0 )
                return false;
        }
        return true;
    }
}

class MyInputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public MyInputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
    
    }

class MyOutputWriter {
    private final PrintWriter writer;

    public MyOutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(outputStream);
    }

    public MyOutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

}

class IOUtils {
    public static void readIntArrays( MyInputReader in,int[]... arrays ){
        for(int i = 0 ; i < arrays[0].length; i++ )
            for( int j = 0 ; j < arrays.length ; j ++ )
                arrays[j][i] = in.nextInt();
    }
    }