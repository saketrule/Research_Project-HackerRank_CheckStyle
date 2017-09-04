import java.util.*;
import java.math.*;
import java.io.*;
import java.util.Collections;

public class Solution {

    static class Solver {

        Solver() {
        }

        public void solve(FastScanner scanner, PrintWriter out) throws IOException{
            int t = scanner.nextInt();
            boolean[] primes = new boolean[100001];
            for(int i = 1; i < primes.length; i++) {
                primes[i] = true;
            }

            for(int i = 2; i * i < primes.length; i++) {
                if(primes[i]) {
                    for(int j = i*i; j < primes.length; j += i) {
                        primes[j] = false;
                    }
                }
            }

            /*
            for(int i = 0; i < 100; i++) {
                if(primes[i]) {
                    System.out.print(i + ", ");
                }
            }
            */
            
            while(t-- > 0) {
                int n = scanner.nextInt(); 
                int count = 0;
                for(int i = 1; i <= n; i++) {
                    if(primes[i]) {
                        ++count;
                    }
                }
                out.println(count % 2 == 0 ? "Alice" : "Bob");
            }
            
        }

    } 

    public static void main(String[] args) throws IOException{
        FastScanner scanner = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        (new Solver()).solve(scanner, out);
        out.flush();

    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        boolean hasNextToken() {
            if(st == null) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(IOException e) {
                    
                }
            }
            return st.hasMoreTokens();
        } 

        String next() {
            while(st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        String nextLine() throws IOException{
            return br.readLine();
        }

        byte nextByte() {
            return Byte.parseByte(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

    }

}