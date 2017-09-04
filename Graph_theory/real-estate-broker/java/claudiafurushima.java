import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static boolean bpm(boolean[][] interested, int costumer, boolean[] handledHome, int[] purchasedBy) {
        int M = interested[0].length; // houses.
        for (int ii = 0; ii < M; ++ii) {
            if (!interested[costumer][ii] || handledHome[ii])
                continue;
            handledHome[ii] = true;
            if (purchasedBy[ii] == -1 || bpm(interested, purchasedBy[ii], handledHome, purchasedBy)) {
                purchasedBy[ii] = costumer;
                return true;
            }
        }
        return false;
    }
    
    private static int maxBPM(boolean[][] graph) {
        int N = graph.length; // clients
        int M = graph[0].length; // houses
        
        int[] purchasedBy = new int[M];
        Arrays.fill(purchasedBy, -1);
        boolean[] seen = new boolean[M];

        int purchases = 0;
        for (int ii = 0; ii < N; ++ii) {
            Arrays.fill(seen, false);
            if (bpm(graph, ii, seen, purchasedBy))
                ++purchases;
        }
        return purchases;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();  // clients
        int M = in.nextInt();  // houses
        boolean[][] interest = new boolean[N][M];
        long[] minArea = new long[N];
        long[] maxPrice = new long[N];
        for (int ii = 0; ii < N; ++ii) {
            minArea[ii] = in.nextLong();
            maxPrice[ii] = in.nextLong();
        }
        
        for (int jj = 0; jj < M; ++jj) {
            long area = in.nextLong();
            long price = in.nextLong();
            for (int ii = 0; ii < N; ++ii) {
                interest[ii][jj] = minArea[ii] < area && maxPrice[ii] >= price;
            }
        }
        System.out.println(maxBPM(interest));
    }
}