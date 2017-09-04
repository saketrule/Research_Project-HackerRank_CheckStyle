import java.io.*;
import java.util.*;

public class Solution {

 private static class Connections extends HashSet<Integer> {
 };

 private static int calculateIndependent(int x, int parent, int parentTaken,
   int[] independent, Connections[] connections, int[][] cache) {
  if (cache[x][parentTaken] != 0) {
   return cache[x][parentTaken];
  }

  int independent1 = 0;
  if (parentTaken == 0) {
   independent1 = independent[x];
   for (int c : connections[x]) {
    if (c != parent) {
     independent1 += calculateIndependent(c, x, 1, independent,
       connections, cache);
    }
   }
  }

  int independent2 = 0;
  for (int c : connections[x]) {
   if (c != parent) {
    independent2 += calculateIndependent(c, x, 0, independent,
      connections, cache);
   }
  }

  cache[x][parentTaken] = Math.max(independent1, independent2);

  return cache[x][parentTaken];
 }

 private static void clear(int x, int parent, int[] independent,
   Connections[] connections) {
  for (int c : connections[x]) {
   if (c != parent) {
    clear(c, x, independent, connections);
   }
  }
  independent[x] = 0;
  connections[x] = null;
 }

 public static void main(String[] args) throws Exception {
  Locale.setDefault(Locale.US);

  Scanner in = new Scanner(System.in);
  PrintStream out = System.out;

  int q = in.nextInt();
  int[] independent = new int[q];
  int[][] cache = new int[q][2];
  Connections[] connections = new Connections[q];

  int n = 0;

  for (int o = 0; o < q; o++) {
   String op = in.next();

   if ("A".equals(op)) {
    int size = in.nextInt();
    independent[n] = size;
    connections[n] = new Connections();
    n++;
   } else if ("B".equals(op)) {
    int x = in.nextInt() - 1;
    int y = in.nextInt() - 1;

    connections[x].add(y);
    connections[y].add(x);
   } else if ("C".equals(op)) {
    int x = in.nextInt() - 1;
    independent[n] = calculateIndependent(x, -1, 0, independent,
      connections, cache);
    connections[n] = new Connections();
    n++;

    clear(x, -1, independent, connections);
   }
  }
  int result = 0;
  for (int x=n-1; x>=0; x--) {
   if (independent[x] > 0) {
    result += calculateIndependent(x, -1, 0, independent, connections, cache);
    clear(x, -1, independent, connections);
   }
  }

  out.println(result);

 }
}