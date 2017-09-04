import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int[] X = new int[16];
    static int[] Y = new int[16];
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        for (int t=1;t<=T;t++) {
            int N = Integer.parseInt(reader.readLine());
            for (int i=0;i<N;i++) {
                String[] xyStr = reader.readLine().split(" ");
                X[i] = Integer.parseInt(xyStr[0]);
                Y[i] = Integer.parseInt(xyStr[1]);
            }
            
            System.out.println(solve(N));
        }
    }
    
    private static String solve(int N) {
        for (int i=0;i<memory.length;i++) {
            for (int j=0;j<memory[i].length;j++) {
                memory[i][j] = -1;
            }
        }
        currentOptimalTurn = 8;
        
        int optimalStep = findOptimalStep(0, 0, N, 0);
        
        for (int i=0;i<memory.length;i++) {
            for (int j=0;j<memory[i].length;j++) {
                memory[i][j] = -1;
            }
        }
        
        int totalWay = count(0, N, 0, optimalStep, 0);
        return optimalStep + " " + totalWay;
    }
    
    static int[][] memory = new int[9][1<<16];
    private static int count(int index, int N, int turn, int optimalStep, int removed) {
        int originalRemoved = removed;
        if (memory[turn][originalRemoved] >= 0) {
            return memory[turn][originalRemoved];
        }
        
        if (turn == optimalStep) {
            if (removed == ((1 << N) - 1)) {
                memory[turn][originalRemoved] = perm(turn);
                return memory[turn][originalRemoved];
            } else {
                memory[turn][originalRemoved] = 0;
                return 0;
            }
        }
        
        if (isRemoved(index, removed)) {
            int result = count(index + 1, N, turn, optimalStep, removed);
            memory[turn][originalRemoved] = result;
            return memory[turn][originalRemoved];
        } else {
            removed = remove(index, removed);
            int[] localSet = new int[16];
            int totalWay = count(index + 1, N, turn + 1, optimalStep, removed); 
            int alreadyConsidered = 0;
            for (int i=0;i<N;i++) {
                if (isRemoved(i, removed)) { continue; }
                if (isRemoved(i, alreadyConsidered)) { continue; }

                int localSize = 0;
                localSet[localSize++] = i;
                alreadyConsidered = remove(i, alreadyConsidered);
                for (int j=0;j<N;j++) {
                    if (i == j) { continue; }
                    if (isRemoved(j, removed)) { continue; }
                    if (isRemoved(j, alreadyConsidered)) { continue; }

                    if (isOnTheLine(index, i, j)) {
                        alreadyConsidered = remove(j, alreadyConsidered);
                        localSet[localSize++] = j;
                    }
                }

                int localSizeComCount = 1 << localSize;
                for (int k=1;k<localSizeComCount;k++) {
                    int localRemoved = 0;
                    for (int m=0;m<localSize;m++) {
                        if ((k & (1 << m)) > 0) {
                            localRemoved = remove(localSet[m], localRemoved);
                        }
                    }
                    int thisWay = count(index + 1, N, turn + 1, optimalStep, removed | localRemoved);
                    totalWay = (totalWay + thisWay) % MODULO;
                }
            }
            memory[turn][originalRemoved] = totalWay;
            return totalWay;
        }
    }
    
    static int currentOptimalTurn = 8;
    private static int findOptimalStep(int index, int turn, int N, int removed) {
        if (turn >= currentOptimalTurn) {
            return 8;
        }
        int originalRemoved = removed;
        if (memory[turn][originalRemoved] >= 0) {
            return memory[turn][originalRemoved];
        }
        
        if (removed == ((1 << N) - 1)) {
            currentOptimalTurn = Math.min(currentOptimalTurn, turn);
            memory[turn][originalRemoved] = turn;
            return turn;
        }
        
        if (isRemoved(index, removed)) {
            int result = findOptimalStep(index + 1, turn, N, removed);
            memory[turn][originalRemoved] = result;
            return result;
        } else {
            removed = remove(index, removed);
            int maxCount = 0;
            for (int i=index+1;i<N;i++) {
                if (isRemoved(i, removed)) { continue; }
                int count = 0;
                
                for (int j=i+1;j<N;j++) {
                    if (isRemoved(j, removed)) { continue; }

                    if (isOnTheLine(index, i, j)) {
                        count++;
                    }
                }
                
                if (maxCount < count) {
                    count = maxCount;
                }
            }
            
            int optimalStep = Integer.MAX_VALUE;
            int alreadyConsidered = 0;
            for (int i=index+1;i<N;i++) {
                if (isRemoved(i, removed)) { continue; }
                if (isRemoved(i, alreadyConsidered)) { continue; }

                int count = 0;
                int localRemoved = remove(i, 0);
                alreadyConsidered = remove(i, alreadyConsidered);
                for (int j=i+1;j<N;j++) {
                    if (isRemoved(j, removed)) { continue; }
                    if (isRemoved(j, alreadyConsidered)) { continue; }

                    if (isOnTheLine(index, i, j)) {
                        alreadyConsidered = remove(i, alreadyConsidered);
                        localRemoved = remove(j, localRemoved);
                        count++;
                    }
                }

                if (maxCount <= count) {
                    optimalStep = Math.min(optimalStep, findOptimalStep(index + 1, turn + 1, N, removed | localRemoved));
                }
            }
            if (maxCount == 0) {
                optimalStep = Math.min(optimalStep, findOptimalStep(index + 1, turn + 1, N, removed));
            }
            memory[turn][originalRemoved] = optimalStep;
            return optimalStep;
        }
    }
    
    static int MODULO = 1000000007;
    static int perm(int n) {
        int p = 1;
        for (int i=2;i<=n;i++) {
            p = (p * i) % MODULO;
        }
        return p;
    }
    
    static boolean isOnTheLine(int i, int j, int k) {
        return (X[k] - X[i]) * (Y[j] - Y[i]) == (Y[k] - Y[i]) * (X[j] - X[i]);
    }
    
    static int remove(int index, int removed) {
        return (removed | (1 << index));
    }
    
    static boolean isRemoved(int index, int removed) {
        return (removed & (1 << index)) > 0;
    }
}