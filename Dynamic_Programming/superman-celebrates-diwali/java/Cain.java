import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static int[][] a = new int[1900][1900];
    
    private static void display(int[][] a, int b, int f) {
        for(int i=f-1; i>=0; i--) {
            for(int j=0; j<b; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private static int max(int a, int b) {
        return (a>b)?a:b;
    }
    
    private static int getMaxSaved(int[][] a, int b, int f, int h) {
        
        int[] floorMaxSaved = new int[f];
        
        //display(a, b, f);
        for(int i=0; i<f; i++) {
            floorMaxSaved[i] = 0;
            for(int j=0; j<b; j++) {
                if(i >= h) {
                    a[i][j] += max(a[i-1][j], floorMaxSaved[i-h]);
                } else if(i > 0){
                    a[i][j] += a[i-1][j];
                }
                if(a[i][j] > floorMaxSaved[i]) {
                    floorMaxSaved[i] = a[i][j];
                }
            }
        }
        
        //display(a, b, f);
        return floorMaxSaved[f-1];
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner in = new Scanner(System.in);
        int noOfBuildings = in.nextInt();
        int noOfFloors = in.nextInt();
        int heightLoss = in.nextInt();
        
        //int[][] a = new int[noOfFloors][noOfBuildings];
        for(int i=0; i<noOfBuildings; i++) {
            for(int j=0; j<noOfFloors; j++) {
                a[j][i] = 0;
            }
        }
        
        for(int i=0; i<noOfBuildings; i++) {
            int noOfPeople = in.nextInt();
            for(int j=0; j<noOfPeople; j++) {
                int floor = in.nextInt();
                a[floor-1][i]++;
            }
        }
        
        int saved = getMaxSaved(a, noOfBuildings, noOfFloors, heightLoss);
        System.out.println(saved);
    }
}