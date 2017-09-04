import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  Scanner sc =  new Scanner(System.in);
  //System.out.println("ip pls..");

  int r = sc.nextInt(), c = sc.nextInt(), n = sc.nextInt();
  int[][] matrix = new int[r][c];

  for (int i = 0; i < r; i++) {
   for (int j = 0; j < c; j++) {
    matrix[i][j] = sc.nextInt();
   }
  }

//  printMatrix(matrix); 
  
  int[][] rotated = rotateMatrix(matrix, r, c, n);
  
//  System.out.println();
  printMatrix(rotated);

  sc.close();
 }

 private static int[][] rotateMatrix(int[][] matrix, int r, int c, int n) {
  
  int ites = Math.min(r, c) / 2;
  for (int i = 0; i < ites; i++) {
   int num = 2 * (r + c - 2 - 4*i);  // Number of elements in the perimeter
   for (int j = 0; j < n % num; j++) {
    int x = i, y = i;
    int number = matrix[i][i];
    
    while(x < r - 1 - i) {
     int t = matrix[x+1][y];
     matrix[x+1][y] = number;
     number = t;
     x++;
    }
    
    while(y < c - 1 - i) {
     int t = matrix[x][y+1];
     matrix[x][y+1] = number;
     number = t;
     y++;
    }
    
    while(x > i) {
     int t = matrix[x-1][y];
     matrix[x-1][y] = number;
     number = t;
     x--;
    }
    
    while(y > i) {
     int t = matrix[x][y-1];
     matrix[x][y-1] = number;
     number = t;
     y--;
    }
   }
  }
  
  return matrix;
 }
 
 private static void printMatrix(int[][] matrix) {
  for (int[] is : matrix) {
   for (int i : is) {
    System.out.print(i+" ");
   }
   System.out.println();
  }
 }
}