import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int R = scanner.nextInt();
        int[][] matrix = new int[M][N];
        for (int i=0;i<M;i++) {
            for (int j=0;j<N;j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }        
        
        // find lower
        for (int i=0;i<M/2 && i<N/2; i++) {
            // rotate all
            Deque<Integer> queue = new ArrayDeque<>();
            // add left side
            for (int x=i+1; x<M-i; x++) {
                queue.add(matrix[x][i]);
            }
            // add bottom side (skip first)
            for (int y=i+1; y<N-i; y++) {
                queue.add(matrix[M-i-1][y]);
            }
            // add right side  (skip first)
            for (int x=M-i-2; x>=i; x--) {
                queue.add(matrix[x][N-i-1]);
            }
            // add top side (skip first)
            for (int y=N-i-2; y>=i; y--) {
                queue.add(matrix[i][y]);
            }
            
            int r = queue.size()-(R%queue.size());

            // rotate
            for (int j=0; j<r; j++) {
                queue.add(queue.remove());
            } 
            
            // plot new
            // add left side
            for (int x=i+1; x<M-i; x++) {
                matrix[x][i] = queue.remove();
            }
            // add bottom side (skip first)
            for (int y=i+1; y<N-i; y++) {
                matrix[M-i-1][y] = queue.remove();
            }
            // add right side  (skip first)
            for (int x=M-i-2; x>=i; x--) {
                matrix[x][N-i-1] = queue.remove();
            }
            // add top side (skip first)
            for (int y=N-i-2; y>=i; y--) {
                matrix[i][y] = queue.remove();
            }
        }
        
        //print
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}