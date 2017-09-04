import java.util.Arrays;
import java.util.Scanner;

class Solution {
 static int n, m;
 static int[][][] dp;
 static char[][] av;

 public static void go(int row, int i, int m0, int m1, int m2, int sm1,
   int sm2) {
  if (i == m) {
   if (m0 == (1 << m) - 1) {
    dp[row][sm1][sm2] += dp[row + 1][m1][m2];
    dp[row][sm1][sm2] %= 1000000007;
   }
  } else {
   // #
   // ####
   if (i >= 2 && (m0 & (1 << i)) == 0 && (m1 & (1 << i)) == 0
     && (m1 & (1 << (i - 1))) == 0 && (m1 & (1 << (i - 2))) == 0) {
    go(row, i + 1, m0 | (1 << i), m1 | (1 << i)
      | (1 << (i - 1) | (1 << (i - 2))), m2, sm1, sm2);
   }
   // ###
   // #
   if (i >= 2 && (m1 & (1 << i)) == 0 && (m0 & (1 << i)) == 0
     && (m0 & (1 << (i - 1))) == 0 && (m0 & (1 << (i - 2))) == 0) {
    go(row, i + 1, m0 | (1 << i) | (1 << (i - 1) | (1 << (i - 2))),
      m1 | (1 << i), m2, sm1, sm2);
   }
   // #
   // ###
   if (i <= m - 3 && (m0 & (1 << i)) == 0 && (m1 & (1 << i)) == 0
     && (m1 & (1 << (i + 1))) == 0 && (m1 & (1 << (i + 2))) == 0) {
    go(row, i + 1, m0 | (1 << i), m1 | (1 << i)
      | (1 << (i + 1) | (1 << (i + 2))), m2, sm1, sm2);
   }
   // ###
   // #
   if (i <= m - 3 && (m1 & (1 << i)) == 0 && (m0 & (1 << i)) == 0
     && (m0 & (1 << (i + 1))) == 0 && (m0 & (1 << (i + 2))) == 0) {
    go(row, i + 1, m0 | (1 << i) | (1 << (i + 1) | (1 << (i + 2))),
      m1 | (1 << i), m2, sm1, sm2);
   }

   if (i >= 1 && (m0 & (1 << i)) == 0 && (m1 & (1 << i)) == 0
     && (m2 & (1 << (i))) == 0 && (m2 & (1 << (i - 1))) == 0) {
    go(row, i + 1, m0 | (1 << i), m1 | (1 << i), m2
      | (1 << (i - 1)) | (1 << i), sm1, sm2);
   }
   if (i >= 1 && (m2 & (1 << i)) == 0 && (m1 & (1 << i)) == 0
     && (m0 & (1 << (i))) == 0 && (m0 & (1 << (i - 1))) == 0) {
    go(row, i + 1, m0 | (1 << (i - 1)) | (1 << i), m1 | (1 << i),
      m2 | (1 << i), sm1, sm2);
   }
   if (i <= m - 2 && (m0 & (1 << i)) == 0 && (m1 & (1 << i)) == 0
     && (m2 & (1 << (i))) == 0 && (m2 & (1 << (i + 1))) == 0) {
    go(row, i + 1, m0 | (1 << i), m1 | (1 << i), m2
      | (1 << (i + 1)) | (1 << i), sm1, sm2);
   }
   if (i <= m - 2 && (m2 & (1 << i)) == 0 && (m1 & (1 << i)) == 0
     && (m0 & (1 << (i))) == 0 && (m0 & (1 << (i + 1))) == 0) {
    go(row, i + 1, m0 | (1 << (i + 1)) | (1 << i), m1 | (1 << i),
      m2 | (1 << i), sm1, sm2);
   }
   go(row, i + 1, m0, m1, m2, sm1, sm2);
  }
 }

 public static int solve() {
  dp = new int[n + 2][1 << m][1 << m];
  dp[n][0][0] = 1;
  int[] mask = new int[n + 2];
  for (int i = 0; i < av.length; i++)
   for (int j = 0; j < av[i].length; j++)
    if (av[i][j] != '.')
     mask[i] |= (1 << j);
  for (int row = n - 1; row >= 0; row--) {
   for (int mask1 = 0; mask1 < 1 << m; mask1++) {
    for (int mask2 = 0; mask2 < 1 << m; mask2++) {
     if ((mask[row] == 0 || (mask1 & mask[row]) == mask[row])
       && (mask[row + 1] == 0 || (mask2 & mask[row + 1]) == mask[row + 1])) {
      go(row, 0, mask1, mask2, 0, mask1, mask2);
     }
    }
   }
  }
  return dp[0][mask[0]][mask[1]];
 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int tc = in.nextInt();
  while (tc-- > 0) {
   n = in.nextInt();
   m = in.nextInt();
   av = new char[n][];
   for (int i = 0; i < av.length; i++)
    av[i] = in.next().toCharArray();
   System.out.println(solve());
  }
 }
}