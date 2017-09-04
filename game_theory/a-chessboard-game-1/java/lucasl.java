import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 private static final String FIRST = "First";
 private static final String SECOND = "Second";

 private static void solve(int x, int y) {
  x = x % 4;
  y = y % 4;
  if ((y == 0) || (y == 3) || (x == 0) || (x == 3)) {
   System.out.println(FIRST);
  } else {
   System.out.println(SECOND);
  }
 }

 public static void main(String[] args) throws FileNotFoundException {
  //System.setIn(new FileInputStream(System.getProperty("user.home") + "/" + "in.txt"));
  Scanner in = new Scanner(System.in);

  int tests = in.nextInt();
  for (int t = 0; t < tests; t++) {
   int x = in.nextInt();
   int y = in.nextInt();

   solve(x, y);
  }
 }
}