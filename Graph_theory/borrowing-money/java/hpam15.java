import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private int[] cost;
    private int[] color;
    private int[][] adjMat;
    
    public Solution(int n, int m) {
        this.cost = new int[n];
        this.color = new int[n];
        this.adjMat = new int[n][n];
    }
    
    public ArrayList<Long> getMaxCostnCount(int n, int m) {
        long num = 0;
        long max = (long) Math.pow(2, n);
        long maxCost = 0;
        long count = 0;
        HashSet<Long> visitedSet = new HashSet<Long>();
        while(num < max) {
            long cst = 0;
            long visit = 0;
            for(int i = 0; i < n; i++) {
                long mask = 1l << i;
                if((num & mask) != 0) {
                    if(color[i] != -1) {
                       visit = visit | mask;
                       cst += cost[i];
                       color[i] = 1;
                       for(int j = 0; j < n; j++) {
                           if(adjMat[i][j] == 1) {
                               color[j] = -1;
                           }
                       }
                    }
                }
            }
            //System.out.println(cst + " " + num);
            if(cst > maxCost) {
                maxCost = cst;
                count = 1;
                visitedSet.clear();
                visitedSet.add(visit);
                //System.out.println("new maxCost " + maxCost + " 1 " + visit);
            } else if(cst == maxCost) {
                //System.out.println("equal cost " + visit + " " + visitedSet.contains(visit) + " " + count);
                if(!visitedSet.contains(visit)) {
                    count++;
                    visitedSet.add(visit);
                }
            }
            num++;
            for(int i = 0; i < n; i++) {
                color[i] = 0;
            }
        }
        ArrayList<Long> result = new ArrayList<Long>();
        result.add(maxCost);
        result.add(count);
        return result;
    }
    
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        Solution sol = new Solution(N, M);
        in.nextLine();
        for(int i = 0; i < N; i++) {
            sol.cost[i] = in.nextInt();
        }
        for(int i = 0; i < M; i++) {
            in.nextLine();
            int j = in.nextInt();
            int k = in.nextInt();
            sol.adjMat[j-1][k-1] = 1;
            sol.adjMat[k-1][j-1] = 1;
        }
        ArrayList<Long> result = sol.getMaxCostnCount(N, M);
        System.out.println(result.get(0) + " " + result.get(1));
    }
}