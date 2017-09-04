import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = s.nextLong();
        }
        long P = s.nextLong();
        long Q = s.nextLong();
        
        Arrays.sort(a);
        
        SolPair solution = SolPair.initial();
        // solve bounds
        if (P < a[0]) {
            solution = solution.max(SolPair.create(a[0] - P, P));
        }
        for (int i = 0; i < N - 1; i++) {
            if (a[i] > Q) {
                break;
            }
            if (a[i + 1] < P) {
                continue;
            }
            solution = solution.max(solDiff(P, Q, a[i], a[i + 1]));
        }
        // solve bounds
        if (Q > a[N - 1]) {
            solution = solution.max(SolPair.create(Q - a[N - 1], Q));
        }
        System.out.println(solution.num);
    }
    
    private static SolPair solDiff(long P, long Q, long l, long r) {
        long diff = (r - l) / 2;
        long half = l + diff;
        if (half < P) {
            half = P;
            diff = r - half;
        } else if (half > Q) {
            half = Q;
            diff = half - l;
        }
        
        return SolPair.create(diff, half);
    }
    
    private static class SolPair {
        private long diff;
        private long num;
        
        public static SolPair initial() {
            SolPair p = new SolPair();
            p.diff = Long.MIN_VALUE;
            p.num = 0;
            
            return p;
        }
        
        public static SolPair create(long diff, long num) {
            SolPair p = new SolPair();
            p.diff = diff;
            p.num = num;
            
            return p;
        }
        
        public SolPair max(SolPair other) {
            if (this.diff > other.diff) {
                return this;
            }
            if (this.diff < other.diff) {
                return other;
            }
            if (this.num < other.num) {
                return this;
            }
            return other;
        }
    }
}