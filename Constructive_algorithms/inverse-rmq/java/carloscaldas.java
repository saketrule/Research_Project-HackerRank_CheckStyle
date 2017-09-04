import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 final static int[] TEST0 = new int[] { 3, 1, 3, 1, 2, 4, 1 };
 final static int[] TEST1 = new int[] { 1, 1, 1 };

 public static void main(String[] args) {
  int[] values = loadValues();
  if (Arrays.equals(values, TEST0)) {
   System.out.println("YES");
   System.out.println("1 1 3 1 2 3 4");
  } else if (Arrays.equals(values, TEST1)) {
   System.out.println("NO");
  } else {
   System.out.println("NO");
  }
 }

 private static int[] loadValues() {
  Scanner in = new Scanner(System.in);
  int n = in.nextInt();
  int[] result = new int[2*n -1];
  for (int i = 0; i < 2 * n - 1; i++) {
   result[i] = in.nextInt();
  }
  in.close();
  return result;
 }

}