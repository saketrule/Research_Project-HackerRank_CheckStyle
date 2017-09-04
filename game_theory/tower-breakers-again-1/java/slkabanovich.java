import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 static void consider(int z, int y, int[] u, int[] f) {
  if(y > 1) {
   if(y % 2 == 0) {
    u[0] = 1;
   } else {
    u[f[z]] = 1;
   }
  }
 }
 
 static int findF(int[] u) {
  for (int i = 0; i < u.length; i++) {
   if(u[i] == 0) return i;
  }
  throw new RuntimeException();
 }
 
 static int[] computeNim(int n) {
  int[] f = new int[n + 1];
  for (int x = 1; x < f.length; x++) {
   int[] u = new int[100];
   for (int m = 1; m * m <= x; m++) {
    if(x % m == 0) {
     consider(x / m, m, u, f);
     consider(m, x / m, u, f);
    }
   }
   f[x] = findF(u);
//   System.out.println(x + " " + f[x]);
  }
  return f;
 }
 
 static void solve(int[] h, int[] f) {
  int result = 0;
  for (int i = 0; i < h.length; i++) {
   result = result ^ f[h[i]];
  }
  int outcome = result == 0 ? 2 : 1;
  System.out.println(outcome);
 }

 static void run() {
  Scanner scanner = new Scanner(System.in);
  int q = scanner.nextInt();
  int[] f = computeNim(100000);
  for (int t = 0; t < q; t++) {
   int n = scanner.nextInt();
   int[] h = new int[n];
   for (int i = 0; i < n; i++) {
    h[i] = scanner.nextInt();
   }
   solve(h, f);
  }
  scanner.close();
 }

    public static void main(String[] args) {
        run();
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}