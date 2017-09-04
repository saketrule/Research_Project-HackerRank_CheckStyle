import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void debug(Object... args) {
        System.out.println(Arrays.deepToString(args));
    }

    private static final long MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            int grps = Integer.parseInt(br.readLine());
            int[]beads = new int[grps];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sum = 0;
            for (int i=0;i<grps;i++) {
                beads[i] = Integer.parseInt(st.nextToken());
                sum += beads[i];
            }
            
            if (grps == 1) {
                System.out.println(modExp(beads[0], beads[0]-2, MOD));
                continue;
            }
            
            long outer = modExp(sum, grps - 2, MOD);
            for (int i=0;i<grps;i++) {
                long inner = modExp(beads[i], beads[i] - 2, MOD);
                outer = (outer * inner) % MOD;
                outer = (outer * beads[i]) % MOD;
            }
            System.out.println(outer);
        }
    }

    private static long modExp(int v, int exp, long mod) {
        
        long val = v;
        long res = 1;
        
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * val) % mod;
            }
            val = (val * val) % mod;
            exp /= 2;
        }
        
        return res;
    }

}