import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
                int n = s.nextInt();
                int a[] = new int[n];

                for (int i = 0; i < n; i++) {
                    a[i] = s.nextInt();
                }
                int p = s.nextInt();
                int q = s.nextInt();
                Arrays.sort(a);

                int sP = Integer.MAX_VALUE;
                int sQ = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    sP = Math.min(Math.abs(a[i]-p), sP);
                    sQ = Math.min(Math.abs(a[i]-q), sQ);
                }
                int resValue = sP;
                int result = p;
                if (resValue < sQ) {
                    result = q;
                    resValue = sQ;
                }

                int maxDist = Integer.MIN_VALUE;
                int maxMid = 0;
                for (int i = 1; i < n; i++) {
                    int mid = (a[i-1]+a[i])/2;
                    if (mid < p || mid > q) continue;

                    int dist = Math.min(mid-a[i-1], a[i]-mid);
                    if (dist > maxDist) {
                        maxDist = dist;
                        maxMid = mid;
                    }
                }

                int sM = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    sM = Math.min(Math.abs(a[i]-maxMid), sM);
                }
                if (resValue < sM) {
                    result = maxMid;
                }
                System.out.println(result);
        }
    }
}