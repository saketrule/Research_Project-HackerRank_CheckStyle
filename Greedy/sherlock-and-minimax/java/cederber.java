import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
  
  private static class Bounds {
    public int p;
    public int q;
  }
  
  private static class Point {
    public int val;
    public int dist;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int numElements = readNumElements(reader.readLine());
    int[] elements = readElements(numElements, reader.readLine());
    Bounds bounds = readBounds(reader.readLine());
    int p = bounds.p;
    int q = bounds.q;
    if (p == q) {
      System.out.println(p);
      System.exit(0);
    }
    if (numElements == 1) {
      System.out.println(findBoundsForOneElement(elements[0], p, q));
      System.exit(0);
    }
    Arrays.sort(elements);
    Point maxDistFromLow = new Point();
    if (p <= elements[0]) {
      maxDistFromLow.val = p;
      maxDistFromLow.dist = Math.abs(elements[0] - p);
    } else {
      maxDistFromLow.val = q;
      maxDistFromLow.dist = 0;
    }
    Point maxDistFromHigh = new Point();
    if (q >= elements[elements.length - 1]) {
      maxDistFromHigh.val = q;
      maxDistFromHigh.dist = Math.abs(q - elements[elements.length - 1]);
    } else {
      maxDistFromHigh.val = q;
      maxDistFromHigh.dist = 0;
    }
    Point[] maxDistFromGaps = computeMaxDistFromGaps(elements, p, q);
    Point maxPoint = maxPoint(maxDistFromLow, maxDistFromHigh);
    for (Point point : maxDistFromGaps) {
      maxPoint = maxPoint(maxPoint, point);
    }
    System.out.println(maxPoint.val);
    reader.close();
  }
  
  private static Point maxPoint(Point a, Point b) {
    if (a.dist > b.dist) {
      return a;
    } else if (b.dist > a.dist) {
      return b;
    } else if (a.val <= b.val) {
      return a;
    } else {
      return b;
    }
  }

  private static Point[] computeMaxDistFromGaps(int[] elements, int p, int q) {
    Point[] points = new Point[elements.length - 1];
    for (int i = 0; i < elements.length - 1; i++) {
      points[i] = new Point();
      // find the farthest-away point between elements[i] and element[i+1]
      if (elements[i] >= q || elements[i+1] <= p) {
        points[i].val = q;
        points[i].dist = 0;
        continue;
      }
      int midpoint = elements[i] + (elements[i+1] - elements[i]) / 2;
      if (midpoint >= p && midpoint <= q) {
        points[i].val = midpoint;
        points[i].dist = midpoint - elements[i];
      } else if (midpoint < p) {
        points[i].val = p;
        points[i].dist = elements[i+1] - p;
      } else {
        // midpoint > q
        points[i].val = q;
        points[i].dist = q - elements[i];
      }
    }
    return points;
  }
  
  private static void printArray(int[] elements) {
    for (int element : elements) {
      System.out.print(" " + element);
    }    
  }
  
  private static int findBoundsForOneElement(int element, int min, int max) {
    if (element >= min && element <= max) {
      return element;
    }
    int distToMin = Math.abs(element - min);
    int distToMax = Math.abs(element - max);
    if (distToMin <= distToMax) {
      return min;
    } else {
      return max;
    }
  }
  
  private static int readNumElements(String line) {
    Scanner scanner = new Scanner(new StringReader(line));
    int numElements = scanner.nextInt();
    scanner.close();
    return numElements;
  }
  
  private static int[] readElements(int numElements, String line) {
    int[] elements = new int[numElements];
    Scanner scanner = new Scanner(new StringReader(line));
    for (int i = 0; i < numElements; i++) {
      elements[i] = scanner.nextInt();
    }
    scanner.close();
    return elements;
  }
  
  private static Bounds readBounds(String line) {
    Bounds bounds = new Bounds();
    Scanner scanner = new Scanner(new StringReader(line));
    bounds.p = scanner.nextInt();
    bounds.q = scanner.nextInt();
    scanner.close();
    return bounds;
  }
}