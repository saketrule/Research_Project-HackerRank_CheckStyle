import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int r = s.nextInt();
        int[][] matrix = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                matrix[i][j] = s.nextInt();
            }
        }
        matrix = rotate(matrix, r);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    public static int[][] rotate(int[][] matrix, int r){
        int numLayers = Math.min(matrix.length / 2, matrix[0].length / 2);
        for(int i = 0; i < numLayers; i++){
            int size = 2 * (matrix.length - i * 2) + 2 * (matrix[0].length - i * 2)  - 4;
            int t = r % size;
            int[] flat = new int[size];
            int c = 0;
            for(int j = i; j < matrix.length - i; j++){
                flat[c] = matrix[j][i];
                c++;
            }
            for(int j = i + 1; j < matrix[0].length - i - 1; j++){
                flat[c] = matrix[matrix.length - i - 1][j]; 
                c++;
            }
            for(int j = matrix.length - i - 1; j >= i; j--){
                flat[c] = matrix[j][matrix[0].length - i - 1];
                c++;
            }
            for(int j = matrix[0].length - i - 2; j > i; j--){
                flat[c] = matrix[i][j]; 
                c++;
            }
            int[] last = new int[t];
            int c1 = 0;
            for(int j = flat.length - t; j < flat.length; j++){
                last[c1] = flat[j];
                c1++;
            }
            for(int j = flat.length - 1; j >= t; j--){
                flat[j] = flat[j - t];
            }
            for(int j = 0; j < t; j++){
                flat[j] = last[j];
            }
            c = 0;
            for(int j = i; j < matrix.length - i; j++){
                matrix[j][i] = flat[c];
                c++;
            }
            for(int j = i + 1; j < matrix[0].length - i - 1; j++){
                matrix[matrix.length - i - 1][j] = flat[c]; 
                c++;
            }
            for(int j = matrix.length - i - 1; j >= i; j--){
                matrix[j][matrix[0].length - i - 1] = flat[c];
                c++;
            }
            for(int j = matrix[0].length - i - 2; j > i; j--){
                matrix[i][j] = flat[c]; 
                c++;
            }
        }
        return matrix;
    }
}