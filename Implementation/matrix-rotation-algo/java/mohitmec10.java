import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
     Scanner in = new Scanner (System.in);
     rows = in.nextInt();
     columns = in.nextInt();
     nRotation = in.nextInt();
     int nLoops = Math.min(rows, columns) / 2;
     matrix = new int [rows][columns] ;
     for (int i = 0; i < rows; i++){
      for (int j = 0; j < columns; j++){
       matrix[i][j] = in.nextInt();
      }
     }
     in.close();
     RotateTheMatrix(nLoops);
    }
    
    private static void RotateTheMatrix(int loopsInMatrix){
     for (int i = 0; i < loopsInMatrix; i++){
      int loopIndex = i;
      rotateMatrixLoop(loopIndex);
     }
     printTheMatrix();
    }
    
    private static void rotateMatrixLoop(int index){
     int size =  2 * (rows + columns - 2 - 4 * (index));
     int [] loopElements = new int [size];
     int rowLength = columns - 2 * index;
     int columnLength = rows - 2 * index - 2;
     findLoopElements(loopElements, size, index, rowLength, columnLength);
     loopElements = rotateLoopElements(loopElements, size);
     findLoopRotatedMatrix(loopElements, index, size);
    }
    
    private static void findLoopElements(int [] loopArray, int length,
      int LoopIndex, int noOfRowElements, int noOfColumnElements){
     
     int row1 = LoopIndex;
     int row2 = rows - LoopIndex - 1;
     int column1 = columns - LoopIndex - 1;
     int column2 = LoopIndex;
     int k1 = 0;
     int k2 = 0;
     int k3 = 0;
     for (int k = 0; k < length; k++){
      if (k < noOfRowElements){
       loopArray[k] = matrix[row1][k + LoopIndex];
      }else if (k < noOfRowElements + noOfColumnElements){
       loopArray[k] = matrix[k1 + LoopIndex + 1][column1];
       k1 += 1;
      }else if ( k < noOfRowElements + noOfColumnElements + noOfRowElements){
       loopArray[k] = matrix[row2][column1 - k2];
       k2 += 1;
      }else{
       loopArray[k] = matrix[row2 - 1 - k3][column2];
       k3 += 1;
      }
     }
    }
    
    private static int[] rotateLoopElements(int [] loopArray, int length){
     int [] loopArrayAfterRotation = new int [length];
     int k1 = 0;
     int nRotate = nRotation % length;
     for (int k = 0; k < length; k ++){
      if (k + nRotate < length){
       loopArrayAfterRotation[k] = loopArray[k + nRotate];
      }else{
       loopArrayAfterRotation[k] = loopArray[k1];
       k1 += 1;
      }
     }
     return (loopArrayAfterRotation);
    }
    
    private static void findLoopRotatedMatrix(int[] loopArray, int LoopIndex, int length){
     int row1 = LoopIndex;
     int row2 = rows - LoopIndex - 1;
     int column1 = columns - LoopIndex - 1;
     int column2 = LoopIndex;
     int k1 = 0;
     int k2 = 0;
     int k3 = 0;
     int constt = row2 - row1;
     for (int k = column2; k <= column1; k++){
      matrix[row1][k] = loopArray[k1];
      matrix[row2][k] = loopArray[length - constt - k3];
      k1 += 1;
      k3 += 1;
     }
     for (int k = row1 + 1; k < row2; k++){
      matrix[k][column1] = loopArray[k1];
      matrix[k][column2] = loopArray[length - 1 - k2];
      k1 += 1;
      k2 += 1;
     }
    }
    
    private static void printTheMatrix(){
     for (int k = 0; k < rows; k++){
   for (int l = 0; l < columns; l++){
    System.out.print(matrix[k][l] + " ");
   }
   System.out.println();
  }
    }
    
    private static int rows, columns, nRotation;
    
    private static int [][] matrix;
    
}