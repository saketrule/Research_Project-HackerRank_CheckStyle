import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static int occupancy[][];
    private static int solution[][];
    private static int floorBests[];
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int numBuildings = in.nextInt();
        int height = in.nextInt();
        int dropHeight = in.nextInt();
        
        occupancy = new int[numBuildings][height];
        solution = new int[numBuildings][height];
        floorBests = new int[height];
        
        for (int b=0; b<numBuildings; b++) {
            int pplInBuilding = in.nextInt();
            
            for (int p=0; p<pplInBuilding; p++) {
                int f = in.nextInt();
                occupancy[b][f - 1]++;    
            }           
        }
        
        int max = 0;
 
        for (int f=0; f<dropHeight; f++) {
            int floorBest = 0;
            
            for (int b=0; b<numBuildings; b++) {
                solution[b][f] = occupancy[b][f] + (f > 0 ? solution[b][f-1] : 0); 
                if (solution[b][f] > floorBest) {
                    floorBest = solution[b][f];
                }
                
                if (solution[b][f] > max) {
                    max = solution[b][f];
                }
            }
            
            floorBests[f] = floorBest;
        }
        
        for (int f=dropHeight; f<height; f++) {
            int floorBest = 0;
            
            for (int b=0; b<numBuildings; b++) {
                int stayInBuilding = occupancy[b][f] + solution[b][f-1];
                int dropBest = occupancy[b][f] + floorBests[f - dropHeight];                
                int optimum = Math.max(stayInBuilding, dropBest);
                
                solution[b][f] = optimum;
                
                if (optimum > floorBest) {
                    floorBest = optimum;
                }
                
                if (optimum > max) {
                    max = optimum;
                }
                
                floorBests[f] = floorBest;
            }
        }
       
        System.out.println(max);
    }
}