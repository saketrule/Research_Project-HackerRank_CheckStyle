import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
static Boolean NimMisereSec(Scanner in) {
  int n = in.nextInt();
  int sum = 0;
  Boolean someGtOne = false;
  Boolean allLesEqOne = true;
  int val = 0;
  while (n-- > 0) {
   val = in.nextInt();
   sum ^= val;
   if (val > 1) {
    someGtOne = true;
    allLesEqOne = false;
   }
  }
  return (sum == 0 && someGtOne) || (sum == 1 && allLesEqOne);
 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int t = in.nextInt();
  while (t-- > 0) {
   System.out.println(NimMisereSec(in) ? "Second" : "First");
  }
  in.close();
 }
}