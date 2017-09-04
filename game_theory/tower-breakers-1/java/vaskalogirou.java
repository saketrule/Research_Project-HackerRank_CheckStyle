import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int cases = in.nextInt();
  for (int i = 0; i < cases; i++) {
   int one = in.nextInt();
   int two = in.nextInt();
   System.out.println(method(one, two));
  }
  in.close();
 }

 public static int method(int one, int two) {
  if (two == 1) {
   return 2;
  }
  if (one % 2 == 0) {
   return 2;
  }
  else {
   return 1;
  }
 }
}