import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int H = in.nextInt();
        int I = in.nextInt();
        int b[][] = new int[N][H+1];
        for(int i = 0; i < N; i++) {
            int u = in.nextInt();
            for(int j = 0; j < u; j++) {
                int floor = in.nextInt();
                b[i][floor]++;
            }
        }
        int saves[][] = new int[N][H+1];
        for(int j = 1; j <= H; j++) {
            int max = getMax(saves, j - I);
            for(int i = 0; i < N; i++) {
                saves[i][j] = b[i][j] + Math.max(max, saves[i][j-1]);
            }
        }
        int max = 0;
        for(int i = 0; i < N; i++) {
            if(saves[i][H] > max) {
                max = saves[i][H];
            }
        }
        System.out.println(max);
    }
    
    private static int getMax(int saves[][], int floor) {
        int max = 0;
        if(floor > 0) {
            for(int k = 0; k < saves.length; k++) {
                if(saves[k][floor] > max) {
                    max = saves[k][floor];
                }
            }
        }
        return max;
    }
}