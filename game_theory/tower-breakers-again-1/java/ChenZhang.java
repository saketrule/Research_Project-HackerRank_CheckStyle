import java.util.*;
import java.io.*;

class Solution {
 private static int getCount(int x) {

  int count = 1 ^ (x & 1);
  while (x % 2 == 0) {
   x /= 2;
  }
  
  for (int i = 3; x > 1; i += 2) {
   while (x % i == 0) {
    x /= i;
    count++;
   }
  }
  return count;
 }
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int T = in.nextInt();
  for (int t = 0; t < T; t++) {
   int N = in.nextInt();
   int res = 0;
   for (int i = 0; i < N; i++) {
    res ^= getCount(in.nextInt());
   }
   if (res == 0) System.out.println(2);
   else System.out.println(1);
  }
 }
}