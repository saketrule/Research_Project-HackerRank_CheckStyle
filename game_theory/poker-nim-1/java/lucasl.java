import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);

  int tests = in.nextInt();
  for (int t = 0; t < tests; t++) {
   int n = in.nextInt();
   int k = in.nextInt();
   int[] p = new int[n];
   int x = 0;
   for (int i = 0; i < n; i++) {
    p[i] = in.nextInt();
    x ^= p[i];
   }

   System.out.println(x == 0 ? "Second" : "First");

  }

 }
}