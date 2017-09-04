import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 private static int[][] arr;
 private static int[][] M;
 private static int N, H, I, max1, max2;

 public static void main(String[] args) {

  Scanner scanner = new Scanner(System.in);
  N = scanner.nextInt();
  H = scanner.nextInt();
  I = scanner.nextInt();

  arr = new int[H][N];
  M = new int [H][N];
  for(int i = 0; i < H; i++) {
   Arrays.fill(arr[i], 0);
   Arrays.fill(M[i], 0);
  }
  for (int i = 0; i < N; i++) {
   int k = scanner.nextInt();
   for (int j = 0; j < k; j++) {
    int t = scanner.nextInt();
    arr[t-1][i]++;
   }
  }

  buildMemo();
  printOutput();
  scanner.close();
 }
 public static void buildMemo() {
  M[0] = Arrays.copyOf(arr[0], N);
  for(int i = 1; i < I; i++) {
   for(int j = 0; j < N; j++) {
    M[i][j] = M[i-1][j] + arr[i][j];
   }
  }
  for(int i = I; i < H; i++) {
   findMax(i);
   for(int j = 0; j < N; j++) {

    if (max1 == M[i - I][j]) {
     M[i][j] = Math.max(arr[i][j] + M[i - 1][j], arr[i][j] + max2);
    } else {
     M[i][j] = Math.max(arr[i][j] + M[i - 1][j], arr[i][j] + max1);
    }
   }
  }
 }
 public static void findMax(int row) {
  int[] temp = Arrays.copyOf(M[row-I], N);
  Arrays.sort(temp);
  max1 = temp[N-1];
  if(N > 1)
   max2 = temp[N-2];
  else max2 = 0;
 }
 public static void printOutput() {
  Arrays.sort(M[H-1]);
  System.out.println(M[H-1][N-1]);
 }
}