import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        
        // Initialization of the matrix
        double[][] distance = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = i == j ? 0 : Double.POSITIVE_INFINITY;
            }
        }
        
        // Read distances from io
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int r = in.nextInt();
            distance[x][y] = r;
        }
        
        // Calculate optimal distance between two nodes with floyd algorithm
        floyd(distance, n);
        
        // Print the result of queries
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            System.out.println(distance[x][y] == Double.POSITIVE_INFINITY ? -1 : (long)distance[x][y]);
        }
    }
    
    static void floyd(double[][] distance, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {                       
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }
}