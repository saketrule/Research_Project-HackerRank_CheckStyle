import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static int mod = 1000000007;
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
        PrintWriter out = new PrintWriter (System.out, true);
        
        int T = Integer.parseInt (in.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            int N = Integer.parseInt (in.readLine());
            st = new StringTokenizer (in.readLine());
            int [] arr = new int [N];
            for (int i = 0; i < N; i++)
                arr [i] = Integer.parseInt (st.nextToken());
            
            
            if (N == 1) {
                System.out.printf ("%d\n", mod_exp (arr [0], arr [0] - 2));
                continue;
            }
            long s = 0;
            for (int i = 0; i < N; i++)
                s += arr [i];
            long res = mod_exp (s, N - 2);
            for (int i = 0; i < N; i++)
                res = (res * arr [i]) % mod;
            for (int i = 0; i < N; i++)
                res = (res * mod_exp (arr [i], arr [i] - 2)) % mod;
            
            System.out.printf ("%d\n", res);
        }
    }
    
    private static long mod_exp (long b, int e) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) == 1)
                res = (res * b) % mod;
            b = (b * b) % mod;
            e >>= 1;
        }
        return res;
    }
}