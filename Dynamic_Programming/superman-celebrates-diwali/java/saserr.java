import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int H = input.nextInt();
        int I = input.nextInt();
        
        int[][] buildings = new int[H][N];
        for (int i=0; i<N; i++) {
            int U = input.nextInt();
            for (int j=0; j<U; j++) {
                buildings[input.nextInt()-1][i]++;
            }
        }
        
        int[][] C = new int[H][N];

        int[] b = buildings[0];
        int[] c = C[0];
        int[] max = new int[H];
        int m = 0;
        for (int i=0; i<N; i++) {
            int d = b[i];
            c[i] = d;
            if (c[m] < d) m = i;
        }
        max[0] = m;

        for (int i=1; i<I; i++) {
            int[] previous = C[i-1];
            b = buildings[i];
            c = C[i];
            m = 0;
            for (int j=0; j<N; j++) {
                int d = b[j] + previous[j];
                c[j] = d;
                if (c[m] < d) m = j;
            }
            max[i] = m;
        }
        
        for (int i=I; i<H; i++) {
            int[] previous = C[i-1];
            int jumpMax = C[i-I][max[i-I]];

            b = buildings[i];
            c = C[i];
            m = 0;
            for (int j=0; j<N; j++) {
                int at = b[j];
                int best = Math.max(at + previous[j], at + jumpMax);
                c[j] = best;
                if (c[m] < best) m = j;
            }
            max[i] = m;
        }
        
        System.out.println(C[H-1][max[H-1]]);
    }
}