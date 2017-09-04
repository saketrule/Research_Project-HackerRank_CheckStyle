import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  public static void simulate(int[][] boards, int x, int y) {
    if ((y + 1 < boards.length && x - 2 >= 0 && boards[y + 1][x - 2] == 2) ||
        (y - 1 >= 0 && x - 2 >= 0 && boards[y - 1][x - 2] == 2) ||
        (y - 2 >= 0 && x + 1 < boards.length && boards[y - 2][x + 1] == 2) ||
        (y - 2 >= 0 && x - 1 >= 0 && boards[y - 2][x - 1] == 2)) {
      boards[y][x] = 1;
      return;
    }
    if (y + 1 < boards.length && x - 2 >= 0 && boards[y + 1][x - 2] == 0) {
      simulate(boards, x - 2, y + 1);
      if (boards[y + 1][x - 2] == 2) {
        boards[y][x] = 1;
        return;
      }
    }
    if (y - 1 >= 0 && x - 2 >= 0 && boards[y - 1][x - 2] == 0) {
      simulate(boards, x - 2, y - 1);
      if (boards[y - 1][x - 2] == 2) {
        boards[y][x] = 1;
        return;
      }
    }
    if (y - 2 >= 0 && x + 1 < boards.length && boards[y - 2][x + 1] == 0) {
      simulate(boards, x + 1, y - 2);
      if (boards[y - 2][x + 1] == 2) {
        boards[y][x] = 1;
        return;
      }
    }
    if (y - 2 >= 0 && x - 1 >= 0 && boards[y - 2][x - 1] == 0) {
      simulate(boards, x - 1, y - 2);
      if (boards[y - 2][x - 1] == 2) {
        boards[y][x] = 1;
        return;
      }
    }
    boards[y][x] = 2;
  }

  public static void main(String[] args) {

    int[][] boards = new int[15][15];
    boards[0][0] = boards[0][1] = boards[1][0] = boards[1][1] = 2;

    for (int x = 0; x < boards.length; x++) {
      for (int y = 0; y < boards[x].length; y++) {
          simulate(boards, x, y);
      }
    }

    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for(int i = 0 ; i < t ; i++) {
      int x = in.nextInt();
      int y = in.nextInt();
      System.out.println(boards[y-1][x-1] == 2 ? "Second" : "First");
    }
  }
}