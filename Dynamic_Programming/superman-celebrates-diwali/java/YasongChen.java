import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numBuild = in.nextInt();
        int height = in.nextInt();
        int switchLoss = in.nextInt();
        
        /* Floor to num people */
        int[][] buildings = new int[numBuild][height];
        
        for (int i = 0; i < numBuild; i++) {
            int numNum = in.nextInt();
            for (int j = 0; j < numNum; j++) {
                buildings[i][in.nextInt() - 1]++;
            }
        }
        
        int[][] peopleAccum = new int[numBuild][height];
        for (int i = 0; i < numBuild; i++) {
            peopleAccum[i][height - 1] = buildings[i][height - 1];
            for (int j = height - 2; j >= 0; j--) {
                peopleAccum[i][j] = peopleAccum[i][j+1] + buildings[i][j];
            }
        }
        
        /* First index: building index; second: floor.
         * Max number of people saved ending in x building y floor
         */
        int[][] maxSavedEndingIn = new int[numBuild][height];
        int[] maxOfFloor = new int[height];
        int[] buildOfMaxOfFloor = new int[height];
        for (int i = height - 1; i >= height - switchLoss; i--) {
            int saved = -1;
            int buildOfMax = -1;
            for (int b = 0; b < numBuild; b++) {
                maxSavedEndingIn[b][i] = peopleAccum[b][i];
                if (maxSavedEndingIn[b][i] > saved) {
                    saved = maxSavedEndingIn[b][i];
                    buildOfMax = b;
                }
            }
            maxOfFloor[i] = saved;
            buildOfMaxOfFloor[i] = buildOfMax;
        }
        
        for (int i = height - switchLoss - 1; i >= 0; i--) {
            int saved = -1;
            int buildOfMax = -1;
            for (int b = 0; b < numBuild; b++) {
                /* Either from the same building, or switched from another building */
                int val1 = maxSavedEndingIn[b][i+1] + buildings[b][i];
                if (buildOfMaxOfFloor[i+switchLoss] == b) {
                    maxSavedEndingIn[b][i] = val1;
                } else {
                    int val2 = buildings[b][i] + maxOfFloor[i+switchLoss];
                    maxSavedEndingIn[b][i] = val1 > val2 ? val1 : val2;
                }
                if (maxSavedEndingIn[b][i] > saved) {
                    saved = maxSavedEndingIn[b][i];
                    buildOfMax = b;
                }
            }
            maxOfFloor[i] = saved;
            buildOfMaxOfFloor[i] = buildOfMax;
        }
        
        System.out.println(maxOfFloor[0]);
    }
}