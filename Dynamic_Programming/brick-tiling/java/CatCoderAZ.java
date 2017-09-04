import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static final int MOD = 1000000007;
    private static final int[][] pieceMasks = new int[][] { {0,7,1,4}, {-2,2,-1,2,0,3}, 
                                                           {0,1,1,7}, {0,3,1,1,2,1},
                                                        {-1,4,0,7}, {0,1,1,1,2,3}, 
                                                           {0,7,1,1}, {0,3,1,2,2,2}};
    private int numCols, numRows;
    private int[] startState;
    private HashMap<String, Long> dp; 
   
    Solution(String[] grid, int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        dp = new HashMap<String, Long>();
        int[] start = new int[numCols];
        for (int i = 0; i < numCols; i++) {
            start[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < grid.length; i++) {
            String row = grid[i];
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '.') {
                    start[j] = start[j] ^ (1 << i);
                }
            }
        }
        startState = start;
    }
    
    private boolean isFilled(int[] state) {
        for (int i: state) {
            if (i < Integer.MAX_VALUE) { return false; }
        }
        return true;
    }
    
    private int[] addShape(int[] shape, int[] state, int col, int row) {
        int[] output = state.clone();
        for (int i = 0; i < shape.length; i+=2) {
            output[col+shape[i]] = output[col+shape[i]] | (shape[i+1] << row);
        }
        return output;
    }
    
    private boolean willShapeFit(int[] shape, int[] state, int col, int row) {
        for (int i = 0; i < shape.length; i+=2) {
            int colIdx = col + shape[i];
            if (colIdx < 0 || colIdx >= numCols) { return false; }
            if ((state[colIdx] & (shape[i + 1] << row)) != 0) {
                return false;
            }
        }
        return true;
    }
    
    private int[] nextEmpty(int[] state, int row, int col) {
        int[] output = null;
        while (row < numRows) {
            if ((state[col] & (1 << row)) == 0) {
                return new int[] {row, col};
            }
            row = (col + 1 == numCols) ? row + 1 : row;
            col = (col + 1) % numCols;
        }   
        return output;
    }
   
    
    private long helper(int[] state, int row, int col) {
        String stateString = Arrays.toString(state);
        if (dp.get(stateString) != null) { return dp.get(stateString); }        
        long output = 0;
        for (int[] shape : pieceMasks) {
            if (willShapeFit(shape, state, col, row)) {
                int[] nextState = addShape(shape, state, col, row);
                int[] nextIdx = nextEmpty(nextState, row, col);
                if (nextIdx == null) { 
                    output += 1; 
                }
                else {
                    output += helper(nextState, nextIdx[0], nextIdx[1]);
                }
            }
        }
        dp.put(Arrays.toString(state), output);
        return output % MOD;
    }
    
    public long solve() {
        int[] empty = nextEmpty(startState, 0, 0);
        return (empty == null) ? 1 : helper(startState, empty[0], empty[1]);
    }
    
    
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t > 0) {
            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            String[] grid = new String[numRows];
            Pattern p = Pattern.compile("[.#]+");
            for (int i = 0; i < numRows; i++) {
                grid[i] = scanner.next(p);
            }
            Solution solution = new Solution(grid, numRows, numCols);
            System.out.println(solution.solve());
            t--;
        }
    }
}