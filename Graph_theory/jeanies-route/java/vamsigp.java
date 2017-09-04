import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static int cities;
 private static int letters;

 private static int[] dest;
 private static int[][] graph;

 public static void main(String[] args) {

  Scanner sc = new Scanner(System.in);

  cities = sc.nextInt();
  letters = sc.nextInt();

  dest = new int[letters];

  for (int i = 0; i < letters; i++) {
   dest[i] = sc.nextInt() - 1;
  }

  graph = new int[cities][cities];
  for (int i = 0; i < cities; i++) {
   Arrays.fill(graph[i], Integer.MAX_VALUE);
  }

  for (int i = 0; i < cities - 1; i++) {
   int src = sc.nextInt() - 1;
   int dest = sc.nextInt() - 1;

   int cost = sc.nextInt();

   graph[src][dest] = graph[dest][src] = cost;
  }

  floydWarshall();

  Arrays.sort(dest);

  boolean flag = true;

  int val = calculateCost(dest);

  while (flag) {
   dest = findNextPermuation(dest);
   if (dest != null) {
    int routeCost = calculateCost(dest);

    val = val < routeCost ? val : routeCost;
   } else {
    flag = false;
   }
  }

  System.out.println(val);
  sc.close();
 }

 private static int calculateCost(int[] route) {

  int cost = 0;
  for (int i = 0; i < route.length - 1; i++) {
   cost += graph[route[i]][route[i + 1]];
  }
  return cost;
 }

 private static int[] findNextPermuation(int[] a) {

  int length = a.length;

  int k = -1;
  for (int i = length - 1; i > 0; i--) {
   if (a[i] > a[i - 1]) {
    k = i;
    break;
   }
  }

  if (k <= 0)
   return null;

  int l = length - 1;
  for (int j = k; j < a.length; j++) {
   if (a[j] > a[k - 1]) {
    l = j;
   }
  }

  int temp = a[k - 1];
  a[k - 1] = a[l];
  a[l] = temp;

  int j = length - 1;
  while (k < j) {
   temp = a[k];
   a[k] = a[j];
   a[j] = temp;
   k++;
   j--;
  }

  return a;
 }

 private static void floydWarshall() {

  int i, j, k;

  for (k = 0; k < cities; k++) {

   for (i = 0; i < cities; i++) {

    for (j = 0; j < cities; j++) {

     if ((graph[i][k] == Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE)
       || i == j) {
      continue;
     }
     int dist = graph[i][k] + graph[k][j];

     if (dist < graph[i][j]) {
      graph[i][j] = dist;
     }
    }
   }
  }

 }
}