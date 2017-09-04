import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static int getMaxMoney(int[] moneys, int[] used, boolean[][] adjMatrix, int currentIndex, int currentSum, Map<Set<Integer>, Integer> possibilities) {
        if (currentIndex < moneys.length) {
            if (used[currentIndex] == 0) {
                int[] usedWith = new int[used.length];
                int[] usedWithout = new int[used.length];
                usedWith[currentIndex] = 1; // visited
                for (int i = 0; i < used.length; i++) {
                    if (i == currentIndex) continue;
                    if (adjMatrix[i][currentIndex]) {
                        if (used[i] == 0) {
                            usedWith[i] = -1; // left town
                        } else {
                            usedWith[i] = used[i];   
                        }
                    } else {
                        usedWith[i] = used[i];
                    }
                    usedWithout[i] = used[i];
                }
                int sumWith = getMaxMoney(moneys, usedWith, adjMatrix, currentIndex + 1, currentSum + moneys[currentIndex], possibilities);
                int sumWithout = getMaxMoney(moneys, usedWithout, adjMatrix, currentIndex + 1, currentSum, possibilities);
                return Math.max(sumWith, sumWithout);
            } else {
                int[] nextUsed = new int[used.length];
                for (int i = 0; i < used.length; i++) {
                    nextUsed[i] = used[i];
                }
                return getMaxMoney(moneys, nextUsed, adjMatrix, currentIndex + 1, currentSum, possibilities);
            }
        } else {
            Set<Integer> visited = new HashSet<Integer>();
            //String usedStr = "";
            for (int i = 1; i < used.length; i++) {
                if (used[i] == 1) {
                    visited.add(i);
                }
                //usedStr += ("," + used[i]);
            }
            possibilities.put(visited, currentSum);
            //System.out.println(usedStr + " || " + visited.toString() + " " + currentSum);
            return currentSum;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] moneys = new int[n + 1];
        boolean[][] adjMatrix = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            moneys[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adjMatrix[a][b] = true;
            adjMatrix[b][a] = true;
        }
        int[] used = new int[n + 1];
        Map<Set<Integer>, Integer> possibilities = new HashMap<Set<Integer>, Integer>();
        int max = getMaxMoney(moneys, used, adjMatrix, 1, 0, possibilities);
        int numWays = 0;
        for (Set<Integer> s : possibilities.keySet()) {
            if (possibilities.get(s) == max) {
                numWays++;
            }
        }
        System.out.println(max + " " + numWays);
    }
}