import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {

 private static int n = 0;
 private static int m = 0;
 private static int k = 0;

 private static int[] centers = null;
 private static final List<Integer> nonZeroCenters = new ArrayList<>(1024);
 private static long[][] mat = null;

 private static int finalMask = 0;

 private static long[] visited = new long[1024 / 64];

 private static long[] combinations = null;

 private static void addVisited(final int v) {
  final int ai = v / 64;
  final int bi = v & 0x3f;
  visited[ai] |= (1 << bi);
 }

 private static void removeVisited(final int v) {
  final int ai = v / 64;
  final int bi = v & 0x3f;
  visited[ai] &= ~(1 << bi);
 }

 private static boolean isVisited(final int v) {
  final int ai = v / 64;
  final int bi = v & 0x3f;
  return (visited[ai] & (1 << bi)) > 0;
 }

 private static void calculateAllPaths() {
  for (int k = 0; k < n; k++) {
   for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
     if (i != j) {
      if ((mat[i][k] > 0) && (mat[k][j] > 0)) {
       final long len = mat[i][k] + mat[k][j];
       if ((mat[i][j] == 0) || (len < mat[i][j])) {
        mat[i][j] = mat[j][i] = len;
       }
      }
     }
    }
   }
  }
 }

 private static void visit(final int vis, final int mask, final long len) {
  if (vis == (n - 1)) {
   if ((combinations[mask] == 0) || (len < combinations[mask])) {
    combinations[mask] = len;
   }
   return;
  }

  {
   final int nmask = mask | centers[n - 1];
   final long nlen = len + mat[vis][n - 1];
   if ((combinations[nmask] == 0) || (nlen < combinations[nmask])) {
    combinations[nmask] = nlen;
   }
  }

  for (int i = 0; i <= finalMask; i++) {
   if (combinations[i] > 0) {
    if ((i & mask) == mask) {
     if (combinations[i] <= len) {
      return;
     }
    }
   }

  }

  addVisited(vis);

  for (final Integer nz : nonZeroCenters) {
   final int idx = nz.intValue();
   final int nmask = mask | centers[idx];
   if ((nmask != mask) && !isVisited(idx)) {
    visit(idx, nmask, len + mat[vis][idx]);
   }
  }

  removeVisited(vis);
 }

 public static void main(final String[] args) {
  @SuppressWarnings("resource")
  final Scanner in = new Scanner(System.in);

  n = in.nextInt(); // centers
  m = in.nextInt(); // roads
  k = in.nextInt(); // types

  /* read fish */
  centers = new int[n];
  for (int i = 0; i < n; i++) {
   final int t = in.nextInt();

   int mask = 0;
   for (int j = 0; j < t; j++) {
    mask |= 1 << (in.nextInt() - 1);
   }
   centers[i] = mask;
   if (mask > 0) {
    nonZeroCenters.add(i);
   }
  }

  Collections.sort(nonZeroCenters, new Comparator<Integer>() {
   @Override
   public int compare(final Integer arg0, final Integer arg1) {
    final int mask1 = centers[arg0.intValue()];
    final int mask2 = centers[arg1.intValue()];

    final int bc1 = Integer.bitCount(mask1);
    final int bc2 = Integer.bitCount(mask2);

    if (bc1 == bc2) {
     return Integer.compare(mask1, mask2);
    } else {
     return Integer.compare(bc1, bc2);
    }
   }
  });

  /* read adj matrix */
  mat = new long[n][n];
  for (int i = 0; i < m; i++) {
   final int from = in.nextInt() - 1;
   final int to = in.nextInt() - 1;
   final int len = in.nextInt();

   mat[from][to] = mat[to][from] = len;
  }

  /* calculate mask */
  finalMask = (1 << k) - 1;

  /* calculate all to all paths */
  calculateAllPaths();

  /* find all solutions */

  combinations = new long[finalMask + 1];
  combinations[centers[0] | centers[n - 1]] = mat[0][n - 1];

  addVisited(0);
  final int mask = centers[0];

  for (final Integer nz : nonZeroCenters) {
   final int idx = nz.intValue();
   final int nmask = mask | centers[idx];
   if (nmask != mask) {
    visit(idx, nmask, mat[0][idx]);
   }
  }

  long min = combinations[finalMask];
  for (int i = 0; i < combinations.length; i++) {
   if (combinations[i] > 0) {
    for (int j = (i + 1); j < combinations.length; j++) {
     if (combinations[j] > 0) {
      if ((i | j) == finalMask) {
       final long l = Math.max(combinations[i], combinations[j]);
       if ((min == 0) || (l < min)) {
        min = l;
       }
      }
     }
    }
   }
  }
  System.out.println(min);
 }
}