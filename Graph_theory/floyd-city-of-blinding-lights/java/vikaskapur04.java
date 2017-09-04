//package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//public class Lights {
public class Solution {

 public static void main(String[] args) throws FileNotFoundException {
//  Scanner sc = new Scanner(new File("lights"));
  Scanner sc = new Scanner(System.in);
  int N = sc.nextInt();
  int E = sc.nextInt();

  double[][] adjMatrix = new double[N + 1][N + 1];

  for (int i = 1; i <= N; i++) {
   for (int j = 1; j <= N; j++) {
    if (i != j) {
     adjMatrix[i][j] = Double.POSITIVE_INFINITY;
    }
   }
  }

  for (int e = 0; e < E; e++) {
   int x = sc.nextInt();
   int y = sc.nextInt();
   int wt = sc.nextInt();
   adjMatrix[x][y] = wt;
  }

  for (int k = 1; k <= N; k++) {
   for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
     if (adjMatrix[i][k] + adjMatrix[k][j] < adjMatrix[i][j]) {
      adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
     }
    }
   }
  }

  int Q = sc.nextInt();
  for (int q = 0; q < Q; q++) {
   int x = sc.nextInt();
   int y = sc.nextInt();
   double val = adjMatrix[x][y];
   if (val == Double.POSITIVE_INFINITY) {
    System.out.println("-1");
   } else {
    System.out.println((int)adjMatrix[x][y]);
   }
  }

 }

}