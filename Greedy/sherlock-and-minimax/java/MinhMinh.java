import java.util.*;
import java.io.*;

class Solution {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();
        int P = in.nextInt();
        int Q = in.nextInt();
        
        System.out.print(findMinMax(a, P, Q));
    }
    
    private static int findMinMax(int[] a, int P, int Q) {
        
        Arrays.sort(a);
        int n = a.length;
       
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
            min = Math.min(min, Math.abs(P - a[i]));
                    
        int min_max = min;    
        int r = P;
       
        for (int i = 0; i < n - 1; i++) {
            int M = (a[i] + a[i + 1]) / 2;
            if (P <= M && M <= Q) {
                if (M - a[i] > min_max) {
                    min_max = M - a[i];
                    r = M;
                }
            }
        }
        
        min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
            min = Math.min(min, Math.abs(Q - a[i]));
        if (min_max < min)
            r = Q;
        
        return r;
    }
}