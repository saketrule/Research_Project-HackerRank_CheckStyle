import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  static Boolean NimF(Scanner in) {
  int n = in.nextInt();
  int pile = 0, val = 0, i = 0, sum = 0;
  while (n-- > 0) {
   val = in.nextInt();
   if (val > 0) {
    if (val % 2 == 0)
     pile = 0;
    else
     pile = i;
    sum ^= pile;
   }
   i++;
  }
  return sum != 0;
 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int t = in.nextInt();
  while (t-- > 0) {
   System.out.println(NimF(in) ? "First" : "Second");
  }
  in.close();
 }
}