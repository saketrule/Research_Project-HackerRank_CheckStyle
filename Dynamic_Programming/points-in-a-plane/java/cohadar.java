import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class PointsInAPlane {

 static final int PRIME = (int)1e9 + 7;
 static final int INF = Integer.MAX_VALUE / 2;

 static class Point {
  final int x;
  final int y;
  Point(int x, int y) {
   this.x = x;
   this.y = y;
  }
  public String toString() {
   return String.format("(x=%d, y=%d)", x, y);
  } 
  public static boolean colinear(Point a, Point b, Point c) {
   return (a.y - c.y) * (a.x - b.x) == (a.y - b.y) * (a.x - c.x);
  }
 }

 final int n;
 final int m;
 final Point[] P;
 final int[] D; // min removals
 final int[] C; // count f min removals
 final boolean[] L; // are points colinear
 
 PointsInAPlane(Point[] P) {
  this.n = P.length;
  this.m = 1 << n;
  this.P = P;
  this.D = new int[m];
  this.C = new int[m];
  this.L = new boolean[m];
  Arrays.fill(D, INF);
  for (int i = 0; i < m; i++) {
   L[i] = areColinear(i);
  }
 }

 boolean areColinear(int i) {
  int bc = Integer.bitCount(i);
  if (bc <= 2) {
   L[i] = true;
  } else {
   Deque<Integer> Q = new ArrayDeque<>();
   for (int j = 0; j < n; j++) {
    if ((i & (1 << j)) != 0) {
     Q.add(j);
    }
   }
   int a = Q.remove();
   int b = Q.remove();
   for (int c : Q) {
    if (!Point.colinear(P[a], P[b], P[c])) {
     return false;
    }
   }
  }   
  return true;
 }

 boolean subset(int i, int j) {
  return (i | j) == i;
 }

 void solve(int i, int j) {
  if (D[i] > 1 + D[j]) {
   D[i] = 1 + D[j];
   C[i] = 1;
  } else if (D[i] == 1 + D[j]) {
   C[i]++;
  }  
 }

 String solve() {
  D[0] = 0;
  C[0] = 1;  
  for (int i = 1; i < m; i++) {
   if (L[i]) {
    D[i] = 1;
    C[i] = 1;
   } else {
    for (int j = 1; j < i; j++) {
     if (L[j] && subset(i, j)) {
      solve(i, i & ~j);
     }
    }
   }
  }
  return String.format("%d %d", D[m - 1], C[m - 1]);
 }

 static PointsInAPlane load(Scanner scanner) {
  int n = scanner.nextInt();
  assert 1 <= n && n <= 16 : "out of range, n: " + n;
  Point[] P = scanPointArray(scanner, n);
  return new PointsInAPlane(P);
 } 

 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  int t = scanner.nextInt();
  assert 1 <= t && t <= 50 : "out of range, t: " + t;
  while (t-->0) {
   PointsInAPlane o = PointsInAPlane.load(scanner);
   System.out.println(o.solve());
  }
 }

 static Point[] scanPointArray(Scanner scanner, int n) {
  Point[] A = new Point[n];
  for (int i = 0; i < n; i++) {
   int x = scanner.nextInt();
   int y = scanner.nextInt();
   assert 0 <= x && x <= 100 : "out of range, x: " + x;
   assert 0 <= y && y <= 100 : "out of range, y: " + y;
   A[i] = new Point(x, y);
  }
  return A;
 }

 static boolean DEBUG = true;
 
 static void debug(Object...os) {
  if (!DEBUG) { return; }
  System.err.printf("%.65536s\n", Arrays.deepToString(os));
 }

}