import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Location {

  public int row;
  public int col;

  public Location(int row, int col) {
   this.row = row;
   this.col = col;
  }

 }

 public static void main(String[] args) {

  Scanner in = new Scanner(System.in);

  int rows      = in.nextInt();
  int columns   = in.nextInt();
  int rotations = in.nextInt();
  
  int[][] matrix = new int[rows][columns];

  // Input matrix
  for (int r = 0; r < rows; r++) {
   for (int c = 0; c < columns; c++) {
    matrix[r][c] = in.nextInt();
   }
  }
  
  in.close();

  // Min is EVEN
  int min = Math.min(rows, columns);

  // Rotate each square
  for (int row = 0; row < min / 2; row++) {

   int perimeter = 2 * (rows + columns - 4 * row) - 4;

   int minRow = row;
   int maxRow = rows - row - 1;
   int minCol = row;
   int maxCol = columns - row - 1;

   int[] elements = new int[perimeter];

   // Get elements of the square
   for (int p = 0; p < perimeter; p++) {
    Location loc = getLocation(p, minRow, maxRow, minCol, maxCol);
    elements[p] = matrix[loc.row][loc.col];
   }

   // Rorate elements of the square
   for (int p = 0; p < perimeter; p++) {
    int rotatedP = (p + rotations) % perimeter;
    Location loc = getLocation(rotatedP, minRow, maxRow, minCol, maxCol);

    matrix[loc.row][loc.col] = elements[p];
   }
  }

  // Output matrix
  for (int r = 0; r < rows; r++) {
   for (int c = 0; c < columns; c++) {
    System.out.print(matrix[r][c]);
    System.out.print(" ");
   }
   System.out.println();
  }
 }

 private static Location getLocation(int p, int minRow, int maxRow, int minCol, int maxCol) {

  int height = maxRow - minRow;
  int width = maxCol - minCol;

  int r = -1;
  int c = -1;

  if (p <= height) {
   r = minRow + p;
   c = minCol;

  } else if (p <= height + width) {
   r = maxRow;
   c = minCol + p - height;

  } else if (p <= height + height + width) {
   r = maxRow - p + height + width;
   c = maxCol;

  } else {
   r = minRow;
   c = maxCol - p + height + height + width;
  }

  return new Location(r, c);
 }
}