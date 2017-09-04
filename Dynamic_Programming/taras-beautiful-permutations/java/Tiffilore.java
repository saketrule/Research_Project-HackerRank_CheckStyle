import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int g = in.nextInt();
  for (int a0 = 0; a0 < g; a0++) {
   int n = in.nextInt();
   Long[] ar = new Long[n];
   for (int i = 0; i < n; i++) {
    ar[i] = in.nextLong();
   }

   // # of permutations of n distinct elements:
   // n!
   long fac = faculty(n);
   // # of permutations of n elements with m elements doppelt:
   // n! / 1l <<m
   // # of permutations of n elements with m elements doppelt that are
   // ugly:
   //

   System.out.println();

   //

  }

 }

 private static long faculty(int n) {
  long res = 1l;
  for (int i = 1; i<= n; i++){
   res*=i;
  }
  return res;
 }
}