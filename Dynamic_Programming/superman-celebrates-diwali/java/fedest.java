import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int n;
    static int h;
    static int dropRate;
    static int[][] buildings;
    static int[] bestAtFloor;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        h = sc.nextInt();
        dropRate = sc.nextInt();
        buildings = new int[n][h];
        bestAtFloor = new int[h];
        for( int b = 0; b < n; b++){
            Arrays.fill(buildings[b],0);
            int p = sc.nextInt();
            for (int j = 0; j < p; j++){
                int fl = sc.nextInt();
                buildings[b][fl-1]++;
            }
        }
       
        bestAtFloor[0] = 0;
        for (int b = 0; b < n; b++){
           if (buildings[b][0] > bestAtFloor[0]) {
              bestAtFloor[0] = buildings[b][0];
           }
        }
        
        for( int f = 1; f < h; f++){
            int bestThisFloor = 0;
            int prevBest;
            if (f >= dropRate){
                prevBest = bestAtFloor[f-dropRate];   
            } else {
                prevBest = 0;
            }
            for (int b = 0; b < n; b++){
                buildings[b][f] += Math.max(buildings[b][f-1],prevBest);
                if (buildings[b][f] > bestThisFloor) {
                    bestThisFloor = buildings[b][f];
                }
            }
            bestAtFloor[f] = bestThisFloor;
        }
    
        System.out.println(bestAtFloor[h-1]);
    }
}