import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  static int max_len = 0;
  static int counter = 1;
  static long way[] = new long[1000];

  public static void blockVertex(int n, int b[], int cn[][], boolean block) {
    if (true == block) {
      b[n] -= 100;
      for (int i = 0; i < b.length; ++i) {
        if (1 == cn[n][i]) {
          b[i]--;
        }
      }
    } else {
      for (int i = 0; i < b.length; ++i) {
        if (1 == cn[n][i] && b[i] < 1) {
          b[i]++;
        }
      }
      b[n] += 100;
    }
  }

  public static void dfs(int n, int w[], int b[], int cn[][], int path, long path_way) {
    path += w[n];
    path_way += (long)1 << n;
    if (path > max_len) {
      max_len = path;
      counter = 1;
      way[0] = path_way;
    } else if (path == max_len) {
      int good = 1;
      for (int i = 0; i < counter; ++i) {
        if (way[i] == path_way) {
          good = 0;
          break;
        }
      }
      if (1 == good) {
        way[counter] = path_way;
        counter++;
      }
    }
    blockVertex(n, b, cn, true);
    for (int i = 0; i < w.length; ++i) {
      if (1 == b[i]) {
        dfs(i, w, b, cn, path, path_way);
      }
    }
    blockVertex(n, b, cn, false);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int n = in.nextInt();
    int m = in.nextInt();

    int weight[]  = new int[n];
    int blocked[] = new int[n];

    for (int i = 0; i < n; ++i) {
      weight[i]  = in.nextInt();
      blocked[i] = 1;
    }

    int connected[][] = new int[n][n];
    for (int i = 0; i < m; ++i) {
      int left_el  = in.nextInt() - 1;
      int right_el = in.nextInt() - 1;
      connected[left_el][right_el] = 1;
      connected[right_el][left_el] = 1;
    }

    in.close();

    for (int i = 0; i < n; ++i) {
      dfs(i, weight, blocked, connected, 0, 0);
    }

    System.out.println(max_len + " " + counter);
  }
}