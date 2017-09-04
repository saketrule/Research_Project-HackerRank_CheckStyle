import java.util.Scanner;

/**
 * Dynamic programming that keeps a status of how many billboards [0...k] exist up to the current point.
 * The program is optimized so that the statuses are stored in a rotating buffer, which makes the complexity
 * O(n+k) in time and O(k) in space
 * @author fra
 */
public class Solution {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        
        long best[] = new long[k+1];
        long tot    = 0;
        int  maxI   = 0;
        for (int i = 0; i < n; i++) {
            int offset = i % best.length;
       
            long v = in.nextLong();
            tot += v;
            //Best configuration after removing this billboard
            best[offset] = best[maxI] - v;
            
            //If the maxI is offset recomputes maxI 
            //(the best configuration had the panel removed, it may not be the best now)
            if(maxI == offset){
                maxI = maxI(best);
            }
        }
        System.out.println(tot + best[maxI(best)]);
    }
    
    
   /**
     * Returns the index of the maximum value of the vector
     * @param v the vector
     * @return the index of the max value
     */
    public static int maxI(long... v) {
        long maxSoFar = v[0];
        int r  = 0;
        for (int i = 0; i < v.length; i++) {
            if(v[i] > maxSoFar){
                maxSoFar = v[i];
                r = i;
            }
        }
        return r;
    }
}