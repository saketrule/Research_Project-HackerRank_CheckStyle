import java.io.*;
    
public class Hackerranker {

 static int[][] matrix;
 static int rotate;

 public static void main(String[] args) {
  try {
    BufferedReader input = new BufferedReader(new
    InputStreamReader(System.in));
    String[] properties = input.readLine().split(" ");
/**
   String[] properties = { "4", "4", "1" };
   String[] values = new String[4];
   values[0] = "1 2 3 4";
   values[1] = "5 6 7 8";
   values[2] = "9 10 11 12";
   values[3] = "13 14 15 16";
**/
   matrix = new int[Integer.parseInt(properties[0])][Integer
     .parseInt(properties[1])];
   rotate = Integer.parseInt(properties[2]);

   // populate matrix

   for (int i = 0; i < matrix.length; i++) {
    String line = input.readLine();
    String[] val = line.split(" ");
    for (int j = 0; j < val.length; j++) {
     matrix[i][j] = Integer.parseInt(val[j]);

    }
   }

   int i = 0;
   while(rotateRing(i++));
   printMatrix(matrix);

  } catch (Exception ioe) {
  }
 }

 public static void printMatrix(int[][] matrix) {
  for (int i = 0; i < matrix.length; i++) {
   for (int j = 0; j < matrix[i].length-1; j++) {
    System.out.print(matrix[i][j] + " ");
   }
   System.out.println(matrix[i][matrix[i].length-1]);
  }

 }

 public static boolean rotateRing(int layer) {
  // System.out.println(layer+"|"+matrix.length+"|"+matrix[0].length);
  if ((layer >= matrix.length - layer)
    || (layer >= matrix[0].length - layer))
   return false;

  int upperBoundX = matrix.length - layer - 1;
  int upperBoundY = matrix[0].length - layer - 1;
     int temp = 0;
  int layerNumbers = (upperBoundX+1-layer)*2 + (upperBoundY+1-layer-2)*2;
  int uniqueRotations = rotate%layerNumbers;

  for (int r = 0; r < uniqueRotations; r++) {
   temp = matrix[layer][layer];
   // Top
   for (int i = layer; i < upperBoundY; i++) {
    matrix[layer][i] = matrix[layer][i + 1];
   }

   // Right
   for (int i = layer; i < upperBoundX; i++) {
    matrix[i][upperBoundY] = matrix[i + 1][upperBoundY];
   }

   // Bottom
   for (int i = upperBoundY; i > layer; i--) {
    matrix[upperBoundX][i] = matrix[upperBoundX][i - 1];
   }

   // Left
   for (int i = upperBoundX; i > layer; i--) {
    matrix[i][layer] = matrix[i - 1][layer];
   }
   matrix[layer + 1][layer] = temp;

  }
  return true;
 }
}