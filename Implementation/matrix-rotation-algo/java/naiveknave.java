import java.util.Scanner;
import java.util.Stack;

public class Solution {
 
 private static int[][] getMatrix(Scanner in, int M, int N) {
  int[][] X = new int[M][N];
  for (int i = 0; i < M; i++) {
   for (int j = 0; j < N; j++) { X[i][j] = in.nextInt(); }
  }
  return X;
 }

 private static void rotateMatrix(int[][] X, int M, int N, int sub, int R) {
  int perimeter = 2*(M-sub-1) + 2*(N-sub-1);
  int newR = (perimeter==0) ? R : R%perimeter;
  if (sub<M && sub<N) {
   for (int r = 0; r < newR; r++) {
    int i = sub; int j = sub;
    Stack<Integer> bin = new Stack<Integer>();
    while (i < M-1) { i++; bin.push(X[i-1][j]); }
    while (j < N-1) { j++; bin.push(X[i][j-1]); }
    while (i > sub) { i--; bin.push(X[i+1][j]); }
    while (j > sub) { j--; bin.push(X[i][j+1]); }
    while (j < N-1) { X[i][j] = bin.pop(); j++; }
    while (i < M-1) { X[i][j] = bin.pop(); i++; }
    while (j > sub) { X[i][j] = bin.pop(); j--; }
    while (i > sub) { X[i][j] = bin.pop(); i--; }
   }
   rotateMatrix(X, M-1, N-1, sub+1, R);
  }
 }

 private static void printMatrix(int[][] X, int M, int N) {
  for (int i = 0; i < M; i++) {
   for (int j = 0; j < N; j++) { System.out.print(X[i][j] + " "); }
   System.out.println();
  }
 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int M = in.nextInt();
  int N = in.nextInt();
  int R = in.nextInt();
  int[][] X = getMatrix(in, M, N);
  rotateMatrix(X, M, N, 0, R);
  printMatrix(X, M, N);
 }

}