/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/


//package dynamicprogramming.bricktiling;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.InputStreamReader;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
 static String probStmtDir = "E:\\Krishnanew\\InterviewStreet\\Problems\\";
 static String probStmtFile =
  probStmtDir +  "DynamicPrgramming\\BrickTiling.txt";
 
 static int TC;

 static Map<Floor, Integer> cache = new HashMap<Floor, Integer>();

 static int cacheHit = 0;
 
 public static void main(String[] args) throws IOException {
  readAndSolve();
  //System.out.println("cachehits = " + cacheHit);
 }
 
 static class Floor
 {
  int r;
  int c;
  int r0;
  int c0;
  
  int [][] floor;
  public Floor(int r0, int c0, int r, int c, int[][] floor) {
   this.r0 = r0;
   this.c0 = c0;
   this.r = r;
   this.c = c;
   this.floor = floor;
  }
  
  @Override
  public boolean equals(Object obj) {
   Floor oth = (Floor)obj;
   
   boolean dimsEqual = 
     ( (r - r0 )!= (oth.r - oth.r0) ? false : 
      ( (c - c0) != (oth.c - oth.c0) ? false : true));
   if (!dimsEqual) {
    return false;
   }
   
   for (int i = 0; i< r; i++) {
    for (int j = 0; j < c; j++) {
     if (floor[i-r0][j-c0] != oth.floor[i - oth.r0][j - oth.c0] ) {
      return false;
     }
    }
   }
   
   return true;
  }
  
  @Override
  public int hashCode() {
   int hc = new Integer(r).hashCode();
   hc += 31* hc + new Integer(c).hashCode();
   for (int i = 0; i< r; i++) {
    for (int j = 0; j < c; j++) {
     hc += 31*hc + i*j*floor[r][c];
    }
   }
   return hc;
  }
 }
 
 static int findWaysToTile( boolean[][] floor)
 {
  if (floor.length == 0 || floor[0].length == 0) {
   return 1;
  }
  int rows = floor.length; 
  int cols = floor[0].length;
  
  int rcm = Math.max(rows, cols);
  if (rcm < 3) {
   
   for (int i = 0;i < rows; i++) {
    for (int j= 0; j< cols; j++) {
     if (floor[i][j]) {
      return 0;
     }
    }
   }
   return 1;
  }
  int emptySquare;
  
  // if (lengthMore(floor)) {
   emptySquare = findEmptySqrInLeftColumn(floor); //System.out.println("empty square in left col = " + emptySquare);
   //System.exit(0);
   if ( emptySquare == -1) {
    return findWaysToTile (createNewFloor(floor, 0, null));
   }
   return TileFromLeftColumn(floor, emptySquare);
  // }
  // else {
  //  emptySquare = findEmptySqrInTopRow(floor);
  // }
  
 }

 static boolean BCheck(boolean[][] floor, int r, int c)
 {
  if (floor.length -1 < r) {
   return false;
  }
  if (floor[0].length -1 < c) {
   return false;
  }
  
  return true;
 }
 
 static int  TileFromLeftColumn(boolean[][] floor, int r)
 {
  //System.out.println("TileFromLeftColumn----------------Floor:");
  printMat(floor);
  int rows = floor.length; int cols = floor[0].length;
  int ways = 0;
  
  
  // check if each of the 6 ways from here can be tiled
  // for each that can be tiled findways for the remaining floor 
  // add all of them up that's you answer
  
  // 7
  //  X 
      //  X X X
  
  //try{
  if ( BCheck(floor, r+1, 2) && floor[r][0] && floor[r + 1][0] && floor[r + 1][1] && floor[r +1 ][2]
                                        ) 
  {
   // construct a new floor with these positions tiled/blocked and recurse for its no of ways
   
   // check the first 3 columns to be totally blocked
   
   int[] newrc = new int[8];
   newrc[0] = r; newrc[1] = 0;
   newrc[2] = r + 1; newrc[3] = 0;
   newrc[4] = r + 1; newrc[5] = 1;
   newrc[6] = r + 1; newrc[7] = 2;
   int lastColBlocked = lastColBlocked(floor,  newrc);
   //System.out.println("last col blocked = " + lastColBlocked);
   boolean[][] newfloor = createNewFloor(floor, lastColBlocked, newrc);
   
   //System.out.println("new floor7");printMat(newfloor);
   //System.exit(0);
   
   ways += findWaysToTile(newfloor);
  }//} catch(ArrayIndexOutOfBoundsException ex) {}
  
  
  
  //8
  //      
  //  X X X
  //  X 
  //try{
  if ( BCheck(floor, r+1, 2) && floor[r][0] && floor[r][1] && floor[r][2] && floor[r +1  ][0]
        ) 
  {
   // construct a new floor with these positions tiled/blocked and recurse for its no of ways

   // check the first 3 columns to be totally blocked

   int[] newrc = new int[8];
   newrc[0] = r; newrc[1] = 0;
   newrc[2] = r; newrc[3] = 1;
   newrc[4] = r; newrc[5] = 2;
   newrc[6] = r + 1; newrc[7] = 0;
   int lastColBlocked = lastColBlocked(floor,  newrc);

   //System.out.println("last col blocked = " + lastColBlocked);
   boolean[][] newfloor = createNewFloor(floor, lastColBlocked, newrc);
   
   //System.out.println("new floor8");printMat(newfloor);

   ways += findWaysToTile(newfloor);
   //System.out.println("Ways till type 8 = " +  ways);
  }//} catch(ArrayIndexOutOfBoundsException ex) {}


  
  //1
  //  X X X
      //      X
  
  
  //try{
  if ( BCheck(floor, r+1, 2) && floor[r][0] && floor[r][1] && floor[r][2] && floor[r +1 ][2]
                                        ) 
  {
   // construct a new floor with these positions tiled/blocked and recurse for its no of ways
   
   // check the first 3 columns to be totally blocked
   
   int[] newrc = new int[8];
   newrc[0] = r; newrc[1] = 0;
   newrc[2] = r; newrc[3] = 1;
   newrc[4] = r; newrc[5] = 2;
   newrc[6] = r + 1; newrc[7] = 2;
   int lastColBlocked = lastColBlocked(floor,  newrc);
   //System.out.println("last col blocked = " + lastColBlocked);
   boolean[][] newfloor = createNewFloor(floor, lastColBlocked, newrc);
   
   //System.out.println("new floor1");printMat(newfloor);
   //System.exit(0);
   
   ways += findWaysToTile(newfloor);
   //System.out.println("Ways till type 1 = " +  ways);
  }//} catch(ArrayIndexOutOfBoundsException ex) {}
  
  
  
  //2
  //      X
  //  X X X
  
  //try{
  if ( r >= 1 && BCheck(floor, r, 2) && floor[r][0] && floor[r][1] && floor[r][2] && floor[r -1 ][2]
        ) 
  {
   // construct a new floor with these positions tiled/blocked and recurse for its no of ways

   // check the first 3 columns to be totally blocked

   int[] newrc = new int[8];
   newrc[0] = r; newrc[1] = 0;
   newrc[2] = r; newrc[3] = 1;
   newrc[4] = r; newrc[5] = 2;
   newrc[6] = r - 1; newrc[7] = 2;
   int lastColBlocked = lastColBlocked(floor,  newrc);

   //System.out.println("last col blocked = " + lastColBlocked);
   boolean[][] newfloor = createNewFloor(floor, lastColBlocked, newrc);
   
   //System.out.println("new floor2");printMat(newfloor);

   int x = findWaysToTile(newfloor);
   //System.out.println("Ways2 = " + x);
   ways += x;
  }//} catch(ArrayIndexOutOfBoundsException ex) {}

  //  X X   //3
  //    X  
  //    X
  //try{
  if ( BCheck(floor, r +2, 1) && floor[r][0] && floor[r][1] && floor[r + 1][1] && floor[r + 2 ][1]
        ) 
  {
   // construct a new floor with these positions tiled/blocked and recurse for its no of ways

   // check the first 3 columns to be totally blocked

   int[] newrc = new int[8];
   newrc[0] = r; newrc[1] = 0;
   newrc[2] = r; newrc[3] = 1;
   newrc[4] = r + 1; newrc[5] = 1;
   newrc[6] = r + 2; newrc[7] = 1;
   int lastColBlocked = lastColBlocked(floor,  newrc);

   //System.out.println("last col blocked = " + lastColBlocked);
   boolean[][] newfloor = createNewFloor(floor, lastColBlocked, newrc);
   
   //System.out.println("new floor3");printMat(newfloor);

   ways += findWaysToTile(newfloor);
  }//} catch(ArrayIndexOutOfBoundsException ex) {}

  //    X 
  //    X  
  //  X X //4
  //try{
  if ( r-2 >=0 && BCheck(floor, r, 1) && floor[r][0] && floor[r][1] && floor[r - 1][1] && floor[r - 2 ][1]
        ) 
  {
   // construct a new floor with these positions tiled/blocked and recurse for its no of ways

   // check the first 3 columns to be totally blocked

   int[] newrc = new int[8];
   newrc[0] = r; newrc[1] = 0;
   newrc[2] = r; newrc[3] = 1;
   newrc[4] = r - 1; newrc[5] = 1;
   newrc[6] = r - 2; newrc[7] = 1;
   int lastColBlocked = lastColBlocked(floor,  newrc);

   //System.out.println("last col blocked = " + lastColBlocked);
   boolean[][] newfloor = createNewFloor(floor, lastColBlocked, newrc);
   
   //System.out.println("new floor4");printMat(newfloor);

   ways += findWaysToTile(newfloor);
  }//} catch(ArrayIndexOutOfBoundsException ex) {}
  

  //  X 
  //  X
  //  X X //5
  //try{
  //System.out.println("Type 5 ---------------------------------");
  printMat(floor);
  if ( BCheck(floor, r + 2, 1) && floor[r][0] && floor[r + 1][0] && floor[r + 2][0] && floor[r +2 ][1]
        ) 
  {
   // construct a new floor with these positions tiled/blocked and recurse for its no of ways

   // check the first 3 columns to be totally blocked

   int[] newrc = new int[8];
   newrc[0] = r; newrc[1] = 0;
   newrc[2] = r +1; newrc[3] = 0;
   newrc[4] = r + 2; newrc[5] = 0;
   newrc[6] = r + 2; newrc[7] = 1;
   int lastColBlocked = lastColBlocked(floor,  newrc);

   //System.out.println("last col blocked = " + lastColBlocked);
   boolean[][] newfloor = createNewFloor(floor, lastColBlocked, newrc);
   
   //System.out.println("new floor5");printMat(newfloor);

   ways += findWaysToTile(newfloor);
   //System.out.println("Ways till type 5 = " +  ways);
  }//} catch(ArrayIndexOutOfBoundsException ex) {}

  
  //  X X
  //  X
  //  X  //6
  //try {
  if ( BCheck(floor, r +2 , 1) && floor[r][0] && floor[r + 1][0] && floor[r + 2][0] && floor[r  ][1]
        ) 
  {
   // construct a new floor with these positions tiled/blocked and recurse for its no of ways

   // check the first 3 columns to be totally blocked

   int[] newrc = new int[8];
   newrc[0] = r; newrc[1] = 0;
   newrc[2] = r +1; newrc[3] = 0;
   newrc[4] = r +2; newrc[5] = 0;
   newrc[6] = r ; newrc[7] = 1;
   int lastColBlocked = lastColBlocked(floor,  newrc);

   //System.out.println("last col blocked = " + lastColBlocked);
   boolean[][] newfloor = createNewFloor(floor, lastColBlocked, newrc);
   
   //System.out.println("new floor6");printMat(newfloor);

   ways += findWaysToTile(newfloor);
   //System.out.println("Ways till type 6 = " +  ways);
  }//} catch(ArrayIndexOutOfBoundsException ex) {}

  return ways;
 }
 
 static boolean[][] createNewFloor(boolean[][] floor, int lastColBlocked, int[] newrc)
 {
  int rows = floor.length;
  int newcols = floor[0].length - (lastColBlocked + 1);
  
  boolean[][] floornew = constructMatrixB(rows, newcols);
  
  for (int r = 0; r < rows; r++) {
   for (int c = 0; c < newcols; c++) {
    floornew[r][c]= floor[r][c + (lastColBlocked + 1)];
   }
  }
  
  if (newrc == null) {
   return floornew;
  }
  // mark the newrc blocked
  try { floornew[newrc[0]][newrc[1] - (lastColBlocked + 1)] = false;} catch(ArrayIndexOutOfBoundsException ex) {}
  try {floornew[newrc[2]][newrc[3] - (lastColBlocked + 1)] = false;} catch(ArrayIndexOutOfBoundsException ex) {}
  try {floornew[newrc[4]][newrc[5] - (lastColBlocked + 1)] = false;} catch(ArrayIndexOutOfBoundsException ex) {}
  try {floornew[newrc[6]][newrc[7] - (lastColBlocked + 1)] = false;} catch(ArrayIndexOutOfBoundsException ex) {}
  
  return floornew;
 }
 static int lastColBlocked(boolean[][] floor, int[] newrc)//settled
 {
  int rows = floor.length; int cols = floor[0].length;
  int columnBlocked = -1;
  
  for (int col = 0;col < cols;col++) {
   for (int ro = 0  ; ro < rows; ro++) {
    //  free before    and   free now
    if ( floor[ro][col] && !oneOfTheNew (ro, col, newrc)) {  // checking for not blocked
     return columnBlocked;
    }
   }
   columnBlocked = col;
  }
  return columnBlocked;
 }
 
 static boolean oneOfTheNew(int r, int c, int[] newrc)
 {
  if (r == newrc[0] && c == newrc[1]) {
   return true;
  }
  if (r == newrc[2] && c == newrc[3]) {
   return true;
  }
  if (r == newrc[4] && c == newrc[5]) {
   return true;
  }
  if (r == newrc[6] && c == newrc[7]) {
   return true;
  }
  return false;
 }
 static boolean lengthMore(boolean[][] floor )
 {
  if (floor[0].length > floor.length) {
   return true;
  }
  return false;
 }
 static int findEmptySqrInLeftColumn(boolean[][] floor)
 {
  int rows = floor.length;
  for (int i = 0; i < rows; i++) {
   if (floor[i][0]) { //empty square
    return i;
   }
  }
  //throw new IllegalStateException("Found no empty square in left column");
  return -1;
 }
 static int findEmptySqrInTopRow(boolean[][] floor)
 {
  int cols = floor[0].length;
  for (int i = 0; i < cols; i++) {
   if (floor[0][i]) { //empty square
    return i;
   }
  }
  throw new IllegalStateException("Found no empty square in top row");
 }
 static void readAndSolve() throws IOException
 {
  BufferedReader in = //new BufferedReader(new FileReader(probStmtFile));
   new BufferedReader(new InputStreamReader(System.in));
     
  String line1 = in.readLine();
  TC = Integer.parseInt(line1.trim());
  
  for (int i =0 ;i < TC; i++) {
   String nm = in.readLine().trim();
   int n = getIntsInString(nm, " ")[0];
   int k = getIntsInString(nm, " ")[1];
   boolean[][] floor = constructMatrixB(n, k);
   
   for (int r = 0; r < n; r++) {
    String line = in.readLine();
    for (int c = 0; c < k; c++) {
     char ch = line.charAt(c);
     if (ch == '.' ) {
      floor[r][c] = true;
     }
     else {
      floor[r][c] = false;
     }
    }
   }
   
   printMat(floor);
   
   int ways = findWaysToTile(floor);
   System.out.println( ways);
   //System.exit(0);
  }
  
 }
 
 static int[] getIntsInString(String str, String sp)
 {
  String[] nm = str.split(sp);
  
  int[] ints = new int[nm.length];
  int i =0;
  for (String s:nm){
   ints[i++] = Integer.parseInt(s.trim());
  }
      
      return ints;
 }
 static int[][] constructMatrix(int r, int c)
 {
  int[][] mat =   new int[r][];
  for (int row = 0 ; row < r; row++){
   mat[row] = new int[c];
  }
  return mat;
  
 }
 static boolean[][] constructMatrixB(int r, int c)
 {
  boolean[][] mat =   new boolean[r][];
  for (int row = 0 ; row < r; row++){
   mat[row] = new boolean[c];
  }
  return mat;
  
 }
 
 static void printMat(boolean[][] m)
 {
  if (m.length == 0 || m[0].length == 0) {
   System.out.println(" [empty ] ");
  }
  //System.out.println("Matrix:" + name);
  for (int i = 0; i< m.length; i++) {
   for (int j = 0; j < m[0].length; j++) {
    //System.out.printf("%s, ", m[i][j]);
   }
   //System.out.println();
  }
 }
 static void printMat(int[][] m)
 {
  if (m.length == 0) {
   //System.out.println(" [empty ] ");
  }
  //System.out.println("Matrix:" + name);
  for (int i = 0; i< m.length; i++) {
   for (int j = 0; j < m[0].length; j++) {
    System.out.printf("%s, ", m[i][j]);
   }
   System.out.println();
  }
 }
}