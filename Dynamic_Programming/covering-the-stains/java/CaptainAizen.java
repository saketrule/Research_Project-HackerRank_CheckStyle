import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  Coordinate[] coordinate = new Coordinate[sc.nextInt()];
  int K = sc.nextInt();
  int minX = 1000, maxX = 0, minY = 1000, maxY = 0;
  for (int i = 0; i < coordinate.length; i++) {
   coordinate[i] = new Coordinate(sc.nextInt(), sc.nextInt());
   minX = Math.min(minX, coordinate[i].x);
   maxX = Math.max(maxX, coordinate[i].x);
   minY = Math.min(minY, coordinate[i].y);
   maxY = Math.max(maxY, coordinate[i].y);
  }
  sc.close();
  int[] count = new int[15];
  for (int i = 0; i < coordinate.length; i++) {
   if (coordinate[i].x == minX) {
    count[0]++;
   }
   if (coordinate[i].x == maxX) {
    count[1]++;
   }
   if (coordinate[i].y == minY) {
    count[2]++;
   }
   if (coordinate[i].y == maxY) {
    count[3]++;
   }
   if (coordinate[i].x == minX || coordinate[i].x == maxX) {
    count[4]++;
   }
   if (coordinate[i].x == minX || coordinate[i].y == minY) {
    count[5]++;
   }
   if (coordinate[i].x == minX || coordinate[i].y == maxY) {
    count[6]++;
   }
   if (coordinate[i].x == maxX || coordinate[i].y == minY) {
    count[7]++;
   }
   if (coordinate[i].x == maxX || coordinate[i].y == maxY) {
    count[8]++;
   }
   if (coordinate[i].y == minY || coordinate[i].y == maxY) {
    count[9]++;
   }
   if (coordinate[i].x == minX || coordinate[i].x == maxX || coordinate[i].y == minY) {
    count[10]++;
   }
   if (coordinate[i].x == minX || coordinate[i].x == maxX || coordinate[i].y == maxY) {
    count[11]++;
   }
   if (coordinate[i].x == minX || coordinate[i].y == minY || coordinate[i].y == maxY) {
    count[12]++;
   }
   if (coordinate[i].x == maxX || coordinate[i].y == minY || coordinate[i].y == maxY) {
    count[13]++;
   }
   if (coordinate[i].x == minX || coordinate[i].x == maxX || coordinate[i].y == minY
     || coordinate[i].y == maxY) {
    count[14]++;
   }
  }
  long result = 0;
  for (int i = 0; i < 4; i++) {
   if (K >= count[i]) {
    result = (result + choose(coordinate.length - count[i], K - count[i])) % 1000000007;
   }
  }
  for (int i = 4; i < 10; i++) {
   if (K >= count[i]) {
    result = (result - choose(coordinate.length - count[i], K - count[i]) + 1000000007) % 1000000007;
   }
  }
  for (int i = 10; i < 14; i++) {
   if (K >= count[i]) {
    result = (result + choose(coordinate.length - count[i], K - count[i])) % 1000000007;
   }
  }
  for (int i = 14; i < 15; i++) {
   if (K >= count[i]) {
    result = (result - choose(coordinate.length - count[i], K - count[i]) + 1000000007) % 1000000007;
   }
  }
  System.out.println(result);
 }

 private static long choose(long n, long k) {
  if (n < k) {
   return 0;
  } else {
   long result = 1;
   for (long i = Math.max(k, n - k) + 1; i <= n; i++) {
    result = (result * i) % 1000000007;
   }
   for (long i = 2; i <= Math.min(k, n - k); i++) {
    result = (result * BigInteger.valueOf(i).modInverse(BigInteger.valueOf(1000000007)).intValue()) % 1000000007;
   }
   return result;
  }
 }

 private static class Coordinate {
  private int x, y;

  private Coordinate(int x, int y) {
   this.x = x;
   this.y = y;
  }
 }
}