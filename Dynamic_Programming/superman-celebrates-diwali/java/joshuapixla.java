import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static long[][] fillInput(int[][] buildings, int N, int H) {

        long[][] retarr = new long[H][N];

        for(int i = 0; i < buildings.length; i++) {
            for(int j = 0; j < buildings[i].length; j++) {
                if(buildings[i][j] == 0) continue;
                retarr[H-buildings[i][j]][i]++;
            }
        }

        return retarr;
    }
    
    private static long[] findTwo(long[] input) {
        
        if(input.length == 1) {
            return new long[]{input[0],0};
        }

        long[] ret = new long[2];
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        int firpos = Integer.MAX_VALUE;

        for(int i = 0; i < input.length; i++) {
            if(input[i] > first) {
                first = input[i];
                firpos = i;
            }
        }
        
        for(int i = 0; i < input.length; i++) {
            if(i == firpos) continue;
            if(input[i] > second) {
                second = input[i];
            }
        }

        ret[0] = first;
        ret[1] = second;

        return ret;
    }
    
    private static void compute(long[][] buildings, int loose) {

        long currbest = 0;
        long[] temp = new long[2];

        for(int i = 0; i < buildings.length; i++) {
            
            if(i >= loose) temp = findTwo(buildings[i-loose]);
            
            for(int j = 0; j < buildings[i].length; j++) {
                currbest = 0;
               
                if(i >= loose) {
                    if(temp[0] == buildings[i-loose][j]){
                        if(temp[1] > currbest) currbest = temp[1];
                    }
                    else {
                        if (temp[0] > currbest) currbest = temp[0];
                    }
                }
                if(i > 0) {
                    if(buildings[i-1][j] > currbest) currbest = buildings[i-1][j];
                }
                buildings[i][j] += currbest;
            }
        }

        long max = 0;
        
        for(int i = 0; i < buildings[0].length; i++) {
            if(buildings[buildings.length-1][i] > max) max = buildings[buildings.length-1][i];
        }
        System.out.println(max);
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String[] temp;
        long[][] input;
        int[][] lines;
        int N,H,I;
        
        line = br.readLine().trim();
        temp = line.split(" ");
        N = Integer.parseInt(temp[0]);
        H = Integer.parseInt(temp[1]);
        I = Integer.parseInt(temp[2]);
        lines = new int[N][];
        
        for(int i = 0; i < N; i++) {
            line = br.readLine().trim();
            temp = line.split(" ");
            lines[i] = new int[temp.length-1];
            
         for(int j = 1; j < temp.length; j++) {
             lines[i][j-1] = Integer.parseInt(temp[j]);
         }
        }
        
        input = fillInput(lines,N,H);
        compute(input,I);
    }
}