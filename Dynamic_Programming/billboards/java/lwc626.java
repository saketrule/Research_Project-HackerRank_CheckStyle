import java.io.InputStreamReader;
import java.io.IOException;
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
  Billboards solver = new Billboards();
  solver.solve(1, in, out);
  out.close();
 }
}

class Billboards {
 public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt() ;
        int K = in.nextInt() ;
        long [] dp = new long[ N + 1];
        long [] D = new long[ N + 1];
        long [] sum = new long[ N + 1];
        int [] dq = new int[ N + 10] ;
        int f = 0 , t = 0 ;
        sum[0] = 0 ;
        for( int i = 1 ; i <= N ; i ++ ){
            sum[i] = sum[i-1] + in.nextLong();
        }
        dp[0] = 0 ;
        D[0]  = 0 ;
        dq[t++] = 0 ;

        for( int i = 1 ; i <= N ; i ++ ){
            while ( f < t &&  i - dq[f] > K + 1 ) f ++ ;
            D[i]  = D[ dq[f] ] + sum[i-1] - sum[ dq[f] ] ;
            while ( f < t && i - dq[f] > K ) f ++ ;
            dp[i] = D[ dq[f] ] + sum[i] - sum[ dq[f] ] ;
            while ( f < t && D[i] >= D[ dq[t-1] ] + sum[i] - sum[ dq[t-1] ] ) t--;
            dq[t++] = i ;

        }
        //IOUtils.printLongArrays( out, D );
        //IOUtils.printLongArrays( out, dp );

        out.printLine( Math.max( D[N] , dp[N] ) );
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
    
    public long nextLong(){
        return Long.parseLong( next() ) ;
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