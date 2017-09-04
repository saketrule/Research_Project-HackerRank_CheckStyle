import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

   static Boolean NimF(Scanner in) {
  int n = in.nextInt();
  int sum = 0;
  while (n-- > 0) {
   sum ^= in.nextInt();
  }
  return sum > 0;
 }

 static Boolean table[][];

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int t = in.nextInt();
  table = new Boolean[15][15];
  while (t-- > 0) {
   System.out.println(NimF(in) ? "First" : "Second");
  }
  in.close();
 }
}