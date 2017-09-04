import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        final int rows = sc.nextInt();
        final int cols = sc.nextInt();
        final int rotations = sc.nextInt();
        int[][] matrix = new int[rows][cols];
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                matrix[i][j] = sc.nextInt();
            }
        }
        rotate(matrix, rotations);
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                if(j > 0){
                    System.out.print(' ');       
                }
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
    
    static void rotate(int[][] matrix, final int rotations){
        for(int i = Math.min(matrix.length, matrix[0].length) / 2 - 1; i >= 0; --i){
            rotatePerimeter(matrix, i, i, matrix[0].length - i - 1, matrix.length - i - 1, rotations);
        }
    }
    
    static void rotatePerimeter(int[][] matrix, int xA, int yA, int xB, int yB, int rotations){
        rotations %= 2*(xB-xA + yB-yA);
        while(rotations-- > 0){
            rotatePerimeter(matrix, xA, yA, xB, yB);
        }
    }
    
    static void rotatePerimeter(int[][] matrix, int xA, int yA, int xB, int yB){
        int curr = 0;
        for(int y = yA; y < yB; ++y){
            int x = xA;
            curr = set(matrix, x, y, curr);
        }
        for(int x = xA; x < xB; ++x){
            int y = yB;
            curr = set(matrix, x, y, curr);
        }
        for(int y = yB; y > yA; --y){
            int x = xB;
            curr = set(matrix, x, y, curr);
        }
        for(int x = xB; x > xA; --x){
            int y = yA;
            curr = set(matrix, x, y, curr);
        }
        set(matrix, xA, yA, curr);
    }
    
    static int set(int[][] matrix, int x, int y, int curr){
        final int tmp = matrix[y][x];
        matrix[y][x] = curr;
        return tmp;
    }
}