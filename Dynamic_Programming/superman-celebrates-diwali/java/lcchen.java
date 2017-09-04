import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class MaxInfo {
        public int max;
        public int maxIndex;
        public int max2;
        
        public MaxInfo(int val1, int index1, int val2, int index2) {
            if (val1 >= val2) {
                max = val1;
                maxIndex = index1;
                max2 = val2;
            }
            else {
                max = val2;
                maxIndex = index2;
                max2 = val1;
            }
        }
        
        public void update(int val, int index) {
            if (val > max) {
                max2 = max;
                max = val;
                maxIndex = index;
            }
            else if (val > max2) {
                max2 = val;
            }
        }
        
        public int getMax(int excludedIndex) {
            return maxIndex == excludedIndex ? max2 : max;
        }
    }

    public static void main(String[] args) {
        //long startTime = new Date().getTime();

        /*int N = 1900;
        int H = 1900;
        int Z = 2;
        int[][] a = new int[N][H + 1];
        int u;
        for (int i = 0; i < N; i++) {
            u = 1900;
            for (int j = 0; j < u; j++) {
                a[i][j + 1]++;
            }
        }*/

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int H = in.nextInt();
        int Z = in.nextInt();
        int[][] a = new int[N][H + 1];
        int u;
        int i;
        int j;
        for (i = 0; i < N; i++) {
            u = in.nextInt();
            for (j = 0; j < u; j++) {
                a[i][in.nextInt()]++;
            }
        }
        
        if (N == 1) {
            int sum = 0;
            for (j = 1; j <= H; j++) {
                sum += a[0][j];
            }
            System.out.println(sum);
            return;
        }

        int[][] vals = new int[H + 1][N];
        MaxInfo[] infos = new MaxInfo[H + 1];
        for (j = 1; j <= H; j++) {
            if (j == 1) {
                for (i = 0; i < N; i++) {
                    vals[j][i] = a[i][j];
                }
            }
            else if (j <= Z) {
                for (i = 0; i < N; i++) {
                    vals[j][i] = a[i][j] + vals[j - 1][i];
                }
            }
            else {
                for (i = 0; i < N; i++) {
                    vals[j][i] = a[i][j] + Math.max(vals[j - 1][i], infos[j - Z].getMax(i));
                }
            }
            infos[j] = new MaxInfo(vals[j][0], 0, vals[j][1], 1);
            for (i = 2; i < N; i++) {
                infos[j].update(vals[j][i], i);
            }
        }
        
        int answer = vals[H][0];
        for (i = 1; i < N; i++) {
            if (vals[H][i] > answer) {
                answer = vals[H][i];
            }
        }
        System.out.println(answer);
        //System.out.println(new Date().getTime() - startTime);
    }
}