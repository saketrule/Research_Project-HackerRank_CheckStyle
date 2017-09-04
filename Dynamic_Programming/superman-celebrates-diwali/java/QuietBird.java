import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        int buildings = scanner.nextInt();
        int height = scanner.nextInt();
        int hLoss = scanner.nextInt();
        int[][] peoples = new int[height][buildings];
        for (int i = 0; i < buildings; i++) {
            int peopleInBuilding = scanner.nextInt();
            for (int j = 0; j < peopleInBuilding; j++) {
                int floor = scanner.nextInt();
                peoples[floor - 1][i]++;
            }
        }
        int[][] summs = new int[height][buildings];
        int[] maxPerFloor = new int[height];
        int max = 0;
        for (int b = 0; b < buildings; b++) {
            summs[0][b] = peoples[0][b];
            max = Math.max(max, peoples[0][b]);
        }
        maxPerFloor[0] = max;
        for (int h = 1; h < height; h++) {
            max = 0;
            for (int b = 0; b < buildings; b++) {
                int summ = 0;
                if(h < hLoss) {
                    summ = summs[h - 1][b] + peoples[h][b];  
                } else {
                    summ += peoples[h][b] + Math.max(summs[h - 1][b], maxPerFloor[h - hLoss]);
                }
                summs[h][b] = summ;
                max = Math.max(max, summ);
            }
            maxPerFloor[h] = max;
        }
        System.out.println(maxPerFloor[height - 1]);
    }
    
    private static class MyScanner {
            private BufferedReader br;
            private StringTokenizer st;
    
            public MyScanner() {
                br = new BufferedReader(new InputStreamReader(System.in));
            }
    
            public String next() {
                while (st == null || !st.hasMoreElements()) {
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException ignored) {
                    }
                }
                return st.nextToken();
            }
    
            public int nextInt() {
                return Integer.parseInt(next());
            }
    
            public long nextLong() {
                return Long.parseLong(next());
            }
    
            public double nextDouble() {
                return Double.parseDouble(next());
            }
    
            public String nextLine() {
                String str = "";
                try {
                    str = br.readLine();
                } catch (IOException ignored) {
                }
                return str;
            }
            
            public void close() {
                try {
                    br.close();
                } catch (IOException ignored) {
                }
            }
        }
}