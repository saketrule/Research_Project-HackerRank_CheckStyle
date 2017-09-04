import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
     setStandardIn();
     Scanner in = new Scanner(System.in);
     int rowCount = in.nextInt();
     int colCount = in.nextInt();
     int rotationCount = in.nextInt();

     int[][] matrix = new int[rowCount][colCount];
     for (int row = 0; row < rowCount; row++) {
      for (int col = 0; col < colCount; col++) {
       matrix[row][col] = in.nextInt();
      }
     }
     in.close();
     
     int [][] rotatedMatrix = new int[rowCount][colCount];
     for (int row = 0; row < rowCount; row++) {
      for (int col = 0; col < colCount; col++) {
       int layer = Math.min(
           Math.min(row, rowCount - 1 - row),
           Math.min(col, colCount - 1 - col)
          );
          int minRow = layer;
          int maxRow = rowCount - 1 - minRow;
       int minCol = layer;
       int maxCol = colCount - 1 - minCol;
       
       int netRotation = rotationCount % (2 * (maxRow - minRow) + 2 * (maxCol - minCol));
       
       /*
        * The rotation change row and col in the following cycle:
        * row increase -> col increase -> row decrease -> col decrease -> row increase
        * Check if we should increase or decrease first. 
        */
       if (col == minCol || (row == maxRow && col < maxCol)) {
        int[] midVal = positiveIncrementRotation(row, col, netRotation, maxRow, maxCol);
        midVal = negativeIncrementRotation(midVal[0], midVal[1], midVal[2], minRow, minCol);
        int[] rotatedVal = positiveIncrementRotation(midVal[0], midVal[1], midVal[2], maxRow, maxCol);
        rotatedMatrix[rotatedVal[0]][rotatedVal[1]] = matrix[row][col];
       } else if (col == maxCol || (row == minRow && col > minCol)){
        int[] midVal = negativeIncrementRotation(row, col, netRotation, minRow, minCol);
        midVal = positiveIncrementRotation(midVal[0], midVal[1], midVal[2], maxRow, maxCol);
        int[] rotatedVal = negativeIncrementRotation(midVal[0], midVal[1], midVal[2], minRow, minCol);
        rotatedMatrix[rotatedVal[0]][rotatedVal[1]] = matrix[row][col];
       } else { // row == col
        throw new RuntimeException("Invalid row and col values.");
       }
      }
     }
     
     StringBuffer sb = new StringBuffer();
     String colSeparator = "";
     String rowSeparator = "";
     for (int row = 0; row < rowCount; row++) {
      sb.append(rowSeparator);
      rowSeparator = "\n";
      colSeparator = "";
      for (int col = 0; col < colCount; col++) {
       sb.append(colSeparator);
       colSeparator = " ";
       sb.append(rotatedMatrix[row][col]);
      }
     }
     System.out.println(sb.toString());
    }
    
    public static int[] positiveIncrementRotation(int row, int col, int netRotation, int maxRow, int maxCol) {
     int rowDelta = Math.min(maxRow - row, netRotation);
     netRotation -= rowDelta;
     row += rowDelta;
     
     int colDelta = Math.min(maxCol - col, netRotation);
     netRotation -= colDelta;
     col += colDelta;

     int[] ret = {row, col, netRotation};
     return ret;
    }
    
    public static int[] negativeIncrementRotation(int row, int col, int netRotation, int minRow, int minCol) {
     int rowDelta = Math.min(row - minRow, netRotation);
     netRotation -= rowDelta;
     row -= rowDelta;
     
     int colDelta = Math.min(col - minCol, netRotation);
     netRotation -= colDelta;
     col -= colDelta;
     
     int[] ret = {row, col, netRotation};
     return ret;
    }

 public static void setStandardIn() {
  java.util.Map<String, String> env = System.getenv();
  if (env.containsKey("mta") && env.get("mta").equals("mta")) {
   try {
    System.setIn(new java.io.FileInputStream("in.txt"));
   } catch (FileNotFoundException e) {
    e.printStackTrace();
   }
  }
 }

    public static double roundToNthDecimal(double number, int roundToPlace) {
     double powerOfTen = Math.pow(10, roundToPlace);
     return Math.round(number * powerOfTen) / powerOfTen;
    }
}