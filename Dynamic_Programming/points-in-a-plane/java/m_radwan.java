import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
 static boolean[] isCollinear;
 static int n;
 static int[] dp1;
 static int[] dp2;
 static byte[] col;

 public static void main(String[] args) throws Exception {
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  StringBuilder out = new StringBuilder();
  int tc = Integer.parseInt(in.readLine());
  dp1 = new int[1 << 16];
  dp2 = new int[1 << 16];
  long[] fact = new long[20];
  fact[0] = 1;
  for (int i = 1; i < fact.length; i++)
   fact[i] = (fact[i - 1] * i) % 1000000007;
  while (tc-- > 0) {
   n = Integer.parseInt(in.readLine());
   int[] x = new int[n];
   int[] y = new int[n];
   for (int i = 0; i < n; i++) {
    String[] sp = in.readLine().split(" ");
    x[i] = Integer.parseInt(sp[0]);
    y[i] = Integer.parseInt(sp[1]);
   }
   int[] xx = new int[n];
   int[] yy = new int[n];
   boolean ok;
   int top;
   isCollinear = new boolean[1 << n];
   for (int i = 1; i < 1 << n; i++) {
    top = 0;
    for (int j = 0; j < n; j++) {
     if (((i >> j) & 1) != 0) {
      xx[top] = x[j];
      yy[top] = y[j];
      top++;
     }
    }
    if (top <= 2)
     isCollinear[i] = true;
    else {
     ok = true;
     int dx = xx[0] - xx[1];
     int dy = yy[0] - yy[1];
     for (int j = 2; j < top && ok; j++) {
      if (dx * (yy[j] - yy[0]) != dy * (xx[j] - xx[0]))
       ok = false;
     }
     isCollinear[i] = ok;
    }
   }

   dp1[(1 << n) - 1] = 0;
   dp2[(1 << n) - 1] = 1;
   int min, ways, next, s, i, nxmin;
   for (s = (1 << n) - 2; s >= 0; s--) {
    min = 63;
    ways = 0;
    next = ((1 << n) - 1) ^ s;

    for (i = next; (i ^ next) < i; i = (i - 1) & next) {
     if (isCollinear[i]) {
      nxmin = dp1[s | i] + 1;
      if (nxmin < min) {
       min = nxmin;
       ways = dp2[s | i];
      } else if (nxmin == min)
       ways = (ways + dp2[s | i]) % 1000000007;
     }
    }
    dp1[s] = min;
    dp2[s] = ways;
   }
   out.append(dp1[0] + " " + (fact[dp1[0]] * dp2[0] % 1000000007)
     + "\n");
  }
  System.out.print(out);
 }
}