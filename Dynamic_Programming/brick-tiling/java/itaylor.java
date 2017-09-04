/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Solution {

 static int[][] matrix;

 static int n, m;

 static long counter = 0;

 static Set<String> countedMatrices = new HashSet<String>();

 // -1 == blocked, 0 == empty, 0 < == filled

 public static enum Rotation {
  upRight, upLeft, downRight, downLeft, hUpRight, hUpLeft, hDownRight, hDownLeft
 }

 public static enum Command {
  add, delete
 }

 public static void main(String[] args) throws IOException {

  int t;
  String[] items;

  //System.setIn(new FileInputStream(
  //  "C:\\Users\\ITDesktop\\workspace\\m\\input00.txt"));

  InputStreamReader inp = new InputStreamReader(System.in);
  BufferedReader br = new BufferedReader(inp);

  String str = br.readLine();

  t = Integer.parseInt(str);

  while (t > 0) {
   t--;
   str = br.readLine();

   items = str.split(" ");

   n = Integer.parseInt(items[0]);
   m = Integer.parseInt(items[1]);

   matrix = new int[n][m];

   for (int i = 0; i < n; i++) {
    str = br.readLine();
    for (int k = 0; k < str.length(); k++) {
     if (str.charAt(k) == '.')
      matrix[i][k] = 0;
     else
      matrix[i][k] = -1;

    }
   }
   counter = 0;
   countedMatrices.clear();
   System.out.println(p(matrix, 1));

  }

 }

 public static long p(int[][] matrix, int lPos) {

  // scan through the array, looking for a empty position

  // if no empty position found, check if the array hasn't been counted
  // already... if not then count it

  // when empty pos is found, try to add an L (two positions)

  // if sucessfull add, recall p with the next position
  // else move on to next position

  boolean hasEmpty = false;

  for (int i = 0; i < n; i++) {
   for (int k = 0; k < m; k++) {

    if (matrix[i][k] == 0) {
     hasEmpty = true;
     for (Rotation x : Rotation.values())
      if (addL(matrix, i, k, lPos, x)) {
       p(matrix, lPos + 1);
       removeL(matrix, i, k, lPos, x);
      }
    }

   }
  }

  if (!hasEmpty && addMatrix(matrix))
   counter++;

  return counter;
 }

 public static boolean addMatrix(int[][] matrix) {
  StringBuilder builder = new StringBuilder(matrix.length
    * matrix[0].length);

  int current = 0;
  int index = 0;

  for (int i = 0; i < n; i++)
   for (int k = 0; k < m; k++)
    if (current == 0 || (matrix[i][k] != current)) {
     index++;
     builder.append(index);
     current = matrix[i][k];
    } else
     builder.append(index);

  if (!countedMatrices.contains(builder.toString())) {
   countedMatrices.add(builder.toString());
   return true;
  } else
   return false;
 }

 public static void removeL(int[][] matrix, int i, int k, int index,
   Rotation r) {

  if (matrix == null || matrix.length < i || matrix[i].length < k)
   return;

  int i1 = 0, i2 = 0, k1 = 0, k2 = 0;

  switch (r) {
   case upRight:
    i1 = 1; i2 = 0;
    k1 = 1; k2 = 2;
    break;
   case upLeft:
    i1 = -1; i2 = 0;
    k1 = 1; k2 = 2;
    break;
   case downRight:
    i1 = 1; i2 = 0;
    k1 = -1; k2 = -2;
    break;
   case downLeft:
    i1 = -1; i2 = 0;
    k1 = -1; k2 = -2;
    break;
   case hUpRight:
    i1 = 1; i2 = 2;
    k1 = 0; k2 = -1;
    break;
   case hUpLeft:
    i1 = -1; i2 = -2;
    k1 = 0; k2 = -1;
    break;
   case hDownRight:
    i1 = 1; i2 = 2;
    k1 = 0; k2 = 1;
    break;
   case hDownLeft:
    i1 = -1; i2 = -2;
    k1 = 0; k2 = 1;
    break;
  }

  try {
   if (matrix[i + i2][k] == index && matrix[i + i1][k] == index
     && matrix[i][k + k1] == index && matrix[i][k + k2] == index) {
    matrix[i][k + k1] = 0;
    matrix[i][k + k2] = 0;
    matrix[i + i1][k] = 0;
    matrix[i + i2][k] = 0;
   }
  } catch (ArrayIndexOutOfBoundsException e) {
  }
 }

 public static boolean addL(int[][] matrix, int i, int k, int index,
   Rotation r) {

  if (matrix == null || matrix.length < i || matrix[i].length < k)
   return false;

  int i1 = 0, i2 = 0, k1 = 0, k2 = 0;

  switch (r) {
   case upRight:
    i1 = 1; i2 = 0;
    k1 = 1; k2 = 2;
    break;
   case upLeft:
    i1 = -1; i2 = 0;
    k1 = 1; k2 = 2;
    break;
   case downRight:
    i1 = 1; i2 = 0;
    k1 = -1; k2 = -2;
    break;
   case downLeft:
    i1 = -1; i2 = 0;
    k1 = -1; k2 = -2;
    break;
   case hUpRight:
    i1 = 1; i2 = 2;
    k1 = 0; k2 = -1;
    break;
   case hUpLeft:
    i1 = -1; i2 = -2;
    k1 = 0; k2 = -1;
    break;
   case hDownRight:
    i1 = 1; i2 = 2;
    k1 = 0; k2 = 1;
    break;
   case hDownLeft:
    i1 = -1; i2 = -2;
    k1 = 0; k2 = 1;
    break;
  }

  try {
   if (matrix[i + i2][k] == 0 && matrix[i + i1][k] == 0
     && matrix[i][k + k1] == 0 && matrix[i][k + k2] == 0) {
    matrix[i][k + k1] = index;
    matrix[i][k + k2] = index;
    matrix[i + i1][k] = index;
    matrix[i + i2][k] = index;
    return true;
   }
  } catch (ArrayIndexOutOfBoundsException e) {
  }
  return false;
 }
}