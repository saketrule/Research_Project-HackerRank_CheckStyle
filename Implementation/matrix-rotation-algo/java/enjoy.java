import java.awt.Point;
import java.util.Scanner;

public class Solution {

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int m = in.nextInt();
  int n = in.nextInt();
  int r = in.nextInt();
  in.nextLine();
  int[][] mat = new int[m][n];
  for (int y = 0; y < m; y++) {
   for (int x = 0; x < n; x++) {
    int a = in.nextInt();
    mat[y][x] = a;
   }
   if (y < m - 1) {
    in.nextLine();
   }
  }

  int[][] out = rotate(mat, m, n, r);
  System.out.println(getOutput(out));
 }

 private static int[][] rotate(int[][] mat, int m, int n, int r) {
  int iters = Math.min(m, n) / 2;
  int[][] out = new int[m][n];
  for (int i = 0; i < iters; i++) {
   int height = m - i * 2;
   int width = n - i * 2;
   int ringLength = (height + width) * 2 - 4;
   for (int ri = 0; ri < ringLength; ri++) {
    Point pO = getCoords(m, n, i, ri, height, width);
    Point pM = getCoords(m, n, i, (ri + r) % ringLength, height,
      width);
    out[pO.y][pO.x] = mat[pM.y][pM.x];
   }

  }
  return out;
 }

 private static Point getCoords(int m, int n, int i, int r, int height,
   int width) {

  if (r < width - 1) {
   return new Point(r + i, i);

  } else if (r < width + height - 2) {
   return new Point(n - 1 - i, r - (width - 1) + i);

  } else if (r < 2 * width + height - 3) {
   return new Point((width - 1) - (r - (width + height - 2)) + i, m
     - 1 - i);

  } else {
   return new Point(i, (height - 1) - (r - (2 * width + height - 3))
     + i);

  }
 }

 private static StringBuilder getOutput(int[][] mat) {
  StringBuilder builder = new StringBuilder();
  for (int y = 0; y < mat.length; y++) {
   for (int x = 0; x < mat[0].length; x++) {
    if (x > 0) {
     builder.append(' ');
    }
    builder.append(mat[y][x]);
   }
   builder.append('\n');
  }
  return builder;
 }
}