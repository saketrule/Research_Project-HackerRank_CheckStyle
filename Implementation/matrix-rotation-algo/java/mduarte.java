import java.util.Scanner;

public class Solution {
    private static int[][] matrix;
    private static int[][] newMatrix;
 
 
 public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
  
  int m = in.nextInt();
  int n = in.nextInt();
  int r = in.nextInt();

  matrix = new int[m][n];
  
  for(int i=0; i<m; i++) {
   for(int j=0; j<n; j++) {
    matrix[i][j] = in.nextInt();
   }
  }
  
  //printMatrix(matrix, m, n);
  
  newMatrix = new int[m][n];
  rotateMatrix(m, n, r);
  printMatrix(newMatrix, m, n);
 }
 
 
 private static void printMatrix(int[][] mat, int m, int n) {
  for(int i=0; i<m; i++) {
   for(int j=0; j<n; j++) {
    System.out.print(mat[i][j] + " ");
   }
   System.out.print("\n");
  } 
 }
 
 private static void rotateMatrix(int m, int n, int r) {
  for(int i=0; i<m; i++) {
   for(int j=0; j<n; j++) {
    Coords coords = rotateMatrixValue(r, j, i, m, n);
    newMatrix[coords.getY()][coords.getX()] = matrix[i][j];
   }
   
  }
 }
 
 
 private static Coords rotateMatrixValue(int r, int x, int y, final int m, final int n) {
  int lowX=0, highX=n-1, lowY=0, highY=m-1;
  int lowX2, highX2, lowY2, highY2;
  for(int i=0; i<Math.max(n,m)/2; i++) {
   lowX2 = lowX + 1;
   highX2 = highX - 1;
   lowY2 = lowY + 1;
   highY2 = highY -1;
   
   if(x>=lowX2 && x<=highX2 && y>=lowY2 && y<=highY2) {
    lowX = lowX2;
    highX = highX2;
    lowY = lowY2;
    highY = highY2;
   }
   else {
    break;
   }
  }
        
        r = r % ((highX - lowX)*2 + (highY - lowY)*2);
  
  for(int i=0; i<r; i++) {
   if(y == lowY) {
    if(x == lowX) y++;
    else x--;
   }
   else if(x == lowX) {
    if(y == highY) x++;
    else y++;
   }
   else if(y == highY) {
    if(x == highX) y--;
    else x++;
   }
   else if(x == highX) {
    if(y == lowY) x--;
    else y--;
   }
  }
  return new Coords(x,y);
 }
 
 private static class Coords {
  int x;
  int y;
  
  public Coords(int x, int y) {
   this.x = x;
   this.y = y;
  }
  
  public int getX() {
   return x;
  }
  
  public int getY() {
   return y;
  }
 }
}